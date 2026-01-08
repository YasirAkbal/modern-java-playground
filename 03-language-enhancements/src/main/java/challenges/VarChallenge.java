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
        return null;
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
    public BigDecimal calculateTotalRevenue() {
        // TODO: Use var for totalRevenue, count, and avgPrice variables
        // Hint: courses.stream().map(Course::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add)
        return BigDecimal.ZERO;
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
        // TODO: Use var for fullName, email, and result
        return null;
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
        return null;
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
        // TODO: Use var for the nested map and intermediate variables
        // Hint: Group students by level, then for each level create a map of categories
        return null;
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
        // TODO: Use var for filtered list, titles list
        return null;
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
        return;
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
        // TODO: Use var for loop counter, course variable, and title
        // Hint: for (var i = 0; i < Math.min(limit, courses.size()); i++)
    }

    /**
     * TASK 3.3: Iterator Pattern
     *
     * Use var with explicit iterator.
     *
     * @return List of course IDs
     */
    public List<String> getCourseIds() {
        // TODO: Use var for result list and iterator
        // Hint: var iterator = courses.iterator();
        return null;
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
        return null;
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
        // TODO: Use var for try-with-resources with two StringReaders
        return null;
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
        return 0;
    }

    /**
     * TASK 5.2: Intersection Types
     *

     * Demonstrate var with intersection types (less common but powerful).
     *
     * @return Result from intersection type
     */
    public String demonstrateIntersectionType() {
        // TODO: Create anonymous class implementing multiple interfaces
        // var processor = new Object() implements Comparable, Serializable...
        // This is an advanced case - show how var captures the full type
        return null;
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
        // TODO: Use var for each stream operation step
        // Calculate: total enrollments, avg per student, max enrollments per student
        return null;
    }

    /**
     * TASK 5.4: Complex Initialization
     *
     * Use var with complex object initialization.
     *
     * @return Configured course object
     */
    public Course createSampleCourse() {
        // TODO: Use var for course, lessons list, instructor, etc.
        // Build a complete course with lessons
        return null;
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
        return;
    }

    /**
     * TASK 6.2: When to Avoid var
     *
     * Show cases where explicit types are better for readability.
     */
    public void demonstratePoorVarUsage() {
        // TODO: Show examples where var hurts readability
        // BAD: var result = process(); // What type is result?
        // BAD: var x = computeValue(); // Non-descriptive name + var
        // GOOD: StudentReport report = generateReport();
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
