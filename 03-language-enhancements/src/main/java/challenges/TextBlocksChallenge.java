package challenges;

import data.SampleDataGenerator;
import model.*;

import java.util.*;

/**
 * ═══════════════════════════════════════════════════════════════════════════════
 * CHALLENGE 01: Text Blocks - Multi-line String Literals (Java 15)
 * ═══════════════════════════════════════════════════════════════════════════════
 *
 * SCENARIO:
 * You are building the Communication & Reporting Module for EduMaster platform.
 * The system needs to generate SQL queries, JSON APIs, HTML emails, and formatted
 * templates with clean, readable code. Traditional string concatenation makes this
 * painful and error-prone.
 *
 * FOCUS:
 * - Text Blocks (""" ... """) for multi-line strings
 * - No need for escape sequences (\n, \", etc.)
 * - Automatic indentation management
 * - String interpolation with .formatted() or String.format()
 * - Incidental vs. essential whitespace
 * - Escape sequences within text blocks (\s, \, etc.)
 *
 * ═══════════════════════════════════════════════════════════════════════════════
 */
public class TextBlocksChallenge {

    private final List<Student> students;
    private final List<Course> courses;
    private final List<Instructor> instructors;

    public TextBlocksChallenge() {
        this.students = SampleDataGenerator.generateStudents(20);
        this.instructors = SampleDataGenerator.generateInstructors(5);
        this.courses = SampleDataGenerator.generateCourses(15, instructors);
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 1: Basic Text Blocks - SQL & Database Queries
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 1.1: Simple SQL Query
     *
     * Convert the traditional concatenated SQL to a clean text block.
     * Old way: "SELECT * FROM users\n" + "WHERE active = true\n" + ...
     *
     * EXAMPLE OUTPUT:
     * SELECT * FROM students
     * WHERE level = 'ADVANCED'
     * ORDER BY average_score DESC
     * LIMIT 10
     *
     * @return SQL query as text block
     */
    public String getTopStudentsQuery() {
        // Placeholder: original implementation removed for challenge reset
        return null;
    }

    /**
     * TASK 1.2: Parameterized SQL Query
     *
     * Create a SQL query with JOIN and use .formatted() to inject parameters.
     * Query should find all enrollments for a specific student.
     *
     * EXAMPLE INPUT: studentId = "S001"
     * EXAMPLE OUTPUT:
     * SELECT e.id, c.title, e.score
     * FROM enrollments e
     * JOIN courses c ON e.course_id = c.id
     * WHERE e.student_id = 'S001'
     *
     * @param studentId Student identifier
     * @return Formatted SQL query
     */
    public String getStudentEnrollmentsQuery(String studentId) {
        // Placeholder: original implementation removed for challenge reset
        return null;
    }

    /**
     * TASK 1.3: Complex Multi-Table Query
     *
     * Create a complex query that aggregates enrollment data.
     * Show total enrollments and average score per course.
     *
     * EXAMPLE OUTPUT:
     * SELECT c.title,
     *        COUNT(e.id) as total_enrollments,
     *        AVG(e.score) as average_score
     * FROM courses c
     * LEFT JOIN enrollments e ON c.id = e.course_id
     * GROUP BY c.title
     * HAVING COUNT(e.id) > 5
     * ORDER BY average_score DESC
     *
     * @return Complex aggregation query
     */
    public String getCourseStatisticsQuery() {
        // Placeholder: original implementation removed for challenge reset
        return null;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 2: JSON Templates for REST APIs
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 2.1: Student JSON Representation
     *
     * Generate JSON for a student object. Text blocks eliminate the need
     * for escaping quotes.
     *
     * EXAMPLE INPUT: Student(id="S001", firstName="John", lastName="Doe",
     *                        email="john@edu.com", level=ADVANCED)
     * EXAMPLE OUTPUT:
     * {
     *   "id": "S001",
     *   "firstName": "John",
     *   "lastName": "Doe",
     *   "email": "john@edu.com",
     *   "level": "ADVANCED"
     * }
     *
     * @param student Student to convert
     * @return JSON representation
     */
    public String generateStudentJson(Student student) {
        // Placeholder: original implementation removed for challenge reset
        return null;
    }

    /**
     * TASK 2.2: Course JSON with Nested Data
     *
     * Generate JSON for a course including nested instructor object.
     *
     * EXAMPLE INPUT: Course with instructor
     * EXAMPLE OUTPUT:
     * {
     *   "id": "C001",
     *   "title": "Advanced Java Programming",
     *   "price": 299.99,
     *   "instructor": {
     *     "id": "I001",
     *     "name": "Dr. Smith"
     *   }
     * }
     *
     * @param course Course to convert
     * @return JSON with nested instructor
     */
    public String generateCourseJson(Course course) {
        // Placeholder: original implementation removed for challenge reset
        return null;
    }

    /**
     * TASK 2.3: JSON Array of Students
     *
     * Generate a JSON array containing multiple students.
     * Use StringBuilder to construct the array dynamically.
     *
     * EXAMPLE INPUT: List of 3 students
     * EXAMPLE OUTPUT:
     * [
     *   {"id": "S001", "name": "John Doe"},
     *   {"id": "S002", "name": "Jane Smith"},
     *   {"id": "S003", "name": "Bob Johnson"}
     * ]
     *
     * @param students List of students
     * @return JSON array
     */
    public String generateStudentArrayJson(List<Student> students) {
        // Placeholder: original implementation removed for challenge reset
        return null;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 3: HTML Email Templates
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 3.1: Welcome Email
     *
     * Create an HTML email template welcoming a new student.
     * Include proper HTML structure with styling.
     *
     * EXAMPLE INPUT: studentName = "John Doe"
     * EXAMPLE OUTPUT:
     * <!DOCTYPE html>
     * <html>
     * <body>
     *   <h1>Welcome to EduMaster, John Doe!</h1>
     *   <p>We're excited to have you join our learning community.</p>
     * </body>
     * </html>
     *
     * @param studentName Name of the student
     * @return HTML email content
     */
    public String generateWelcomeEmail(String studentName) {
        // Placeholder: original implementation removed for challenge reset
        return null;
    }

    /**
     * TASK 3.2: Course Enrollment Confirmation
     *
     * Generate HTML email confirming course enrollment with course details.
     * Include inline CSS for styling.
     *
     * EXAMPLE INPUT: studentName="John", courseTitle="Java Basics", price=99.99
     * EXAMPLE OUTPUT:
     * <html>
     * <head>
     *   <style>
     *     .header { color: #2c3e50; }
     *     .details { background: #ecf0f1; padding: 10px; }
     *   </style>
     * </head>
     * <body>
     *   <h2 class="header">Enrollment Confirmed!</h2>
     *   <div class="details">
     *     <p>Student: John</p>
     *     <p>Course: Java Basics</p>
     *     <p>Price: $99.99</p>
     *   </div>
     * </body>
     * </html>
     *
     * @param studentName Student name
     * @param courseTitle Course title
     * @param price Course price
     * @return HTML email with styling
     */
    public String generateEnrollmentConfirmation(String studentName, String courseTitle, double price) {
        // Placeholder: original implementation removed for challenge reset
        return null;
    }

    /**
     * TASK 3.3: Course Completion Certificate
     *
     * Generate an HTML certificate with student name, course title, and completion date.
     * Include decorative elements and formatting.
     *
     * @param studentName Student name
     * @param courseTitle Course title
     * @param completionDate Completion date
     * @return HTML certificate
     */
    public String generateCompletionCertificate(String studentName, String courseTitle, String completionDate) {
        // Placeholder: original implementation removed for challenge reset
        return null;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 4: Advanced Text Block Features
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 4.1: Preserving Trailing Whitespace
     *
     * Text blocks automatically strip incidental whitespace. To preserve trailing
     * spaces, use \s escape sequence.
     *
     * Create a formatted table where columns must align perfectly.
     *
     * EXAMPLE OUTPUT:
     * ID     Name            Email
     * S001   John Doe        john@edu.com
     * S002   Jane Smith      jane@edu.com
     *
     * @return Formatted table with preserved whitespace
     */
    public String generateAlignedTable() {
        // Placeholder: original implementation removed for challenge reset
        return null;
    }

    /**
     * TASK 4.2: Line Terminator Control
     *
     * By default, text blocks normalize line endings. Use \ at end of line
     * to continue the string on the next line without a line break.
     *
     * Create a long sentence that spans multiple lines in source code
     * but appears as a single line.
     *
     * EXAMPLE OUTPUT (single line):
     * This is a very long sentence that spans multiple lines in the source code but appears as a single continuous line in the output.
     *
     * @return Single-line output from multi-line source
     */
    public String generateContinuousLine() {
        // Placeholder: original implementation removed for challenge reset
        return null;
    }

    /**
     * TASK 4.3: Embedded Expressions and Special Characters
     *
     * Create a code snippet template that includes quotes, backslashes,
     * and other special characters.
     *
     * EXAMPLE OUTPUT:
     * public static void main(String[] args) {
     *     String message = "Hello \"World\"";
     *     String path = "C:\\Users\\Documents";
     *     System.out.println(message);
     * }
     *
     * @return Java code snippet with special characters
     */
    public String generateCodeSnippet() {
        // Placeholder: original implementation removed for challenge reset
        return null;
    }

    /**
     * TASK 4.4: Dynamic Report Generation
     *
     * Generate a comprehensive report combining multiple students and courses.
     * Use text blocks for the template and loop to build content.
     *
     * EXAMPLE OUTPUT:
     * ╔════════════════════════════════════════╗
     * ║        ENROLLMENT REPORT               ║
     * ╚════════════════════════════════════════╝
     *
     * Student: John Doe (S001)
     * Courses Enrolled: 3
     * - Advanced Java Programming
     * - Data Structures
     * - Web Development
     *
     * Student: Jane Smith (S002)
     * ...
     *
     * @return Complete enrollment report
     */
    public String generateEnrollmentReport() {
        // Placeholder: original implementation removed for challenge reset
        return null;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // TEST HELPERS
    // ═══════════════════════════════════════════════════════════════════════════

    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("    Text Blocks Challenge - Java 15 Multi-line Strings");
        System.out.println("═══════════════════════════════════════════════════════════════\n");

        TextBlocksChallenge challenge = new TextBlocksChallenge();

        // Test PART 1: SQL Queries
        System.out.println("--- PART 1: SQL Queries ---");
        System.out.println(challenge.getTopStudentsQuery());
        System.out.println();

        // Test PART 2: JSON Generation
        System.out.println("--- PART 2: JSON Templates ---");
        if (!challenge.students.isEmpty()) {
            Student firstStudent = challenge.students.get(0);
            System.out.println(challenge.generateStudentJson(firstStudent));
        }
        System.out.println();

        // Test PART 3: HTML Email
        System.out.println("--- PART 3: HTML Email Templates ---");
        System.out.println(challenge.generateWelcomeEmail("John Doe"));
        System.out.println();

        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("Complete the remaining TODOs to master Text Blocks!");
        System.out.println("═══════════════════════════════════════════════════════════════");
    }
}
