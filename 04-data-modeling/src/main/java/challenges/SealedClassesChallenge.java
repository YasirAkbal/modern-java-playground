package challenges;

import data.SampleDataGenerator;
import model.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.*;

/**
 * ═══════════════════════════════════════════════════════════════════════════════
 * CHALLENGE 03: Sealed Classes - Restricted Type Hierarchies
 * ═══════════════════════════════════════════════════════════════════════════════
 *
 * SCENARIO:
 * You are building a type-safe Payment Processing and Enrollment Management System.
 * Sealed classes restrict which classes can extend/implement them, providing
 * exhaustive pattern matching and preventing unauthorized extensions.
 *
 * FOCUS:
 * - Sealed interfaces and classes
 * - Permitted subclasses
 * - Exhaustive pattern matching with switch expressions
 * - Combining sealed classes with records
 * - Type-safe state machines
 *
 * ═══════════════════════════════════════════════════════════════════════════════
 */
public class SealedClassesChallenge {

    private final List<Student> students;
    private final List<Course> courses;
    private final List<Instructor> instructors;
    private final List<Enrollment> enrollments;
    private final List<Payment> payments;

    public SealedClassesChallenge() {
        this.instructors = SampleDataGenerator.generateInstructors(10);
        this.courses = SampleDataGenerator.generateCourses(30, instructors);
        this.students = SampleDataGenerator.generateStudents(50);
        this.enrollments = SampleDataGenerator.generateEnrollments(students, courses, 100);
        this.payments = SampleDataGenerator.generatePayments(students, courses, 80);
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 1: Basic Sealed Hierarchy - Payment Results
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * Sealed interface for payment results.
     * Only Success and Failure are permitted implementations.
     *
     * EXAMPLE:
     * <pre>
     * PaymentResult result = new Success("TXN-12345", BigDecimal.valueOf(99.99));
     * String message = formatPaymentResult(result);
     * </pre>
     */
    public sealed interface PaymentResult permits Success, Failure {
        String getTransactionId();
    }

    /**
     * Successful payment - implemented as a record (implicitly final).
     */
    public record Success(String transactionId, BigDecimal amount) implements PaymentResult {
        @Override
        public String getTransactionId() {
            return transactionId;
        }
    }

    /**
     * Failed payment - implemented as a final class with details.
     */
    public final class Failure implements PaymentResult {
        private final String transactionId;
        private final String reason;
        private final LocalDateTime failedAt;

        public Failure(String transactionId, String reason) {
            this.transactionId = transactionId;
            this.reason = reason;
            this.failedAt = LocalDateTime.now();
        }

        @Override
        public String getTransactionId() {
            return transactionId;
        }

        public String getReason() {
            return reason;
        }

        public LocalDateTime getFailedAt() {
            return failedAt;
        }
    }

    /**
     * TASK 1.1: Format Payment Result
     *
     * Use pattern matching to format payment results.
     *
     * @param result The payment result
     * @return Formatted message
     *
     * EXAMPLE:
     * <pre>
     * PaymentResult success = new Success("TXN-1", BigDecimal.valueOf(99.99));
     * String msg = formatPaymentResult(success);
     * // Returns: "Payment successful: TXN-1 ($99.99)"
     * </pre>
     */
    public String formatPaymentResult(PaymentResult result) {
        return null;
    }

    /**
     * TASK 1.2: Create Payment Results from Domain
     *
     * Convert Payment domain objects to PaymentResult sealed types.
     *
     * @return List of payment results
     */
    public List<PaymentResult> getPaymentResults() {
        return null;
    }

    /**
     * TASK 1.3: Count Successful Payments
     *
     * Count only successful payment results using pattern matching.
     *
     * @return Count of successful payments
     */
    public long countSuccessfulPayments() {
        // TODO: Use getPaymentResults() and filter/count Success instances
        return 0;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 2: Sealed Class Hierarchy - Enrollment States
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * Sealed abstract class for enrollment states.
     *
     * EXAMPLE:
     * <pre>
     * EnrollmentState state = new Active("ENR-1", 75);
     * String status = getEnrollmentStatus(state);
     * </pre>
     */
    public sealed abstract class EnrollmentState permits Active, Completed, Dropped, Expired {
        protected final String enrollmentId;
        protected final LocalDateTime timestamp;

        protected EnrollmentState(String enrollmentId) {
            this.enrollmentId = enrollmentId;
            this.timestamp = LocalDateTime.now();
        }

        public String getEnrollmentId() {
            return enrollmentId;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }
    }

    /**
     * Active enrollment with progress tracking.
     */
    public final class Active extends EnrollmentState {
        private final int progressPercentage;

        public Active(String enrollmentId, int progressPercentage) {
            super(enrollmentId);
            this.progressPercentage = progressPercentage;
        }

        public int getProgressPercentage() {
            return progressPercentage;
        }
    }

    /**
     * Completed enrollment with final score.
     */
    public final class Completed extends EnrollmentState {
        private final double finalScore;
        private final boolean certified;

        public Completed(String enrollmentId, double finalScore) {
            super(enrollmentId);
            this.finalScore = finalScore;
            this.certified = finalScore >= 70.0;
        }

        public double getFinalScore() {
            return finalScore;
        }

        public boolean isCertified() {
            return certified;
        }
    }

    /**
     * Dropped enrollment with reason.
     */
    public final class Dropped extends EnrollmentState {
        private final String reason;

        public Dropped(String enrollmentId, String reason) {
            super(enrollmentId);
            this.reason = reason;
        }

        public String getReason() {
            return reason;
        }
    }

    /**
     * Expired enrollment (no activity for extended period).
     */
    public final class Expired extends EnrollmentState {
        private final LocalDateTime expiryDate;

        public Expired(String enrollmentId, LocalDateTime expiryDate) {
            super(enrollmentId);
            this.expiryDate = expiryDate;
        }

        public LocalDateTime getExpiryDate() {
            return expiryDate;
        }
    }

    /**
     * TASK 2.1: Get Enrollment Status Description
     *
     * Use exhaustive pattern matching to describe enrollment state.
     *
     * @param state The enrollment state
     * @return Status description
     *
     * EXAMPLE:
     * <pre>
     * EnrollmentState active = new Active("ENR-1", 75);
     * String desc = getEnrollmentStatus(active);
     * // Returns: "In Progress: 75% complete"
     * </pre>
     */
    public String getEnrollmentStatus(EnrollmentState state) {
        return null;
    }

    /**
     * TASK 2.2: Create Enrollment States from Domain
     *
     * Convert Enrollment domain objects to EnrollmentState sealed types.
     *
     * @return List of enrollment states
     */
    public List<EnrollmentState> getEnrollmentStates() {
        return null;
    }

    /**
     * TASK 2.3: Filter Active Enrollments
     *
     * Get only active enrollment states with progress >= 50%.
     *
     * @return List of active enrollments with good progress
     */
    public List<Active> getActiveEnrollmentsWithProgress() {
        // TODO: Filter getEnrollmentStates() for Active instances with progress >= 50%
        return null;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 3: Nested Sealed Types - Course Access Levels
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * Sealed interface for course access permissions.
     */
    public sealed interface AccessLevel permits Free, Premium, Enterprise {}

    /**
     * Free access - limited features.
     */
    public record Free(List<String> allowedLessonIds) implements AccessLevel {}

    /**
     * Premium access - all course features.
     */
    public record Premium(LocalDateTime expiresAt) implements AccessLevel {}

    /**
     * Enterprise access - multiple users.
     */
    public record Enterprise(int maxUsers, String organizationId) implements AccessLevel {}

    /**
     * TASK 3.1: Get Access Description
     *
     * Describe access level using pattern matching.
     *
     * @param access The access level
     * @return Description string
     */
    public String describeAccess(AccessLevel access) {
        return null;
    }

    /**
     * TASK 3.2: Determine Access for Students
     *
     * Create access levels based on student level and enrollment status.
     *
     * @return Map of student IDs to access levels
     */
    public Map<String, AccessLevel> getStudentAccessLevels() {
        // TODO: Create access levels:
        // - BEGINNER students get Free access (first 2 lesson IDs from a course)
        // - INTERMEDIATE/ADVANCED get Premium (expires in 1 year)
        // - Students with 5+ enrollments get Enterprise access
        return null;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 4: Complex Sealed Hierarchy - Notification Events
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * Sealed interface for notification events.
     */
    public sealed interface NotificationEvent {
        String recipientId();
        LocalDateTime timestamp();
    }

    /**
     * Course enrollment notification.
     */
    public record EnrollmentNotification(
        String recipientId,
        String courseTitle,
        LocalDateTime timestamp
    ) implements NotificationEvent {}

    /**
     * Payment confirmation notification.
     */
    public record PaymentNotification(
        String recipientId,
        BigDecimal amount,
        String transactionId,
        LocalDateTime timestamp
    ) implements NotificationEvent {}

    /**
     * Course completion notification.
     */
    public record CompletionNotification(
        String recipientId,
        String courseTitle,
        double score,
        boolean certified,
        LocalDateTime timestamp
    ) implements NotificationEvent {}

    /**
     * TASK 4.1: Format Notification Message
     *
     * Create notification message using exhaustive pattern matching.
     *
     * @param event The notification event
     * @return Formatted notification message
     *
     * EXAMPLE:
     * <pre>
     * NotificationEvent event = new EnrollmentNotification(
     *     "STU-1", "Java Masterclass", LocalDateTime.now()
     * );
     * String msg = formatNotification(event);
     * // Returns: "Welcome to Java Masterclass!"
     * </pre>
     */
    public String formatNotification(NotificationEvent event) {
        return null;
    }

    /**
     * TASK 4.2: Generate Notifications
     *
     * Create notification events from domain data.
     *
     * @return List of notification events
     */
    public List<NotificationEvent> generateNotifications() {
        return null;
    }

    /**
     * TASK 4.3: Count Notifications by Type
     *
     * Group notifications by their type.
     *
     * @return Map of notification types to counts
     */
    public Map<String, Long> countNotificationsByType() {
        // TODO: Use generateNotifications() and group by class simple name
        return null;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 5: Sealed Types with Guards - Advanced Pattern Matching
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 5.1: Calculate Refund Amount
     *
     * Calculate refund based on payment result and enrollment state.
     * Use pattern matching with guards.
     *
     * @param paymentResult The payment result
     * @param enrollmentState The enrollment state
     * @return Refund amount
     *
     * EXAMPLE:
     * <pre>
     * PaymentResult payment = new Success("TXN-1", BigDecimal.valueOf(100));
     * EnrollmentState enrollment = new Active("ENR-1", 25);
     * BigDecimal refund = calculateRefund(payment, enrollment);
     * // Returns partial refund if dropped early, full if within 7 days, etc.
     * </pre>
     */
    public BigDecimal calculateRefund(PaymentResult paymentResult, EnrollmentState enrollmentState) {
        return null;
    }

    /**
     * TASK 5.2: Get Refund Eligible Enrollments
     *
     * Find enrollments eligible for refund (Active with < 10% progress or Dropped).
     *
     * @return List of enrollment IDs eligible for refund
     */
    public List<String> getRefundEligibleEnrollments() {
        // TODO: Filter getEnrollmentStates() for refund-eligible states
        return null;
    }

    public static void main(String[] args) {
        SealedClassesChallenge challenge = new SealedClassesChallenge();

        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("  SealedClassesChallenge - Restricted Type Hierarchies");
        System.out.println("═══════════════════════════════════════════════════════════════\n");

        // TEST 1: Payment Results
        System.out.println("--- TEST 1: Payment Results (Sealed Interface) ---");
        List<PaymentResult> results = challenge.getPaymentResults();
        System.out.println("Total payment results: " + results.size());
        results.stream()
                .limit(5)
                .forEach(r -> System.out.println("  " + challenge.formatPaymentResult(r)));

        long successCount = results.stream()
                .filter(r -> r instanceof Success)
                .count();
        System.out.println("Successful payments: " + successCount);

        // TEST 2: Enrollment States
        System.out.println("\n--- TEST 2: Enrollment States (Sealed Abstract Class) ---");
        List<EnrollmentState> states = challenge.getEnrollmentStates();
        System.out.println("Total enrollment states: " + states.size());
        states.stream()
                .limit(5)
                .forEach(s -> System.out.println("  " + challenge.getEnrollmentStatus(s)));

        // TEST 3: Access Levels
        System.out.println("\n--- TEST 3: Access Levels (Nested Sealed Types) ---");
        AccessLevel free = new Free(List.of("L1", "L2", "L3"));
        AccessLevel premium = new Premium(LocalDateTime.now().plusYears(1));
        AccessLevel enterprise = new Enterprise(100, "ORG-12345");

        System.out.println("Free: " + challenge.describeAccess(free));
        System.out.println("Premium: " + challenge.describeAccess(premium));
        System.out.println("Enterprise: " + challenge.describeAccess(enterprise));

        // TEST 4: Notification Events
        System.out.println("\n--- TEST 4: Notification Events (Complex Sealed Types) ---");
        List<NotificationEvent> notifications = challenge.generateNotifications();
        System.out.println("Total notifications: " + notifications.size());
        notifications.stream()
                .limit(5)
                .forEach(n -> System.out.println("  " + challenge.formatNotification(n)));

        Map<String, Long> notificationCounts = notifications.stream()
                .collect(Collectors.groupingBy(
                    n -> n.getClass().getSimpleName(),
                    Collectors.counting()
                ));
        System.out.println("Notification counts by type:");
        notificationCounts.forEach((type, count) ->
            System.out.println("  " + type + ": " + count));

        // TEST 5: Pattern Matching with Guards
        System.out.println("\n--- TEST 5: Pattern Matching with Guards (Refund Logic) ---");
        PaymentResult successPayment = new Success("TXN-1", BigDecimal.valueOf(100));
        EnrollmentState activeEarly = challenge.new Active("ENR-1", 5);
        EnrollmentState activeLate = challenge.new Active("ENR-2", 75);
        EnrollmentState dropped = challenge.new Dropped("ENR-3", "Changed mind");
        EnrollmentState completed = challenge.new Completed("ENR-4", 85.0);

        System.out.println("Refund for early active: $" +
            challenge.calculateRefund(successPayment, activeEarly));
        System.out.println("Refund for late active: $" +
            challenge.calculateRefund(successPayment, activeLate));
        System.out.println("Refund for dropped: $" +
            challenge.calculateRefund(successPayment, dropped));
        System.out.println("Refund for completed: $" +
            challenge.calculateRefund(successPayment, completed));

        // TEST 6: Exhaustive Pattern Matching
        System.out.println("\n--- TEST 6: Exhaustive Pattern Matching Benefits ---");
        System.out.println("Sealed types enable exhaustive switch expressions.");
        System.out.println("The compiler ensures all cases are handled.");
        System.out.println("This prevents runtime errors and improves type safety.");

        System.out.println("\n═══════════════════════════════════════════════════════════════");
        System.out.println("  All reference implementations executed successfully!");
        System.out.println("═══════════════════════════════════════════════════════════════");
    }
}
