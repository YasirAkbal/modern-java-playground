package challenges;

import data.SampleDataGenerator;
import model.Course;
import model.Student;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorMasteryChallenge {

    /**
     * ═══════════════════════════════════════════════════════════════════════════════
     * CHALLENGE 08: Comparator Mastery (Chaining & Nulls)
     * ═══════════════════════════════════════════════════════════════════════════════
     * 
     * SCENARIO:
     * Sorting is common, but complex multi-level sorting with explicit null handling
     * is often implemented poorly. Use Java 8 Comparator factory methods to do this cleanly.
     * 
     * ═══════════════════════════════════════════════════════════════════════════════
     */

    private final List<Student> students = SampleDataGenerator.generateStudents(50);
    private final List<Course> courses = SampleDataGenerator.generateCourses(50, SampleDataGenerator.generateInstructors(10));

    /**
     * TASK 1: Multi-Level Sort
     * Sort students by:
     * 1. Level (Natural Order: BEGINNER < INTERMEDIATE < ADVANCED)
     * 2. If levels equal, sort by Last Name (Alphabetical)
     * 3. If Last Names equal, sort by ID (Descending)
     * 
     * Use Comparator.comparing().thenComparing().reversed()
     */
    public void multiLevelSort() {
        Comparator<Student> studentComparator = Comparator.comparing(Student::getLevel)
            .thenComparing(Student::getLastName)
            .thenComparing(Student::getId, Comparator.reverseOrder());

        students.sort(studentComparator);
    }

    /**
     * TASK 2: Nulls Last
     * Some students have a NULL 'averageScore'.
     * Sort students by averageScore descending, but keep NULLs at the very end
     * (don't let them appear at the top or throw exception).
     * 
     * Use Comparator.nullsLast() and comparing()
     */
    public void sortWithNullsLast() {
        students.sort(
            Comparator.comparing(
                Student::getAverageScore,
                Comparator.nullsLast(Comparator.reverseOrder())
            )
        );
    }

    /**
     * TASK 3: Key Extractor with Primitive Optimization
     * Sort courses by price (BigDecimal) descending. 
     * If prices are equal, sort by Duration (int) ascending.
     * 
     * Use Comparator.comparing(..., Comparator.reverseOrder())
     * and .thenComparingInt(...) for optimization.
     */
    public void sortCoursesComplex() {
        courses.sort(
            Comparator.comparing(Course::getPrice, Comparator.reverseOrder())
                .thenComparingInt(Course::getDurationInHours)
        );
    }
}
