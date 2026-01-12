package challenges;

import data.SampleDataGenerator;
import model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.*;

/**
 * ═══════════════════════════════════════════════════════════════════════════════
 * CHALLENGE 01: Records - Immutable Data Carriers
 * ═══════════════════════════════════════════════════════════════════════════════
 *
 * SCENARIO:
 * You are building DTOs (Data Transfer Objects) for the EduMaster API.
 * Traditional POJOs require verbose getters, equals(), hashCode(), and toString().
 * Java Records provide automatic generation of these methods for immutable data carriers.
 *
 * FOCUS:
 * - Record definition and components
 * - Compact constructors for validation
 * - Wither methods for immutability
 * - Static factory methods
 * - Converting domain objects to DTOs
 *
 * ═══════════════════════════════════════════════════════════════════════════════
 */
public class RecordsChallenge {

    private final List<Student> students;
    private final List<Course> courses;
    private final List<Instructor> instructors;
    private final List<Enrollment> enrollments;

    public RecordsChallenge() {
        this.instructors = SampleDataGenerator.generateInstructors(10);
        this.courses = SampleDataGenerator.generateCourses(30, instructors);
        this.students = SampleDataGenerator.generateStudents(50);
        this.enrollments = SampleDataGenerator.generateEnrollments(students, courses, 100);
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 1: Basic Record Definition
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * A simple DTO to summarize student information.
     *
     * EXAMPLE:
     * <pre>
     * StudentSummary summary = new StudentSummary("STU-1001", "John Doe", 3.85);
     * String name = summary.fullName(); // Automatic accessor
     * </pre>
     */
    public record StudentSummary(String id, String fullName, double gpa) {}

    /**
     * TASK 1.1: Convert to Student Summary
     *
     * Create StudentSummary DTOs from domain Student objects.
     * Use the student's average score as GPA (or 0.0 if null).
     *
     * @return List of student summaries
     *
     * EXAMPLE:
     * <pre>
     * List<StudentSummary> summaries = getStudentSummaries();
     * summaries.forEach(s -> System.out.println(s.fullName() + ": " + s.gpa()));
     * </pre>
     */
    public List<StudentSummary> getStudentSummaries() {
        return students.stream()
                .map(s -> new StudentSummary(s.getId(), s.getFullName(), s.getAverageScore()))
                .toList();
    }

    /**
     * TASK 1.2: Filter High Performers
     *
     * Get summaries only for students with GPA >= 3.5
     *
     * @return List of high-performing student summaries
     */
    public List<StudentSummary> getHighPerformers() {
        return students.stream()
                .filter(s -> s.getAverageScore() >= 3.5)
                .map(s -> new StudentSummary(s.getId(), s.getFullName(), s.getAverageScore()))
                .toList();
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 2: Compact Constructor with Validation
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * Course configuration with validation.
     *
     * EXAMPLE:
     * <pre>
     * CourseConfig config = new CourseConfig(30, 120);
     * CourseConfig invalid = new CourseConfig(-5, 60); // Throws IllegalArgumentException
     * </pre>
     */
    public record CourseConfig(int maxStudents, int durationMinutes) {
        /**
         * Compact constructor validates input.
         */
        public CourseConfig {
            if (maxStudents < 0) {
                throw new IllegalArgumentException("Max students cannot be negative: " + maxStudents);
            }
            if (durationMinutes <= 0) {
                throw new IllegalArgumentException("Duration must be positive: " + durationMinutes);
            }
        }

        /**
         * Wither method - creates a new instance with modified duration.
         */
        public CourseConfig withDuration(int newDuration) {
            return new CourseConfig(maxStudents, newDuration);
        }

        /**
         * Static factory method with default max students.
         */
        public static CourseConfig ofDefault(int duration) {
            return new CourseConfig(30, duration);
        }
    }

    /**
     * TASK 2.1: Create Course Configs
     *
     * Create CourseConfig records from actual courses.
     * Use course duration in hours converted to minutes, and enrollment count as max students.
     *
     * @return List of course configurations
     */
    public List<CourseConfig> getCourseConfigs() {
        return courses.stream()
            .map(c -> new CourseConfig(c.getEnrollmentCount(), c.getDurationInHours() * 60))
            .toList();
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 3: Nested Records and Composition
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * Instructor information DTO.
     */
    public record InstructorInfo(String id, String name, String specialization) {}

    /**
     * Course summary with nested instructor information.
     *
     * EXAMPLE:
     * <pre>
     * InstructorInfo instructor = new InstructorInfo("INS-1", "Dr. Smith", "Java");
     * CourseSummary course = new CourseSummary(
     *     "CRS-1", "Java Masterclass", BigDecimal.valueOf(99.99), instructor
     * );
     * </pre>
     */
    public record CourseSummary(
        String id,
        String title,
        BigDecimal price,
        InstructorInfo instructor
    ) {}

    /**
     * TASK 3.1: Convert to Course Summaries
     *
     * Create CourseSummary DTOs with nested InstructorInfo from domain objects.
     *
     * @return List of course summaries
     *
     * EXAMPLE:
     * <pre>
     * List<CourseSummary> summaries = getCourseSummaries();
     * summaries.forEach(c ->
     *     System.out.println(c.title() + " by " + c.instructor().name())
     * );
     * </pre>
     */
    public List<CourseSummary> getCourseSummaries() {
        return courses.stream()
                .map(c ->  {
                    Instructor instructor = c.getInstructor();
                    InstructorInfo instructorInfo = new InstructorInfo(instructor.getId(), 
                        instructor.getFullName(), instructor.getSpecialization());
                    
                    return new CourseSummary(c.getId(), c.getTitle(), 
                        c.getPrice(), instructorInfo);
                })
                .toList();
    }

    /**
     * TASK 3.2: Find Expensive Courses
     *
     * Get course summaries for courses costing more than $100.
     *
     * @return List of expensive course summaries
     */
    public List<CourseSummary> getExpensiveCourseSummaries() {
        final BigDecimal oneHundred = BigDecimal.valueOf(100);

        return courses.stream()
                .filter(c -> c.getPrice() != null && c.getPrice().compareTo(oneHundred) > 0)
                .map(c ->  {
                    Instructor instructor = c.getInstructor();
                    InstructorInfo instructorInfo = new InstructorInfo(instructor.getId(), 
                        instructor.getFullName(), instructor.getSpecialization());
                    
                    return new CourseSummary(c.getId(), c.getTitle(), 
                        c.getPrice(), instructorInfo);
                })
                .toList();
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 4: Records with Collections
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * Student progress report with list of enrolled course titles.
     *
     * EXAMPLE:
     * <pre>
     * ProgressReport report = new ProgressReport(
     *     "John Doe",
     *     List.of("Java", "Python", "React"),
     *     75.5
     * );
     * </pre>
     */
    public record ProgressReport(
        String studentName,
        List<String> enrolledCourses,
        double averageProgress
    ) {}

    /**
     * TASK 4.1: Generate Progress Reports
     *
     * Create progress reports for students who have enrollments.
     *
     * @return List of progress reports
     */
    public List<ProgressReport> getProgressReports() {
        return students.stream()
                .filter(s -> enrollments.stream().anyMatch(e -> e.getStudentId().equals(s.getId())))
                .map(s -> {
                    List<Enrollment> studentEnrollments = enrollments.stream()
                            .filter(e -> e.getStudentId().equals(s.getId()))
                            .toList();

                    List<String> courseTitles = studentEnrollments.stream()
                            .map(e -> e.getCourse().getTitle())
                            .toList();

                    double avgProgress = studentEnrollments.stream()
                            .mapToDouble(Enrollment::getProgressPercentage)
                            .average()
                            .orElse(0.0);

                    return new ProgressReport(s.getFullName(), courseTitles, avgProgress);
                })
                .toList();
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 5: Record as Map Keys
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * Category and difficulty combination for grouping.
     */
    public record CategoryDifficulty(Category category, DifficultyLevel difficulty) {}

    /**
     * TASK 5.1: Group Courses by Category and Difficulty
     *
     * Create a map where the key is a CategoryDifficulty record
     * and value is the count of courses.
     *
     * @return Map of category-difficulty combinations to counts
     *
     * EXAMPLE:
     * <pre>
     * Map<CategoryDifficulty, Long> grouping = groupCoursesByTypeAndLevel();
     * grouping.forEach((key, count) ->
     *     System.out.println(key.category() + " - " + key.difficulty() + ": " + count)
     * );
     * </pre>
     */
    public Map<CategoryDifficulty, Long> groupCoursesByTypeAndLevel() {
        return courses.stream()
                .map(c -> new CategoryDifficulty(c.getCategory(), c.getDifficulty()))
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
    }

    /**
     * TASK 5.2: Find Most Common Combination
     *
     * Find the category-difficulty combination with the most courses.
     *
     * @return Optional containing the most common combination
     */
    public Optional<Map.Entry<CategoryDifficulty, Long>> getMostCommonCombination() {
        return groupCoursesByTypeAndLevel().entrySet().stream()
                .max(Map.Entry.comparingByValue());
    }

    public static void main(String[] args) {
        RecordsChallenge challenge = new RecordsChallenge();

        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("  RecordsChallenge - Immutable Data Carriers");
        System.out.println("═══════════════════════════════════════════════════════════════\n");

        // TEST 1: Student Summaries
        System.out.println("--- TEST 1: Student Summaries ---");
        List<StudentSummary> summaries = challenge.getStudentSummaries();
        System.out.println("Total students: " + summaries.size());
        summaries.stream()
                .limit(3)
                .forEach(s -> System.out.println("  " + s.fullName() + " (GPA: " +
                        String.format("%.2f", s.gpa()) + ")"));

        // TEST 2: Course Config Validation
        System.out.println("\n--- TEST 2: Course Config Validation ---");
        try {
            CourseConfig valid = new CourseConfig(30, 120);
            System.out.println("Valid config: " + valid);

            CourseConfig withNew = valid.withDuration(180);
            System.out.println("Modified duration: " + withNew);

            CourseConfig defaultConfig = CourseConfig.ofDefault(90);
            System.out.println("Default config: " + defaultConfig);

            // This should throw
            CourseConfig invalid = new CourseConfig(-5, 60);
        } catch (IllegalArgumentException e) {
            System.out.println("Validation works: " + e.getMessage());
        }

        // TEST 3: Course Summaries with Nested Records
        System.out.println("\n--- TEST 3: Course Summaries with Nested Instructors ---");
        List<CourseSummary> courseSummaries = challenge.getCourseSummaries();
        System.out.println("Total courses: " + courseSummaries.size());
        courseSummaries.stream()
                .limit(3)
                .forEach(c -> System.out.println("  " + c.title() +
                        " ($" + c.price() + ") by " + c.instructor().name()));

        // TEST 4: Records as Map Keys
        System.out.println("\n--- TEST 4: Records as Map Keys ---");
        Map<CategoryDifficulty, Long> grouping = challenge.groupCoursesByTypeAndLevel();
        System.out.println("Course groupings by category and difficulty:");
        grouping.entrySet().stream()
                .sorted(Map.Entry.<CategoryDifficulty, Long>comparingByValue().reversed())
                .limit(5)
                .forEach(e -> System.out.println("  " + e.getKey().category() +
                        " - " + e.getKey().difficulty() + ": " + e.getValue() + " courses"));

        // TEST 5: Record Equality
        System.out.println("\n--- TEST 5: Record Equality (Auto-generated) ---");
        StudentSummary s1 = new StudentSummary("S1", "Alice", 3.8);
        StudentSummary s2 = new StudentSummary("S1", "Alice", 3.8);
        StudentSummary s3 = new StudentSummary("S2", "Bob", 3.5);
        System.out.println("s1.equals(s2): " + s1.equals(s2) + " (should be true)");
        System.out.println("s1.equals(s3): " + s1.equals(s3) + " (should be false)");
        System.out.println("s1.hashCode() == s2.hashCode(): " +
                (s1.hashCode() == s2.hashCode()) + " (should be true)");

        System.out.println("\n═══════════════════════════════════════════════════════════════");
        System.out.println("  All reference implementations executed successfully!");
        System.out.println("═══════════════════════════════════════════════════════════════");
    }
}
