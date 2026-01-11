package challenges;

import data.SampleDataGenerator;
import model.*;

import java.io.StringReader;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ═══════════════════════════════════════════════════════════════════════════════
 * CHALLENGE 03: Local Variable Type Inference (var) - Java 10
 * ═══════════════════════════════════════════════════════════════════════════════
 *
 * SCENARIO:
 * You are refactoring the EduMaster codebase to reduce verbosity and improve
 * code readability. Java 10's 'var' keyword allows the compiler to infer local
 * variable types from their initializers, eliminating redundant type declarations.
 *
 * FOCUS:
 * - var for local variables (not fields, parameters, or return types)
 * - Type inference from initializer expressions
 * - Use cases: primitives, strings, collections, generics
 * - var in loops (for-each, traditional for)
 * - var with try-with-resources
 * - Non-denotable types (anonymous classes, intersection types)
 * - Best practices: When to use var vs. explicit types
 *
 * RULES:
 * - var is ONLY for local variables (inside methods)
 * - Initializer is REQUIRED (cannot be null)
 * - Goal: Improve readability, not obscure types
 * - Avoid var when type isn't obvious from context
 *
 * ═══════════════════════════════════════════════════════════════════════════════
 */
public class VarChallenge {

    private final List<Student> students;
    private final List<Course> courses;
    private final List<Enrollment> enrollments;
    private final List<Instructor> instructors;

    public VarChallenge() {
        this.students = SampleDataGenerator.generateStudents(50);
        this.instructors = SampleDataGenerator.generateInstructors(15);
        this.courses = SampleDataGenerator.generateCourses(40, instructors);
        this.enrollments = SampleDataGenerator.generateEnrollments(students, courses, 200);
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 1: Basic var Usage - Primitives and Simple Types
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 1.1: Simple Variable Declarations
     *
     * Replace explicit types with var for simple local variables.
     * The type is obvious from the initializer.
     *
     * EXAMPLE:
     * Before: String appName = "EduMaster";
     * After:  var appName = "EduMaster";
     *
     * @return Map containing application configuration
     */
    public Map<String, Object> getApplicationConfig() {
        return Map.of(
            "appName", "EduMaster",
            "version", "1.0.0",
            "maxStudents", 1000
        );
    }

    /**
     * TASK 1.2: Numeric Operations
     *
     * Use var for numeric computations where type is clear from context.
     *
     * EXAMPLE:
     * var totalRevenue = calculateRevenue();
     * var avgPrice = totalRevenue / courseCount;
     *
     * @return Total revenue as BigDecimal
     */
    public BigDecimal calculateAvgPrice() {
        var totalRevenue = courses.stream()
            .map(Course::getPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        var count = courses.size();
        var avgPrice = count > 0 ? totalRevenue.divide(BigDecimal.valueOf(count)) : BigDecimal.ZERO;
        return avgPrice;
    }

    /**
     * TASK 1.3: String Manipulation
     *
     * Use var for string operations and transformations.
     *
     * EXAMPLE INPUT: Student with firstName="John", lastName="Doe"
     * EXAMPLE OUTPUT: "JOHN DOE (john.doe@edu.com)"
     *
     * @param student Student to format
     * @return Formatted string
     */
    public String formatStudentInfo(Student student) {
        var fullName = student.getFirstName() + " " + student.getLastName();
        var email = student.getEmail();
        var result = fullName.toUpperCase() + " (" + email + ")";
        return result;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 2: var with Collections and Generics
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 2.1: Simplify Complex Generic Types
     *
     * Replace verbose generic declarations with var.
     * This is where var shines - eliminating type repetition.
     *
     * EXAMPLE:
     * Before: Map<Category, List<Course>> coursesByCategory = new HashMap<>();
     * After:  var coursesByCategory = new HashMap<Category, List<Course>>();
     *
     * @return Courses grouped by category
     */
    public Map<Category, List<Course>> groupCoursesByCategory() {
        var coursesByCategory = courses.stream()
            .collect(Collectors.groupingBy(Course::getCategory));
        return coursesByCategory;
    }

    /**
     * TASK 2.2: Nested Collections
     *
     * Use var with deeply nested generic types.
     *
     * EXAMPLE OUTPUT:
     * Map<StudentLevel, Map<Category, List<Student>>>
     * - Maps student level to categories to list of interested students
     *
     * @return Nested map structure
     */
    public Map<StudentLevel, Map<Category, List<Student>>> createStudentInterestMatrix() {
        var studentsByLevel = students.stream()
            .collect(Collectors.groupingBy(Student::getLevel));

        var result = new HashMap<StudentLevel, Map<Category, List<Student>>>();

        for (var entry : studentsByLevel.entrySet()) {
            var level = entry.getKey();
            var levelStudents = entry.getValue();
            var categoryMap = new HashMap<Category, List<Student>>();

            for (var category : Category.values()) {
                // Group students by category based on their enrolled courses
                var interestedStudents = levelStudents.stream()
                    .filter(s -> s.getEnrollments().stream()
                        .anyMatch(e -> e.getCourse() != null && e.getCourse().getCategory() == category))
                    .collect(Collectors.toList());
                categoryMap.put(category, interestedStudents);
            }
            result.put(level, categoryMap);
        }

        return result;
    }

    /**
     * TASK 2.3: List Operations
     *
     * Use var for list transformations and filtering.
     *
     * EXAMPLE INPUT: courses list
     * EXAMPLE OUTPUT: List of published course titles sorted alphabetically
     *
     * @return Sorted list of published course titles
     */
    public List<String> getPublishedCourseTitles() {
        var publishedCourses = courses.stream()
            .filter(Course::isPublished)
            .collect(Collectors.toList());

        var titles = publishedCourses.stream()
            .map(Course::getTitle)
            .sorted()
            .collect(Collectors.toList());

        return titles;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 3: var in Loops
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 3.1: For-Each Loop
     *
     * Use var in enhanced for loop to reduce verbosity.
     *
     * EXAMPLE:
     * Before: for (Student student : students)
     * After:  for (var student : students)
     *
     * Prints active students with their enrollment count.
     */
    public void printActiveStudents() {
        for (var student : students) {
            if (student.isActive()) {
                var enrollmentCount = enrollments.stream()
                    .filter(e -> e.getStudentId().equals(student.getId()))
                    .count();
                System.out.println(student.getFirstName() + " " + student.getLastName() +
                    " - Enrollments: " + enrollmentCount);
            }
        }
    }

    /**
     * TASK 3.2: Traditional For Loop
     *
     * Use var in traditional for loop with index.
     *
     * EXAMPLE OUTPUT:
     * 1. Course Title One
     * 2. Course Title Two
     * ...
     *
     * @param limit Maximum number of courses to list
     */
    public void listTopCourses(int limit) {
        for (var i = 0; i < Math.min(limit, courses.size()); i++) {
            var course = courses.get(i);
            var title = course.getTitle();
            System.out.println((i + 1) + ". " + title);
        }
    }

    /**
     * TASK 3.3: Iterator Pattern
     *
     * Use var with explicit iterator.
     *
     * @return List of course IDs
     */
    public List<String> getCourseIds() {
        var result = new ArrayList<String>();
        var iterator = courses.iterator();
        while (iterator.hasNext()) {
            var course = iterator.next();
            result.add(course.getId());
        }
        return result;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 4: var with Try-With-Resources and Exception Handling
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 4.1: Try-With-Resources
     *
     * Use var in try-with-resources statement for AutoCloseable resources.
     *
     * EXAMPLE:
     * try (var reader = new BufferedReader(new FileReader("data.txt"))) {
     *     // use reader
     * }
     *
     * @param content Content to process
     * @return Processed content
     */
    public String processContent(String content) {
        try (var reader = new StringReader(content)) {
            var result = new StringBuilder();
            var buffer = new char[1024];
            var charsRead = 0;
            while ((charsRead = reader.read(buffer)) != -1) {
                result.append(buffer, 0, charsRead);
            }
            return result.toString().toUpperCase();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * TASK 4.2: Multiple Resources
     *
     * Use var with multiple resources in try-with-resources.
     *
     * @param input1 First input
     * @param input2 Second input
     * @return Combined output
     */
    public String combineInputs(String input1, String input2) {
        try (var reader1 = new StringReader(input1);
             var reader2 = new StringReader(input2)) {
            var result = new StringBuilder();
            var buffer = new char[1024];
            var charsRead = 0;

            while ((charsRead = reader1.read(buffer)) != -1) {
                result.append(buffer, 0, charsRead);
            }

            result.append(" | ");

            while ((charsRead = reader2.read(buffer)) != -1) {
                result.append(buffer, 0, charsRead);
            }

            return result.toString();
        } catch (Exception e) {
            return "";
        }
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 5: Advanced var Usage
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 5.1: Non-Denotable Types (Anonymous Classes)
     *
     * Use var to work with anonymous class instances that have unique members.
     * Without var, you cannot access members defined in the anonymous class.
     *
     * EXAMPLE:
     * var counter = new Object() {
     *     int count = 0;
     *     void increment() { count++; }
     * };
     * counter.increment(); // Accessible with var
     *
     * @return Count value from anonymous object
     */
    public int demonstrateAnonymousClass() {
        var counter = new Object() {
            int count = 0;
            void increment() { count++; }
            void add(int value) { count += value; }
        };

        counter.increment();
        counter.increment();
        counter.add(5);

        return counter.count;
    }

    /**
     * TASK 5.2: Intersection Types
     *

     * Demonstrate var with intersection types (less common but powerful).
     *
     * @return Result from intersection type
     */
    public String demonstrateIntersectionType() {
        var processor = new Object() {
            String data = "Intersection Type Demo";
            int priority = 1;

            String process() {
                return data + " (Priority: " + priority + ")";
            }
        };

        return processor.process();
    }

    /**
     * TASK 5.3: Stream Operations
     *
     * Use var heavily in stream pipeline intermediate variables.
     *
     * EXAMPLE:
     * var filtered = students.stream().filter(...);
     * var mapped = filtered.map(...);
     * var result = mapped.collect(...);
     *
     * @return Statistics about student enrollments
     */
    public Map<String, Object> getEnrollmentStatistics() {
        var totalEnrollments = enrollments.size();

        var enrollmentsByStudent = enrollments.stream()
            .collect(Collectors.groupingBy(Enrollment::getStudentId, Collectors.counting()));

        var avgPerStudent = enrollmentsByStudent.values().stream()
            .mapToLong(Long::longValue)
            .average()
            .orElse(0.0);

        var maxEnrollments = enrollmentsByStudent.values().stream()
            .mapToLong(Long::longValue)
            .max()
            .orElse(0L);

        var result = new HashMap<String, Object>();
        result.put("totalEnrollments", totalEnrollments);
        result.put("avgPerStudent", avgPerStudent);
        result.put("maxEnrollmentsPerStudent", maxEnrollments);

        return result;
    }

    /**
     * TASK 5.4: Complex Initialization
     *
     * Use var with complex object initialization.
     *
     * @return Configured course object
     */
    public Course createSampleCourse() {
        var instructor = instructors.isEmpty() ? null : instructors.get(0);
        var courseId = "COURSE-" + System.currentTimeMillis();
        var title = "Advanced Java Programming";
        var description = "Master modern Java features";
        var price = new BigDecimal("299.99");
        var category = Category.PROGRAMMING;
        var difficulty = DifficultyLevel.ADVANCED;

        var course = new Course(courseId, title, description, category, difficulty, price, instructor);
        course.setPublished(true);

        var lessons = new ArrayList<Lesson>();
        var lesson1 = new Lesson("L1", "Introduction to Modern Java", "Overview of Java 10+",
            LessonType.VIDEO, 1, java.time.Duration.ofMinutes(30));
        var lesson2 = new Lesson("L2", "var Keyword Deep Dive", "Type inference mastery",
            LessonType.VIDEO, 2, java.time.Duration.ofMinutes(45));
        lessons.add(lesson1);
        lessons.add(lesson2);

        course.setLessons(lessons);

        return course;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 6: var Best Practices and Anti-Patterns
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 6.1: When var Improves Readability
     *
     * Demonstrate cases where var makes code more readable.
     */
    public void demonstrateGoodVarUsage() {
        // GOOD: Type is obvious from initializer
        var appName = "EduMaster";
        var maxStudents = 1000;
        var isActive = true;

        // GOOD: Reduces verbosity with complex generic types
        var studentsByCourse = new HashMap<Course, List<Student>>();
        var coursesByInstructor = courses.stream()
            .collect(Collectors.groupingBy(Course::getInstructor));

        // GOOD: Clear from method name
        var activeStudents = getActiveStudents();
        var totalCourses = calculateTotalCourses();

        System.out.println("Good var usage demonstrated");
    }

    /**
     * TASK 6.2: When to Avoid var
     *
     * Show cases where explicit types are better for readability.
     */
    public void demonstratePoorVarUsage() {
        // BAD: Type not clear from context
        // var data = getData(); // What type is data?

        // BAD: Non-descriptive variable name
        // var x = computeValue();
        // var temp = process();

        // BAD: Numeric literals - prefer explicit type
        // var result = 10; // Is this int, long, or something else?

        // GOOD ALTERNATIVES:
        Student student = students.get(0); // Clear type when not obvious
        int count = 10; // Explicit for primitives when context matters
        List<String> names = List.of("Alice", "Bob"); // Clear when type isn't obvious

        System.out.println("Poor var usage examples shown");
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // HELPER METHODS
    // ═══════════════════════════════════════════════════════════════════════════

    private List<Student> getActiveStudents() {
        return students.stream()
            .filter(Student::isActive)
            .collect(Collectors.toList());
    }

    private int calculateTotalCourses() {
        return courses.size();
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // TEST HELPERS
    // ═══════════════════════════════════════════════════════════════════════════

    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("    var Challenge - Java 10 Local Variable Type Inference");
        System.out.println("═══════════════════════════════════════════════════════════════\n");

        var challenge = new VarChallenge();

        // Test PART 1: Basic var usage
        System.out.println("--- PART 1: Basic var Usage ---");
        var config = challenge.getApplicationConfig();
        System.out.println("App Config: " + config);
        System.out.println();

        // Test PART 2: Collections and generics
        System.out.println("--- PART 2: Collections with var ---");
        var coursesByCategory = challenge.groupCoursesByCategory();
        System.out.println("Categories found: " + coursesByCategory.keySet());
        System.out.println();

        // Test PART 3: Loops
        System.out.println("--- PART 3: var in Loops ---");
        challenge.printActiveStudents();
        System.out.println();

        // Test PART 4: Try-with-resources
        System.out.println("--- PART 4: var with Resources ---");
        var processed = challenge.processContent("Hello, EduMaster!");
        System.out.println("Processed: " + processed);
        System.out.println();

        // Test PART 5: Advanced usage
        System.out.println("--- PART 5: Advanced var ---");
        var count = challenge.demonstrateAnonymousClass();
        System.out.println("Anonymous class counter: " + count);
        System.out.println();

        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("Complete the remaining TODOs to master var!");
        System.out.println("═══════════════════════════════════════════════════════════════");
    }
}
