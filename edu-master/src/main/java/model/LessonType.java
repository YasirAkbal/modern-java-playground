package model;

/**
 * Represents the type of lesson content.
 */
public enum LessonType {
    VIDEO("Video", "Video lecture"),
    ARTICLE("Article", "Written content"),
    QUIZ("Quiz", "Multiple choice questions"),
    ASSIGNMENT("Assignment", "Hands-on exercise"),
    LIVE_SESSION("Live Session", "Real-time interactive session"),
    DOWNLOAD("Download", "Downloadable resources");

    private final String name;
    private final String description;

    LessonType(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
}
