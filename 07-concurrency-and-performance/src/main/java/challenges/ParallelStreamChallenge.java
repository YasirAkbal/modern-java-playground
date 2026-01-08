package challenges;

import data.SampleDataGenerator;
import model.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

/**
 * ═══════════════════════════════════════════════════════════════════════════════
 * CHALLENGE 02: Parallel Streams - Performance Optimization
 * ═══════════════════════════════════════════════════════════════════════════════
 *
 * SCENARIO:
 * You are optimizing the EduMaster Analytics Engine that processes massive datasets
 * of students, courses, and enrollments. Parallel streams can dramatically speed up
 * CPU-intensive operations, but must be used carefully to avoid pitfalls.
 *
 * FOCUS:
 * - parallel() - converting sequential to parallel streams
 * - CPU-bound vs I/O-bound tasks - when to use parallel
 * - Performance measurement and benchmarking
 * - Common pitfalls: race conditions, blocking operations, ordering
 * - Thread-safe collectors and reductions
 *
 * ═══════════════════════════════════════════════════════════════════════════════
 */
public class ParallelStreamChallenge {

    private final List<Student> students;
    private final List<Course> courses;
    private final List<Enrollment> enrollments;
    private final List<Instructor> instructors;

    public ParallelStreamChallenge() {
        this.students = SampleDataGenerator.generateStudents(1000);
        this.instructors = SampleDataGenerator.generateInstructors(50);
        this.courses = SampleDataGenerator.generateCourses(500, instructors);
        this.enrollments = SampleDataGenerator.generateEnrollments(students, courses, 5000);
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 1: CPU-Intensive Operations (Good Use Case)
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 1.1: Prime Number Counting (Parallel)
     *
     * Calculate count of prime numbers from 2 to limit using parallel streams.
     * This is CPU-intensive, making it ideal for parallel processing.
     *
     * EXAMPLE:
     * long count = countPrimesParallel(10000);
     * // Returns 1229 (much faster than sequential)
     */
    public long countPrimesParallel(int limit) {
        // Placeholder: original implementation removed for challenge reset
        return 0;
    }

    /**
     * TASK 1.2: Sequential vs Parallel Benchmark
     *
     * Compare performance of sequential vs parallel prime counting.
     * Return time difference in milliseconds (sequential - parallel).
     * Positive value means parallel was faster.
     *
     * EXAMPLE:
     * long improvement = benchmarkPrimeCount(50000);
     * System.out.println("Parallel improvement: " + improvement + "ms");
     */
    public long benchmarkPrimeCount(int limit) {
        // Placeholder: original implementation removed for challenge reset
        return 0;
    }

    /**
     * TASK 1.3: Heavy Score Calculation
     *
     * For each student, calculate a "weighted score" using expensive computation:
     * score = (avgScore * 0.7) + (enrollmentCount * 0.3) + expensiveCalculation()
     *
     * Use parallel stream to speed up processing 1000 students.
     *
     * @return Map of student ID to weighted score
     */
    public Map<String, Double> calculateWeightedScoresParallel() {
        // Placeholder: original implementation removed for challenge reset
        return Collections.emptyMap();
    }

    /**
     * TASK 1.4: Large Dataset Sum (Parallel Reduction)
     *
     * Sum all course prices using parallel stream.
     * Use reduce() or sum() with parallel processing.
     *
     * @return Total value of all courses
     */
    public BigDecimal calculateTotalCourseValue() {
        // Placeholder: original implementation removed for challenge reset
        return BigDecimal.ZERO;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 2: Common Pitfalls & Anti-Patterns
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 2.1: Race Condition Demo (BROKEN!)
     *
     * This method demonstrates why NOT to modify shared collections in parallel.
     * Try adding 10,000 elements to a standard ArrayList in parallel.
     * The size will often be LESS than 10,000 due to race conditions.
     *
     * EXAMPLE:
     * int size = unsafeParallelAdd();
     * System.out.println("Size: " + size + " (expected 10000)");
     * // Typical output: Size: 9847 (WRONG!)
     */
    public int unsafeParallelAdd() {
        // Placeholder: original implementation removed for challenge reset
        return 0;
    }

    /**
     * TASK 2.2: Safe Parallel Collection (FIXED!)
     *
     * Fix the previous problem using thread-safe collection or collect().
     * Two approaches:
     * 1. Use Collections.synchronizedList() - slower
     * 2. Use collect(Collectors.toList()) - recommended!
     *
     * @return Correct size (always 10000)
     */
    public int safeParallelAdd() {
        // Placeholder: original implementation removed for challenge reset
        return 0;
    }

    /**
     * TASK 2.3: Blocking I/O Anti-Pattern
     *
     * Demonstrate why parallel streams are BAD for I/O-bound operations.
     * Simulate fetching student details from slow API (100ms per call).
     *
     * With 100 students:
     * - Sequential: ~10 seconds (100 * 100ms)
     * - Parallel: Still ~10 seconds (limited by ForkJoinPool size)
     * - Virtual Threads or async: ~100ms (proper solution)
     *
     * @return Time taken in milliseconds
     */
    public long demonstrateIOBottleneck() {
        // Placeholder: original implementation removed for challenge reset
        return 0;
    }

    /**
     * TASK 2.4: Order Sensitivity
     *
     * Parallel streams may not preserve encounter order.
     * Get first 10 student names using parallel stream with forEachOrdered.
     *
     * Compare:
     * - forEach() - unordered, faster
     * - forEachOrdered() - ordered, slower
     *
     * @return List of first 10 student names in order
     */
    public List<String> getFirstTenStudentsOrdered() {
        // Placeholder: original implementation removed for challenge reset
        return Collections.emptyList();
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 3: Performance Optimization Patterns
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 3.1: Grouping with Parallel Streams
     *
     * Group students by level using parallel streams.
     * Use Collectors.groupingByConcurrent() for thread-safe grouping.
     *
     * EXAMPLE:
     * Map<StudentLevel, List<Student>> groups = groupStudentsByLevelParallel();
     * // Returns concurrent map with students grouped by level
     */
    public Map<StudentLevel, List<Student>> groupStudentsByLevelParallel() {
        // Placeholder: original implementation removed for challenge reset
        return Collections.emptyMap();
    }

    /**
     * TASK 3.2: Filtering Large Dataset
     *
     * From 5000 enrollments, find all with score > 85.
     * Benchmark sequential vs parallel to see performance gain.
     *
     * @param useParallel Whether to use parallel stream
     * @return Filtered enrollments and time taken in ms
     */
    public PerformanceResult<List<Enrollment>> filterHighScoreEnrollments(boolean useParallel) {
        // Placeholder: original implementation removed for challenge reset
        return new PerformanceResult<>(Collections.emptyList(), 0);
    }

    /**
     * TASK 3.3: Complex Aggregation
     *
     * Calculate statistics across all enrollments in parallel:
     * - Average score
     * - Min score
     * - Max score
     * - Total count
     *
     * Use DoubleSummaryStatistics with parallel stream.
     *
     * @return Statistics object
     */
    public DoubleSummaryStatistics calculateEnrollmentStats() {
        // Placeholder: original implementation removed for challenge reset
        return new DoubleSummaryStatistics();
    }

    /**
     * TASK 3.4: Partitioning with Parallel
     *
     * Partition courses into two groups: expensive (price > 100) and cheap.
     * Use parallel stream for large dataset.
     *
     * @return Map with true=expensive, false=cheap
     */
    public Map<Boolean, List<Course>> partitionCoursesByPrice() {
        // Placeholder: original implementation removed for challenge reset
        return Collections.emptyMap();
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 4: Advanced Parallel Patterns
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 4.1: Custom Thread Pool
     *
     * By default, parallel streams use common ForkJoinPool.
     * Sometimes you want a custom pool for isolation.
     *
     * Process courses in custom ForkJoinPool with specified parallelism.
     *
     * @param parallelism Number of parallel threads
     * @return Processed course count
     */
    public int processWithCustomPool(int parallelism) {
        // Placeholder: original implementation removed for challenge reset
        return 0;
    }

    /**
     * TASK 4.2: Spliterator Control
     *
     * Count courses that can be efficiently split and processed in parallel.
     * Check if stream's spliterator has SIZED and SUBSIZED characteristics.
     *
     * @return Whether courses can be efficiently parallelized
     */
    public boolean canEfficientlyParallelize() {
        // Placeholder: original implementation removed for challenge reset
        return false;
    }

    /**
     * TASK 4.3: Parallel Reduce with Combiner
     *
     * Use three-argument reduce() to sum enrollment counts in parallel.
     * This version provides:
     * - Identity
     * - Accumulator
     * - Combiner (for parallel combining)
     *
     * @return Total enrollment count across all courses
     */
    public int sumEnrollmentCountsParallel() {
        // Placeholder: original implementation removed for challenge reset
        return 0;
    }

    /**
     * TASK 4.4: When NOT to Use Parallel
     *
     * Demonstrate that parallel streams can be SLOWER for small datasets.
     * Process 10 students with heavy computation: compare sequential vs parallel.
     *
     * @return Map with "sequential" and "parallel" times in ms
     */
    public Map<String, Long> compareSmallDatasetPerformance() {
        // Placeholder: original implementation removed for challenge reset
        return Collections.emptyMap();
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // HELPER METHODS
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * Check if number is prime (CPU-intensive).
     */
    private boolean isPrime(int n) {
        // Placeholder: original implementation removed for challenge reset
        return false;
    }

    /**
     * Simulate slow API call (100ms delay).
     */
    private Student simulateSlowAPICall(Student student) {
        // Placeholder: original implementation removed for challenge reset
        return null;
    }

    /**
     * Expensive calculation for benchmarking.
     */
    private double expensiveCalculation() {
        // Placeholder: original implementation removed for challenge reset
        return 0.0;
    }

    /**
     * Performance result wrapper.
     */
    public static class PerformanceResult<T> {
        private final T result;
        private final long timeMs;

        public PerformanceResult(T result, long timeMs) {
            this.result = result;
            this.timeMs = timeMs;
        }

        public T getResult() { return result; }
        public long getTimeMs() { return timeMs; }
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // MAIN METHOD - Testing Ground
    // ═══════════════════════════════════════════════════════════════════════════

    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("    Parallel Streams Challenge - EduMaster Platform    ");
        System.out.println("═══════════════════════════════════════════════════════\n");

        ParallelStreamChallenge challenge = new ParallelStreamChallenge();

        // Test 1.1: Prime counting parallel
        System.out.println("TEST 1.1: Parallel Prime Counting");
        long start = System.currentTimeMillis();
        long primeCount = challenge.countPrimesParallel(100000);
        long elapsed = System.currentTimeMillis() - start;
        System.out.println("Found " + primeCount + " primes in " + elapsed + "ms");
        System.out.println();

        // Test 1.2: Sequential vs Parallel benchmark
        System.out.println("TEST 1.2: Sequential vs Parallel Benchmark");
        long improvement = challenge.benchmarkPrimeCount(50000);
        System.out.println("Performance improvement: " + improvement + "ms");
        System.out.println();

        // Test 1.4: Total course value
        System.out.println("TEST 1.4: Calculate Total Course Value (Parallel)");
        start = System.currentTimeMillis();
        BigDecimal totalValue = challenge.calculateTotalCourseValue();
        elapsed = System.currentTimeMillis() - start;
        System.out.println("Total course value: $" + totalValue + " (took " + elapsed + "ms)");
        System.out.println();

        // Test 2.1: Race condition demo
        System.out.println("TEST 2.1: Race Condition Demo (BROKEN)");
        int unsafeSize = challenge.unsafeParallelAdd();
        System.out.println("Unsafe parallel add size: " + unsafeSize + " (expected 10000)");
        System.out.println("Notice: Size is often less than 10000 due to race condition!");
        System.out.println();

        // Test 2.2: Safe parallel collection
        System.out.println("TEST 2.2: Safe Parallel Collection (FIXED)");
        int safeSize = challenge.safeParallelAdd();
        System.out.println("Safe parallel add size: " + safeSize + " (correct!)");
        System.out.println();

        // Test 3.1: Grouping parallel
        System.out.println("TEST 3.1: Group Students by Level (Parallel)");
        start = System.currentTimeMillis();
        Map<StudentLevel, List<Student>> groups = challenge.groupStudentsByLevelParallel();
        elapsed = System.currentTimeMillis() - start;
        System.out.println("Grouped into " + groups.size() + " levels in " + elapsed + "ms");
        groups.forEach((level, students) ->
            System.out.println("  " + level + ": " + students.size() + " students"));
        System.out.println();

        // Test 3.2: Filter performance comparison
        System.out.println("TEST 3.2: Filter High Score Enrollments");
        PerformanceResult<List<Enrollment>> seqResult =
            challenge.filterHighScoreEnrollments(false);
        PerformanceResult<List<Enrollment>> parResult =
            challenge.filterHighScoreEnrollments(true);
        System.out.println("Sequential: " + seqResult.getResult().size() +
            " results in " + seqResult.getTimeMs() + "ms");
        System.out.println("Parallel: " + parResult.getResult().size() +
            " results in " + parResult.getTimeMs() + "ms");
        System.out.println();

        // Test 3.4: Partition by price
        System.out.println("TEST 3.4: Partition Courses by Price");
        start = System.currentTimeMillis();
        Map<Boolean, List<Course>> partition = challenge.partitionCoursesByPrice();
        elapsed = System.currentTimeMillis() - start;
        System.out.println("Expensive courses (>$100): " + partition.get(true).size());
        System.out.println("Cheap courses (≤$100): " + partition.get(false).size());
        System.out.println("Took " + elapsed + "ms");
        System.out.println();

        // Test 4.1: Custom thread pool
        System.out.println("TEST 4.1: Custom Thread Pool");
        int lessonCount = challenge.processWithCustomPool(4);
        System.out.println("Total lessons across all courses: " + lessonCount);
        System.out.println();

        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("Complete remaining tasks to test more functionality!");
        System.out.println("═══════════════════════════════════════════════════════");
    }
}
