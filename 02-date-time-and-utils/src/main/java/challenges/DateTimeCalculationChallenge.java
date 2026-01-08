package challenges;

import data.SampleDataGenerator;
import model.*;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

/**
 * ═══════════════════════════════════════════════════════════════════════════════
 * CHALLENGE 02: Advanced Date Calculations
 * ═══════════════════════════════════════════════════════════════════════════════
 *
 * SCENARIO:
 * You are enhancing the EduMaster scheduling and deadline management system.
 * The platform needs sophisticated date calculations for enrollment expiration
 * warnings, course schedule planning, meeting coordination across time zones,
 * and tracking lesson durations. These features ensure students never miss
 * deadlines and courses are scheduled at optimal times.
 *
 * FOCUS:
 * - ChronoUnit for precise date/time differences
 * - TemporalAdjusters for advanced date manipulation
 * - Period and Duration for time-based calculations
 * - Time zone conversions for global scheduling
 * - Working with real course and enrollment data
 * - Aggregating durations from multiple lessons
 *
 * ═══════════════════════════════════════════════════════════════════════════════
 */
public class DateTimeCalculationChallenge {

    private final List<Student> students;
    private final List<Course> courses;
    private final List<Enrollment> enrollments;

    public DateTimeCalculationChallenge() {
        this.students = SampleDataGenerator.generateStudents(100);
        var instructors = SampleDataGenerator.generateInstructors(20);
        this.courses = SampleDataGenerator.generateCourses(50, instructors);
        this.enrollments = SampleDataGenerator.generateEnrollments(students, courses, 200);
    }

    // ═══════════════════════════════════════════════════════════════════════════════
    // PART 1: Enrollment Expiration & Deadline Management
    // ═══════════════════════════════════════════════════════════════════════════════

    /**
     * TASK 1.1: Check If Enrollment Expiring Soon
     *
     * Determine if a student's enrollment expires within 5 days from today.
     * This triggers email notifications to remind students to complete their courses.
     *
     *
     * EXAMPLE:
     * Input: today=2024-01-08, enrollment expires on 2024-01-12
     * Output: true (4 days remaining, which is <= 5)
     *
     * @param today Current date
     * @param enrollment Enrollment to check
     * @return true if enrollment expires within 5 days
     */
    public boolean isExpiringSoon(LocalDate today, Enrollment enrollment) {
        return false;
    }

    /**
     * TASK 1.2: Calculate Days Until Deadline
     *
     * Calculate the exact number of days between today and an assignment deadline.
     * Returns negative if deadline has passed.
     *
     * HINT: Use ChronoUnit.DAYS.between()
     *
     * EXAMPLE:
     * Input: today=2024-01-08, deadline=2024-01-15
     * Output: 7 (days remaining)
     *
     * @param today Current date
     * @param deadline Assignment deadline
     * @return Number of days until deadline (negative if passed)
     */
    public long calculateDaysUntilDeadline(LocalDate today, LocalDate deadline) {
        // TODO: Calculate days between today and deadline
        return 0;
    }

    /**
     * TASK 1.3: Find Enrollments Expiring This Week
     *
     * Filter all enrollments that expire within the next 7 days.
     * Used to generate weekly expiration reports for student success teams.
     *
     * HINT: Stream enrollments, filter using isExpiringSoon logic with 7 days
     *
     * EXAMPLE:
     * Input: today=2024-01-08, enrollments with various expiration dates
     * Output: List of enrollments expiring by 2024-01-15
     *
     * @param today Current date
     * @return List of enrollments expiring within 7 days
     */
    public List<Enrollment> findExpiringEnrollments(LocalDate today) {
        // TODO: Filter enrollments expiring within 7 days
        return null;
    }

    // ═══════════════════════════════════════════════════════════════════════════════
    // PART 2: Course Schedule Planning
    // ═══════════════════════════════════════════════════════════════════════════════

    /**
     * TASK 2.1: Calculate Next Course Start Date
     *
     * Courses always start on the first Monday of the next month.
     * Given any date, calculate when the next course cohort begins.
     *
     * HINT: Use plusMonths(1) and TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)
     *
     * EXAMPLE:
     * Input: 2024-01-15 (any day in January)
     * Output: 2024-02-05 (first Monday of February)
     *
     * @param currentDate Current date
     * @return First Monday of next month
     */
    public LocalDate getNextCourseStartDate(LocalDate currentDate) {
        // Placeholder: original implementation removed for challenge reset
        return null;
    }

    /**
     * TASK 2.2: Calculate Course End Date
     *
     * Given a start date and course duration in weeks, calculate the end date.
     * Courses run Monday through Friday, ending on a Friday.
     *
     * HINT: Add weeks, then adjust to previous or same Friday
     *
     * EXAMPLE:
     * Input: startDate=2024-02-05 (Monday), durationWeeks=8
     * Output: 2024-03-29 (Friday, 8 weeks later)
     *
     * @param startDate Course start date
     * @param durationWeeks Duration in weeks
     * @return Course end date (Friday)
     */
    public LocalDate calculateCourseEndDate(LocalDate startDate, int durationWeeks) {
        // TODO: Add weeks and adjust to Friday
        return null;
    }

    /**
     * TASK 2.3: Find Next Office Hours Date
     *
     * Office hours are held every Thursday. Given today's date,
     * find the date of the next office hours session.
     *
     * HINT: Use TemporalAdjusters.next(DayOfWeek.THURSDAY)
     *
     * EXAMPLE:
     * Input: today=2024-01-08 (Monday)
     * Output: 2024-01-11 (Thursday of same week)
     *
     * @param today Current date
     * @return Date of next Thursday
     */
    public LocalDate findNextOfficeHours(LocalDate today) {
        // TODO: Find next Thursday
        return null;
    }

    // ═══════════════════════════════════════════════════════════════════════════════
    // PART 3: Time Zone Conversions for Global Meetings
    // ═══════════════════════════════════════════════════════════════════════════════

    /**
     * TASK 3.1: Convert Istanbul Time to New York
     *
     * A meeting is scheduled at 20:00 Istanbul time.
     * Convert this to New York time for American students.
     *
     * HINT: Create ZonedDateTime in Istanbul zone, use withZoneSameInstant()
     *
     * EXAMPLE:
     * Input: meetingDate=2024-01-15, time=20:00 Istanbul
     * Output: 2024-01-15 12:00 New York (8 hours behind)
     *
     * @param meetingDate Date of meeting
     * @return Meeting time in New York timezone
     */
    public ZonedDateTime convertIstanbulToNewYork(LocalDate meetingDate) {
        // placeholder - original solution removed
        return null;
    }

    /**
     * TASK 3.2: Check If Meeting Time is Reasonable
     *
     * Given a meeting time in one timezone, check if it falls within
     * reasonable hours (08:00-22:00) in another timezone.
     *
     * HINT: Convert timezone, extract hour, check range
     *
     * EXAMPLE:
     * Input: 20:00 Istanbul time
     * Output: For New York (12:00) = true, For Tokyo (02:00 next day) = false
     *
     * @param meetingTime Meeting time in original zone
     * @param targetZoneId Target timezone to check
     * @return true if time is between 08:00-22:00 in target zone
     */
    public boolean isMeetingTimeReasonable(ZonedDateTime meetingTime, String targetZoneId) {
        // placeholder - original solution removed
        return false;
    }

    // ═══════════════════════════════════════════════════════════════════════════════
    // PART 4: Duration Calculations for Course Content
    // ═══════════════════════════════════════════════════════════════════════════════

    /**
     * TASK 4.1: Calculate Total Course Duration
     *
     * Sum up all lesson durations for a course to display total content length.
     * This helps students understand time commitment required.
     *
     * HINT: Stream lessons, map to duration, reduce with Duration::plus
     *
     * EXAMPLE:
     * Input: Course with 10 lessons, each 45 minutes
     * Output: Duration of 7 hours 30 minutes
     *
     * @param course Course to analyze
     * @return Total duration of all lessons
     */
    public Duration calculateTotalCourseDuration(Course course) {
        // Placeholder: original implementation removed for challenge reset
        return null;
    }

    /**
     * TASK 4.2: Format Duration as Hours and Minutes
     *
     * Convert a Duration to a human-readable format like "7 hours 30 minutes".
     *
     * HINT: Use toHours() and toMinutesPart()
     *
     * EXAMPLE:
     * Input: Duration of 450 minutes
     * Output: "7 hours 30 minutes"
     *
     * @param duration Duration to format
     * @return Formatted string
     */
    public String formatDuration(Duration duration) {
        // placeholder - original solution removed
        return null;
    }

    /**
     * TASK 4.3: Calculate Average Lesson Duration
     *
     * Calculate the average duration of lessons in a course.
     * Helps maintain consistent pacing across course content.
     *
     * HINT: Calculate total duration, divide by lesson count
     *
     * EXAMPLE:
     * Input: Course with 10 lessons totaling 450 minutes
     * Output: Duration of 45 minutes
     *
     * @param course Course to analyze
     * @return Average lesson duration
     */
    public Duration calculateAverageLessonDuration(Course course) {
        // placeholder - original solution removed
        return null;
    }

    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("  Advanced Date Calculations - Test Suite");
        System.out.println("═══════════════════════════════════════════════════════════");

        var challenge = new DateTimeCalculationChallenge();
        LocalDate today = LocalDate.now();

        // Test PART 1: Enrollment Expiration
        System.out.println("\n--- PART 1: Enrollment Expiration & Deadline Management ---");
        if (!challenge.enrollments.isEmpty()) {
            Enrollment sample = challenge.enrollments.get(0);
            boolean expiring = challenge.isExpiringSoon(today, sample);
            System.out.println("Sample enrollment expiring soon: " + expiring);
        }

        // Test PART 2: Course Schedule Planning
        System.out.println("\n--- PART 2: Course Schedule Planning ---");
        LocalDate nextStart = challenge.getNextCourseStartDate(today);
        System.out.println("Today: " + today);
        System.out.println("Next course starts: " + nextStart);
        System.out.println("Next course starts on: " + nextStart.getDayOfWeek());

        // Test PART 3: Time Zone Conversions
        System.out.println("\n--- PART 3: Time Zone Conversions ---");
        ZonedDateTime nyTime = challenge.convertIstanbulToNewYork(LocalDate.of(2024, 1, 15));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm z");
        System.out.println("20:00 Istanbul time = " + nyTime.format(formatter));

        // Test PART 4: Duration Calculations
        System.out.println("\n--- PART 4: Duration Calculations ---");
        if (!challenge.courses.isEmpty()) {
            Course sampleCourse = challenge.courses.stream()
                    .filter(c -> !c.getLessons().isEmpty())
                    .findFirst()
                    .orElse(null);

            if (sampleCourse != null) {
                Duration total = challenge.calculateTotalCourseDuration(sampleCourse);
                System.out.println("Course: " + sampleCourse.getTitle());
                System.out.println("Number of lessons: " + sampleCourse.getLessons().size());
                System.out.println("Total duration: " + total.toMinutes() + " minutes");
                System.out.println("Total duration: " + total.toHours() + " hours " +
                        total.toMinutesPart() + " minutes");
            }
        }

        System.out.println("\n═══════════════════════════════════════════════════════════");
    }
}
