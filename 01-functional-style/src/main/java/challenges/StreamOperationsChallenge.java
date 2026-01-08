package challenges;

import data.SampleDataGenerator;
import model.*;

import java.math.BigDecimal;
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
        // TODO: Implement pipeline
        return null;
    }
    
    /**
     * TASK 1.2: Discounted Price List
     * 
     * Apply 20% discount to courses costing > 100, return sorted prices.
     */
    public List<BigDecimal> getDiscountedPrices() {
        // TODO: filter > 100 -> map (price * 0.8) -> sorted
        return null;
    }

    /**
     * TASK 1.3: Pagination
     * 
     * Return a "page" of students.
     * Skip the first (page-1) * pageSize elements, then take pageSize elements.
     */
    public List<Student> getStudentPage(int page, int pageSize) {
        // TODO: skip ... limit ...
        return null;
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
        // TODO: courses.stream().flatMap(...)
        return null;
    }
    
    /**
     * TASK 2.2: Unique Tags
     * 
     * Collect all unique tags from all courses.
     */
    public List<String> getAllUniqueTags() {
        // TODO: flatMap tags -> distinct
        return null;
    }

    /**
     * TASK 2.3: Cartesian Product (All Possible Enrollments)
     * 
     * Generate a list of String "Student: {name} - Course: {title}" 
     * for EVERY student and EVERY course combination.
     * Use flatMap to iterate courses inside student stream.
     */
    public List<String> getCartesianProduct() {
        // TODO: students.stream().flatMap(s -> courses.stream().map(c -> ...))
        return null;
    }
    
    // 
    // PART 3: Short-Circuiting & Match
    // 
    
    /**
     * TASK 3.1: Hard Course Check
     * Is there ANY course with difficulty == HARD?
     */
    public boolean hasHardCourses() {
        // TODO: anyMatch
        return false;
    }
    
    /**
     * TASK 3.2: First Free Course
     * Find the first course that is free (price == 0 or null).
     */
    public Optional<Course> findFirstFreeCourse() {
        // TODO: findFirst
        return Optional.empty();
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
        // TODO: mapToInt -> sum
        return 0;
    }
    
    /**
     * TASK 4.2: Maximum Rating
     * Find the highest rating among all courses.
     */
    public OptionalDouble getMaxRating() {
        // TODO: mapToDouble -> max
        return OptionalDouble.empty();
    }
    
    /**
     * TASK 4.3: Custom Reduction
     * Multiply all numbers in the list.
     * Use reduce identity.
     */
    public long getProductOfNumbers(List<Integer> numbers) {
        // TODO: reduce((a, b) -> a * b)
        return 0;
    }
}
