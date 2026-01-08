package challenges;

import data.SampleDataGenerator;
import model.*;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

/**
 * ═══════════════════════════════════════════════════════════════════════════════
 * CHALLENGE 04: var in Lambda Parameters - Enhanced Type Inference (Java 11)
 * ═══════════════════════════════════════════════════════════════════════════════
 *
 * SCENARIO:
 * You are modernizing the EduMaster platform's functional code. While var was
 * introduced in Java 10 for local variables, Java 11 extended it to lambda
 * parameters. This enables adding annotations to lambda parameters, which is
 * essential for tools like @NonNull, @Nullable, and other validation frameworks.
 *
 * FOCUS:
 * - var in lambda parameters for annotation support
 * - Consistent usage: ALL parameters must use var if one does
 * - Use cases with Consumer, BiConsumer, Function, BiFunction
 * - Annotations for validation and documentation
 * - Comparison with explicit types and implicit types
 *
 * KEY RULE:
 * If you use var for one lambda parameter, ALL parameters must use var.
 * Cannot mix: (var x, String y) -> ... is INVALID
 *
 * ═══════════════════════════════════════════════════════════════════════════════
 */
public class VarInLambdaChallenge {

    private final List<Student> students;
    private final List<Course> courses;
    private final List<Enrollment> enrollments;

    public VarInLambdaChallenge() {
        this.students = SampleDataGenerator.generateStudents(30);
        List<Instructor> instructors = SampleDataGenerator.generateInstructors(8);
        this.courses = SampleDataGenerator.generateCourses(25, instructors);
        this.enrollments = SampleDataGenerator.generateEnrollments(students, courses, 100);
    }

    // Custom annotations for demonstration
    @interface NonNull {}
    @interface Validated {}
    @interface Trimmed {}

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 1: Basic var in Lambda Parameters
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 1.1: Single Parameter Lambda with Annotation
     *
     * Use var in lambda to enable annotation support.
     * The main benefit of var in lambdas is allowing annotations.
     *
     * EXAMPLE INPUT: List of student names
     * EXAMPLE OUTPUT: Prints each name with validation annotation
     *
     * Syntax:
     * Before: students.forEach(s -> ...)
     * After:  students.forEach((var s) -> ...)
     * With annotation: students.forEach((@NonNull var s) -> ...)
     *
     * @param names List of student names
     */
    public void printStudentNames(List<String> names) {
        return;
    }

    /**
     * TASK 1.2: Consumer with Annotation
     *
     * Create a Consumer that processes students with annotated var parameter.
     *
     * EXAMPLE: Consumer that prints student info with @NonNull validation
     *
     * @return Consumer for student processing
     */
    public Consumer<Student> createStudentProcessor() {
        return null;
    }

    /**
     * TASK 1.3: Predicate with var
     *
     * Create a Predicate using var in lambda parameter.
     *
     * @return Predicate checking if student is active
     */
    public Predicate<Student> createActiveStudentFilter() {
        // TODO: return (@Validated var student) -> student.isActive();
        return null;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 2: Multiple Parameters with var
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 2.1: BiConsumer with Both Parameters Using var
     *
     * IMPORTANT: If you use var for one parameter, ALL must use var.
     * This is invalid: (var s, String c) -> ...
     * This is valid:   (var s, var c) -> ...
     *
     * EXAMPLE INPUT: student and course
     * EXAMPLE OUTPUT: Prints enrollment information
     *
     * @return BiConsumer processing student and course
     */
    public BiConsumer<Student, Course> createEnrollmentLogger() {
        return null;
    }

    /**
     * TASK 2.2: BiFunction with Annotated Parameters
     *
     * Create a function that combines two strings with validation.
     *
     * EXAMPLE INPUT: ("John", "Doe")
     * EXAMPLE OUTPUT: "John Doe"
     *
     * @return BiFunction combining strings
     */
    public BiFunction<String, String, String> createNameCombiner() {
        // TODO: return (@Trimmed var first, @Trimmed var last) -> first.trim() + " " + last.trim();
        return null;
    }

    /**
     * TASK 2.3: Comparator with var
     *
     * Create a comparator using var in lambda parameters.
     *
     * EXAMPLE: Sort students by average score
     *
     * @return Comparator for students
     */
    public Comparator<Student> createScoreComparator() {
        // TODO: return (var s1, var s2) -> Double.compare(s1.getAverageScore(), s2.getAverageScore());
        return null;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 3: var in Complex Lambda Expressions
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 3.1: Stream Operations with var
     *
     * Use var in lambda parameters within stream operations.
     *
     * EXAMPLE OUTPUT: List of formatted course titles with prices
     * Format: "Course Title - $99.99"
     *
     * @return List of formatted strings
     */
    public List<String> getFormattedCoursePrices() {
        return null;
    }

    /**
     * TASK 3.2: Grouping with var
     *
     * Use var in Collectors.groupingBy with custom classifier.
     *
     * EXAMPLE OUTPUT: Map<StudentLevel, List<Student>>
     *
     * @return Students grouped by level
     */
    public Map<StudentLevel, List<Student>> groupStudentsByLevel() {
        // TODO: Use var in lambda: collect(Collectors.groupingBy((@NonNull var s) -> s.getLevel()))
        return null;
    }

    /**
     * TASK 3.3: Reducing with var
     *
     * Use var in reduce operation with BiFunction.
     *
     * EXAMPLE: Calculate total enrollment count across all courses
     *
     * @return Total enrollment count
     */
    public int getTotalEnrollmentCount() {
        // TODO: courses.stream().reduce(0, (var sum, var course) -> sum + course.getEnrollmentCount(), Integer::sum)
        return 0;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 4: Practical Applications with Annotations
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 4.1: Input Validation
     *
     * Process a map using var with annotations for validation.
     *
     * EXAMPLE INPUT: Map of student IDs to grades
     * EXAMPLE OUTPUT: Prints validated entries
     *
     * @param grades Map of student ID to grade
     */
    public void processGrades(Map<String, Double> grades) {
        return;
    }

    /**
     * TASK 4.2: Data Transformation with Annotations
     *
     * Transform enrollment data with annotated parameters.
     *
     * EXAMPLE OUTPUT: Map<String, List<String>>
     * Maps student ID to list of course titles
     *
     * @return Student to courses mapping
     */
    public Map<String, List<String>> getStudentCourseMappings() {
        // TODO: Use var with annotations in groupingBy and mapping
        return null;
    }

    /**
     * TASK 4.3: Filtering with Multiple Conditions
     *
     * Create a complex filter using var in multiple lambda parameters.
     *
     * EXAMPLE: Find enrollments where student is ADVANCED and course is published
     *
     * @return Filtered enrollments
     */
    public List<Enrollment> getAdvancedStudentEnrollments() {
        // TODO: Use var in filter lambdas
        return null;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 5: Comparison - var vs. Explicit vs. Implicit Types
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 5.1: Demonstrate Three Approaches
     *
     * Show the same lambda written three ways:
     * 1. Implicit types: (s, c) -> ...
     * 2. Explicit types: (Student s, Course c) -> ...
     * 3. var with annotations: (@NonNull var s, @NonNull var c) -> ...
     */
    public void demonstrateThreeApproaches() {
        return;
    }

    /**
     * TASK 5.2: When to Use var in Lambdas
     *
     * Best practices guide for using var in lambda parameters.
     */
    public void demonstrateBestPractices() {
        return;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 6: Advanced Scenarios
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 6.1: Nested Lambdas with var
     *
     * Use var in nested lambda expressions.
     *
     * EXAMPLE: For each student, process all their enrollments
     *
     * @return Summary string
     */
    public String processNestedData() {
        // TODO: Use var in both outer and inner lambdas
        return null;
    }

    /**
     * TASK 6.2: Method References vs var Lambdas
     *
     * Show when you can't use method references but can use var lambdas.
     *
     * @return List of uppercase names
     */
    public List<String> convertToUpperCase(List<String> names) {
        return null;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // HELPER METHODS
    // ═══════════════════════════════════════════════════════════════════════════

    private void process(Student s) {
        System.out.println("Processing: " + s.getFullName());
    }

    private void processEnrollment(Enrollment e, Course c) {
        System.out.println("Enrollment in: " + c.getTitle());
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // TEST HELPERS
    // ═══════════════════════════════════════════════════════════════════════════

    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("    var in Lambda Parameters - Java 11 Enhancement");
        System.out.println("═══════════════════════════════════════════════════════════════\n");

        var challenge = new VarInLambdaChallenge();

        // Test PART 1: Basic var in lambdas
        System.out.println("--- PART 1: Basic var with Annotations ---");
        List<String> sampleNames = Arrays.asList("Alice", "Bob", "Charlie");
        challenge.printStudentNames(sampleNames);
        System.out.println();

        // Test PART 2: Multiple parameters
        System.out.println("--- PART 2: Multiple Parameters with var ---");
        var logger = challenge.createEnrollmentLogger();
        if (!challenge.students.isEmpty() && !challenge.courses.isEmpty()) {
            logger.accept(challenge.students.get(0), challenge.courses.get(0));
        }
        System.out.println();

        // Test PART 3: Complex expressions
        System.out.println("--- PART 3: Stream Operations ---");
        var formattedPrices = challenge.getFormattedCoursePrices();
        formattedPrices.stream().limit(3).forEach(System.out::println);
        System.out.println();

        // Test PART 4: Practical applications
        System.out.println("--- PART 4: Input Validation ---");
        Map<String, Double> sampleGrades = new HashMap<>();
        sampleGrades.put("S001", 85.5);
        sampleGrades.put("S002", 92.0);
        sampleGrades.put("S003", 150.0); // Invalid
        challenge.processGrades(sampleGrades);
        System.out.println();

        // Test PART 5: Comparisons
        System.out.println("--- PART 5: Three Approaches Comparison ---");
        challenge.demonstrateThreeApproaches();
        System.out.println();

        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("Complete the remaining TODOs to master var in lambdas!");
        System.out.println("═══════════════════════════════════════════════════════════════");
    }
}
