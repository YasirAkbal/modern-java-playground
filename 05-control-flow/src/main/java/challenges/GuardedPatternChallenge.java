package challenges;

import data.SampleDataGenerator;
import model.*;

import java.math.BigDecimal;
import java.util.*;

/**
 * ═══════════════════════════════════════════════════════════════════════════════
 * CHALLENGE 02: Guarded Patterns - State-Based Conditional Routing
 * ═══════════════════════════════════════════════════════════════════════════════
 *
 * SCENARIO:
 * You are building a Smart Routing Engine for the EduMaster platform that makes
 * decisions based not just on object types, but on their internal STATE and
 * complex business rules. Traditional type matching isn't enough - you need
 * conditional logic combined with pattern matching.
 *
 * FOCUS:
 * - Guarded patterns with 'when' clauses (Java 21+)
 * - Combining type tests with boolean conditions
 * - Multi-level decision trees using patterns
 * - Complex business rule evaluation
 * - Performance-based routing and filtering
 *
 * ═══════════════════════════════════════════════════════════════════════════════
 */
public class GuardedPatternChallenge {

    private final List<Student> students;
    private final List<Course> courses;
    private final List<Instructor> instructors;
    private final List<Enrollment> enrollments;

    public GuardedPatternChallenge() {
        this.students = SampleDataGenerator.generateStudents(100);
        this.instructors = SampleDataGenerator.generateInstructors(15);
        this.courses = SampleDataGenerator.generateCourses(40, instructors);
        this.enrollments = SampleDataGenerator.generateEnrollments(students, courses, 200);
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 1: Business Logic with Guarded Patterns
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 1.1: Multi-Tier Discount Calculator
     *
     * Calculate discount percentage based on entity type and state:
     * - Student (ADVANCED level) -> 50%
     * - Student (INTERMEDIATE level, average > 80) -> 30%
     * - Student (BEGINNER level, average > 90) -> 20%
     * - Student (any other) -> 10%
     * - Course (price > 200) -> 15%
     * - Course (price > 100) -> 10%
     * - Course (price > 0) -> 5%
     * - Course (free) -> 0%
     * - Instructor (verified, rating > 4.5) -> 25%
     * - Instructor (verified) -> 15%
     * - String "VIP" (case insensitive) -> 100%
     * - Default -> 0%
     *
     * EXAMPLE:
     * calculateDiscount(advancedStudent) -> 50
     * calculateDiscount(courseWith150Price) -> 10
     * calculateDiscount("VIP") -> 100
     *
     * @param obj Object to calculate discount for
     * @return Discount percentage
     */
    public int calculateDiscount(Object obj) {
        return 0;
    }

    /**
     * TASK 1.2: Course Eligibility Checker
     *
     * Determine if a user is eligible to enroll in a course:
     * - Course not published -> "COURSE_NOT_AVAILABLE"
     * - Student (inactive) -> "ACCOUNT_INACTIVE"
     * - Student (BEGINNER) + Course (ADVANCED) -> "INSUFFICIENT_LEVEL"
     * - Student (INTERMEDIATE) + Course (ADVANCED) -> "RECOMMENDED_PREREQUISITES"
     * - Student (already has 10+ enrollments) -> "ENROLLMENT_LIMIT_REACHED"
     * - Student (active) + Course (published) -> "ELIGIBLE"
     * - Default -> "INVALID_REQUEST"
     *
     * EXAMPLE:
     * checkEligibility(beginnerStudent, advancedCourse) -> "INSUFFICIENT_LEVEL"
     * checkEligibility(activeStudent, publishedCourse) -> "ELIGIBLE"
     *
     * @param student Student to check
     * @param course Course to check
     * @return Eligibility status
     */
    public String checkEligibility(Student student, Course course) {
        // TODO: Use nested switch or if with pattern matching
        return null;
    }

    /**
     * TASK 1.3: Performance-Based Grading
     *
     * Assign letter grade based on score with special cases:
     * - Score >= 95 -> "A+"
     * - Score >= 90 -> "A"
     * - Score >= 85 -> "A-"
     * - Score >= 80 -> "B+"
     * - Score >= 75 -> "B"
     * - Score >= 70 -> "B-"
     * - Score >= 65 -> "C+"
     * - Score >= 60 -> "C"
     * - Score >= 50 -> "D"
     * - Score < 50 -> "F"
     *
     * If obj is not a number, return "INVALID"
     *
     * EXAMPLE:
     * assignGrade(92.5) -> "A"
     * assignGrade(78) -> "B+"
     *
     * @param obj Score value (could be Double, Integer, or other)
     * @return Letter grade
     */
    public String assignGrade(Object obj) {
        return null;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 2: Null Handling and Edge Cases
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 2.1: Safe Integer Processing
     *
     * Handle null and process integers safely:
     * - null -> -1
     * - Integer (even) -> value * 2
     * - Integer (odd) -> value * 3
     *
     * Java 21 allows 'case null' in switch.
     *
     * EXAMPLE:
     * processInteger(null) -> -1
     * processInteger(4) -> 8
     * processInteger(5) -> 15
     *
     * @param input Integer value (may be null)
     * @return Processed value
     */
    public int processInteger(Integer input) {
        return 0;
    }

    /**
     * TASK 2.2: Optional Score Handler
     *
     * Handle student scores that might be null:
     * - Student with score >= 80 -> "EXCELLENT"
     * - Student with score >= 60 -> "GOOD"
     * - Student with score < 60 -> "NEEDS_IMPROVEMENT"
     * - Student with null score -> "NOT_GRADED"
     * - Not a student -> "INVALID"
     *
     * EXAMPLE:
     * handleScore(studentWith85) -> "EXCELLENT"
     * handleScore(studentWithNullScore) -> "NOT_GRADED"
     *
     * @param obj Object to check
     * @return Score status
     */
    public String handleScore(Object obj) {
        // TODO: Implement with null-safe guards
        return null;
    }

    /**
     * TASK 2.3: Collection Size Router
     *
     * Route based on collection sizes:
     * - null -> "NULL_COLLECTION"
     * - Empty list -> "EMPTY"
     * - List with 1 element -> "SINGLE"
     * - List with 2-5 elements -> "SMALL"
     * - List with 6-20 elements -> "MEDIUM"
     * - List with > 20 elements -> "LARGE"
     * - Not a list -> "NOT_A_LIST"
     *
     * EXAMPLE:
     * routeBySize(listWith3Items) -> "SMALL"
     * routeBySize(null) -> "NULL_COLLECTION"
     *
     * @param obj Object to check
     * @return Size category
     */
    public String routeBySize(Object obj) {
        // TODO: Use pattern matching with guards for List
        return null;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 3: Complex State-Based Routing
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 3.1: Enrollment Status Evaluator
     *
     * Evaluate enrollment status and provide recommendations:
     * - COMPLETED (score >= 80) -> "EXCELLENT_COMPLETION"
     * - COMPLETED (score >= 60) -> "GOOD_COMPLETION"
     * - COMPLETED (score < 60) -> "MINIMAL_COMPLETION"
     * - ACTIVE (progress >= 75) -> "ALMOST_DONE"
     * - ACTIVE (progress >= 50) -> "HALFWAY_THERE"
     * - ACTIVE (progress >= 25) -> "GOOD_START"
     * - ACTIVE (progress < 25) -> "JUST_STARTED"
     * - DROPPED -> "DROPPED_OUT"
     * - SUSPENDED -> "ACCOUNT_SUSPENDED"
     * - Not an enrollment -> "INVALID"
     *
     * EXAMPLE:
     * evaluateEnrollment(completedWith85Score) -> "EXCELLENT_COMPLETION"
     * evaluateEnrollment(active80Progress) -> "ALMOST_DONE"
     *
     * @param obj Object to evaluate
     * @return Status evaluation
     */
    public String evaluateEnrollment(Object obj) {
        // TODO: Use EnrollmentStatus with guards
        return null;
    }

    /**
     * TASK 3.2: Instructor Rating Classifier
     *
     * Classify instructors based on multiple criteria:
     * - Verified, rating > 4.5, students > 1000 -> "ELITE"
     * - Verified, rating > 4.0, students > 500 -> "EXPERT"
     * - Verified, rating > 3.5 -> "PROFESSIONAL"
     * - Verified -> "CERTIFIED"
     * - Not verified, rating > 4.0 -> "PROMISING"
     * - Not verified -> "BEGINNER"
     * - Not an instructor -> "INVALID"
     *
     * EXAMPLE:
     * classifyInstructor(verifiedWith4_8Rating1500Students) -> "ELITE"
     * classifyInstructor(unverifiedWith4_2Rating) -> "PROMISING"
     *
     * @param obj Object to classify
     * @return Instructor classification
     */
    public String classifyInstructor(Object obj) {
        return null;
    }

    /**
     * TASK 3.3: Course Recommendation Engine
     *
     * Recommend action based on course state:
     * - Published, rating > 4.5, enrollments > 1000 -> "FEATURED"
     * - Published, rating > 4.0, enrollments > 500 -> "TRENDING"
     * - Published, rating > 3.5, enrollments > 100 -> "POPULAR"
     * - Published, enrollments > 50 -> "ACTIVE"
     * - Published, free -> "FREE_LEARN"
     * - Published -> "AVAILABLE"
     * - Not published, rating > 4.0 -> "READY_TO_PUBLISH"
     * - Not published -> "IN_DEVELOPMENT"
     * - Not a course -> "INVALID"
     *
     * EXAMPLE:
     * recommendCourse(featuredCourse) -> "FEATURED"
     * recommendCourse(unpublishedNewCourse) -> "IN_DEVELOPMENT"
     *
     * @param obj Object to check
     * @return Recommendation
     */
    public String recommendCourse(Object obj) {
        // TODO: Implement with complex guards
        return null;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 4: Range-Based Pattern Matching
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 4.1: Price Range Categorizer
     *
     * Categorize courses by price range:
     * - Free -> "FREE"
     * - Under $20 -> "BUDGET"
     * - $20-$50 -> "ECONOMY"
     * - $50-$100 -> "STANDARD"
     * - $100-$200 -> "PREMIUM"
     * - Over $200 -> "LUXURY"
     * - Not a course -> "INVALID"
     *
     * EXAMPLE:
     * categorizePrice(courseWith75Price) -> "STANDARD"
     * categorizePrice(freeCourse) -> "FREE"
     *
     * @param obj Object to categorize
     * @return Price category
     */
    public String categorizePrice(Object obj) {
        return null;
    }

    /**
     * TASK 4.2: Duration-Based Course Filter
     *
     * Categorize courses by duration:
     * - null duration -> "UNKNOWN"
     * - Under 10 hours -> "SHORT"
     * - 10-30 hours -> "MEDIUM"
     * - 30-60 hours -> "LONG"
     * - Over 60 hours -> "COMPREHENSIVE"
     * - Not a course -> "INVALID"
     *
     * EXAMPLE:
     * categorizeDuration(course25Hours) -> "MEDIUM"
     * categorizeDuration(course80Hours) -> "COMPREHENSIVE"
     *
     * @param obj Object to categorize
     * @return Duration category
     */
    public String categorizeDuration(Object obj) {
        // TODO: Implement with guards on durationInHours
        return null;
    }

    /**
     * TASK 4.3: Student Progress Tracker
     *
     * Track student overall progress:
     * - No enrollments -> "NEW_STUDENT"
     * - 1-2 enrollments, any completed -> "ACTIVE_LEARNER"
     * - 3-5 enrollments, > 50% completed -> "COMMITTED_LEARNER"
     * - 6-10 enrollments, > 70% completed -> "DEDICATED_LEARNER"
     * - > 10 enrollments, > 80% completed -> "SUPER_LEARNER"
     * - Has enrollments but < 20% completed -> "NEEDS_MOTIVATION"
     * - Other -> "CASUAL_LEARNER"
     * - Not a student -> "INVALID"
     *
     * EXAMPLE:
     * trackProgress(studentWith8EnrollmentsMostCompleted) -> "DEDICATED_LEARNER"
     * trackProgress(studentWith12EnrollmentsHighCompletion) -> "SUPER_LEARNER"
     *
     * @param obj Object to track
     * @return Progress category
     */
    public String trackProgress(Object obj) {
        // TODO: Complex calculation with enrollment data
        return null;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 5: Multi-Criteria Decision Making
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 5.1: Scholarship Eligibility
     *
     * Determine scholarship eligibility based on multiple factors:
     * - Student (ADVANCED, average > 90, active) -> "FULL_SCHOLARSHIP"
     * - Student (ADVANCED, average > 80, active) -> "PARTIAL_SCHOLARSHIP_75"
     * - Student (INTERMEDIATE, average > 90, active) -> "PARTIAL_SCHOLARSHIP_50"
     * - Student (any level, average > 85, active, > 5 enrollments) -> "MERIT_SCHOLARSHIP"
     * - Student (active, > 10 enrollments) -> "LOYALTY_SCHOLARSHIP"
     * - Student (active) -> "ELIGIBLE_FOR_ASSISTANCE"
     * - Student (inactive) -> "NOT_ELIGIBLE"
     * - Not a student -> "INVALID"
     *
     * EXAMPLE:
     * checkScholarship(advancedStudent95Active) -> "FULL_SCHOLARSHIP"
     * checkScholarship(activeStudentWith12Enrollments) -> "LOYALTY_SCHOLARSHIP"
     *
     * @param obj Object to check
     * @return Scholarship status
     */
    public String checkScholarship(Object obj) {
        // TODO: Complex multi-criteria evaluation
        return null;
    }

    /**
     * TASK 5.2: Priority Queue Assignment
     *
     * Assign entities to priority queues based on urgency:
     * - Enrollment (ACTIVE, progress < 25, enrolled > 30 days ago) -> "P1_AT_RISK"
     * - Enrollment (ACTIVE, progress 25-50) -> "P2_NEEDS_ENCOURAGEMENT"
     * - Enrollment (ACTIVE, progress > 75) -> "P3_NEAR_COMPLETION"
     * - Student (inactive, has enrollments) -> "P1_REACTIVATION"
     * - Student (active, no enrollments) -> "P2_NEW_USER"
     * - Course (published, rating < 3.0) -> "P1_QUALITY_REVIEW"
     * - Course (not published, created > 60 days ago) -> "P2_PENDING_REVIEW"
     * - Default -> "P3_GENERAL"
     *
     * EXAMPLE:
     * assignPriority(strugglingEnrollment) -> "P1_AT_RISK"
     * assignPriority(lowRatedCourse) -> "P1_QUALITY_REVIEW"
     *
     * @param obj Object to prioritize
     * @return Priority queue
     */
    public String assignPriority(Object obj) {
        // TODO: Complex prioritization logic
        return null;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // MAIN METHOD - Test Your Solutions
    // ═══════════════════════════════════════════════════════════════════════════

    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("  Guarded Pattern Challenge - State-Based Routing");
        System.out.println("═══════════════════════════════════════════════════════════════\n");

        GuardedPatternChallenge challenge = new GuardedPatternChallenge();

        // Test PART 1: Business logic with guarded patterns
        System.out.println("PART 1: Business Logic with Guarded Patterns");
        System.out.println("─────────────────────────────────────────────");

        Student advancedStudent = challenge.students.stream()
            .filter(s -> s.getLevel() == StudentLevel.ADVANCED)
            .findFirst()
            .orElse(challenge.students.get(0));

        Course expensiveCourse = challenge.courses.stream()
            .filter(c -> c.getPrice() != null && c.getPrice().compareTo(BigDecimal.valueOf(150)) > 0)
            .findFirst()
            .orElse(challenge.courses.get(0));

        System.out.println("Advanced student discount: " +
            challenge.calculateDiscount(advancedStudent) + "%");
        System.out.println("Expensive course discount: " +
            challenge.calculateDiscount(expensiveCourse) + "%");
        System.out.println("VIP discount: " +
            challenge.calculateDiscount("VIP") + "%");

        // Test PART 2: Null handling
        System.out.println("\nPART 2: Null Handling and Edge Cases");
        System.out.println("─────────────────────────────────────────────");

        System.out.println("Process null: " + challenge.processInteger(null));
        System.out.println("Process 4 (even): " + challenge.processInteger(4));
        System.out.println("Process 5 (odd): " + challenge.processInteger(5));

        // Test PART 3: Complex state-based routing
        System.out.println("\nPART 3: Complex State-Based Routing");
        System.out.println("─────────────────────────────────────────────");

        Instructor topInstructor = challenge.instructors.stream()
            .filter(i -> i.isVerified() && i.getRating() > 4.0)
            .findFirst()
            .orElse(challenge.instructors.get(0));

        System.out.println("Instructor classification: " +
            challenge.classifyInstructor(topInstructor));

        // Test PART 4: Range-based patterns
        System.out.println("\nPART 4: Range-Based Pattern Matching");
        System.out.println("─────────────────────────────────────────────");

        Course freeCourse = challenge.courses.stream()
            .filter(Course::isFree)
            .findFirst()
            .orElse(challenge.courses.get(0));

        System.out.println("Free course category: " +
            challenge.categorizePrice(freeCourse));
        System.out.println("Expensive course category: " +
            challenge.categorizePrice(expensiveCourse));

        // Test grading
        System.out.println("\nGrading Examples:");
        System.out.println("─────────────────────────────────────────────");
        System.out.println("Score 92.5: " + challenge.assignGrade(92.5));
        System.out.println("Score 78: " + challenge.assignGrade(78));
        System.out.println("Score 55: " + challenge.assignGrade(55));
        System.out.println("Invalid input: " + challenge.assignGrade("ABC"));

        System.out.println("\n═══════════════════════════════════════════════════════════════");
    }
}
