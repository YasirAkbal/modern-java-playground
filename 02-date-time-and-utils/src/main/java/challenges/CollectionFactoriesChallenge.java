package challenges;

import data.SampleDataGenerator;
import model.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * ═══════════════════════════════════════════════════════════════════════════════
 * CHALLENGE 03: Collection Factory Methods (Java 9+)
 * ═══════════════════════════════════════════════════════════════════════════════
 *
 * SCENARIO:
 * You are modernizing the EduMaster configuration management system to use
 * immutable collections throughout the application. This prevents accidental
 * modifications to critical data like course tags, enrollment statuses, and
 * system configuration. The new factory methods provide cleaner, safer code.
 *
 * FOCUS:
 * - List.of(), Set.of(), Map.of() for creating immutable collections
 * - List.copyOf(), Set.copyOf(), Map.copyOf() for defensive copying
 * - Map.ofEntries() for larger maps
 * - Working with course tags, categories, and configuration data
 * - Understanding null-hostile behavior and immutability guarantees
 *
 * ═══════════════════════════════════════════════════════════════════════════════
 */
public class CollectionFactoriesChallenge {

    private final List<Student> students;
    private final List<Course> courses;
    private final List<Instructor> instructors;

    public CollectionFactoriesChallenge() {
        this.students = SampleDataGenerator.generateStudents(100);
        this.instructors = SampleDataGenerator.generateInstructors(20);
        this.courses = SampleDataGenerator.generateCourses(50, instructors);
    }

    // ═══════════════════════════════════════════════════════════════════════════════
    // PART 1: Basic Immutable Collections
    // ═══════════════════════════════════════════════════════════════════════════════

    /**
     * TASK 1.1: Create Immutable List
     *
     * Create an immutable list of supported programming languages offered on the platform.
     * This list will be used across the application and must never change.
     *
-     *
     * EXAMPLE:
     * Input: (none)
     * Output: List["Java", "Python", "JavaScript", "Go"]
     *
     * @return Immutable list of programming languages
     */
    public List<String> getSupportedLanguages() {
        return null;
    }

    /**
     * TASK 1.2: Create Immutable Set
     *
     * Create an immutable set of valid enrollment statuses.
     * Using a Set ensures no duplicates and provides O(1) containment checks.
     *
     *
     * EXAMPLE:
     * Input: (none)
     * Output: Set["ACTIVE", "COMPLETED", "CANCELLED", "EXPIRED"]
     *
     * @return Immutable set of enrollment statuses
     */
    public Set<String> getValidEnrollmentStatuses() {
        return null;
    }

    /**
     * TASK 1.3: Create Immutable Map
     *
     * Create a system configuration map with retry and timeout settings.
     * This configuration must be immutable to prevent accidental changes at runtime.
     *
     *
     * EXAMPLE:
     * Input: (none)
     * Output: Map{"max_retries" -> "3", "timeout_ms" -> "5000"}
     *
     * @return Immutable configuration map
     */
    public Map<String, String> getSystemConfig() {
        return null;
    }

    // ═══════════════════════════════════════════════════════════════════════════════
    // PART 2: Defensive Copying
    // ═══════════════════════════════════════════════════════════════════════════════

    /**
     * TASK 2.1: Defensive List Copy
     *
     * Create an immutable copy of an input list to prevent external modifications.
     * This is crucial when returning internal collections to external callers.
     *
     *
     * EXAMPLE:
     * Input: List["tag1", "tag2"]
     * Output: Immutable copy of the list
     *
     * @param input Mutable list to copy
     * @return Immutable copy of the input list
     */
    public List<String> createDefensiveCopy(List<String> input) {
        return null;
    }

    /**
     * TASK 2.2: Extract Course Tags Safely
     *
     * Extract all tags from a course as an immutable list.
     * The course internally stores tags in a mutable list, but we want to
     * return an immutable view to prevent external modifications.
     *
     *
     * EXAMPLE:
     * Input: Course with tags ["java", "beginner", "programming"]
     * Output: Immutable List["java", "beginner", "programming"]
     *
     * @param course The course to extract tags from
     * @return Immutable list of course tags
     */
    public List<String> getCourseTagsSafely(Course course) {
        // TODO: Return immutable copy of course tags
        return null;
    }

    /**
     * TASK 2.3: Defensive Set Copy
     *
     * Create an immutable set from a collection, removing duplicates.
     * Used when we need to ensure uniqueness and immutability.
     *
     * HINT: Use Set.copyOf()
     *
     * EXAMPLE:
     * Input: List["java", "python", "java", "go"]
     * Output: Set["java", "python", "go"]
     *
     * @param input Collection to copy (may contain duplicates)
     * @return Immutable set (no duplicates)
     */
    public Set<String> createImmutableSet(Collection<String> input) {
        // TODO: Use Set.copyOf()
        return null;
    }

    // ═══════════════════════════════════════════════════════════════════════════════
    // PART 3: Working with Course Data
    // ═══════════════════════════════════════════════════════════════════════════════

    /**
     * TASK 3.1: Get All Unique Course Tags
     *
     * Extract all unique tags from all courses and return as an immutable set.
     * This is used for generating tag clouds and filter options in the UI.
     *
     *
     * EXAMPLE:
     * Input: Courses with various tags
     * Output: Set of all unique tags across all courses
     *
     * @return Immutable set of all unique tags
     */
    public Set<String> getAllUniqueTags() {
        return null;
    }

    /**
     * TASK 3.2: Get Category Display Names
     *
     * Create an immutable list of all category display names used by courses.
     * Useful for populating category dropdowns in the UI.
     *
     *
     * EXAMPLE:
     * Input: Courses in various categories
     * Output: List["Programming", "Data Science", "Web Development", ...]
     *
     * @return Immutable list of category display names
     */
    public List<String> getCategoryDisplayNames() {
        // TODO: Extract unique category display names from all courses
        return null;
    }

    /**
     * TASK 3.3: Build Difficulty Level Map
     *
     * Create a map of difficulty levels to course counts.
     * Shows how many courses exist at each difficulty level.
     *
     *
     * EXAMPLE:
     * Input: 50 courses at various difficulty levels
     * Output: Map{BEGINNER -> 20, INTERMEDIATE -> 18, ADVANCED -> 12}
     *
     * @return Immutable map of difficulty level to course count
     */
    public Map<DifficultyLevel, Long> getDifficultyDistribution() {
        // TODO: Group courses by difficulty and count
        return null;
    }

    // ═══════════════════════════════════════════════════════════════════════════════
    // PART 4: Advanced Map Factories
    // ═══════════════════════════════════════════════════════════════════════════════

    /**
     * TASK 4.1: Create Large Configuration Map
     *
     * For maps with more than 10 entries, Map.of() becomes unwieldy.
     * Use Map.ofEntries() with Map.entry() for better readability.
     *
     * Create a feature flag configuration map with 12+ flags.
     *
     *
     * EXAMPLE:
     * Input: (none)
     * Output: Map with feature flags like "enable_ai_tutor" -> "true", etc.
     *
     * @return Immutable map of feature flags
     */
    public Map<String, Boolean> getFeatureFlags() {
        return null;
    }

    /**
     * TASK 4.2: Course Instructor Map
     *
     * Create a map of course IDs to instructor names for quick lookup.
     * This is used in various reporting and admin dashboards.
     *
     * HINT: Stream courses, collect to map using Collectors.toMap(), then Map.copyOf()
     *
     * EXAMPLE:
     * Input: List of courses with instructors
     * Output: Map{"course1" -> "John Smith", "course2" -> "Jane Doe", ...}
     *
     * @return Immutable map of course ID to instructor name
     */
    public Map<String, String> getCourseInstructorMap() {
        // TODO: Create map of course ID to instructor full name
        return null;
    }

    /**
     * TASK 4.3: Validate Immutability
     *
     * Demonstrate that attempting to modify an immutable collection throws
     * UnsupportedOperationException. This is a key feature of the new factories.
     *
     * Try to add an element to an immutable list and catch the exception.
     *
     * EXAMPLE:
     * Input: Immutable list
     * Output: true (exception was thrown as expected)
     *
     * @return true if UnsupportedOperationException is thrown, false otherwise
     */
    public boolean verifyImmutability() {
        return false;
    }

    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("  Collection Factory Methods - Test Suite");
        System.out.println("═══════════════════════════════════════════════════════════");

        var challenge = new CollectionFactoriesChallenge();

        // Test PART 1: Basic Immutable Collections
        System.out.println("\n--- PART 1: Basic Immutable Collections ---");
        System.out.println("Supported Languages: " + challenge.getSupportedLanguages());
        System.out.println("Valid Statuses: " + challenge.getValidEnrollmentStatuses());
        System.out.println("System Config: " + challenge.getSystemConfig());

        // Test PART 2: Defensive Copying
        System.out.println("\n--- PART 2: Defensive Copying ---");
        List<String> mutableList = new ArrayList<>(List.of("tag1", "tag2", "tag3"));
        List<String> immutableCopy = challenge.createDefensiveCopy(mutableList);
        System.out.println("Original list: " + mutableList);
        System.out.println("Immutable copy: " + immutableCopy);
        mutableList.add("tag4"); // This won't affect the copy
        System.out.println("After modifying original: " + mutableList);
        System.out.println("Copy remains unchanged: " + immutableCopy);

        // Test PART 3: Working with Course Data
        System.out.println("\n--- PART 3: Working with Course Data ---");
        Set<String> allTags = challenge.getAllUniqueTags();
        System.out.println("Total unique tags: " + allTags.size());
        System.out.println("Sample tags: " + allTags.stream().limit(5).toList());

        // Test PART 4: Advanced Map Factories
        System.out.println("\n--- PART 4: Advanced Map Factories ---");
        Map<String, Boolean> featureFlags = challenge.getFeatureFlags();
        System.out.println("Total feature flags: " + featureFlags.size());
        System.out.println("AI Tutor enabled: " + featureFlags.get("enable_ai_tutor"));
        System.out.println("Gamification enabled: " + featureFlags.get("enable_gamification"));

        // Test immutability
        System.out.println("\n--- Immutability Verification ---");
        System.out.println("Collections are truly immutable: " + challenge.verifyImmutability());

        System.out.println("\n═══════════════════════════════════════════════════════════");
    }
}
