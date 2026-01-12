package challenges;

import data.SampleDataGenerator;
import model.*;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalQuery;
import java.util.List;
import java.util.Locale;

/**
 * ═══════════════════════════════════════════════════════════════════════════════
 * CHALLENGE 01: Java Date/Time API Basics
 * ═══════════════════════════════════════════════════════════════════════════════
 *
 * SCENARIO:
 * You are replacing legacy Date/Calendar usage in the EduMaster Scheduling Module.
 * The platform needs to handle course start/end dates, lesson times, enrollment
 * timestamps, and deadline calculations with the modern, thread-safe Date/Time API.
 *
 * FOCUS:
 * - LocalDate, LocalTime, LocalDateTime for date/time representation
 * - DateTimeFormatter for parsing and formatting
 * - Period and Duration for time calculations
 * - TemporalAdjusters for date manipulation
 * - Working with real course and enrollment data
 *
 * ═══════════════════════════════════════════════════════════════════════════════
 */
public class DateTimeBasicsChallenge {

    private final List<Student> students;
    private final List<Course> courses;
    private final List<Enrollment> enrollments;

    public DateTimeBasicsChallenge() {
        this.students = SampleDataGenerator.generateStudents(100);
        var instructors = SampleDataGenerator.generateInstructors(20);
        this.courses = SampleDataGenerator.generateCourses(50, instructors);
        this.enrollments = SampleDataGenerator.generateEnrollments(students, courses, 200);
    }

    // ═══════════════════════════════════════════════════════════════════════════════
    // PART 1: Date/Time Creation & Parsing
    // ═══════════════════════════════════════════════════════════════════════════════

    /**
     * TASK 1.1: Create Specific Date
     *
     * Create a LocalDate for April 23, 2025 - the platform launch date.
     *
     * EXAMPLE:
     * Input: (none)
     * Output: 2025-04-23
     *
     * @return LocalDate representing April 23, 2025
     */
    public LocalDate createSpecificDate() {
        return LocalDate.of(2025, 4, 23);
    }

    /**
     * TASK 1.2: Parse ISO Date
     *
     * Parse an ISO-formatted date string "2024-12-31" to LocalDate.
     * This is commonly used when receiving dates from APIs or databases.
     *
     * EXAMPLE:
     * Input: "2024-12-31"
     * Output: LocalDate of 2024-12-31
     *
     * @param dateStr ISO formatted date string (yyyy-MM-dd)
     * @return Parsed LocalDate
     */
    public LocalDate parseIsoDate(String dateStr) {
        return LocalDate.parse(dateStr);
    }

    /**
     * TASK 1.3: Custom Date Format
     *
     * Format the date as "23 April 2024" (dd MMMM yyyy) for friendly display
     * in enrollment emails and course certificates.
     * Use Locale.ENGLISH for consistent formatting.
     *
     * EXAMPLE:
     * Input: LocalDate.of(2024, 4, 23)
     * Output: "23 April 2024"
     *
     * @param date Date to format
     * @return Formatted date string
     */
    public String formatFriendlyDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH));
    }

    // ═══════════════════════════════════════════════════════════════════════════════
    // PART 2: Time Calculations & Manipulation
    // ═══════════════════════════════════════════════════════════════════════════════

    /**
     * TASK 2.1: Calculate Lesson End Time
     *
     * If a lesson starts at 09:30 and lasts 45 minutes, calculate when it ends.
     * This is crucial for scheduling consecutive lessons without overlaps.
     *
     * EXAMPLE:
     * Input: start=09:30, duration=45 minutes
     * Output: 10:15
     *
     * @return End time of the lesson
     */
    public LocalTime calculateEndTime() {
        return LocalTime.of(9, 30).plusMinutes(45);
    }

    /**
     * TASK 2.2: Calculate Days Between Dates
     *
     * Calculate the number of days between two dates.
     * Used for tracking course duration, enrollment periods, and deadline countdowns.
     *
     * HINT: Use ChronoUnit.DAYS.between() or Period.between().getDays()
     *
     * EXAMPLE:
     * Input: start=2024-01-01, end=2024-01-31
     * Output: 30
     *
     * @param start Start date
     * @param end End date
     * @return Number of days between the dates
     */
    public long calculateDaysBetween(LocalDate start, LocalDate end) {
        // return ChronoUnit.DAYS.between(start, end);
        return Period.between(start, end).getDays();
    }

    /**
     * TASK 2.3: Calculate Duration in Seconds
     *
     * Calculate the duration between two times in seconds.
     * Used for tracking actual lesson attendance time and system usage metrics.
     *
     * HINT: Use Duration.between().toSeconds()
     *
     * EXAMPLE:
     * Input: start=09:00, end=10:30
     * Output: 5400 (1.5 hours = 5400 seconds)
     *
     * @param start Start time
     * @param end End time
     * @return Duration in seconds
     */
    public long calculateDurationInSeconds(LocalTime start, LocalTime end) {
        return Duration.between(start, end).getSeconds();
    }

    // ═══════════════════════════════════════════════════════════════════════════════
    // PART 3: Date Manipulation & Business Logic
    // ═══════════════════════════════════════════════════════════════════════════════

    /**
     * TASK 3.1: Weekend Checker
     *
     * Check if the given date falls on a weekend (Saturday or Sunday).
     * Used to determine if support tickets need urgent escalation.
     *
     * HINT: Use getDayOfWeek() and compare with DayOfWeek enum
     *
     * EXAMPLE:
     * Input: LocalDate of Saturday
     * Output: true
     *
     * @param date Date to check
     * @return true if weekend, false otherwise
     */
    public boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    /**
     * TASK 3.2: Find Next Friday
     *
     * Find the date of the next Friday from the given date.
     * Used for scheduling weekly course progress reviews.
     *
     * HINT: Use with(TemporalAdjusters.next(DayOfWeek.FRIDAY))
     *
     * EXAMPLE:
     * Input: Monday, January 8, 2024
     * Output: Friday, January 12, 2024
     *
     * @param date Starting date
     * @return Date of the next Friday
     */
    public LocalDate findNextFriday(LocalDate date) {
        TemporalAdjuster nextFriday = TemporalAdjusters.next(DayOfWeek.FRIDAY);
        return date.with(nextFriday);
    }

    /**
     * TASK 3.3: Calculate Course End Date
     *
     * Given a course start date and duration in weeks, calculate the end date.
     * Assumes courses run Monday-Friday (5 days per week).
     *
     * EXAMPLE:
     * Input: startDate=2024-01-08 (Monday), durationWeeks=4
     * Output: 2024-02-02 (Friday, 4 weeks later)
     *
     * @param startDate Course start date
     * @param durationWeeks Course duration in weeks
     * @return Course end date
     */
    public LocalDate calculateCourseEndDate(LocalDate startDate, int durationWeeks) {
        return startDate.plusWeeks(durationWeeks).with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
    }

    // ═══════════════════════════════════════════════════════════════════════════════
    // PART 4: Working with Real Course Data
    // ═══════════════════════════════════════════════════════════════════════════════

    /**
     * TASK 4.1: Get Courses Created This Month
     *
     * Filter courses that were created in the current month.
     * Used for generating monthly course catalogs.
     *
     * @param currentDate The reference date (usually today)
     * @return List of courses created in the same month
     */
    public List<Course> getCoursesCreatedThisMonth(LocalDate currentDate) {
        return courses.stream()
                .filter(c -> {
                    LocalDate createdDate = c.getCreatedAt().toLocalDate();
                    return createdDate.getMonth() == currentDate.getMonth()
                            && createdDate.getYear() == currentDate.getYear();
                }).toList();
    }

    /**
     * TASK 4.2: Format Enrollment Timestamp
     *
     * Format an enrollment's creation timestamp in a user-friendly way.
     * Example: "Enrolled on 15 March 2024 at 14:30"
     *
     * HINT: Create a DateTimeFormatter with pattern "dd MMMM yyyy 'at' HH:mm"
     *
     * @param enrollment The enrollment to format
     * @return Formatted enrollment time string
     */
    public String formatEnrollmentTimestamp(Enrollment enrollment) {
        return enrollment.getEnrolledAt()
                .format(DateTimeFormatter.ofPattern("dd MMMM yyyy 'at' HH:mm", Locale.ENGLISH)).toString();
    }

    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("  Date/Time API Basics - Test Suite");
        System.out.println("═══════════════════════════════════════════════════════════");

        var challenge = new DateTimeBasicsChallenge();

        // Test PART 1: Creation & Parsing
        System.out.println("\n--- PART 1: Date/Time Creation & Parsing ---");
        System.out.println("Platform Launch Date: " + challenge.createSpecificDate());
        System.out.println("Parsed Date: " + challenge.parseIsoDate("2024-12-31"));
        System.out.println("Friendly Format: " + challenge.formatFriendlyDate(LocalDate.now()));

        // Test PART 2: Calculations
        System.out.println("\n--- PART 2: Time Calculations ---");
        System.out.println("Lesson ends at: " + challenge.calculateEndTime());
        System.out.println("Days between Jan 1 and Jan 31: " +
                challenge.calculateDaysBetween(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 31)));

        // Test PART 3: Date Manipulation
        System.out.println("\n--- PART 3: Date Manipulation ---");
        LocalDate today = LocalDate.now();
        System.out.println("Is today weekend? " + challenge.isWeekend(today));
        System.out.println("Course ending 4 weeks from today: " +
                challenge.calculateCourseEndDate(today, 4));

        // Test PART 4: Real Data
        System.out.println("\n--- PART 4: Working with Course Data ---");
        List<Course> monthCourses = challenge.getCoursesCreatedThisMonth(LocalDate.now());
        System.out.println("Courses created this month: " + monthCourses.size());
        if (!monthCourses.isEmpty()) {
            System.out.println("First course: " + monthCourses.get(0).getTitle());
        }

        System.out.println("\n═══════════════════════════════════════════════════════════");
    }
}
