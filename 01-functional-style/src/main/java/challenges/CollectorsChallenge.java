package challenges;

import data.SampleDataGenerator;
import model.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 
 * CHALLENGE 04: Advanced Collectors & Grouping
 * 
 * 
 * SCENARIO:
 * You are building the Analytics Dashboard. You need to group, partition,
 * and summarize data to display charts and statistics.
 * 
 * FOCUS:
 * - GroupingBy (simple & cascaded)
 * - PartitioningBy
 * - Joining
 * - Mapping & CollectingAndThen
 * - SummarizingInt/Double
 * 
 * 
 */
public class CollectorsChallenge {
    
    private final List<Course> courses;
    private final List<Student> students;
    
    public CollectorsChallenge() {
        this.courses = SampleDataGenerator.generateCourses(50, SampleDataGenerator.generateInstructors(10));
        this.students = SampleDataGenerator.generateStudents(100);
    }
    
    // 
    // PART 1: Grouping
    // 
    
    /**
     * TASK 1.1: Courses by Category
     * Group courses by their Category.
     */
    public Map<Category, List<Course>> getCoursesByCategory() {
        // TODO: groupingBy(Category)
        return null;
    }
    
    /**
     * TASK 1.2: Course Count by Category
     * Count how many courses exist in each category.
     * Use downstream collector: counting()
     */
    public Map<Category, Long> getCourseCountByCategory() {
        // TODO: groupingBy(Category, counting())
        return null;
    }
    
    /**
     * TASK 1.3: Instructor Course Titles
     * Group by Instructor, but only map to list of Course Titles (Strings).
     * Use: groupingBy(Instructor, mapping(Course::getTitle, toList()))
     */
    public Map<Instructor, List<String>> getCourseTitlesByInstructor() {
        // TODO: Implement
        return null;
    }
    
    // 
    // PART 2: Partitioning
    // 
    
    /**
     * TASK 2.1: Free vs Paid
     * Partition courses into two lists: Free (true) and Paid (false).
     */
    public Map<Boolean, List<Course>> partitionFreeAndPaid() {
        // TODO: partitioningBy(isFree)
        return null;
    }
    
    // 
    // PART 3: Statistics
    // 
    
    /**
     * TASK 3.1: Price Statistics
     * Get count, sum, min, average, max of course prices.
     */
    public DoubleSummaryStatistics getPriceStatistics() {
        // TODO: summarizingDouble
        return null;
    }
    
    /**
     * TASK 3.2: Instructor Names CSV
     * Return all instructor names joined by comma.
     */
    public String getInstructorNamesCSV(List<Instructor> instructors) {
        // TODO: map(name) -> collect(joining(", "))
        return null;
    }
}
