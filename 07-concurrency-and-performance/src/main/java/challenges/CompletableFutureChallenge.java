package challenges;

import data.SampleDataGenerator;
import model.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * ═══════════════════════════════════════════════════════════════════════════════
 * CHALLENGE 01: CompletableFuture - Asynchronous Programming
 * ═══════════════════════════════════════════════════════════════════════════════
 *
 * SCENARIO:
 * You are building the Dashboard & Analytics Module for EduMaster that needs to
 * fetch data from multiple "slow" services (database, cache, external APIs)
 * independently and combine the results efficiently.
 *
 * FOCUS:
 * - CompletableFuture.supplyAsync() - run async tasks
 * - thenApply/thenAccept/thenRun - chaining transformations
 * - thenCombine/thenCompose - combining multiple futures
 * - allOf/anyOf - waiting for multiple futures
 * - exceptionally/handle - error handling in async pipelines
 *
 * ═══════════════════════════════════════════════════════════════════════════════
 */
public class CompletableFutureChallenge {

    private final List<Student> students;
    private final List<Course> courses;
    private final List<Enrollment> enrollments;
    private final List<Instructor> instructors;
    private final ExecutorService executor;

    public CompletableFutureChallenge() {
        this.students = SampleDataGenerator.generateStudents(100);
        this.instructors = SampleDataGenerator.generateInstructors(20);
        this.courses = SampleDataGenerator.generateCourses(50, instructors);
        this.enrollments = SampleDataGenerator.generateEnrollments(students, courses, 200);
        this.executor = Executors.newFixedThreadPool(10);
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 1: Async Execution & Basic Chaining
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 1.1: Simple Async Task
     *
     * Run a task asynchronously that simulates a 1-second delay and returns "Done".
     * Use CompletableFuture.supplyAsync() with common ForkJoinPool.
     *
     * EXAMPLE:
     * CompletableFuture<String> future = runAsyncJob();
     * future.get(); // Returns "Done" after ~1 second
     */
    public CompletableFuture<String> runAsyncJob() {
        // Placeholder: original implementation removed for challenge reset
        return CompletableFuture.completedFuture(null);
    }

    /**
     * TASK 1.2: Async Student Data Fetch
     *
     * Simulate fetching student profile from database with 500ms delay.
     * Return the student with highest average score.
     *
     * EXAMPLE:
     * CompletableFuture<Student> future = fetchTopStudent();
     * Student topStudent = future.get(); // Returns best student after delay
     */
    public CompletableFuture<Student> fetchTopStudent() {
        // TODO: supplyAsync with delay, find student with max averageScore
        return null;
    }

    /**
     * TASK 1.3: Chaining Transformation (thenApply)
     *
     * 1. Fetch top student asynchronously (500ms delay)
     * 2. Transform to uppercase full name (sync)
     * 3. Add prefix "TOP STUDENT: " (sync)
     *
     * Use thenApply for synchronous transformations.
     *
     * EXAMPLE:
     * CompletableFuture<String> future = getTopStudentNameFormatted();
     * future.get(); // Returns "TOP STUDENT: JOHN DOE"
     */
    public CompletableFuture<String> getTopStudentNameFormatted() {
        return fetchTopStudent()
            .thenApply(student -> student.getFullName().toUpperCase())
            .thenApply(name -> "TOP STUDENT: " + name);
    }

    /**
     * TASK 1.4: Chaining with Side Effects (thenAccept)
     *
     * Fetch top student and print their details to console.
     * Use thenAccept (returns CompletableFuture<Void>).
     *
     * EXAMPLE:
     * CompletableFuture<Void> future = printTopStudent();
     * future.get(); // Prints student details
     */
    public CompletableFuture<Void> printTopStudent() {
        // TODO: fetchTopStudent().thenAccept(s -> System.out.println(...))
        return null;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 2: Combining Multiple Futures
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 2.1: Parallel Data Fetch (thenCombine)
     *
     * Fetch student profile AND course catalog in parallel.
     * When BOTH complete, combine into summary string:
     * "Student: [name] - Total Courses: [count]"
     *
     * Student fetch delay: 500ms
     * Course count delay: 300ms
     * Total time should be ~500ms (not 800ms!)
     *
     * EXAMPLE:
     * CompletableFuture<String> future = combineStudentAndCourseData();
     * future.get(); // Returns combined string after ~500ms
     */
    public CompletableFuture<String> combineStudentAndCourseData() {
        CompletableFuture<Student> studentFuture = fetchTopStudent();
        // Placeholder: original implementation removed for challenge reset
        return CompletableFuture.completedFuture(null);
    }

    /**
     * TASK 2.2: Sequential Dependent Async (thenCompose)
     *
     * 1. Fetch a student by ID (async, 300ms)
     * 2. Then fetch their enrollments (async, 400ms) - depends on student
     *
     * Use thenCompose (not thenApply!) to flatten nested futures.
     *
     * @param studentId Student identifier
     * @return Future with list of enrollments
     */
    public CompletableFuture<List<Enrollment>> fetchStudentEnrollments(String studentId) {
        // TODO: Fetch student, then compose with enrollment fetch
        return null;
    }

    /**
     * TASK 2.3: Dashboard Data Aggregation
     *
     * Fetch three pieces of data in parallel:
     * 1. Total student count (200ms delay)
     * 2. Total course count (150ms delay)
     * 3. Total enrollment count (250ms delay)
     *
     * Combine into Map<String, Integer>:
     * {"students": X, "courses": Y, "enrollments": Z}
     *
     * EXAMPLE:
     * CompletableFuture<Map<String, Integer>> future = fetchDashboardStats();
     * Map<String, Integer> stats = future.get();
     * // Returns all stats after ~250ms (longest delay)
     */
    public CompletableFuture<Map<String, Integer>> fetchDashboardStats() {
        // TODO: Three parallel supplyAsync calls, combine with thenCombine
        return null;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 3: Waiting for Multiple Futures
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 3.1: Wait for All (allOf)
     *
     * Given a list of course IDs, fetch each course's details asynchronously.
     * Wait for ALL fetches to complete.
     * Return list of all courses.
     *
     * Each fetch has 100ms delay.
     *
     * EXAMPLE:
     * List<String> ids = List.of("c1", "c2", "c3");
     * CompletableFuture<List<Course>> future = fetchAllCourses(ids);
     * List<Course> courses = future.get(); // All 3 courses after ~100ms
     */
    public CompletableFuture<List<Course>> fetchAllCourses(List<String> courseIds) {
        // Placeholder: original implementation removed for challenge reset
        return CompletableFuture.completedFuture(Collections.emptyList());
    }

    /**
     * TASK 3.2: Wait for Any (anyOf)
     *
     * Query multiple data sources (simulated with different delays).
     * Return the result from whichever source responds first.
     *
     * Sources:
     * - Primary DB: 500ms delay
     * - Cache: 100ms delay (should win!)
     * - Backup DB: 800ms delay
     *
     * @return Future with first available course count
     */
    public CompletableFuture<Integer> fetchCourseCountFromFastestSource() {
        // TODO: Create 3 futures with different delays, use anyOf
        return null;
    }

    /**
     * TASK 3.3: Batch Processing
     *
     * Process a batch of students asynchronously (send welcome emails).
     * Each email takes 50ms to send.
     * Wait for all emails to be sent.
     *
     * @param studentIds List of student identifiers
     * @return Future that completes when all emails sent
     */
    public CompletableFuture<Void> sendBulkEmails(List<String> studentIds) {
        // TODO: Map to futures, use allOf
        return null;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 4: Error Handling
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 4.1: Handle Errors (exceptionally)
     *
     * Fetch student data that may fail (50% chance of exception).
     * If it fails, return a default "Guest Student".
     *
     * Use exceptionally() to provide fallback.
     *
     * EXAMPLE:
     * CompletableFuture<Student> future = fetchStudentWithFallback("s1");
     * Student student = future.get(); // Either real student or guest
     */
    public CompletableFuture<Student> fetchStudentWithFallback(String studentId) {
        // TODO: Simulate fetch with random failure, add exceptionally
        return null;
    }

    /**
     * TASK 4.2: Handle with Result (handle)
     *
     * Fetch course price (may fail).
     * Return price if successful, or BigDecimal.ZERO if failed.
     *
     * Use handle((result, exception) -> ...) to handle both cases.
     *
     * EXAMPLE:
     * CompletableFuture<BigDecimal> future = fetchCoursePrice("c1");
     * BigDecimal price = future.get(); // Either price or 0.00
     */
    public CompletableFuture<BigDecimal> fetchCoursePrice(String courseId) {
        // TODO: Simulate fetch with potential failure, use handle
        return null;
    }

    /**
     * TASK 4.3: Timeout Handling
     *
     * Fetch student data with 2-second timeout.
     * If not completed in time, throw TimeoutException.
     *
     * Use orTimeout() (Java 9+) or completeOnTimeout().
     *
     * @param studentId Student identifier
     * @param timeoutSeconds Timeout in seconds
     * @return Future that may timeout
     */
    public CompletableFuture<Student> fetchStudentWithTimeout(String studentId, long timeoutSeconds) {
        // TODO: Add timeout to async operation
        return null;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 5: Advanced Patterns
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 5.1: Async Pipeline with Custom Executor
     *
     * Build a complete async pipeline using custom executor:
     * 1. Fetch student list (async, 300ms)
     * 2. Filter active students (sync)
     * 3. Calculate average score (sync)
     * 4. Format as "Average: XX.XX" (sync)
     *
     * Use the instance executor field.
     *
     * EXAMPLE:
     * CompletableFuture<String> future = calculateAverageScoreAsync();
     * String result = future.get(); // "Average: 85.50"
     */
    public CompletableFuture<String> calculateAverageScoreAsync() {
        // TODO: supplyAsync with executor, chain transformations
        return null;
    }

    /**
     * TASK 5.2: Race Condition Demo
     *
     * Create two futures that modify shared counter (intentionally unsafe).
     * Run them 1000 times each.
     * Show that final count is usually less than 2000 (race condition).
     *
     * This demonstrates why shared mutable state is dangerous in async code.
     *
     * @return Future with final counter value (likely < 2000)
     */
    public CompletableFuture<Integer> demonstrateRaceCondition() {
        // TODO: Two futures incrementing shared counter
        return null;
    }

    /**
     * TASK 5.3: Fan-Out / Fan-In Pattern
     *
     * For each course:
     * 1. Fetch enrollment count asynchronously (100ms each)
     * 2. Wait for all fetches to complete
     * 3. Sum all enrollment counts
     *
     * This is a common scatter-gather pattern.
     *
     * EXAMPLE:
     * CompletableFuture<Integer> future = getTotalEnrollmentCount();
     * Integer total = future.get(); // Sum of all enrollments
     */
    public CompletableFuture<Integer> getTotalEnrollmentCount() {
        // TODO: Map courses to futures, allOf, then sum
        return null;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // HELPER METHODS (Simulated Async Operations)
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * Simulates fetching a course by ID with network delay.
     */
    private CompletableFuture<Course> fetchCourseById(String courseId) {
        // Placeholder: original implementation removed for challenge reset
        return CompletableFuture.completedFuture(null);
    }

    /**
     * Simulates sending email to student.
     */
    private CompletableFuture<Void> sendEmailToStudent(String studentId) {
        // Placeholder: original implementation removed for challenge reset
        return CompletableFuture.completedFuture(null);
    }

    /**
     * Cleanup executor on shutdown.
     */
    public void shutdown() {
        executor.shutdown();
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // MAIN METHOD - Testing Ground
    // ═══════════════════════════════════════════════════════════════════════════

    public static void main(String[] args) throws Exception {
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("    CompletableFuture Challenge - EduMaster Platform    ");
        System.out.println("═══════════════════════════════════════════════════════\n");

        CompletableFutureChallenge challenge = new CompletableFutureChallenge();

        // Test 1.1: Simple async job
        System.out.println("TEST 1.1: Simple Async Job");
        long start = System.currentTimeMillis();
        CompletableFuture<String> job = challenge.runAsyncJob();
        String result = job.get();
        long elapsed = System.currentTimeMillis() - start;
        System.out.println("Result: " + result + " (took " + elapsed + "ms)");
        System.out.println();

        // Test 1.3: Chaining transformations
        System.out.println("TEST 1.3: Chaining Transformations");
        start = System.currentTimeMillis();
        CompletableFuture<String> formatted = challenge.getTopStudentNameFormatted();
        String topStudent = formatted.get();
        elapsed = System.currentTimeMillis() - start;
        System.out.println("Result: " + topStudent + " (took " + elapsed + "ms)");
        System.out.println();

        // Test 2.1: Combining futures
        System.out.println("TEST 2.1: Combining Futures (Parallel)");
        start = System.currentTimeMillis();
        CompletableFuture<String> combined = challenge.combineStudentAndCourseData();
        String combinedResult = combined.get();
        elapsed = System.currentTimeMillis() - start;
        System.out.println("Result: " + combinedResult + " (took " + elapsed + "ms)");
        System.out.println("Expected: ~500ms (not 800ms!)");
        System.out.println();

        // Test 3.1: Wait for all
        System.out.println("TEST 3.1: Wait for All Courses");
        List<String> courseIds = challenge.courses.stream()
            .limit(5)
            .map(Course::getId)
            .collect(Collectors.toList());
        start = System.currentTimeMillis();
        CompletableFuture<List<Course>> allCourses = challenge.fetchAllCourses(courseIds);
        List<Course> fetchedCourses = allCourses.get();
        elapsed = System.currentTimeMillis() - start;
        System.out.println("Fetched " + fetchedCourses.size() + " courses (took " + elapsed + "ms)");
        System.out.println("Expected: ~100ms (parallel fetch)");
        System.out.println();

        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("Complete remaining tasks to test more functionality!");
        System.out.println("═══════════════════════════════════════════════════════");

        challenge.shutdown();
    }
}
