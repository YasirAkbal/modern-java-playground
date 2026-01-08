package challenges;

import data.SampleDataGenerator;
import model.*;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

/**
 * ═══════════════════════════════════════════════════════════════════════════════
 * CHALLENGE 03: Virtual Threads - Lightweight Concurrency (Java 21+)
 * ═══════════════════════════════════════════════════════════════════════════════
 *
 * SCENARIO:
 * You are modernizing the EduMaster Platform to handle massive concurrent I/O
 * operations (database queries, API calls, file operations). Traditional platform
 * threads are expensive (1MB stack each, OS-managed). Virtual threads are
 * lightweight user-mode threads that enable massive concurrency.
 *
 * FOCUS:
 * - Thread.ofVirtual() - creating virtual threads
 * - Executors.newVirtualThreadPerTaskExecutor() - executor for virtual threads
 * - Structured Concurrency - managing thread lifecycles
 * - Performance comparison: Platform threads vs Virtual threads
 * - Blocking operations - where virtual threads shine
 *
 * ═══════════════════════════════════════════════════════════════════════════════
 */
public class VirtualThreadsChallenge {

    private final List<Student> students;
    private final List<Course> courses;
    private final List<Enrollment> enrollments;
    private final List<Instructor> instructors;

    public VirtualThreadsChallenge() {
        this.students = SampleDataGenerator.generateStudents(500);
        this.instructors = SampleDataGenerator.generateInstructors(50);
        this.courses = SampleDataGenerator.generateCourses(200, instructors);
        this.enrollments = SampleDataGenerator.generateEnrollments(students, courses, 2000);
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 1: Virtual Thread Basics
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 1.1: Create Single Virtual Thread
     *
     * Start a single virtual thread that prints "Hello from Virtual Thread!"
     * and the thread's name.
     *
     * Use Thread.ofVirtual().start(Runnable)
     *
     * EXAMPLE:
     * startOneVirtualThread();
     * // Output: Hello from Virtual Thread! (VirtualThread[#21]/runnable@ForkJoinPool...)
     */
    public void startOneVirtualThread() throws InterruptedException {
        // Placeholder: original implementation removed for challenge reset
    }

    /**
     * TASK 1.2: Named Virtual Thread
     *
     * Create a virtual thread with a custom name.
     * Use Thread.ofVirtual().name("custom-name").start(...)
     *
     * @param threadName Name for the virtual thread
     * @param task Task to execute
     */
    public void startNamedVirtualThread(String threadName, Runnable task) throws InterruptedException {
        // Placeholder: original implementation removed for challenge reset
    }

    /**
     * TASK 1.3: Virtual Thread Factory
     *
     * Create a ThreadFactory that produces virtual threads.
     * Use Thread.ofVirtual().factory()
     *
     * This is useful for integration with existing executor-based code.
     *
     * @return ThreadFactory for virtual threads
     */
    public ThreadFactory createVirtualThreadFactory() {
        // Placeholder: original implementation removed for challenge reset
        return null;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 2: Massive Concurrency with Virtual Threads
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 2.1: 10,000 Concurrent Tasks (Virtual Threads)
     *
     * Create an ExecutorService using newVirtualThreadPerTaskExecutor.
     * Submit 10,000 tasks that sleep for 10ms each.
     * Measure total time (should be very fast due to non-blocking sleep).
     *
     * Expected: ~10-20ms (NOT 100 seconds!)
     *
     * EXAMPLE:
     * long time = runMassiveConcurrentTasks();
     * System.out.println("Completed 10,000 tasks in " + time + "ms");
     */
    public long runMassiveConcurrentTasks() throws InterruptedException {
        // Placeholder: original implementation removed for challenge reset
        return 0;
    }

    /**
     * TASK 2.2: Virtual vs Platform Thread Comparison
     *
     * Compare performance of 1000 blocking tasks:
     * - Platform threads (fixed pool of 10)
     * - Virtual threads (one per task)
     *
     * Each task blocks for 50ms (simulating I/O).
     *
     * Expected:
     * - Platform: ~5000ms (1000 tasks / 10 threads * 50ms)
     * - Virtual: ~50ms (all parallel)
     *
     * @return Map with "platform" and "virtual" times in ms
     */
    public Map<String, Long> compareVirtualVsPlatformThreads() throws InterruptedException, ExecutionException {
        // Placeholder: original implementation removed for challenge reset
        return Collections.emptyMap();
    }

    /**
     * TASK 2.3: Database Query Simulation
     *
     * Simulate fetching student details from database (100ms per query).
     * Fetch all students concurrently using virtual threads.
     *
     * With 500 students:
     * - Sequential: 50 seconds
     * - Virtual threads: ~100ms
     *
     * @return Time taken in milliseconds
     */
    public long fetchAllStudentsConcurrently() {
        // Placeholder: original implementation removed for challenge reset
        return 0;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 3: Blocking Operations (Virtual Threads Advantage)
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 3.1: HTTP API Calls Simulation
     *
     * Simulate calling external API for each course (200ms per call).
     * Use virtual threads to make all calls concurrently.
     *
     * Traditional approach: Use async libraries (CompletableFuture, etc.)
     * Virtual threads: Write simple blocking code that runs efficiently!
     *
     * @return List of course enrichment results
     */
    public List<String> enrichCoursesWithExternalData() {
        // Placeholder: original implementation removed for challenge reset
        return Collections.emptyList();
    }

    /**
     * TASK 3.2: File I/O Operations
     *
     * Simulate reading student records from slow storage (50ms per read).
     * Process all students concurrently.
     *
     * @return Count of processed students
     */
    public int processStudentFiles() throws InterruptedException {
        // Placeholder: original implementation removed for challenge reset
        return 0;
    }

    /**
     * TASK 3.3: Database Transaction Pattern
     *
     * Simulate complex workflow for each enrollment:
     * 1. Fetch student (50ms)
     * 2. Fetch course (50ms)
     * 3. Validate enrollment (30ms)
     * 4. Save to database (70ms)
     *
     * Total: 200ms per enrollment, but all run concurrently!
     *
     * @param enrollmentCount Number of enrollments to process
     * @return Processing time in milliseconds
     */
    public long processEnrollmentWorkflows(int enrollmentCount) {
        // Placeholder: original implementation removed for challenge reset
        return 0;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 4: Structured Concurrency (Preview Feature)
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 4.1: Fork-Join Pattern with Virtual Threads
     *
     * Process students in batches using fork-join pattern:
     * 1. Split students into batches
     * 2. Process each batch in virtual thread
     * 3. Collect results
     *
     * @param batchSize Size of each batch
     * @return Total processed count
     */
    public int processBatchesWithVirtualThreads(int batchSize) throws InterruptedException {
        // Placeholder: original implementation removed for challenge reset
        return 0;
    }

    /**
     * TASK 4.2: Timeout with Virtual Threads
     *
     * Execute tasks with timeout using virtual threads.
     * If any task takes longer than timeout, cancel all remaining tasks.
     *
     * @param timeoutMs Timeout in milliseconds
     * @return Number of completed tasks before timeout
     */
    public int executeWithTimeout(long timeoutMs) {
        // Placeholder: original implementation removed for challenge reset
        return 0;
    }

    /**
     * TASK 4.3: Error Handling Pattern
     *
     * Process all courses, but some may fail (10% failure rate).
     * Continue processing even if some tasks fail.
     * Return both successful and failed counts.
     *
     * @return Map with "success" and "failure" counts
     */
    public Map<String, Integer> processWithErrorHandling() {
        // Placeholder: original implementation removed for challenge reset
        return Collections.emptyMap();
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 5: Performance Analysis
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 5.1: Thread Pool Sizing Analysis
     *
     * Compare different approaches for 500 blocking tasks (100ms each):
     * 1. Fixed thread pool (10 threads)
     * 2. Fixed thread pool (100 threads)
     * 3. Virtual threads
     *
     * @return Map with approach name and time in ms
     */
    public Map<String, Long> analyzeThreadPoolSizing() throws InterruptedException, ExecutionException {
        // Placeholder: original implementation removed for challenge reset
        return Collections.emptyMap();
    }

    /**
     * TASK 5.2: Memory Footprint Demo
     *
     * Create many idle threads to show memory difference:
     * - Platform threads: ~1MB per thread
     * - Virtual threads: ~1KB per thread
     *
     * Create 10,000 threads and measure memory usage.
     *
     * Note: This is a demonstration only. In production, use monitoring tools.
     *
     * @return Memory used in MB (approximate)
     */
    public long measureMemoryFootprint() {
        // Placeholder: original implementation removed for challenge reset
        return 0;
    }

    /**
     * TASK 5.3: CPU-Bound vs I/O-Bound
     *
     * Demonstrate that virtual threads are NOT beneficial for CPU-bound tasks.
     *
     * Compare:
     * 1. CPU-bound task (prime calculation) - virtual threads no benefit
     * 2. I/O-bound task (sleep/blocking) - virtual threads huge benefit
     *
     * @return Map with task type and performance ratio
     */
    public Map<String, Double> compareCpuVsIOBound() {
        // Placeholder: original implementation removed for challenge reset
        return Collections.emptyMap();
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 6: Migration Patterns
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 6.1: Legacy Code Migration
     *
     * Show how to migrate from traditional ExecutorService to virtual threads.
     * Process enrollment updates using both approaches.
     *
     * @param useVirtual Whether to use virtual threads
     * @return Processing time in ms
     */
    public long migrateToVirtualThreads(boolean useVirtual) throws InterruptedException {
        // Placeholder: original implementation removed for challenge reset
        return 0;
    }

    /**
     * TASK 6.2: Virtual Thread Pinning Warning
     *
     * Demonstrate thread pinning - when virtual threads cannot be unmounted.
     * Causes:
     * 1. synchronized blocks
     * 2. native method calls
     *
     * Use ReentrantLock instead of synchronized for better virtual thread support.
     *
     * @return Processing time difference between synchronized and Lock
     */
    public Map<String, Long> demonstrateThreadPinning() {
        // Placeholder: original implementation removed for challenge reset
        return Collections.emptyMap();
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // HELPER METHODS
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * Simulate database query (100ms delay).
     */
    private Student simulateDatabaseQuery(Student student) {
        // Placeholder: original implementation removed for challenge reset
        return null;
    }

    /**
     * Simulate external API call (200ms delay).
     */
    private String simulateAPICall(Course course) {
        // Placeholder: original implementation removed for challenge reset
        return null;
    }

    /**
     * Check if number is prime (CPU-intensive).
     */
    private boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // MAIN METHOD - Testing Ground
    // ═══════════════════════════════════════════════════════════════════════════

    public static void main(String[] args) throws Exception {
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("    Virtual Threads Challenge - EduMaster Platform     ");
        System.out.println("═══════════════════════════════════════════════════════\n");

        VirtualThreadsChallenge challenge = new VirtualThreadsChallenge();

        // Test 1.1: Single virtual thread
        System.out.println("TEST 1.1: Single Virtual Thread");
        challenge.startOneVirtualThread();
        System.out.println();

        // Test 2.1: Massive concurrency
        System.out.println("TEST 2.1: 10,000 Concurrent Tasks (Virtual Threads)");
        long time = challenge.runMassiveConcurrentTasks();
        System.out.println("Completed 10,000 tasks in " + time + "ms");
        System.out.println("Expected: ~10-50ms (NOT 100 seconds!)");
        System.out.println();

        // Test 2.2: Virtual vs Platform comparison
        System.out.println("TEST 2.2: Virtual vs Platform Thread Comparison");
        Map<String, Long> comparison = challenge.compareVirtualVsPlatformThreads();
        System.out.println("Platform threads (10 pool): " + comparison.get("platform") + "ms");
        System.out.println("Virtual threads: " + comparison.get("virtual") + "ms");
        System.out.println("Speedup: " + (comparison.get("platform") / (double) comparison.get("virtual")) + "x");
        System.out.println();

        // Test 3.2: File I/O operations
        System.out.println("TEST 3.2: File I/O Operations (Virtual Threads)");
        long start = System.currentTimeMillis();
        int processedCount = challenge.processStudentFiles();
        long elapsed = System.currentTimeMillis() - start;
        System.out.println("Processed " + processedCount + " students in " + elapsed + "ms");
        System.out.println("Expected: ~50ms (all concurrent)");
        System.out.println();

        // Test 6.1: Migration comparison
        System.out.println("TEST 6.1: Legacy to Virtual Thread Migration");
        long platformTime = challenge.migrateToVirtualThreads(false);
        long virtualTime = challenge.migrateToVirtualThreads(true);
        System.out.println("Platform threads: " + platformTime + "ms");
        System.out.println("Virtual threads: " + virtualTime + "ms");
        System.out.println("Improvement: " + (platformTime - virtualTime) + "ms");
        System.out.println();

        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("Complete remaining tasks to test more functionality!");
        System.out.println("                                                       ");
        System.out.println("Key Takeaways:");
        System.out.println("✓ Virtual threads are lightweight (1KB vs 1MB)");
        System.out.println("✓ Enable massive concurrency (millions of threads)");
        System.out.println("✓ Perfect for I/O-bound tasks (DB, API, file I/O)");
        System.out.println("✓ NOT beneficial for CPU-bound tasks");
        System.out.println("✓ Simplify code - write blocking code that scales!");
        System.out.println("═══════════════════════════════════════════════════════");
    }
}
