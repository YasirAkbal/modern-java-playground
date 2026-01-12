package challenges;

import data.SampleDataGenerator;
import model.*;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.*;
import java.util.stream.*;

/**
 * 
 * CHALLENGE 03: Stream API Operations - High Performance Data Processing
 * =
 * 
 * SCENARIO:
 * You are developing the Reporting & Analytics Module for EduMaster.
 * You need to process large in-memory datasets efficiently without manual loops.
 * 
 * FOCUS:
 * - Stream Pipeline: Source -> Intermediate -> Terminal
 * - Filter, Map, FlatMap, Sorted, Distinct, Peek, Limit, Skip
 * - FindFirst, AnyMatch, AllMatch, Reduce
 * 
 * 
 */
public class StreamOperationsChallenge {
    
    private final List<Student> students;
    private final List<Course> courses;
    
    public StreamOperationsChallenge() {
        this.students = SampleDataGenerator.generateStudents(100);
        this.courses = SampleDataGenerator.generateCourses(50, SampleDataGenerator.generateInstructors(10));
    }
    
    // 
    // PART 1: Filter & Transformation
    // =
    
    /**
     * TASK 1.1: Published Course Titles
     * 
     * Get titles of all PUBLISHED courses, sorted alphabetically.
     * Use: filter -> map -> sorted -> toList
     */
    public List<String> getPublishedCourseTitles() {
        List<String> filteredCourses = courses.stream()
                                            .filter(c -> c.isPublished())
                                            .map(Course::getTitle)
                                            .sorted()
                                            .toList();
        return filteredCourses;
    }
    
    /**
     * TASK 1.2: Discounted Price List
     * 
     * Apply 20% discount to courses costing > 100, return sorted prices.
     */
    public List<BigDecimal> getDiscountedPrices() {
        BigDecimal oneHundred = BigDecimal.valueOf(100);
        BigDecimal priceMultiplier = BigDecimal.valueOf(0.8);
        var newPriceList = courses.stream()
                                .map(c -> {
                                    BigDecimal price = c.getPrice();
                                    if (price == null) return BigDecimal.ZERO;

                                    return price.compareTo(oneHundred) > 0 ?  
                                        price.multiply(priceMultiplier) : 
                                        price;
                                })
                                .sorted()
                                .toList();

        return newPriceList;
    }

    /**
     * TASK 1.3: Pagination
     * 
     * Return a "page" of students.
     * Skip the first (page-1) * pageSize elements, then take pageSize elements.
     */
    public List<Student> getStudentPage(int page, int pageSize) {
        var studentPage = students.stream()
                            .skip((page - 1) * pageSize)
                            .limit(pageSize)
                            .toList();

        return studentPage;
    }
    
    // 
    // PART 2: FlatMap & Structural Flattening
    // 
    
    /**
     * TASK 2.1: Flatten Lessons
     * 
     * Get a single list of ALL lessons from ALL courses.
     * Course::getLessons returns List<Lesson>.
     * Use flatMap.
     */
    public List<Lesson> getAllLessons() {
        var lessons = courses.stream()
                        .flatMap(c -> c.getLessons().stream())
                        .distinct() //since object equals isn't overridden in course class this has no effect
                        .toList();
        return lessons;
    }
    
    /**
     * TASK 2.2: Unique Tags
     * 
     * Collect all unique tags from all courses.
     */
    public List<String> getAllUniqueTags() {
        var uniqueTags = courses.stream()
                            .flatMap(c -> c.getTags().stream())
                            .distinct()
                            .toList();
        return uniqueTags;
    }

    /**
     * TASK 2.3: Cartesian Product (All Possible Enrollments)
     * 
     * Generate a list of String "Student: {name} - Course: {title}" 
     * for EVERY student and EVERY course combination.
     * Use flatMap to iterate courses inside student stream.
     */
    public List<String> getCartesianProduct() {
        var cartesianProduct = students.stream()
                                .flatMap(
                                    s -> courses.stream()
                                        .map(c -> MessageFormat.format("Student: {0} - Course: {1}", 
                                            s.getFullName(), c.getTitle()))
                                )
                                .toList();
        return cartesianProduct;
    }
    
    // 
    // PART 3: Short-Circuiting & Match
    // 
    
    /**
     * TASK 3.1: Advanced Course Check
     * Is there ANY course with difficulty == ADVANCED?
     */
    public boolean hasAdvancedCourses() {
        return courses.stream()
                    .anyMatch(c -> c.getDifficulty().equals(DifficultyLevel.ADVANCED));
    }
    
    /**
     * TASK 3.2: First Free Course
     * Find the first course that is free (price == 0 or null).
     */
    public Optional<Course> findFirstFreeCourse() {
        return courses.stream()
                .filter(c -> c.getPrice() == null || c.getPrice().equals(BigDecimal.ZERO))
                .findFirst();
    }
    
    // 
    // PART 4: Reduction & Statistics
    // 
    
    /**
     * TASK 4.1: Total Duration
     * Calculate total duration (hours) of all courses.
     * Use mapToInt and sum.
     */
    public int getTotalCourseDuration() {
        return courses.stream()
                .mapToInt(Course::getDurationInHours)
                .sum();
    }
    
    /**
     * TASK 4.2: Maximum Rating
     * Find the highest rating among all courses.
     */
    public OptionalDouble getMaxRating() {
        return courses.stream()
                .mapToDouble(Course::getRating)
                .max();
    }
    
    /**
     * TASK 4.3: Custom Reduction
     * Multiply all numbers in the list.
     * Use reduce identity.
     */
    public long getProductOfNumbers(List<Integer> numbers) {
        return numbers.stream()
                .reduce(1, (acc, num) -> acc * num);
    }
}
