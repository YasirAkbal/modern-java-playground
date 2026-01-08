package examples;

import data.SampleDataGenerator;
import model.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Example implementations for Challenge 1.1 and 1.2
 * 
 * This demonstrates how to solve the challenges using Java 8 features.
 * You should create similar classes for each challenge you solve.
 */
public class StreamExamples {

    /**
     * Challenge 1.1: Course Filtering and Search
     */
    public static class CourseFiltering {
        
        // Filter courses by category
        public static List<Course> findCoursesByCategory(List<Course> courses, Category category) {
            return courses.stream()
                    .filter(course -> course.getCategory() == category)
                    .collect(Collectors.toList());
        }
        
        // Find top-rated courses (rating > 4.5)
        public static List<Course> findTopRatedCourses(List<Course> courses) {
            return courses.stream()
                    .filter(course -> course.getRating() > 4.5)
                    .sorted((c1, c2) -> Double.compare(c2.getRating(), c1.getRating()))
                    .collect(Collectors.toList());
        }
        
        // Get all free courses
        public static List<Course> findFreeCourses(List<Course> courses) {
            return courses.stream()
                    .filter(Course::isFree)
                    .collect(Collectors.toList());
        }
        
        // Search courses by instructor name
        public static List<Course> findCoursesByInstructorName(List<Course> courses, String instructorName) {
            return courses.stream()
                    .filter(course -> course.getInstructor().getFullName()
                            .toLowerCase()
                            .contains(instructorName.toLowerCase()))
                    .collect(Collectors.toList());
        }
        
        // Find courses with most enrollments
        public static List<Course> findMostPopularCourses(List<Course> courses, int limit) {
            return courses.stream()
                    .sorted((c1, c2) -> Integer.compare(c2.getEnrollmentCount(), c1.getEnrollmentCount()))
                    .limit(limit)
                    .collect(Collectors.toList());
        }
    }

    /**
     * Challenge 1.2: Student Enrollment Analytics
     */
    public static class EnrollmentAnalytics {
        
        // Calculate average course completion rate
        public static double calculateAverageCompletionRate(List<Enrollment> enrollments) {
            return enrollments.stream()
                    .mapToInt(Enrollment::getProgressPercentage)
                    .average()
                    .orElse(0.0);
        }
        
        // Find students with highest scores
        public static List<Student> findTopStudents(List<Student> students, int limit) {
            return students.stream()
                    .filter(student -> student.getAverageScore() != null)
                    .sorted((s1, s2) -> Double.compare(
                            s2.getAverageScore(),
                            s1.getAverageScore()))
                    .limit(limit)
                    .collect(Collectors.toList());
        }
        
        // Group enrollments by status
        public static Map<EnrollmentStatus, List<Enrollment>> groupEnrollmentsByStatus(
                List<Enrollment> enrollments) {
            return enrollments.stream()
                    .collect(Collectors.groupingBy(Enrollment::getStatus));
        }
        
        // Find the most popular course category
        public static Optional<Category> findMostPopularCategory(List<Course> courses) {
            return courses.stream()
                    .collect(Collectors.groupingBy(
                            Course::getCategory,
                            Collectors.summingInt(Course::getEnrollmentCount)))
                    .entrySet().stream()
                    .max(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey);
        }
        
        // Calculate total revenue by category
        public static Map<Category, BigDecimal> calculateRevenueByCategory(List<Payment> payments,
                                                                            List<Course> courses) {
            // Create a map of courseId -> category
            Map<String, Category> courseCategoryMap = courses.stream()
                    .collect(Collectors.toMap(Course::getId, Course::getCategory));
            
            return payments.stream()
                    .filter(payment -> payment.getStatus() == PaymentStatus.COMPLETED)
                    .collect(Collectors.groupingBy(
                            payment -> courseCategoryMap.get(payment.getCourseId()),
                            Collectors.reducing(
                                    BigDecimal.ZERO,
                                    Payment::getFinalAmount,
                                    BigDecimal::add)));
        }
    }

    /**
     * Demo main method to show usage
     */
    public static void main(String[] args) {
        // Generate sample data
        List<Instructor> instructors = SampleDataGenerator.generateInstructors(10);
        List<Course> courses = SampleDataGenerator.generateCourses(30, instructors);
        List<Student> students = SampleDataGenerator.generateStudents(50);
        List<Enrollment> enrollments = SampleDataGenerator.generateEnrollments(students, courses, 100);
        List<Payment> payments = SampleDataGenerator.generatePayments(students, courses, 80);

        System.out.println("=== Course Filtering Examples ===");
        
        // Example 1: Find programming courses
        List<Course> programmingCourses = CourseFiltering.findCoursesByCategory(courses, Category.PROGRAMMING);
        System.out.println("\nProgramming courses: " + programmingCourses.size());
        programmingCourses.forEach(c -> System.out.println("  - " + c.getTitle()));
        
        // Example 2: Find top-rated courses
        List<Course> topRated = CourseFiltering.findTopRatedCourses(courses);
        System.out.println("\nTop-rated courses:");
        topRated.stream()
                .limit(5)
                .forEach(c -> System.out.printf("  - %s (%.2f stars)%n", c.getTitle(), c.getRating()));
        
        // Example 3: Find free courses
        List<Course> freeCourses = CourseFiltering.findFreeCourses(courses);
        System.out.println("\nFree courses: " + freeCourses.size());
        
        System.out.println("\n=== Enrollment Analytics Examples ===");
        
        // Example 4: Average completion rate
        double avgCompletion = EnrollmentAnalytics.calculateAverageCompletionRate(enrollments);
        System.out.printf("\nAverage completion rate: %.2f%%%n", avgCompletion);
        
        // Example 5: Group by status
        Map<EnrollmentStatus, List<Enrollment>> groupedByStatus = 
                EnrollmentAnalytics.groupEnrollmentsByStatus(enrollments);
        System.out.println("\nEnrollments by status:");
        groupedByStatus.forEach((status, list) -> 
                System.out.printf("  %s: %d enrollments%n", status, list.size()));
        
        // Example 6: Most popular category
        Optional<Category> popularCategory = EnrollmentAnalytics.findMostPopularCategory(courses);
        popularCategory.ifPresent(category -> 
                System.out.println("\nMost popular category: " + category.getDisplayName()));
        
        // Example 7: Revenue by category
        Map<Category, BigDecimal> revenueByCategory = 
                EnrollmentAnalytics.calculateRevenueByCategory(payments, courses);
        System.out.println("\nRevenue by category:");
        revenueByCategory.forEach((category, revenue) -> 
                System.out.printf("  %s: $%.2f%n", category.getDisplayName(), revenue));
    }
}
