package challenges;

import data.SampleDataGenerator;
import model.*;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * ═══════════════════════════════════════════════════════════════════════════════
 * CHALLENGE 05: Time Zone Mastery & Global Scheduling
 * ═══════════════════════════════════════════════════════════════════════════════
 *
 * SCENARIO:
 * You are building the EduMaster Global Classroom Scheduler. The platform serves
 * students and instructors across multiple time zones. Live webinars, office hours,
 * and assignment deadlines must be displayed in each user's local time zone.
 * You need to handle time zone conversions, DST transitions, and coordinate
 * international course schedules.
 *
 * FOCUS:
 * - ZonedDateTime for timezone-aware timestamps
 * - Converting between time zones with withZoneSameInstant()
 * - Handling Daylight Saving Time (DST) transitions
 * - OffsetDateTime for fixed offset scenarios
 * - Calculating duration across time zones
 * - Working with course schedules and enrollment data
 *
 * ═══════════════════════════════════════════════════════════════════════════════
 */
public class TimeZoneMasteryChallenge {

    private final List<Student> students;
    private final List<Course> courses;
    private final List<Enrollment> enrollments;
    private final List<Instructor> instructors;

    public TimeZoneMasteryChallenge() {
        this.students = SampleDataGenerator.generateStudents(100);
        this.instructors = SampleDataGenerator.generateInstructors(20);
        this.courses = SampleDataGenerator.generateCourses(50, instructors);
        this.enrollments = SampleDataGenerator.generateEnrollments(students, courses, 200);
    }

    // ═══════════════════════════════════════════════════════════════════════════════
    // PART 1: Basic Time Zone Conversions
    // ═══════════════════════════════════════════════════════════════════════════════

    /**
     * TASK 1.1: Convert Webinar Time
     *
     * A live webinar is scheduled at 15:00 London time (Europe/London).
     * Convert this to the student's local time zone.
     * This is essential for displaying correct meeting times to international users.
     *
     * HINT: Create ZonedDateTime with London zone, then use withZoneSameInstant()
     *
     * EXAMPLE:
     * Input: londonTime=2024-01-15 15:00, targetZone="Asia/Tokyo"
     * Output: 2024-01-16 00:00 (Tokyo is 9 hours ahead)
     *
     * @param londonLocalTime The webinar time in London
     * @param targetZoneId Target time zone ID
     * @return Webinar time in target zone
     */
    public ZonedDateTime convertWebinarTime(LocalDateTime londonLocalTime, String targetZoneId) {
        return ZonedDateTime.of(londonLocalTime, ZoneId.of("Europe/London"))
                .withZoneSameInstant(ZoneId.of(targetZoneId));
    }

    /**
     * TASK 1.2: Show Course Start in Multiple Zones
     *
     * A course starts at 09:00 America/New_York.
     * Show what time this is in London, Tokyo, and Sydney for international students.
     *
     * HINT: Create a ZonedDateTime for NY, then convert to each zone
     *
     * EXAMPLE:
     * Input: courseStart=2024-02-01 09:00 in New York
     * Output: "NY: 09:00, London: 14:00, Tokyo: 23:00, Sydney: 01:00 (next day)"
     *
     * @param courseStart Course start time in New York
     * @return Formatted string showing times in all zones
     */
    public String showTimeInMultipleZones(LocalDateTime courseStart) {
        ZonedDateTime zonedNY = ZonedDateTime.of(courseStart, ZoneId.of("America/New_York"));
        List<String> zones = Arrays.asList("America/New_York","Europe/London", "Asia/Tokyo", "Australia/Sydney");

        return zones.stream()
                .map(z -> zonedNY.withZoneSameInstant(ZoneId.of(z)))
                .map(z -> "%s: %s".formatted(z.getZone().getId(), 
                    z.format(DateTimeFormatter.ofPattern("hh:mm"))))
                .collect(Collectors.joining(", "));
    }

    /**
     * TASK 1.3: Check if Time is During Business Hours
     *
     * Given a ZonedDateTime, check if it falls within business hours (09:00-17:00)
     * in a specific time zone. Used to validate meeting times.
     *
     * HINT: Convert to target zone, extract hour, check if between 9 and 17
     *
     * EXAMPLE:
     * Input: time=15:00 UTC, targetZone="America/New_York"
     * Output: true (15:00 UTC = 10:00 EST, which is business hours)
     *
     * @param time The time to check
     * @param targetZoneId The time zone to check business hours in
     * @return true if during business hours (9am-5pm)
     */
    public boolean isDuringBusinessHours(ZonedDateTime time, String targetZoneId) {
        var targetTime =  time.withZoneSameInstant(ZoneId.of(targetZoneId));

        return targetTime.getHour() >= 9 && targetTime.getHour() <= 17;
    }

    // ═══════════════════════════════════════════════════════════════════════════════
    // PART 2: Duration Calculations Across Time Zones
    // ═══════════════════════════════════════════════════════════════════════════════

    /**
     * TASK 2.1: Calculate Flight Arrival Time
     *
     * An instructor is flying from New York to Paris for an in-person workshop.
     * Calculate the arrival time in Paris local time given departure time and duration.
     *
     * HINT: Create ZonedDateTime in NY, add duration, convert to Paris time
     *
     * EXAMPLE:
     * Input: departure=2024-03-15 18:00 NY, duration=8h 30m
     * Output: 2024-03-16 09:30 Paris time (next day, +6 hour timezone diff)
     *
     * @param departureLocal Departure time in New York
     * @param flightDuration Duration of flight
     * @return Arrival time in Paris
     */
    public ZonedDateTime calculateFlightArrival(LocalDateTime departureLocal, Duration flightDuration) {
        return departureLocal.atZone(ZoneId.of("America/New_York"))
                .plus(flightDuration)
                .withZoneSameInstant(ZoneId.of("Europe/Paris"));
    }

    /**
     * TASK 2.2: Calculate Meeting Duration in Student's Zone
     *
     * A webinar runs from 14:00 to 16:30 UTC.
     * Calculate the actual duration the student will attend, ensuring it's
     * the same regardless of their time zone.
     *
     * HINT: Duration is independent of time zone, use ChronoUnit.MINUTES.between()
     *
     * EXAMPLE:
     * Input: start=14:00 UTC, end=16:30 UTC
     * Output: 150 minutes (2.5 hours) - same in any timezone
     *
     * @param startUtc Start time in UTC
     * @param endUtc End time in UTC
     * @return Duration in minutes
     */
    public long calculateWebinarDurationMinutes(ZonedDateTime startUtc, ZonedDateTime endUtc) {
        return ChronoUnit.MINUTES.between(startUtc, endUtc);
    }

    /**
     * TASK 2.3: Find Optimal Meeting Time
     *
     * Find a time that works for three instructors in different zones:
     * - Instructor A in "America/Los_Angeles" (09:00-17:00 work hours)
     * - Instructor B in "Europe/London" (09:00-17:00 work hours)
     * - Instructor C in "Asia/Singapore" (09:00-17:00 work hours)
     *
     * Given a date, suggest 14:00 UTC as a compromise time and verify
     * if it falls within work hours for all three.
     *
     * EXAMPLE:
     * Input: date=2024-01-15
     * Output: "14:00 UTC = LA: 06:00 (NO), London: 14:00 (YES), Singapore: 22:00 (NO)"
     *
     * @param date The date to check
     * @return Analysis string showing times in each zone
     */
    public String analyzeOptimalMeetingTime(LocalDate date) {
        ZonedDateTime proposedTimeUtc = ZonedDateTime.of(
                LocalDateTime.of(date, LocalTime.of(14, 0)),
                ZoneId.of("UTC")
        );

        StringBuilder result = new StringBuilder();
        result.append("14:00 UTC = ");
        Map<String, String> zones = Map.of(
                "America/Los_Angeles", "LA",
                "Europe/London", "London",
                "Asia/Singapore", "Singapore"
        );
        for (var entry : zones.entrySet()) {
            var zoneId = entry.getKey();
            var zoneName = entry.getValue();
            var localTime = proposedTimeUtc.withZoneSameInstant(ZoneId.of(zoneId));
            boolean isBusinessHours = localTime.getHour() >= 9 && localTime.getHour() <= 17;
            result.append(String.format("%s: %02d:00 (%s), ", zoneName, localTime.getHour(),
                    isBusinessHours ? "YES" : "NO"));
        }

        return result.toString();
    }

    // ═══════════════════════════════════════════════════════════════════════════════
    // PART 3: Daylight Saving Time (DST) Handling
    // ═══════════════════════════════════════════════════════════════════════════════

    /**
     * TASK 3.1: Handle DST Gap
     *
     * During DST transition, clocks "spring forward" (e.g., 02:00 -> 03:00).
     * Times in the gap don't exist. Java adjusts them automatically.
     *
     * Create a ZonedDateTime for 02:30 on March 12, 2023 in America/New_York
     * (when DST starts) and see what time Java actually creates.
     *
     * HINT: Use ZonedDateTime.of() with the non-existent time
     *
     * EXAMPLE:
     * Input: 2023-03-12 02:30 America/New_York (doesn't exist)
     * Output: 2023-03-12 03:30 America/New_York (automatically adjusted)
     *
     * @return The adjusted ZonedDateTime
     */
    public ZonedDateTime demonstrateDstGap() {
        return ZonedDateTime.of(
                LocalDateTime.of(2023, 3, 12, 2, 30),
                ZoneId.of("America/New_York")
        );
    }

    /**
     * TASK 3.2: Handle DST Overlap
     *
     * During "fall back", clocks repeat an hour (e.g., 02:00 occurs twice).
     * The first occurrence is in daylight time, the second in standard time.
     *
     * Create two ZonedDateTimes for the same local time during overlap
     * but with different zone offsets.
     *
     * HINT: Use withEarlierOffsetAtOverlap() and withLaterOffsetAtOverlap()
     *
     * EXAMPLE:
     * Input: 2023-11-05 01:30 America/New_York (occurs twice)
     * Output: First: 01:30 EDT (-04:00), Second: 01:30 EST (-05:00)
     *
     * @return Description of both occurrences
     */
    public String demonstrateDstOverlap() {
        LocalDateTime overlapTime = LocalDateTime.of(2023, 11, 5, 1, 30);
        ZoneId zone = ZoneId.of("America/New_York");
        ZonedDateTime ambiguous = ZonedDateTime.of(overlapTime, zone);

        ZonedDateTime first = ambiguous.withEarlierOffsetAtOverlap();
        ZonedDateTime second = ambiguous.withLaterOffsetAtOverlap();

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm z (XXX)");
        return String.format(
            "First occurrence: %s, Second occurrence: %s",
            first.format(fmt),
            second.format(fmt)
        );
    }

    /**
     * TASK 3.3: Calculate Course Duration Across DST
     *
     * A course runs weekly from March 5 to March 19, 2023 at 10:00 America/New_York.
     * DST starts on March 12. Calculate if the course duration in UTC changes.
     *
     * HINT: Create ZonedDateTime for both dates, convert to UTC, compare
     *
     * EXAMPLE:
     * Input: March 5 10:00 ET vs March 19 10:00 ET
     * Output: "Before DST: 15:00 UTC, After DST: 14:00 UTC (1 hour difference)"
     *
     * @return Description of UTC times before and after DST
     */
    public String analyzeCourseDuringDst() {
        ZonedDateTime beforeDst = ZonedDateTime.of(
                LocalDateTime.of(2023, 3, 5, 10, 0),
                ZoneId.of("America/New_York")
        );
        ZonedDateTime afterDst = ZonedDateTime.of(
                LocalDateTime.of(2023, 3, 19, 10, 0),
                ZoneId.of("America/New_York")
        );

        ZonedDateTime beforeDstUtc = beforeDst.withZoneSameInstant(ZoneOffset.UTC);
        ZonedDateTime afterDstUtc = afterDst.withZoneSameInstant(ZoneOffset.UTC);

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm 'UTC'");
        String beforeStr = beforeDstUtc.format(fmt);
        String afterStr = afterDstUtc.format(fmt);

        long hoursDifference = Duration.between(beforeDstUtc, afterDstUtc).toHours();

        return String.format("Before DST: %s, After DST: %s (%d hour difference)", beforeStr, afterStr, hoursDifference);
    }

    // ═══════════════════════════════════════════════════════════════════════════════
    // PART 4: Working with Course Schedule Data
    // ═══════════════════════════════════════════════════════════════════════════════

    /**
     * TASK 4.1: Convert Enrollment Times to Time Zone
     *
     * Get all enrollments and show when they occurred in a specific time zone.
     * Enrollments are stored as LocalDateTime, assume they're in UTC.
     *
     * HINT: Convert LocalDateTime to ZonedDateTime in UTC, then to target zone
     *
     * EXAMPLE:
     * Input: targetZone="Asia/Tokyo"
     * Output: List of enrollment times in Tokyo time
     *
     * @param targetZoneId Target time zone
     * @return List of formatted enrollment timestamps
     */
    public List<String> getEnrollmentTimesInZone(String targetZoneId) {
        return enrollments.stream()
            .map(enr -> {
                ZonedDateTime zonedDateTime = ZonedDateTime.of(enr.getEnrolledAt(), ZoneId.of(targetZoneId));
                return "Enrollment ID: %s, Enrollment Date: %s".formatted(enr.getId(), 
                    zonedDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm z")));
            })
            .toList();
    }

    /**
     * TASK 4.2: Group Courses by Instructor Time Zone
     *
     * Group courses by the instructor's time zone (assume stored in instructor data).
     * This helps schedule live sessions at instructor-friendly times.
     *
     * For this exercise, randomly assign time zones to demonstrate grouping.
     *
     * HINT: Use Collectors.groupingBy() with time zone as key
     *
     * EXAMPLE:
     * Output: Map{"America/New_York" -> [course1, course2], "Europe/London" -> [course3]}
     *
     * @return Map of time zone to course count
     */
    public Map<String, Long> groupCoursesByInstructorZone() {
        List<String> shuffled = ZoneId.getAvailableZoneIds().stream().toList();
        Collections.shuffle(shuffled);

        final var timeZoneSize = 5;
        List<String> selectedTimeZones = shuffled.stream()
            .limit(timeZoneSize)
            .toList();

        
        return courses.stream()
                .map(c -> {
                    int randIndex = (int)(Math.random() * timeZoneSize);
                    return selectedTimeZones.get(randIndex);
                })
                .collect(Collectors.groupingBy(
                    ctz -> ctz,
                    Collectors.counting()
                ));
    }

    /**
     * TASK 4.3: Find Courses with Upcoming Deadlines in User's Zone
     *
     * Given a student's time zone, find courses where assignments are due
     * within the next 24 hours in their local time.
     *
     * For this exercise, create sample deadline times.
     *
     * HINT: Get current time in user's zone, check if deadline is within 24 hours
     *
     * @param studentZoneId Student's time zone
     * @return Count of courses with upcoming deadlines
     */
    public long countUpcomingDeadlines(String studentZoneId) {
        return courses.stream()
                .filter(c -> {
                    ZonedDateTime nowInZone = ZonedDateTime.now(ZoneId.of(studentZoneId));
                    ZonedDateTime deadlineInZone = nowInZone.plusHours((long)(Math.random() * 72)); // random deadline within 72 hours
                    Duration duration = Duration.between(nowInZone, deadlineInZone);
                    return !duration.isNegative() && duration.toHours() <= 24;
                })
                .count();
    }

    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("  Time Zone Mastery - Test Suite");
        System.out.println("═══════════════════════════════════════════════════════════");

        var challenge = new TimeZoneMasteryChallenge();

        // Test PART 1: Basic Time Zone Conversions
        System.out.println("\n--- PART 1: Basic Time Zone Conversions ---");
        LocalDateTime webinarTime = LocalDateTime.of(2024, 1, 15, 15, 0);
        ZonedDateTime tokyoTime = challenge.convertWebinarTime(webinarTime, "Asia/Tokyo");
        System.out.println("Webinar at 15:00 London = " + tokyoTime);

        LocalDateTime courseStart = LocalDateTime.of(2024, 2, 1, 9, 0);
        System.out.println("Course times: " + challenge.showTimeInMultipleZones(courseStart));

        // Test PART 2: Duration Calculations
        System.out.println("\n--- PART 2: Duration Calculations Across Time Zones ---");
        LocalDateTime departure = LocalDateTime.of(2024, 3, 15, 18, 0);
        Duration flightTime = Duration.ofHours(8).plusMinutes(30);
        ZonedDateTime arrival = challenge.calculateFlightArrival(departure, flightTime);
        System.out.println("Flight arrives at: " + arrival);
        System.out.println("Arrival in Paris: " + arrival.format(
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm z")
        ));

        // Test PART 3: DST Handling
        System.out.println("\n--- PART 3: Daylight Saving Time Handling ---");
        ZonedDateTime dstGap = challenge.demonstrateDstGap();
        System.out.println("DST Gap (2:30 doesn't exist): " + dstGap);
        System.out.println("Actual time created: " + dstGap.format(
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z")
        ));

        String dstOverlap = challenge.demonstrateDstOverlap();
        System.out.println("DST Overlap: " + dstOverlap);

        // Test PART 4: Working with Course Data
        System.out.println("\n--- PART 4: Working with Course Schedule Data ---");
        List<String> enrollmentTimes = challenge.getEnrollmentTimesInZone("Asia/Tokyo");
        System.out.println("Sample enrollment times in Tokyo:");
        enrollmentTimes.stream().limit(5).forEach(time ->
            System.out.println("  - " + time)
        );
         
        // Show current time in various zones
        System.out.println("\n--- Current Time Around the World ---");
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC"));
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm z");
        System.out.println("UTC:       " + now.format(fmt));
        System.out.println("New York:  " + now.withZoneSameInstant(ZoneId.of("America/New_York")).format(fmt));
        System.out.println("London:    " + now.withZoneSameInstant(ZoneId.of("Europe/London")).format(fmt));
        System.out.println("Tokyo:     " + now.withZoneSameInstant(ZoneId.of("Asia/Tokyo")).format(fmt));
        System.out.println("Sydney:    " + now.withZoneSameInstant(ZoneId.of("Australia/Sydney")).format(fmt));

        System.out.println("\n═══════════════════════════════════════════════════════════");
    }
}
