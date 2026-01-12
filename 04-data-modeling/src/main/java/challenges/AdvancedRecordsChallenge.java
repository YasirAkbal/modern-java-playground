package challenges;

import data.SampleDataGenerator;
import model.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.*;

/**
 * ═══════════════════════════════════════════════════════════════════════════════
 * CHALLENGE 02: Advanced Records - Validation, Composition & Serialization
 * ═══════════════════════════════════════════════════════════════════════════════
 *
 * SCENARIO:
 * You are building advanced DTOs for the EduMaster Microservices Architecture.
 * Records need validation, interface implementation, serialization support,
 * and complex composition patterns for API responses.
 *
 * FOCUS:
 * - Compact constructors with complex validation
 * - Multiple constructors with delegation
 * - Interface implementation
 * - Nested records and collections
 * - Serialization and JSON-friendly DTOs
 *
 * ═══════════════════════════════════════════════════════════════════════════════
 */
public class AdvancedRecordsChallenge {

    private final List<Student> students;
    private final List<Course> courses;
    private final List<Instructor> instructors;
    private final List<Enrollment> enrollments;
    private final List<Payment> payments;

    public AdvancedRecordsChallenge() {
        this.instructors = SampleDataGenerator.generateInstructors(10);
        this.courses = SampleDataGenerator.generateCourses(30, instructors);
        this.students = SampleDataGenerator.generateStudents(50);
        this.enrollments = SampleDataGenerator.generateEnrollments(students, courses, 100);
        this.payments = SampleDataGenerator.generatePayments(students, courses, 80);
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 1: Validation in Compact Constructor
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * Email address with strict validation.
     *
     * EXAMPLE:
     * <pre>
     * EmailAddress valid = new EmailAddress("user@example.com");
     * EmailAddress invalid = new EmailAddress("notanemail"); // Throws exception
     * </pre>
     */
    public record EmailAddress(String value) {
        public EmailAddress {
            if (value == null || value.isBlank()) {
                throw new IllegalArgumentException("Email cannot be null or blank");
            }
            if (!value.contains("@")) {
                throw new IllegalArgumentException("Email must contain @: " + value);
            }
            if (!value.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                throw new IllegalArgumentException("Invalid email format: " + value);
            }
        }
    }

    /**
     * TASK 1.1: Extract Valid Emails
     *
     * Extract all valid student emails as EmailAddress records.
     * Skip any that fail validation.
     *
     * @return List of valid email addresses
     */
    public List<EmailAddress> getValidStudentEmails() {
        return students.stream()
                .map(Student::getEmail)
                .flatMap(email -> {
                    try {
                        return Stream.of(new EmailAddress(email));
                    } catch (IllegalArgumentException e) {
                        return Stream.empty();
                    }
                })
                .toList();
    }

    /**
     * TASK 1.2: Find Students by Domain
     *
     * Get all EmailAddress records ending with a specific domain.
     *
     * @param domain e.g., "@gmail.com"
     * @return List of matching email addresses
     */
    public List<EmailAddress> getEmailsByDomain(String domain) {
        return getValidStudentEmails().stream()
                    .filter(email -> email.value.endsWith("@gmail.com"))
                    .toList();
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 2: Multiple Constructors with Delegation
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * Range with flexible constructors.
     *
     * EXAMPLE:
     * <pre>
     * Range r1 = new Range(0, 100);     // Full constructor
     * Range r2 = new Range(50);          // Starts from 0
     * Range r3 = Range.ofLength(20);     // 0 to 20
     * </pre>
     */
    public record Range(int start, int end) {
        /**
         * Validation in canonical constructor.
         */
        public Range {
            if (start > end) {
                throw new IllegalArgumentException("Start must be <= end: " + start + " > " + end);
            }
        }

        /**
         * Convenience constructor - defaults start to 0.
         */
        public Range(int end) {
            this(0, end);
        }

        /**
         * Static factory method.
         */
        public static Range ofLength(int length) {
            return new Range(0, length);
        }

        /**
         * Calculate range size.
         */
        public int size() {
            return end - start;
        }
    }

    /**
     * TASK 2.1: Create Score Ranges
     *
     * Create Range records for different score categories:
     * - Failing: 0-59
     * - Passing: 60-79
     * - Excellent: 80-100
     *
     * @return List of score ranges
     */
    public List<Range> getScoreRanges() {
        List<Range> ranges = new ArrayList<>();
        ranges.add(new Range(0, 59));    
        ranges.add(new Range(60, 79));   
        ranges.add(new Range(80, 100));  
        return ranges;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 3: Interface Implementation
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * Interface for entities with IDs.
     */
    public interface Identifiable {
        String id();
    }

    /**
     * Interface for entities that can be serialized.
     */
    public interface Transferable extends Serializable {
        // Marker interface
    }

    /**
     * Product record implementing multiple interfaces.
     *
     * EXAMPLE:
     * <pre>
     * Product p = new Product("P-001", "Laptop", BigDecimal.valueOf(999.99));
     * String id = p.id(); // From Identifiable interface
     * </pre>
     */
    public record Product(String id, String name, BigDecimal price)
            implements Identifiable, Transferable {}

    /**
     * TASK 3.1: Convert Courses to Products
     *
     * Create Product records from courses, implementing Identifiable.
     *
     * @return List of course products
     */
    public List<Product> getCoursesAsProducts() {
        return courses.stream()
                .map(c -> new Product(c.getId(), c.getTitle(), c.getPrice()))
                .toList();
    }

    /**
     * TASK 3.2: Find Product by ID
     *
     * Use the Identifiable interface to find a product.
     *
     * @param productId The product ID to search for
     * @return Optional containing the product if found
     */
    public Optional<Product> findProductById(String productId) {
        return getCoursesAsProducts().stream()
                .filter(p -> p.id().equals(productId))
                .findFirst();
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 4: Nested Records and Composition
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * Point in 2D space.
     */
    public record Point(int x, int y) {}

    /**
     * Rectangle defined by two points.
     *
     * EXAMPLE:
     * <pre>
     * Rectangle rect = new Rectangle(new Point(0, 0), new Point(10, 5));
     * int area = rect.area(); // Returns 50
     * </pre>
     */
    public record Rectangle(Point topLeft, Point bottomRight) {
        public int area() {
            int width = bottomRight.x - topLeft.x;
            int height = bottomRight.y - topLeft.y;
            return Math.abs(width * height);
        }

        public int perimeter() {
            int width = Math.abs(bottomRight.x - topLeft.x);
            int height = Math.abs(bottomRight.y - topLeft.y);
            return 2 * (width + height);
        }
    }

    /**
     * TASK 4.1: Create Rectangles from Dimensions
     *
     * Create rectangles starting at origin (0,0) with given dimensions.
     *
     * @param dimensions List of {width, height} pairs
     * @return List of rectangles
     */
    public List<Rectangle> createRectangles(List<int[]> dimensions) {
        return dimensions.stream()
                .map(dim -> new Rectangle(new Point(0, 0), new Point(dim[0], dim[1])))
                .toList();
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 5: Complex Nested DTOs for API Responses
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * Minimal lesson info.
     */
    public record LessonInfo(String id, String title, int orderIndex) {}

    /**
     * Course details with nested lessons.
     */
    public record CourseDetail(
        String id,
        String title,
        BigDecimal price,
        String instructorName,
        List<LessonInfo> lessons,
        int totalLessons,
        int totalDurationMinutes
    ) {}

    /**
     * TASK 5.1: Build Course Details
     *
     * Create detailed course DTOs with nested lesson information.
     *
     * @return List of course details
     *
     * EXAMPLE:
     * <pre>
     * List<CourseDetail> details = getCourseDetails();
     * details.forEach(d ->
     *     System.out.println(d.title() + " has " + d.totalLessons() + " lessons")
     * );
     * </pre>
     */
    public List<CourseDetail> getCourseDetails() {
        return courses.stream()
                .map(c -> {
                    String instructorName = c.getInstructor().getFullName();
                    List<LessonInfo> lessonInfos = c.getLessons().stream()
                            .map(l -> new LessonInfo(l.getId(), l.getTitle(), l.getOrderIndex()))
                            .toList();
                    int totalLessons = lessonInfos.size();
                    int totalDuration = c.getLessons().stream()
                            .mapToInt(l -> (int) l.getDuration().toMinutes())
                            .sum();

                    return new CourseDetail(
                            c.getId(),
                            c.getTitle(),
                            c.getPrice(),
                            instructorName,
                            lessonInfos,
                            totalLessons,
                            totalDuration
                    );
                })
                .toList();
    }

    /**
     * TASK 5.2: Find Long Courses
     *
     * Get course details for courses with more than 10 lessons.
     *
     * @return List of long courses
     */
    public List<CourseDetail> getLongCourses() {
        return getCourseDetails().stream()
                .filter(c -> c.totalLessons > 10)
                .toList();
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 6: Records with Collections - Enrollment Summary
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * Student enrollment summary.
     */
    public record EnrollmentSummary(
        String studentId,
        String studentName,
        int totalEnrollments,
        int completedCourses,
        List<String> activeCourses,
        double averageProgress
    ) {}

    /**
     * TASK 6.1: Generate Enrollment Summaries
     *
     * Create enrollment summaries for students with active enrollments.
     *
     * @return List of enrollment summaries
     */
    public List<EnrollmentSummary> getEnrollmentSummaries() {
        // Group enrollments by student ID
        Map<String, List<Enrollment>> enrollmentsByStudent = enrollments.stream()
                .collect(Collectors.groupingBy(Enrollment::getStudentId));

        // Create summaries for each student
        return enrollmentsByStudent.entrySet().stream()
                .map(entry -> {
                    String studentId = entry.getKey();
                    List<Enrollment> studentEnrollments = entry.getValue();

                    // Find the student
                    Student student = students.stream()
                            .filter(s -> s.getId().equals(studentId))
                            .findFirst()
                            .orElse(null);

                    if (student == null) {
                        return null;
                    }

                    // Calculate statistics
                    int totalEnrollments = studentEnrollments.size();

                    int completedCourses = (int) studentEnrollments.stream()
                            .filter(Enrollment::isCompleted)
                            .count();

                    List<String> activeCourses = studentEnrollments.stream()
                            .filter(e -> e.getStatus() == EnrollmentStatus.ACTIVE)
                            .map(e -> e.getCourse().getTitle())
                            .toList();

                    double averageProgress = studentEnrollments.stream()
                            .mapToInt(Enrollment::getProgressPercentage)
                            .average()
                            .orElse(0.0);

                    return new EnrollmentSummary(
                            studentId,
                            student.getFullName(),
                            totalEnrollments,
                            completedCourses,
                            activeCourses,
                            averageProgress
                    );
                })
                .filter(summary -> summary != null)
                .toList();
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 7: Payment Transaction Records
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * Payment transaction DTO.
     */
    public record PaymentTransaction(
        String transactionId,
        String studentId,
        String courseTitle,
        BigDecimal amount,
        PaymentStatus status,
        LocalDateTime timestamp
    ) implements Transferable {}

    /**
     * TASK 7.1: Get Completed Transactions
     *
     * Create PaymentTransaction records for completed payments.
     *
     * @return List of completed payment transactions
     *
     * EXAMPLE:
     * <pre>
     * List<PaymentTransaction> transactions = getCompletedTransactions();
     * BigDecimal total = transactions.stream()
     *     .map(PaymentTransaction::amount)
     *     .reduce(BigDecimal.ZERO, BigDecimal::add);
     * </pre>
     */
    public List<PaymentTransaction> getCompletedTransactions() {
        return payments.stream()
                .filter(p -> p.getStatus() == PaymentStatus.COMPLETED)
                .map(p -> {
                    // Find the course title
                    String courseTitle = courses.stream()
                            .filter(c -> c.getId().equals(p.getCourseId()))
                            .map(Course::getTitle)
                            .findFirst()
                            .orElse("Unknown Course");

                    return new PaymentTransaction(
                            p.getTransactionId() != null ? p.getTransactionId() : p.getId(),
                            p.getStudentId(),
                            courseTitle,
                            p.getFinalAmount(),
                            p.getStatus(),
                            p.getProcessedAt() != null ? p.getProcessedAt() : p.getCreatedAt()
                    );
                })
                .toList();
    }

    /**
     * TASK 7.2: Calculate Total Revenue
     *
     * Sum all completed transaction amounts.
     *
     * @return Total revenue
     */
    public BigDecimal getTotalRevenue() {
        return getCompletedTransactions().stream()
                .map(PaymentTransaction::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static void main(String[] args) {
        AdvancedRecordsChallenge challenge = new AdvancedRecordsChallenge();

        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("  AdvancedRecordsChallenge - Validation & Composition");
        System.out.println("═══════════════════════════════════════════════════════════════\n");

        // TEST 1: Email Validation
        System.out.println("--- TEST 1: Email Validation ---");
        List<EmailAddress> emails = challenge.getValidStudentEmails();
        System.out.println("Valid emails: " + emails.size());
        emails.stream().limit(3).forEach(e -> System.out.println("  " + e.value()));

        try {
            EmailAddress invalid = new EmailAddress("not-an-email");
        } catch (IllegalArgumentException e) {
            System.out.println("Validation works: " + e.getMessage());
        }

        // TEST 2: Range with Multiple Constructors
        System.out.println("\n--- TEST 2: Range with Multiple Constructors ---");
        Range r1 = new Range(0, 100);
        Range r2 = new Range(50);
        Range r3 = Range.ofLength(20);
        System.out.println("Range(0, 100): " + r1 + " - size: " + r1.size());
        System.out.println("Range(50): " + r2 + " - size: " + r2.size());
        System.out.println("Range.ofLength(20): " + r3 + " - size: " + r3.size());

        try {
            Range invalid = new Range(100, 50);
        } catch (IllegalArgumentException e) {
            System.out.println("Validation works: " + e.getMessage());
        }

        // TEST 3: Interface Implementation
        System.out.println("\n--- TEST 3: Interface Implementation (Identifiable) ---");
        List<Product> products = challenge.getCoursesAsProducts();
        System.out.println("Total products: " + products.size());
        products.stream()
                .limit(3)
                .forEach(p -> System.out.println("  ID: " + p.id() + " - " + p.name() + " ($" + p.price() + ")"));

        // TEST 4: Nested Records (Rectangle)
        System.out.println("\n--- TEST 4: Nested Records (Rectangle with Point) ---");
        Rectangle rect1 = new Rectangle(new Point(0, 0), new Point(10, 5));
        Rectangle rect2 = new Rectangle(new Point(5, 5), new Point(15, 20));
        System.out.println("Rectangle 1: " + rect1 + " - Area: " + rect1.area() + ", Perimeter: " + rect1.perimeter());
        System.out.println("Rectangle 2: " + rect2 + " - Area: " + rect2.area() + ", Perimeter: " + rect2.perimeter());

        // TEST 5: Complex Nested DTOs
        System.out.println("\n--- TEST 5: Complex Nested DTOs (CourseDetail) ---");
        List<CourseDetail> details = challenge.getCourseDetails();
        System.out.println("Total courses with details: " + details.size());
        details.stream()
                .limit(3)
                .forEach(d -> System.out.println("  " + d.title() +
                        " - " + d.totalLessons() + " lessons, " +
                        d.totalDurationMinutes() + " minutes"));

        // TEST 6: Payment Transactions
        System.out.println("\n--- TEST 6: Payment Transactions (Serializable) ---");
        List<PaymentTransaction> transactions = challenge.getCompletedTransactions();
        System.out.println("Completed transactions: " + transactions.size());
        transactions.stream()
                .limit(3)
                .forEach(t -> System.out.println("  " + t.transactionId() +
                        " - " + t.courseTitle() + " - $" + t.amount()));

        BigDecimal total = transactions.stream()
                .map(PaymentTransaction::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total revenue: $" + total);

        // TEST 7: Serialization Check
        System.out.println("\n--- TEST 7: Serialization Support ---");
        Product product = products.get(0);
        System.out.println("Product is Serializable: " + (product instanceof Serializable));
        System.out.println("Product is Transferable: " + (product instanceof Transferable));

        System.out.println("\n═══════════════════════════════════════════════════════════════");
        System.out.println("  All reference implementations executed successfully!");
        System.out.println("═══════════════════════════════════════════════════════════════");
    }
}
