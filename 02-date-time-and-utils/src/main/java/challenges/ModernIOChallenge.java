package challenges;

import data.SampleDataGenerator;
import model.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * ═══════════════════════════════════════════════════════════════════════════════
 * CHALLENGE 04: Modern I/O (Java 11+)
 * ═══════════════════════════════════════════════════════════════════════════════
 *
 * SCENARIO:
 * You are building the EduMaster logging and reporting system. The platform
 * generates daily activity logs, course exports, and user reports that need to be
 * efficiently read and written. The new Files API methods in Java 11 provide
 * simple, convenient ways to work with text files without boilerplate.
 *
 * FOCUS:
 * - Files.readString() / Files.writeString() for simple file operations
 * - Files.lines() for streaming large files
 * - StandardOpenOption for append operations
 * - Path API for file location management
 * - Processing course data and generating reports
 * - Working with log files and activity tracking
 *
 * ═══════════════════════════════════════════════════════════════════════════════
 */
public class ModernIOChallenge {

    private final List<Student> students;
    private final List<Course> courses;
    private final List<Enrollment> enrollments;
    private final Path workingDirectory;

    public ModernIOChallenge() {
        this.students = SampleDataGenerator.generateStudents(100);
        var instructors = SampleDataGenerator.generateInstructors(20);
        this.courses = SampleDataGenerator.generateCourses(50, instructors);
        this.enrollments = SampleDataGenerator.generateEnrollments(students, courses, 200);
        this.workingDirectory = Paths.get(System.getProperty("user.dir"), "temp");

        // Create working directory if it doesn't exist
        try {
            Files.createDirectories(workingDirectory);
        } catch (IOException e) {
            System.err.println("Failed to create working directory: " + e.getMessage());
        }
    }

    // ═══════════════════════════════════════════════════════════════════════════════
    // PART 1: Basic File Reading & Writing
    // ═══════════════════════════════════════════════════════════════════════════════

    /**
     * TASK 1.1: Read Entire File
     *
     * Read the entire contents of a text file into a String.
     * This is perfect for small configuration files or reports.
     *
     * HINT: Use Files.readString(path)
     *
     * EXAMPLE:
     * Input: Path to "config.txt" containing "timeout=5000"
     * Output: "timeout=5000"
     *
     * @param path Path to the file to read
     * @return File contents as a String
     * @throws IOException if file cannot be read
     */
    public String readFileContent(Path path) throws IOException {
        // placeholder - original solution removed
        return null;
    }

    /**
     * TASK 1.2: Write Entire File
     *
     * Write a String to a file, overwriting if it exists.
     * Used for exporting course data, generating reports, or saving configurations.
     *
     * HINT: Use Files.writeString(path, content)
     *
     * EXAMPLE:
     * Input: path="report.txt", content="Total students: 100"
     * Output: File is created/overwritten with the content
     *
     * @param path Path to the file to write
     * @param content Content to write
     * @throws IOException if file cannot be written
     */
    public void writeFileContent(Path path, String content) throws IOException {
        // placeholder - original solution removed
        // no-op
    }

    /**
     * TASK 1.3: Append to File
     *
     * Append content to an existing file without overwriting.
     * Essential for logging systems that continuously add entries.
     *
     * HINT: Use Files.writeString(path, content, StandardOpenOption.APPEND)
     *
     * EXAMPLE:
     * Input: path="activity.log", content="[2024-01-08] User logged in\n"
     * Output: Content is appended to the end of the file
     *
     * @param path Path to the file
     * @param content Content to append
     * @throws IOException if file cannot be written
     */
    public void appendToFile(Path path, String content) throws IOException {
        // placeholder - original solution removed
        // no-op
    }

    // ═══════════════════════════════════════════════════════════════════════════════
    // PART 2: Streaming Large Files
    // ═══════════════════════════════════════════════════════════════════════════════

    /**
     * TASK 2.1: Count Error Lines
     *
     * Count how many lines in a log file contain the word "ERROR".
     * Uses streaming to handle large files efficiently without loading entire file.
     *
     * HINT: Use Files.lines(path).filter(...).count()
     *
     * EXAMPLE:
     * Input: Log file with 1000 lines, 15 contain "ERROR"
     * Output: 15
     *
     * @param path Path to log file
     * @return Number of lines containing "ERROR"
     * @throws IOException if file cannot be read
     */
    public long countErrorLines(Path path) throws IOException {
        // placeholder - original solution removed
        return 0;
    }

    /**
     * TASK 2.2: Find Lines by Pattern
     *
     * Find all lines in a file that contain a specific pattern.
     * Used for searching through logs or filtering data files.
     *
     * HINT: Use Files.lines(path).filter(...).toList()
     *
     * EXAMPLE:
     * Input: Log file, pattern="enrollment"
     * Output: List of all lines containing "enrollment"
     *
     * @param path Path to file
     * @param pattern Pattern to search for
     * @return List of matching lines
     * @throws IOException if file cannot be read
     */
    public List<String> findLinesByPattern(Path path, String pattern) throws IOException {
        try (Stream<String> lines = Files.lines(path)) {
            return lines.filter(line -> line.contains(pattern)).toList();
        }
    }

    /**
     * TASK 2.3: Count Total Lines
     *
     * Count the total number of lines in a file.
     * Useful for file statistics and progress tracking.
     *
     * HINT: Use Files.lines(path).count()
     *
     * EXAMPLE:
     * Input: File with 500 lines
     * Output: 500
     *
     * @param path Path to file
     * @return Total number of lines
     * @throws IOException if file cannot be read
     */
    public long countTotalLines(Path path) throws IOException {
        // placeholder - original solution removed
        return 0;
    }

    // ═══════════════════════════════════════════════════════════════════════════════
    // PART 3: Working with Course Data Files
    // ═══════════════════════════════════════════════════════════════════════════════

    /**
     * TASK 3.1: Generate Course Report
     *
     * Generate a text report of all courses with their titles, categories,
     * and enrollment counts. Write it to a file.
     *
     * EXAMPLE:
     * Output file format:
     * ═══ EDUMASTER COURSE REPORT ═══
     * Total Courses: 50
     *
     * Course: Introduction to Java
     * Category: PROGRAMMING
     * Enrollments: 25
     * ---
     *
     * @param outputPath Path where report should be written
     * @throws IOException if file cannot be written
     */
    public void generateCourseReport(Path outputPath) throws IOException {
        // placeholder - original solution removed
        // no-op
    }

    /**
     * TASK 3.2: Export Course Titles
     *
     * Export just the course titles to a file, one per line.
     * This simple format is useful for importing into other systems.
     *
     * HINT: Stream courses, map to titles, collect with joining("\n"), then write
     *
     * EXAMPLE:
     * Output:
     * Introduction to Java
     * Python for Data Science
     * Web Development Bootcamp
     *
     * @param outputPath Path where titles should be written
     * @throws IOException if file cannot be written
     */
    public void exportCourseTitles(Path outputPath) throws IOException {
        // placeholder - original solution removed
        // no-op
    }

    /**
     * TASK 3.3: Generate Daily Activity Log Entry
     *
     * Create a log entry for today's enrollment activity and append to log file.
     * Each entry shows date, number of active enrollments, and new enrollments.
     *
     * EXAMPLE:
     * Log entry format:
     * [2024-01-08 14:30:00] ACTIVITY - Active: 150, Completed: 30, New Today: 5
     *
     * @param logPath Path to activity log file
     * @throws IOException if file cannot be written
     */
    public void logDailyActivity(Path logPath) throws IOException {
        // placeholder - original solution removed
        // no-op
    }

    // ═══════════════════════════════════════════════════════════════════════════════
    // PART 4: Sample Data Generation & Testing
    // ═══════════════════════════════════════════════════════════════════════════════

    /**
     * TASK 4.1: Generate Sample Log File
     *
     * Create a sample log file with INFO, WARNING, and ERROR entries.
     * This simulates real application logs for testing log processing.
     *
     * EXAMPLE:
     * [2024-01-08 09:00:00] INFO - System started
     * [2024-01-08 09:15:30] ERROR - Failed to connect to database
     * [2024-01-08 09:16:00] WARNING - Retrying connection
     *
     * @param logPath Path where log should be created
     * @throws IOException if file cannot be written
     */
    public void generateSampleLogFile(Path logPath) throws IOException {
        // placeholder - original solution removed
        // no-op
    }

    /**
     * TASK 4.2: Parse Configuration File
     *
     * Read a configuration file and parse it into a map-like structure.
     * Each line is in format "key=value".
     *
     * HINT: Use Files.lines(), filter non-empty/non-comment lines, parse key=value
     *
     * EXAMPLE:
     * Input file:
     * # Database Configuration
     * db.host=localhost
     * db.port=5432
     *
     * Output: String representation of parsed config
     *
     * @param configPath Path to configuration file
     * @return Summary of configuration
     * @throws IOException if file cannot be read
     */
    public String parseConfiguration(Path configPath) throws IOException {
        // placeholder - original solution removed
        return null;
    }

    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("  Modern I/O Operations - Test Suite");
        System.out.println("═══════════════════════════════════════════════════════════");

        var challenge = new ModernIOChallenge();
        Path testDir = challenge.workingDirectory;

        try {
            // Test PART 1: Basic File Operations
            System.out.println("\n--- PART 1: Basic File Reading & Writing ---");
            Path testFile = testDir.resolve("test.txt");
            challenge.writeFileContent(testFile, "Hello EduMaster!\n");
            System.out.println("Written to file: " + testFile);

            String content = challenge.readFileContent(testFile);
            System.out.println("Read from file: " + content.trim());

            challenge.appendToFile(testFile, "Appended line!\n");
            System.out.println("Content after append: " + challenge.readFileContent(testFile));

            // Test PART 2: Streaming Large Files
            System.out.println("\n--- PART 2: Streaming Large Files ---");
            Path logFile = testDir.resolve("application.log");
            challenge.generateSampleLogFile(logFile);
            System.out.println("Sample log file created: " + logFile);

            long errorCount = challenge.countErrorLines(logFile);
            System.out.println("Total ERROR lines: " + errorCount);

            List<String> warningLines = challenge.findLinesByPattern(logFile, "WARNING");
            System.out.println("WARNING lines found: " + warningLines.size());

            // Test PART 3: Working with Course Data
            System.out.println("\n--- PART 3: Working with Course Data Files ---");
            Path reportFile = testDir.resolve("course_report.txt");
            challenge.generateCourseReport(reportFile);
            System.out.println("Course report generated: " + reportFile);
            System.out.println("Report size: " + Files.size(reportFile) + " bytes");

            // Show sample from report
            List<String> reportLines = Files.readAllLines(reportFile);
            System.out.println("Report preview (first 10 lines):");
            reportLines.stream().limit(10).forEach(line -> System.out.println("  " + line));

            System.out.println("\n--- Test Files Created ---");
            System.out.println("Working directory: " + testDir);
            System.out.println("Files created:");
            Files.list(testDir).forEach(path ->
                System.out.println("  - " + path.getFileName())
            );

        } catch (IOException e) {
            System.err.println("I/O Error: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n═══════════════════════════════════════════════════════════");
    }
}
