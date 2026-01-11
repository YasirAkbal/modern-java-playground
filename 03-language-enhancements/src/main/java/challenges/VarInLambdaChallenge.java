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
        //feasible only with frameworks/tools that process annotations
        names.forEach((@NonNull var student) -> System.out.println(student));
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
        var studentConsumer = (Consumer<Student>)(@NonNull var student) -> System.out.println(student);
        return studentConsumer;
    }

    /**
     * TASK 1.3: Predicate with var
     *
     * Create a Predicate using var in lambda parameter.
     *
     * @return Predicate checking if student is active
     */
    public Predicate<Student> createActiveStudentFilter() {
        return (@Validated var student) -> student.isActive();
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
        return (var s, var c) -> {
            System.out.println("Student Name: %s".formatted(s.getFullName()));
            System.out.println("Course taken: %s".formatted(c.getTitle()));
        };
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
        return (@Trimmed var first, @Trimmed var last) -> first.trim() + " " + last.trim();
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
    }
}
