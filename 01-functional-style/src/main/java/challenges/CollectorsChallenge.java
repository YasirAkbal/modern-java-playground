package challenges;

import data.SampleDataGenerator;
import model.*;

import java.util.*;
import java.util.stream.Collector;
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
        var coursesByCategory = courses.stream()
                                    .collect(Collectors.groupingBy(Course::getCategory));
        return coursesByCategory;
    }
    
    /**
     * TASK 1.2: Course Count by Category
     * Count how many courses exist in each category.
     * Use downstream collector: counting()
     */
    public Map<Category, Long> getCourseCountByCategory() {
        var courseCountByCategory = courses.stream()
                                    .collect(Collectors.groupingBy(Course::getCategory, Collectors.counting()));
        return courseCountByCategory;
    }
    
    /**
     * TASK 1.3: Instructor Course Titles
     * Group by Instructor, but only map to list of Course Titles (Strings).
     * Use: groupingBy(Instructor, mapping(Course::getTitle, toList()))
     */
    public Map<Instructor, List<String>> getCourseTitlesByInstructor() {
        return courses.stream()
                .collect(Collectors.groupingBy(Course::getInstructor, 
                    Collectors.mapping(Course::getTitle, Collectors.toList())));
    }
    
    // 
    // PART 2: Partitioning
    // 
    
    /**
     * TASK 2.1: Free vs Paid
     * Partition courses into two lists: Free (true) and Paid (false).
     */
    public Map<Boolean, List<Course>> partitionFreeAndPaid() {
        return courses.stream()
                .collect(Collectors.partitioningBy(c -> c.isFree()));
    }
    
    // 
    // PART 3: Statistics
    // 
    
    /**
     * TASK 3.1: Price Statistics
     * Get count, sum, min, average, max of course prices.
     */
    public DoubleSummaryStatistics getPriceStatistics() {
        return courses.stream()
                .collect(Collectors.summarizingDouble(c -> c.getPrice().doubleValue()));
    }
    
    /**
     * TASK 3.2: Instructor Names CSV
     * Return all instructor names joined by comma.
     */
    public String getInstructorNamesCSV(List<Instructor> instructors) {
        return instructors.stream()
                    .map(Instructor::getFullName)
                    .collect(Collectors.joining(","));
    }

    //
    // PART 4: Advanced Grouping (Cascaded)
    //

    /**
     * TASK 4.1: Courses by Category and Difficulty
     * Create a nested map: Category -> DifficultyLevel -> List<Course>
     * Use cascaded groupingBy.
     * Hint: groupingBy(Category, groupingBy(DifficultyLevel))
     */
    public Map<Category, Map<DifficultyLevel, List<Course>>> getCoursesByCategoryAndDifficulty() {
        return courses.stream()
            .collect(Collectors.groupingBy(Course::getCategory, 
                Collectors.groupingBy(Course::getDifficulty)));
    }

    /**
     * TASK 4.2: Average Price by Category and Difficulty
     * Create a nested map: Category -> DifficultyLevel -> Average Price
     * Use cascaded groupingBy with averagingDouble downstream.
     * Hint: groupingBy(Category, groupingBy(DifficultyLevel, averagingDouble(price)))
     */
    public Map<Category, Map<DifficultyLevel, Double>> getAveragePriceByCategoryAndDifficulty() {
        return courses.stream()
                .collect(Collectors.groupingBy(Course::getCategory,
                    Collectors.groupingBy(Course::getDifficulty, 
                        Collectors.averagingDouble(c -> c.getPrice().doubleValue()))
                ));
    }

    /**
     * TASK 4.3: Course Titles by Instructor and Category
     * Group by Instructor, then by Category, then collect only titles as Set.
     * Result: Map<Instructor, Map<Category, Set<String>>>
     * Hint: groupingBy(Instructor, groupingBy(Category, mapping(getTitle, toSet())))
     */
    public Map<Instructor, Map<Category, Set<String>>> getTitlesByInstructorAndCategory() {
        return courses.stream()
                .collect(Collectors.groupingBy(Course::getInstructor, 
                    Collectors.groupingBy(Course::getCategory, 
                        Collectors.mapping(Course::getTitle, Collectors.toSet()))
                ));
    }

    //
    // PART 5: Partitioning with Downstream Collectors
    //

    /**
     * TASK 5.1: Count Free vs Paid Courses
     * Partition courses by isFree, but count them instead of listing.
     * Result: Map<Boolean, Long> where true=free count, false=paid count
     * Hint: partitioningBy(isFree, counting())
     */
    public Map<Boolean, Long> countFreeAndPaidCourses() {
        return courses.stream()
                .collect(Collectors.partitioningBy(Course::isFree, 
                    Collectors.counting()));
    }

    /**
     * TASK 5.2: Top-Rated Course in Free vs Paid
     * Partition by isFree, then find the course with max rating in each partition.
     * Result: Map<Boolean, Optional<Course>>
     * Hint: partitioningBy(isFree, maxBy(comparingDouble(getRating)))
     */
    public Map<Boolean, Optional<Course>> getTopRatedFreeAndPaid() {
        return courses.stream()
                .collect(Collectors.partitioningBy(Course::isFree, 
                    Collectors.maxBy(Comparator.comparingDouble(Course::getRating))));
    }

    //
    // PART 6: CollectingAndThen
    //

    /**
     * TASK 6.1: Most Expensive Course
     * Find the most expensive course and return it wrapped in Optional.
     * But transform to get only its title (String).
     * Use: collectingAndThen(maxBy(...), optional -> optional.map(Course::getTitle))
     * Result type: Optional<String>
     */
    public Optional<String> getMostExpensiveCourseTitle() {
        return courses.stream()
                .collect(Collectors.collectingAndThen(Collectors.maxBy(
                    Comparator.comparingDouble(c -> c.getPrice().doubleValue())
                ), optional -> optional.map(Course::getTitle)));
    }

    /**
     * TASK 6.2: Course Titles as Unmodifiable List
     * Collect all course titles into a list, then make it unmodifiable.
     * Hint: collectingAndThen(toList(), Collections::unmodifiableList)
     */
    public List<String> getAllCourseTitlesUnmodifiable() {
        return courses.stream()
                .map(Course::getTitle)
                .collect(Collectors.collectingAndThen(Collectors.toList(), 
                Collections::unmodifiableList));
    }
}
