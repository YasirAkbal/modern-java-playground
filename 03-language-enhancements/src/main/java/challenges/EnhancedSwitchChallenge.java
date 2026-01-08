package challenges;

import data.SampleDataGenerator;
import model.*;

import java.math.BigDecimal;
import java.util.*;

/**
 * ═══════════════════════════════════════════════════════════════════════════════
 * CHALLENGE 02: Switch Expressions - Modern Conditional Logic (Java 14)
 * ═══════════════════════════════════════════════════════════════════════════════
 *
 * SCENARIO:
 * You are modernizing the Business Rules Engine for the EduMaster platform.
 * Traditional switch statements with verbose case/break patterns make code
 * hard to read and maintain. Switch expressions provide a cleaner, more functional
 * approach to conditional logic.
 *
 * FOCUS:
 * - Arrow syntax (->) for concise case branches
 * - Switch as an expression (returns values directly)
 * - Multiple case labels (case A, B -> ...)
 * - Yield keyword for complex blocks
 * - Exhaustiveness checking by compiler
 * - Pattern matching in switch (preview/standard in later versions)
 *
 * ═══════════════════════════════════════════════════════════════════════════════
 */
public class EnhancedSwitchChallenge {

    private final List<Student> students;
    private final List<Course> courses;
    private final List<Enrollment> enrollments;

    public EnhancedSwitchChallenge() {
        this.students = SampleDataGenerator.generateStudents(50);
        List<Instructor> instructors = SampleDataGenerator.generateInstructors(10);
        this.courses = SampleDataGenerator.generateCourses(30, instructors);
        this.enrollments = SampleDataGenerator.generateEnrollments(students, courses, 150);
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 1: Basic Switch Expressions with Arrow Syntax
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 1.1: Student Badge Color
     *
     * Assign badge colors based on student level using switch expression.
     * This replaces the old verbose switch statement with clean arrow syntax.
     *
     * EXAMPLE INPUT: StudentLevel.ADVANCED
     * EXAMPLE OUTPUT: "Gold"
     *
     * Mapping:
     * - BEGINNER -> "Blue"
     * - INTERMEDIATE -> "Green"
     * - ADVANCED -> "Gold"
     *
     * @param level Student level
     * @return Badge color
     */
    public String getBadgeColor(StudentLevel level) {
        return null;
    }

    /**
     * TASK 1.2: Course DifficultyLevel Points
     *
     * Calculate credit points based on course difficulty.
     *
     * EXAMPLE INPUT: DifficultyLevel.ADVANCED
     * EXAMPLE OUTPUT: 5
     *
     * Mapping:
     * - BEGINNER, ELEMENTARY -> 2 points
     * - INTERMEDIATE -> 3 points
     * - ADVANCED, EXPERT -> 5 points
     *
     * @param difficulty Course difficulty
     * @return Credit points
     */
    public int getDifficultyLevelPoints(DifficultyLevel difficulty) {
        return 0;
    }

    /**
     * TASK 1.3: Enrollment Status Message
     *
     * Generate user-friendly message based on enrollment status.
     *
     * EXAMPLE INPUT: EnrollmentStatus.COMPLETED
     * EXAMPLE OUTPUT: "Congratulations! Course completed."
     *
     * @param status Enrollment status
     * @return Status message
     */
    public String getStatusMessage(EnrollmentStatus status) {
        // TODO: Use switch expression to map each status to a message
        // ENROLLED -> "You are currently enrolled"
        // IN_PROGRESS -> "Keep up the good work!"
        // COMPLETED -> "Congratulations! Course completed."
        // DROPPED -> "Sorry to see you go"
        return null;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 2: Multiple Case Labels
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 2.1: Day Type Classification
     *
     * Classify day numbers into weekday/weekend using multiple case labels.
     *
     * EXAMPLE INPUT: 1
     * EXAMPLE OUTPUT: "Weekday"
     *
     * EXAMPLE INPUT: 7
     * EXAMPLE OUTPUT: "Weekend"
     *
     * @param day Day number (1-7)
     * @return "Weekday" or "Weekend"
     * @throws IllegalArgumentException if day is not in 1-7 range
     */
    public String getDayType(int day) {
        return null;
    }

    /**
     * TASK 2.2: Category Group
     *
     * Group categories into broader classifications.
     *
     * EXAMPLE INPUT: Category.IT
     * EXAMPLE OUTPUT: "Technology"
     *
     * Mapping:
     * - IT, DATA_SCIENCE -> "Technology"
     * - BUSINESS, MARKETING -> "Commerce"
     * - DESIGN, ARTS -> "Creative"
     * - LANGUAGE -> "Communication"
     *
     * @param category Course category
     * @return Category group
     */
    public String getCategoryGroup(Category category) {
        // TODO: Use multiple case labels to group categories
        return null;
    }

    /**
     * TASK 2.3: Score Grade
     *
     * Convert numeric score ranges to letter grades.
     *
     * EXAMPLE INPUT: 92.5
     * EXAMPLE OUTPUT: "A"
     *
     * Mapping:
     * - 90-100 -> "A"
     * - 80-89 -> "B"
     * - 70-79 -> "C"
     * - 60-69 -> "D"
     * - Below 60 -> "F"
     *
     * @param score Numeric score
     * @return Letter grade
     */
    public String getLetterGrade(double score) {
        // TODO: Use switch on score/10 with multiple labels
        // Hint: switch ((int) score / 10) { case 10, 9 -> "A"; ... }
        return null;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 3: Yield Keyword for Complex Logic
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 3.1: Category Bonus Points
     *
     * Calculate bonus points with complex logic per category.
     * Use yield for multi-statement blocks.
     *
     * EXAMPLE INPUT: Category.IT
     * EXAMPLE OUTPUT: 100 (after logging)
     *
     * Logic:
     * - IT: Log "High demand category", yield 100
     * - DATA_SCIENCE: Log "Trending category", yield 120
     * - BUSINESS: Log "Popular category", yield 80
     * - Others: yield 50
     *
     * @param category Course category
     * @return Bonus points
     */
    public int calculateCategoryBonus(Category category) {
        return 0;
    }

    /**
     * TASK 3.2: Dynamic Discount Calculator
     *
     * Calculate discount percentage based on student level with validation.
     * Use yield for complex discount logic.
     *
     * EXAMPLE INPUT: StudentLevel.ADVANCED, hasSubscription=true
     * EXAMPLE OUTPUT: 0.30 (30% discount)
     *
     * Logic:
     * - ADVANCED: Check subscription, yield 0.30 or 0.20
     * - INTERMEDIATE: Check subscription, yield 0.20 or 0.10
     * - BEGINNER: yield 0.10
     *
     * @param level Student level
     * @param hasSubscription Whether student has premium subscription
     * @return Discount percentage (0.0 to 1.0)
     */
    public double calculateDiscount(StudentLevel level, boolean hasSubscription) {
        // TODO: Implement with yield blocks checking hasSubscription
        return 0.0;
    }

    /**
     * TASK 3.3: Enrollment Validation
     *
     * Validate enrollment eligibility with complex business rules.
     *
     * EXAMPLE INPUT: BEGINNER student, ADVANCED course
     * EXAMPLE OUTPUT: "Rejected: Course too advanced for your level"
     *
     * Rules:
     * - BEGINNER students can only enroll in BEGINNER or ELEMENTARY courses
     * - INTERMEDIATE students can enroll up to INTERMEDIATE courses
     * - ADVANCED students can enroll in any difficulty
     *
     * @param studentLevel Student level
     * @param courseDifficultyLevel Course difficulty
     * @return Validation message
     */
    public String validateEnrollment(StudentLevel studentLevel, DifficultyLevel courseDifficultyLevel) {
        // TODO: Use switch with yield to implement validation logic
        return null;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 4: Business Logic Applications
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 4.1: Price Adjustment Strategy
     *
     * Apply different pricing strategies based on category and difficulty.
     *
     * EXAMPLE INPUT: Category.IT, DifficultyLevel.ADVANCED, basePrice=100
     * EXAMPLE OUTPUT: BigDecimal(150.00) - 50% markup for high-demand advanced course
     *
     * Strategy:
     * - IT/DATA_SCIENCE + ADVANCED/EXPERT: 50% markup
     * - IT/DATA_SCIENCE + INTERMEDIATE: 30% markup
     * - IT/DATA_SCIENCE + BEGINNER/ELEMENTARY: 10% markup
     * - Other categories: no markup
     *
     * @param category Course category
     * @param difficulty Course difficulty
     * @param basePrice Base price
     * @return Adjusted price
     */
    public BigDecimal calculateAdjustedPrice(Category category, DifficultyLevel difficulty, BigDecimal basePrice) {
        // TODO: Use nested switch or combined logic
        // Hint: You can switch on category, then switch on difficulty inside
        return basePrice;
    }

    /**
     * TASK 4.2: Notification Channel Selection
     *
     * Select appropriate notification channel based on student level and enrollment status.
     *
     * EXAMPLE INPUT: ADVANCED student, COMPLETED status
     * EXAMPLE OUTPUT: "EMAIL,SMS,APP"
     *
     * Logic:
     * - ADVANCED students: All channels (EMAIL,SMS,APP)
     * - INTERMEDIATE + COMPLETED: EMAIL,APP
     * - INTERMEDIATE + others: EMAIL
     * - BEGINNER: EMAIL only
     *
     * @param level Student level
     * @param status Enrollment status
     * @return Comma-separated notification channels
     */
    public String selectNotificationChannels(StudentLevel level, EnrollmentStatus status) {
        // TODO: Implement with switch expressions
        return null;
    }

    /**
     * TASK 4.3: Course Recommendation Score
     *
     * Calculate recommendation score combining multiple factors.
     *
     * EXAMPLE INPUT: Student preferences and course attributes
     * EXAMPLE OUTPUT: Numeric score for ranking
     *
     * Factors:
     * - Match between student level and course difficulty (0-40 points)
     * - Category interest bonus (0-30 points)
     * - Course rating bonus (0-30 points)
     *
     * @param studentLevel Student level
     * @param courseDifficultyLevel Course difficulty
     * @param studentPreferredCategory Student's preferred category
     * @param courseCategory Course category
     * @param courseRating Course rating
     * @return Recommendation score (0-100)
     */
    public int calculateRecommendationScore(StudentLevel studentLevel, DifficultyLevel courseDifficultyLevel,
                                            Category studentPreferredCategory, Category courseCategory,
                                            double courseRating) {
        // TODO: Use multiple switch expressions to calculate total score
        // Hint: Break down into separate switch expressions for each factor
        return 0;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 5: Real-World Scenarios
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 5.1: Access Control
     *
     * Determine if a student can access specific course features based on their status.
     *
     * EXAMPLE INPUT: Status=COMPLETED, feature="CERTIFICATE"
     * EXAMPLE OUTPUT: true
     *
     * Rules:
     * - COMPLETED: All features
     * - IN_PROGRESS: MATERIALS, VIDEOS, QUIZZES
     * - ENROLLED: PREVIEW only
     * - DROPPED: No access
     *
     * @param status Enrollment status
     * @param feature Feature name
     * @return true if access granted
     */
    public boolean canAccessFeature(EnrollmentStatus status, String feature) {
        // TODO: Use switch expression returning boolean
        return false;
    }

    /**
     * TASK 5.2: Certificate Type
     *
     * Determine certificate type based on score and course difficulty.
     *
     * EXAMPLE INPUT: score=95, difficulty=ADVANCED
     * EXAMPLE OUTPUT: "DISTINCTION"
     *
     * Rules:
     * - ADVANCED/EXPERT course + score >= 90: "DISTINCTION"
     * - ADVANCED/EXPERT course + score >= 70: "MERIT"
     * - INTERMEDIATE course + score >= 85: "DISTINCTION"
     * - INTERMEDIATE course + score >= 70: "MERIT"
     * - BEGINNER/ELEMENTARY course + score >= 90: "MERIT"
     * - Others: "COMPLETION"
     *
     * @param score Final score
     * @param difficulty Course difficulty
     * @return Certificate type
     */
    public String determineCertificateType(double score, DifficultyLevel difficulty) {
        // TODO: Combine switch on difficulty with score checks
        return null;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // TEST HELPERS
    // ═══════════════════════════════════════════════════════════════════════════

    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("    Enhanced Switch Challenge - Java 14 Switch Expressions");
        System.out.println("═══════════════════════════════════════════════════════════════\n");

        EnhancedSwitchChallenge challenge = new EnhancedSwitchChallenge();

        // Test PART 1: Basic Switch Expressions
        System.out.println("--- PART 1: Basic Switch Expressions ---");
        System.out.println("ADVANCED Badge Color: " + challenge.getBadgeColor(StudentLevel.ADVANCED));
        System.out.println("ADVANCED Difficulty Points: " + challenge.getDifficultyLevelPoints(DifficultyLevel.ADVANCED));
        System.out.println();

        // Test PART 2: Multiple Case Labels
        System.out.println("--- PART 2: Multiple Case Labels ---");
        System.out.println("Day 1 Type: " + challenge.getDayType(1));
        System.out.println("Day 7 Type: " + challenge.getDayType(7));
        System.out.println();

        // Test PART 3: Yield Keyword
        System.out.println("--- PART 3: Yield for Complex Logic ---");
        int bonus = challenge.calculateCategoryBonus(Category.SECURITY);
        System.out.println("SECURITY Category Bonus: " + bonus);
        System.out.println();

        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("Complete the remaining TODOs to master Switch Expressions!");
        System.out.println("═══════════════════════════════════════════════════════════════");
    }
}
