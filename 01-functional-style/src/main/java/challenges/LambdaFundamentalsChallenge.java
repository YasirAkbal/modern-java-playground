package challenges;

import data.SampleDataGenerator;
import model.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.*;

import challenges.LambdaFundamentalsChallenge.TriFunction;

import java.text.MessageFormat;
import java.time.LocalDateTime;


/**
 * 
 * CHALLENGE 01: Lambda Fundamentals & Functional Interfaces
 * 
 * 
 * SCENARIO:
 * You are building a flexible student and course filtering system for the EduMaster 
 * platform. The system must be able to filter, transform, and process data based 
 * on criteria defined at runtime.
 * 
 * CONSTRUCTS TO USE:
 * - Lambda expressions (various syntax forms)
 * - Predicate<T> - for condition checking
 * - Function<T, R> - for transformation
 * - Consumer<T> - for side-effect operations
 * - Supplier<T> - for lazy initialization
 * - BiFunction<T, U, R> - for two-argument transformations
 * - UnaryOperator<T> and BinaryOperator<T>
 * 
 * 
 */
public class LambdaFundamentalsChallenge {
    
    private final List<Student> students;
    private final List<Course> courses;
    private final List<Instructor> instructors;
    private final List<Enrollment> enrollments;
    
    public LambdaFundamentalsChallenge() {
        this.students = SampleDataGenerator.generateStudents(100);
        this.instructors = SampleDataGenerator.generateInstructors(20);
        this.courses = SampleDataGenerator.generateCourses(50, instructors);
        this.enrollments = SampleDataGenerator.generateEnrollments(students, courses, 200);
    }
    
    // 
    // PART 1: Conditional Filtering with Predicate<T>
    // =
    
    /**
     * TASK 1.1: Dynamic Student Filter
     * 
     * Filter students using the provided Predicate.
     * This method should be able to filter by any criteria provided externally.
     * 
     * EXAMPLE USAGE:
     * filterStudents(s -> s.getLevel() == StudentLevel.ADVANCED)
     * filterStudents(s -> s.isActive() && s.getAverageScore() > 80)
     * 
     * @param predicate Filtering condition
     * @return List of students matching the condition
     */
    public List<Student> filterStudents(Predicate<Student> predicate) {
        // Filter students using the predicate (without Streams for now, use loop or removeIf on copy)
        // Or simpler: Iterate and add to new list if predicate.test() is true.

        List<Student> filterStudents = new ArrayList<>();
        for(Student student: students) {
            if(predicate.test(student)) {
                filterStudents.add(student);
            }
        }
        
        return filterStudents;
    }
    
    /**
     * TASK 1.2: Composite Filter Builder
     * 
     * Write a method that combines multiple Predicates using AND logic.
     * Use Predicate.and().
     * 
     * @param predicates List of conditions to combine
     * @return A single Predicate combining all conditions
     */
    public Predicate<Student> combineFilters(List<Predicate<Student>> predicates) {
        // Combine all predicates using Predicate.and()
        Predicate<Student> combinedPredicate = (x) -> true;

        for(Predicate<Student> predicate: predicates) {
            combinedPredicate = combinedPredicate.and(predicate);
        }

        return combinedPredicate;
    }
    
    /**
     * TASK 1.3: Course Eligibility Checker
     * 
     * Return a Predicate that checks if a course meets ALL criteria:
     * - Must be published (isPublished)
     * - Rating must be > minimumRating
     * - Enrollment count must be > minimumEnrollments
     * 
     * @param minimumRating Threshold for rating
     * @param minimumEnrollments Threshold for enrollments
     * @return Predicate for eligibility check
     */
    public Predicate<Course> createCourseEligibilityChecker(double minimumRating, int minimumEnrollments) {
        Predicate<Course> isPublished = course -> course.isPublished();
        Predicate<Course> isRatingGreaterThenThreshold = course -> course.getRating() > minimumRating;
        Predicate<Course> isEnrollmentGreaterThenThreshold = course -> course.getEnrollmentCount() > minimumEnrollments;

        return isPublished.and(isRatingGreaterThenThreshold).and(isEnrollmentGreaterThenThreshold);
    }

    /**
     * TASK 1.4: Negate Condition
     * 
     * Return a predicate that is the NEGATION of the input predicate.
     * If input checks for "Active", output checks for "Inactive".
     * Use Predicate.negate().
     */
    public Predicate<Student> complement(Predicate<Student> p) {
        return p.negate();
    }
    
    // 
    // PART 2: Data Transformation with Function<T, R>
    // 
    
    /**
     * TASK 2.1: Student Summary Generator
     * 
     * Create a Function that generates a summary string for a student:
     * "[LEVEL] Name Surname (email) - Avg: XX.XX"
     * 
     * If average is null, write "Avg: N/A".
     * 
     * @return Function generating the summary
     */
    public Function<Student, String> createStudentSummaryGenerator() {
        Function<Student, String> function = (s) -> MessageFormat.format("[{0}] {1} ({2}) - Avg: {3}", 
                s.getLevel(), s.getFullName(), s.getEmail(), s.getAverageScore());

        return function;
    }
    
    /**
     * TASK 2.2: Function Composition (andThen)
     * 
     * Chain two Functions:
     * 1. First: Student -> Course (find the last course enrolled)
     * 2. Second: Course -> String (extract title)
     * 
     * Use Function.andThen().
     * 
     * @param enrollmentFinder Function to find course from student
     * @param titleExtractor Function to extract title from course
     * @return Composed function
     */
    public Function<Student, String> composeStudentToCourseTitle(
            Function<Student, Course> enrollmentFinder,
            Function<Course, String> titleExtractor) {
        
        return enrollmentFinder.andThen(titleExtractor);
    }
    
    /**
     * TASK 2.3: Discount Calculator with BiFunction
     * 
     * Write a BiFunction to calculate discounted price from Course and Discount Rate.
     * 
     * @return BiFunction taking (Course, Double) and returning BigDecimal
     */
    public BiFunction<Course, Double, BigDecimal> createDiscountCalculator() {
        BiFunction<Course, Double, BigDecimal> function = (course, discountRate) -> {
            BigDecimal coursePrice = course.getPrice();
            BigDecimal newRate = BigDecimal.ONE.subtract(BigDecimal.valueOf(discountRate));

            return coursePrice.multiply(newRate);
        };

        return function;
    }

    /**
     * TASK 2.4: Score Adjuster (UnaryOperator)
     * 
     * Create a UnaryOperator that takes a score (Double).
     * Logic:
     * 1. If score > 100, return 100.
     * 2. If score < 0, return 0.
     * 3. Otherwise return score.
     */
    public UnaryOperator<Double> createScoreAdjuster() {
        return (score) -> {
            if(score > 100)
                return 100.0;
            else if(score < 0)
                return 0.0;

            return score;
        };
    }
    
    // 
    // PART 3: Side Effects with Consumer<T>
    // 
    
    /**
     * TASK 3.1: Student Notification System
     * 
     * Create a Consumer used to send notifications to students.
     * Print to console using the template: "{name}, Notification: {message}"
     * 
     * @param messageTemplate Template string
     * @return Consumer for notification
     */
    public Consumer<Student> createNotificationSender(String messageTemplate) {
        Consumer<Student> consumer = (student) -> 
            System.out.println(MessageFormat.format("{0}, Notification: {1}", student.getFullName(), messageTemplate));
        return consumer;
    }

    /**
     * TASK 3.3: Chained Consumer
     * 
     * Combine two consumers: one that logs "Processing..." and another that performs the action.
     * Use Consumer.andThen().
     */
    public Consumer<Student> chainConsumers(Consumer<Student> logger, Consumer<Student> action) {
        return logger.andThen(action); 
    }
    
    /**
     * TASK 3.2: Course Updater
     * 
     * Return a Consumer that updates a course:
     * - Set updatedAt to now
     * - Increment enrollmentCount by 1
     * 
     * @return Consumer for update
     */
    public Consumer<Course> createCourseUpdater() {
        Consumer<Course> consumer = (course) -> {
            course.setUpdatedAt(LocalDateTime.now());
            course.setEnrollmentCount(course.getEnrollmentCount()+1);
        };
        return consumer;
    }
    
    // 
    // PART 4: Lazy Initialization with Supplier<T>
    // 
    
    /**
     * TASK 4.1: Lazy Cache Mechanism
     * 
     * Create a Supplier that caches the result of an expensive calculation 
     * (Memoization pattern).
     * The expensiveCalculation should run only on the first get() call.
     * 
     * @param expensiveCalculation The source supplier
     * @return Cached Supplier
     */
    public <T> Supplier<T> createLazyCache(Supplier<T> expensiveCalculation) {        
        AtomicReference<T> cache = new AtomicReference<>();
        
        return () -> {
            if (cache.get() == null) {
                cache.compareAndSet(null, expensiveCalculation.get());
            }
            return cache.get();
        };
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 5: Custom Functional Interfaces
    // ═══════════════════════════════════════════════════════════════════════════
    
    @FunctionalInterface
    public interface TriFunction<T, U, V, R> {
        R apply(T t, U u, V v);
    }

    /**
     * TASK 5.1: Three Argument Function
     * 
     * Implement strict Functional Interface definition above.
     * Use it to create a function that takes (Student, Course, Double score) 
     * and returns a new Enrollment object.
     * 
     * Hint: You might need to check Enrollment constructor in model.
     */
    public TriFunction<Student, Course, Double, Enrollment> createEnrollmentFactory() {
         // TODO: (s, c, score) -> new Enrollment(...)
         return (student, course, score) -> {
            Enrollment enrollment = new Enrollment("1", student.getId(), course);
            enrollment.setScore(score);

            return enrollment;
         };
    }
    
    // 
    // TEST HELPERS
    // 
    
    public static void main(String[] args) {
        System.out.println("=== Lambda Fundamentals Challenge ===");
        // Add tests here
    }
}
