package data;

import model.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Generates sample data for the EduMaster platform.
 * This class provides realistic test data for all domain entities.
 */
public class SampleDataGenerator {
    private static final Random random = new Random();
    private static int studentIdCounter = 1000;
    private static int courseIdCounter = 2000;
    private static int instructorIdCounter = 3000;
    private static int enrollmentIdCounter = 4000;
    private static int paymentIdCounter = 5000;
    private static int reviewIdCounter = 6000;
    private static int certificateIdCounter = 7000;
    private static int lessonIdCounter = 8000;

    // Sample data arrays
    private static final String[] FIRST_NAMES = {
        "Emma", "Liam", "Olivia", "Noah", "Ava", "Ethan", "Sophia", "Mason",
        "Isabella", "William", "Mia", "James", "Charlotte", "Benjamin", "Amelia",
        "Lucas", "Harper", "Henry", "Evelyn", "Alexander"
    };

    private static final String[] LAST_NAMES = {
        "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis",
        "Rodriguez", "Martinez", "Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson",
        "Thomas", "Taylor", "Moore", "Jackson", "Martin"
    };

    private static final String[] COURSE_TITLES = {
        "Complete Java Masterclass",
        "Python for Data Science",
        "Full-Stack Web Development Bootcamp",
        "AWS Cloud Practitioner Essentials",
        "Machine Learning A-Z",
        "Docker and Kubernetes for Beginners",
        "React - The Complete Guide",
        "iOS Development with Swift",
        "Database Design and SQL",
        "Cybersecurity Fundamentals",
        "Angular - Complete Developer Course",
        "Node.js Backend Development",
        "DevOps Engineering Masterclass",
        "MongoDB - The Complete Guide",
        "Microservices with Spring Boot",
        "Advanced JavaScript Patterns",
        "UI/UX Design Bootcamp",
        "Git and GitHub Mastery",
        "REST API Design Best Practices",
        "Agile Project Management"
    };

    private static final String[] SPECIALIZATIONS = {
        "Java Development", "Python Programming", "Web Development", "Cloud Architecture",
        "Data Science", "DevOps", "Mobile Development", "Database Engineering",
        "Machine Learning", "Cybersecurity", "Full-Stack Development", "Frontend Development"
    };

    private static final String[] LESSON_TITLES_PREFIX = {
        "Introduction to", "Understanding", "Deep Dive into", "Mastering", "Advanced",
        "Getting Started with", "Practical", "Real-World", "Building", "Implementing"
    };

    private static final String[] LESSON_TITLES_SUFFIX = {
        "Concepts", "Principles", "Patterns", "Techniques", "Strategies",
        "Best Practices", "Applications", "Solutions", "Projects", "Examples"
    };

    /**
     * Generate a list of sample students
     */
    public static List<Student> generateStudents(int count) {
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Student student = new Student(
                "STU-" + (studentIdCounter++),
                randomElement(FIRST_NAMES),
                randomElement(LAST_NAMES),
                generateEmail(FIRST_NAMES[random.nextInt(FIRST_NAMES.length)], 
                             LAST_NAMES[random.nextInt(LAST_NAMES.length)]),
                randomBirthDate(),
                randomEnum(StudentLevel.class)
            );
            student.setActive(random.nextBoolean() ? true : random.nextDouble() > 0.2);
            student.setAverageScore(random.nextBoolean() ? 50 + random.nextDouble() * 50 : null);
            students.add(student);
        }
        return students;
    }

    /**
     * Generate a list of sample instructors
     */
    public static List<Instructor> generateInstructors(int count) {
        List<Instructor> instructors = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Instructor instructor = new Instructor(
                "INS-" + (instructorIdCounter++),
                randomElement(FIRST_NAMES),
                randomElement(LAST_NAMES),
                generateEmail(FIRST_NAMES[random.nextInt(FIRST_NAMES.length)], 
                             LAST_NAMES[random.nextInt(LAST_NAMES.length)]),
                randomElement(SPECIALIZATIONS)
            );
            instructor.setVerified(random.nextDouble() > 0.3);
            instructor.setRating(3.0 + random.nextDouble() * 2.0);
            instructor.setTotalStudents(random.nextInt(5000));
            instructor.setBio("Experienced " + instructor.getSpecialization() + 
                            " instructor with " + (5 + random.nextInt(15)) + 
                            " years of industry experience.");
            instructors.add(instructor);
        }
        return instructors;
    }

    /**
     * Generate a list of sample courses
     */
    public static List<Course> generateCourses(int count, List<Instructor> instructors) {
        List<Course> courses = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Instructor instructor = randomElement(instructors);
            Course course = new Course(
                "CRS-" + (courseIdCounter++),
                randomElement(COURSE_TITLES),
                "A comprehensive course covering all aspects of the topic with hands-on projects.",
                randomEnum(Category.class),
                randomEnum(DifficultyLevel.class),
                randomPrice(),
                instructor
            );
            course.setPublished(random.nextDouble() > 0.2);
            course.setRating(3.0 + random.nextDouble() * 2.0);
            course.setEnrollmentCount(random.nextInt(10000));
            course.setDurationInHours(10 + random.nextInt(100));
            
            // Add tags
            List<String> tags = new ArrayList<>();
            tags.add(course.getCategory().getDisplayName());
            tags.add(course.getDifficulty().name().toLowerCase());
            if (course.isFree()) tags.add("free");
            course.setTags(tags);
            
            // Add lessons
            int lessonCount = 5 + random.nextInt(20);
            for (int j = 0; j < lessonCount; j++) {
                course.addLesson(generateLesson(course.getId(), j));
            }
            
            courses.add(course);
            instructor.addCourse(course);
        }
        return courses;
    }

    /**
     * Generate a single lesson
     */
    public static Lesson generateLesson(String courseId, int orderIndex) {
        Lesson lesson = new Lesson(
            "LSN-" + (lessonIdCounter++),
            randomElement(LESSON_TITLES_PREFIX) + " " + randomElement(LESSON_TITLES_SUFFIX),
            "Detailed content covering important concepts and practical examples.",
            randomEnum(LessonType.class),
            orderIndex,
            Duration.ofMinutes(5 + random.nextInt(60))
        );
        lesson.setCourseId(courseId);
        lesson.setFreePreview(orderIndex < 2 && random.nextDouble() > 0.5);
        if (lesson.getType() == LessonType.VIDEO) {
            lesson.setVideoUrl("https://video.edumaster.com/" + lesson.getId());
        }
        return lesson;
    }

    /**
     * Generate enrollments for students in courses
     */
    public static List<Enrollment> generateEnrollments(List<Student> students, List<Course> courses, int count) {
        List<Enrollment> enrollments = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Student student = randomElement(students);
            Course course = randomElement(courses);
            
            Enrollment enrollment = new Enrollment(
                "ENR-" + (enrollmentIdCounter++),
                student.getId(),
                course
            );
            enrollment.setStatus(randomEnum(EnrollmentStatus.class));
            enrollment.setProgressPercentage(random.nextInt(101));
            enrollment.setLessonsCompleted(random.nextInt(course.getLessons().size() + 1));
            
            if (enrollment.getStatus() == EnrollmentStatus.COMPLETED) {
                enrollment.setProgressPercentage(100);
                enrollment.setCompletedAt(LocalDateTime.now().minusDays(random.nextInt(100)));
                enrollment.setScore(60.0 + random.nextDouble() * 40);
            }
            
            enrollments.add(enrollment);
            student.addEnrollment(enrollment);
        }
        return enrollments;
    }

    /**
     * Generate payments for course enrollments
     */
    public static List<Payment> generatePayments(List<Student> students, List<Course> courses, int count) {
        List<Payment> payments = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Student student = randomElement(students);
            Course course = randomElement(courses);
            
            Payment payment = new Payment(
                "PAY-" + (paymentIdCounter++),
                student.getId(),
                course.getId(),
                course.getPrice(),
                randomEnum(PaymentMethod.class)
            );
            
            // Apply random discount
            if (random.nextDouble() > 0.7) {
                BigDecimal discount = payment.getAmount()
                    .multiply(BigDecimal.valueOf(0.1 + random.nextDouble() * 0.3));
                payment.setDiscount(discount);
                payment.setFinalAmount(payment.getAmount().subtract(discount));
            }
            
            payment.setStatus(randomEnum(PaymentStatus.class));
            if (payment.getStatus() == PaymentStatus.COMPLETED) {
                payment.setProcessedAt(LocalDateTime.now().minusDays(random.nextInt(100)));
                payment.setTransactionId("TXN-" + System.currentTimeMillis() + "-" + random.nextInt(1000));
            } else if (payment.getStatus() == PaymentStatus.FAILED) {
                payment.setFailureReason("Payment gateway error: insufficient funds");
            }
            
            payments.add(payment);
        }
        return payments;
    }

    /**
     * Generate course reviews
     */
    public static List<Review> generateReviews(List<Student> students, List<Course> courses, int count) {
        List<Review> reviews = new ArrayList<>();
        String[] reviewTitles = {
            "Excellent course!", "Very informative", "Great instructor", 
            "Needs improvement", "Outstanding content", "Could be better",
            "Highly recommended", "Worth every penny", "Disappointed",
            "Best course ever", "Good for beginners", "Too advanced for me"
        };
        
        for (int i = 0; i < count; i++) {
            Student student = randomElement(students);
            Course course = randomElement(courses);
            int rating = 1 + random.nextInt(5);
            
            Review review = new Review(
                "REV-" + (reviewIdCounter++),
                student.getId(),
                course.getId(),
                student.getFullName(),
                rating,
                randomElement(reviewTitles),
                generateReviewComment(rating)
            );
            review.setVerifiedPurchase(random.nextDouble() > 0.3);
            review.setHelpfulCount(random.nextInt(100));
            
            reviews.add(review);
        }
        return reviews;
    }

    /**
     * Generate certificates for completed courses
     */
    public static List<Certificate> generateCertificates(List<Enrollment> enrollments) {
        List<Certificate> certificates = new ArrayList<>();
        
        for (Enrollment enrollment : enrollments) {
            if (enrollment.isCompleted() && enrollment.getScore() != null && enrollment.getScore() >= 70) {
                Certificate certificate = new Certificate(
                    "CERT-" + (certificateIdCounter++),
                    enrollment.getStudentId(),
                    enrollment.getCourseId(),
                    "Student Name", // Would be populated from student lookup
                    enrollment.getCourse().getTitle(),
                    enrollment.getCourse().getInstructor().getFullName(),
                    enrollment.getScore()
                );
                certificate.setVerificationUrl("https://verify.edumaster.com/" + certificate.getCertificateNumber());
                
                // Some certificates expire after 2 years
                if (random.nextDouble() > 0.7) {
                    certificate.setExpiresAt(certificate.getIssuedAt().plusYears(2));
                }
                
                certificates.add(certificate);
            }
        }
        return certificates;
    }

    // Helper methods
    private static <T> T randomElement(List<T> list) {
        return list.get(random.nextInt(list.size()));
    }

    private static <T> T randomElement(T[] array) {
        return array[random.nextInt(array.length)];
    }

    private static <T extends Enum<?>> T randomEnum(Class<T> enumClass) {
        T[] values = enumClass.getEnumConstants();
        return values[random.nextInt(values.length)];
    }

    private static String generateEmail(String firstName, String lastName) {
        return firstName.toLowerCase() + "." + lastName.toLowerCase() + 
               random.nextInt(100) + "@email.com";
    }

    private static LocalDate randomBirthDate() {
        return LocalDate.now().minusYears(18 + random.nextInt(40));
    }

    private static BigDecimal randomPrice() {
        if (random.nextDouble() > 0.8) {
            return BigDecimal.ZERO; // Free course
        }
        return BigDecimal.valueOf(19.99 + random.nextDouble() * 280);
    }

    private static String generateReviewComment(int rating) {
        if (rating >= 4) {
            return "This course exceeded my expectations. The instructor explains concepts clearly " +
                   "and the hands-on projects are very practical. Highly recommended!";
        } else if (rating == 3) {
            return "Decent course with good content, but could use more examples and better pacing. " +
                   "Still learned quite a bit.";
        } else {
            return "Unfortunately, this course didn't meet my expectations. The content was either " +
                   "too basic or too advanced, and the explanations were unclear.";
        }
    }
}
