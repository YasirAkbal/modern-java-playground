package challenges;

import data.SampleDataGenerator;
import model.*;

import java.math.BigDecimal;
import java.util.*;

/**
 * ═══════════════════════════════════════════════════════════════════════════════
 * CHALLENGE 01: Pattern Matching - Type Testing Without Casting
 * ═══════════════════════════════════════════════════════════════════════════════
 *
 * SCENARIO:
 * You are building an Event Processing System for the EduMaster platform that
 * receives heterogeneous objects (students, courses, enrollments, notifications).
 * The system must route and process these objects based on their runtime types
 * without manual casting or verbose instanceof checks.
 *
 * FOCUS:
 * - instanceof pattern matching (Java 16+)
 * - Switch pattern matching (Java 21+)
 * - Guarded patterns with when clauses
 * - Type test patterns with scope
 * - Null handling in pattern matching
 *
 * ═══════════════════════════════════════════════════════════════════════════════
 */
public class PatternMatchingChallenge {

    private final List<Student> students;
    private final List<Course> courses;
    private final List<Instructor> instructors;
    private final List<Enrollment> enrollments;

    public PatternMatchingChallenge() {
        this.students = SampleDataGenerator.generateStudents(50);
        this.instructors = SampleDataGenerator.generateInstructors(10);
        this.courses = SampleDataGenerator.generateCourses(30, instructors);
        this.enrollments = SampleDataGenerator.generateEnrollments(students, courses, 100);
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 1: instanceof Pattern Matching - Simplifying Type Tests
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 1.1: Basic instanceof Pattern Matching
     *
     * Extract information from different object types without manual casting.
     * - If obj is a String, return its length
     * - If obj is a Course, return its enrollment count
     * - Otherwise return 0
     *
     * DO NOT use manual casting like (String) obj.
     * Use pattern variable directly: if (obj instanceof String s) { return s.length(); }
     *
     * EXAMPLE:
     * getLengthOrCount("Hello") -> 5
     * getLengthOrCount(courseWith500Enrollments) -> 500
     * getLengthOrCount(123) -> 0
     *
     * @param obj The object to analyze
     * @return Length of string or enrollment count of course
     */
    public int getLengthOrCount(Object obj) {
        if (obj instanceof String s) {
            return s.length();
        }
        if (obj instanceof Course c) {
            return c.getEnrollmentCount();
        }
        return 0;
    }

    /**
     * TASK 1.2: Pattern Matching with Scope
     *
     * Use pattern variables that are automatically scoped within the if block.
     * Check if obj is a Student with average score > 80.
     *
     * EXAMPLE:
     * isHighPerformer(studentWith85Score) -> true
     * isHighPerformer(studentWith70Score) -> false
     * isHighPerformer("John") -> false
     *
     * @param obj Object to check
     * @return True if student has average score > 80
     */
    public boolean isHighPerformer(Object obj) {
        // TODO: if (obj instanceof Student s && s.getAverageScore() != null && s.getAverageScore() > 80)
        return false;
    }

    /**
     * TASK 1.3: Nested Type Checks
     *
     * Handle object that could be Student, Course, or Enrollment.
     * Return enrollment count from different sources:
     * - Student: return number of enrollments (student.getEnrollments().size())
     * - Course: return enrollmentCount
     * - Enrollment: return 1
     * - Otherwise: return 0
     *
     * EXAMPLE:
     * countEnrollments(studentWith3Enrollments) -> 3
     * countEnrollments(courseWith100Enrollments) -> 100
     *
     * @param obj Object to analyze
     * @return Enrollment count
     */
    public int countEnrollments(Object obj) {
        if (obj instanceof Student s) {
            return s.getEnrollments().size();
        }
        if (obj instanceof Course c) {
            return c.getEnrollmentCount();
        }
        if (obj instanceof Enrollment e) {
            return 1;
        }
        return 0;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 2: Switch Pattern Matching - Elegant Type Routing
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 2.1: Basic Switch Pattern Matching
     *
     * Route different types using switch expressions (Java 21).
     * Return descriptive strings:
     * - String s -> "Text: " + s
     * - Integer i -> "Number: " + i
     * - Student st -> "Student: " + st.getFullName()
     * - Course c -> "Course: " + c.getTitle()
     * - null -> "Null Object"
     * - default -> "Unknown Type"
     *
     * EXAMPLE:
     * describeObject("Hello") -> "Text: Hello"
     * describeObject(studentNamed("Alice")) -> "Student: Alice Smith"
     * describeObject(null) -> "Null Object"
     *
     * @param obj Object to describe
     * @return Description string
     */
    public String describeObject(Object obj) {
        return switch (obj) {
            case String s -> "Text: " + s;
            case Integer i -> "Number: " + i;
            case Student st -> "Student: " + st.getFullName();
            case Course c -> "Course: " + c.getTitle();
            case null -> "Null Object";
            default -> "Unknown Type";
        };
    }

    /**
     * TASK 2.2: Switch with Multiple Type Patterns
     *
     * Calculate a "priority score" for different entities:
     * - Instructor (verified) -> 100
     * - Instructor (not verified) -> 50
     * - Student (ADVANCED level) -> 80
     * - Student (other levels) -> 40
     * - Course (published) -> 60
     * - Course (not published) -> 20
     * - Default -> 0
     *
     * Use guarded patterns (when clause).
     *
     * EXAMPLE:
     * getPriorityScore(verifiedInstructor) -> 100
     * getPriorityScore(advancedStudent) -> 80
     *
     * @param entity Entity to score
     * @return Priority score
     */
    public int getPriorityScore(Object entity) {
        // TODO: Use switch with when guards
        return 0;
    }

    /**
     * TASK 2.3: Complex Business Logic Switch
     *
     * Determine notification message based on object type and state:
     * - Student (active, no enrollments) -> "Welcome! Browse our courses."
     * - Student (active, has enrollments) -> "Continue your learning journey!"
     * - Student (inactive) -> "We miss you! Come back."
     * - Course (free) -> "Free course available!"
     * - Course (price < 50) -> "Affordable course!"
     * - Course (price >= 50) -> "Premium course!"
     * - Enrollment (completed) -> "Congratulations on completion!"
     * - Enrollment (in progress) -> "Keep up the good work!"
     * - Default -> "No notification"
     *
     * EXAMPLE:
     * getNotificationMessage(activeStudentNoEnrollments) -> "Welcome! Browse our courses."
     * getNotificationMessage(freeCourse) -> "Free course available!"
     *
     * @param obj Object to generate notification for
     * @return Notification message
     */
    public String getNotificationMessage(Object obj) {
        // TODO: Implement switch with guarded patterns
        return null;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 3: Guarded Patterns - Conditional Pattern Matching
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 3.1: Number Range Classification
     *
     * Classify integers into categories using guarded patterns:
     * - Integer > 100 -> "Large Number"
     * - Integer > 50 -> "Medium Number"
     * - Integer > 0 -> "Small Number"
     * - Integer == 0 -> "Zero"
     * - Integer < 0 -> "Negative Number"
     * - Not an Integer -> "Not a Number"
     *
     * Use 'when' keyword in switch cases.
     *
     * EXAMPLE:
     * classifyNumber(150) -> "Large Number"
     * classifyNumber(25) -> "Small Number"
     * classifyNumber("text") -> "Not a Number"
     *
     * @param obj Object to classify
     * @return Classification string
     */
    public String classifyNumber(Object obj) {
        return switch (obj) {
            case Integer i when i > 100 -> "Large Number";
            case Integer i when i > 50 -> "Medium Number";
            case Integer i when i > 0 -> "Small Number";
            case Integer i when i == 0 -> "Zero";
            case Integer i when i < 0 -> "Negative Number";
            default -> "Not a Number";
        };
    }

    /**
     * TASK 3.2: Course Pricing Strategy
     *
     * Determine pricing strategy based on course attributes:
     * - Free course -> "FREE_TIER"
     * - Price < 30 -> "BUDGET"
     * - Price 30-100 and BEGINNER difficulty -> "STARTER"
     * - Price 30-100 and not BEGINNER -> "STANDARD"
     * - Price > 100 and ADVANCED difficulty -> "PREMIUM"
     * - Price > 100 and not ADVANCED -> "PROFESSIONAL"
     * - Not a course -> "UNKNOWN"
     *
     * EXAMPLE:
     * getPricingStrategy(freeCourse) -> "FREE_TIER"
     * getPricingStrategy(advancedCourse120) -> "PREMIUM"
     *
     * @param obj Object to analyze
     * @return Pricing strategy
     */
    public String getPricingStrategy(Object obj) {
        // TODO: Implement using switch with when clauses
        return null;
    }

    /**
     * TASK 3.3: Student Discount Calculator
     *
     * Calculate discount percentage based on student status:
     * - Student (ADVANCED level, average > 90) -> 50%
     * - Student (ADVANCED level) -> 30%
     * - Student (INTERMEDIATE, average > 80) -> 20%
     * - Student (active) -> 10%
     * - Student (inactive) -> 0%
     * - Not a student -> 0%
     *
     * EXAMPLE:
     * calculateStudentDiscount(advancedStudent95) -> 50
     * calculateStudentDiscount(activeBeginnerStudent) -> 10
     *
     * @param obj Object to check
     * @return Discount percentage
     */
    public int calculateStudentDiscount(Object obj) {
        // TODO: Use pattern matching with guards
        return 0;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 4: Null Safety in Pattern Matching
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 4.1: Safe Object Processing
     *
     * Handle null explicitly in switch:
     * - null -> "NULL"
     * - Empty string -> "EMPTY"
     * - String with length > 10 -> "LONG"
     * - Other string -> "SHORT"
     * - Not a string -> "NOT_STRING"
     *
     * Java 21 allows 'case null' in switch.
     *
     * EXAMPLE:
     * safeStringClassifier(null) -> "NULL"
     * safeStringClassifier("") -> "EMPTY"
     * safeStringClassifier("Hello World!") -> "LONG"
     *
     * @param obj Object to classify
     * @return Classification
     */
    public String safeStringClassifier(Object obj) {
        return switch (obj) {
            case null -> "NULL";
            case String s when s.isEmpty() -> "EMPTY";
            case String s when s.length() > 10 -> "LONG";
            case String s -> "SHORT";
            default -> "NOT_STRING";
        };
    }

    /**
     * TASK 4.2: Null-Safe Field Access
     *
     * Get price value safely from various sources:
     * - Course with non-null price -> return price
     * - Course with null price -> return 0.0
     * - Payment with non-null finalAmount -> return finalAmount
     * - Payment with null finalAmount but has amount -> return amount
     * - null -> return 0.0
     * - Other -> return 0.0
     *
     * EXAMPLE:
     * extractPrice(courseWith99_99Price) -> 99.99
     * extractPrice(null) -> 0.0
     *
     * @param obj Object to extract price from
     * @return Price as double
     */
    public double extractPrice(Object obj) {
        // TODO: Implement with null-safe pattern matching
        return 0.0;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 5: Advanced Pattern Combinations
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 5.1: Multi-Level Type Routing
     *
     * Process list of heterogeneous objects and generate report:
     * Count occurrences of each type:
     * - Students
     * - Courses
     * - Enrollments
     * - Instructors
     * - Others
     *
     * Return: "Students: X, Courses: Y, Enrollments: Z, Instructors: W, Others: V"
     *
     * EXAMPLE:
     * generateTypeReport([3 students, 2 courses, 1 instructor])
     *   -> "Students: 3, Courses: 2, Enrollments: 0, Instructors: 1, Others: 0"
     *
     * @param objects List of objects to analyze
     * @return Report string
     */
    public String generateTypeReport(List<Object> objects) {
        // TODO: Use pattern matching to count each type
        return null;
    }

    /**
     * TASK 5.2: Entity Status Router
     *
     * Route entities to appropriate processing queues based on status:
     * - Active students -> "STUDENT_ACTIVE_QUEUE"
     * - Inactive students -> "STUDENT_REACTIVATION_QUEUE"
     * - Published courses -> "COURSE_LIVE_QUEUE"
     * - Unpublished courses -> "COURSE_REVIEW_QUEUE"
     * - Completed enrollments -> "ENROLLMENT_CERTIFICATE_QUEUE"
     * - Active enrollments -> "ENROLLMENT_PROGRESS_QUEUE"
     * - Other enrollment status -> "ENROLLMENT_SUPPORT_QUEUE"
     * - Other -> "GENERAL_QUEUE"
     *
     * EXAMPLE:
     * routeToQueue(activeStudent) -> "STUDENT_ACTIVE_QUEUE"
     * routeToQueue(publishedCourse) -> "COURSE_LIVE_QUEUE"
     *
     * @param entity Entity to route
     * @return Queue name
     */
    public String routeToQueue(Object entity) {
        // TODO: Implement routing logic with pattern matching
        return null;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // MAIN METHOD - Test Your Solutions
    // ═══════════════════════════════════════════════════════════════════════════

    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("  Pattern Matching Challenge - Modern Java Control Flow");
        System.out.println("═══════════════════════════════════════════════════════════════\n");

        PatternMatchingChallenge challenge = new PatternMatchingChallenge();

        // Test PART 1: instanceof pattern matching
        System.out.println("PART 1: instanceof Pattern Matching");
        System.out.println("─────────────────────────────────────");

        System.out.println("String length: " +
            challenge.getLengthOrCount("Hello World"));

        Course testCourse = challenge.courses.get(0);
        System.out.println("Course enrollment count: " +
            challenge.getLengthOrCount(testCourse));

        Student testStudent = challenge.students.get(0);
        System.out.println("Student enrollment count: " +
            challenge.countEnrollments(testStudent));

        // Test PART 2: Switch pattern matching
        System.out.println("\nPART 2: Switch Pattern Matching");
        System.out.println("─────────────────────────────────────");

        System.out.println(challenge.describeObject("Pattern Matching"));
        System.out.println(challenge.describeObject(42));
        System.out.println(challenge.describeObject(testStudent));
        System.out.println(challenge.describeObject(testCourse));
        System.out.println(challenge.describeObject(null));

        // Test PART 3: Guarded patterns
        System.out.println("\nPART 3: Guarded Patterns");
        System.out.println("─────────────────────────────────────");

        System.out.println("Classify 150: " + challenge.classifyNumber(150));
        System.out.println("Classify 25: " + challenge.classifyNumber(25));
        System.out.println("Classify -5: " + challenge.classifyNumber(-5));
        System.out.println("Classify 'text': " + challenge.classifyNumber("text"));

        // Test PART 4: Null safety
        System.out.println("\nPART 4: Null Safety");
        System.out.println("─────────────────────────────────────");

        System.out.println("Null: " + challenge.safeStringClassifier(null));
        System.out.println("Empty: " + challenge.safeStringClassifier(""));
        System.out.println("Long string: " + challenge.safeStringClassifier("Hello World!!!"));
        System.out.println("Short string: " + challenge.safeStringClassifier("Hi"));

        System.out.println("\n═══════════════════════════════════════════════════════════════");
    }
}
