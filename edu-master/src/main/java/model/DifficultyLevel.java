package model;

/**
 * Represents the difficulty level of a course.
 */
public enum DifficultyLevel {
    BEGINNER(1, "Beginner", "No prior experience required"),
    ELEMENTARY(2, "Elementary", "Basic understanding required"),
    INTERMEDIATE(3, "Intermediate", "Some experience needed"),
    ADVANCED(4, "Advanced", "Solid experience required"),
    EXPERT(5, "Expert", "Deep expertise required");

    private final int level;
    private final String name;
    private final String description;

    DifficultyLevel(int level, String name, String description) {
        this.level = level;
        this.name = name;
        this.description = description;
    }

    public int getLevel() { return level; }
    public String getName() { return name; }
    public String getDescription() { return description; }
}
