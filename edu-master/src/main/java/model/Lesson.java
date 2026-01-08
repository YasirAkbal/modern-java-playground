package model;

import java.time.Duration;

/**
 * Represents a lesson within a course.
 */
public class Lesson {
    private String id;
    private String title;
    private String content;
    private LessonType type;
    private int orderIndex;
    private Duration duration;
    private String videoUrl;
    private boolean isFreePreview;
    private String courseId;

    public Lesson() {}

    public Lesson(String id, String title, String content, LessonType type, 
                  int orderIndex, Duration duration) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.type = type;
        this.orderIndex = orderIndex;
        this.duration = duration;
        this.isFreePreview = false;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public LessonType getType() { return type; }
    public void setType(LessonType type) { this.type = type; }

    public int getOrderIndex() { return orderIndex; }
    public void setOrderIndex(int orderIndex) { this.orderIndex = orderIndex; }

    public Duration getDuration() { return duration; }
    public void setDuration(Duration duration) { this.duration = duration; }

    public String getVideoUrl() { return videoUrl; }
    public void setVideoUrl(String videoUrl) { this.videoUrl = videoUrl; }

    public boolean isFreePreview() { return isFreePreview; }
    public void setFreePreview(boolean freePreview) { isFreePreview = freePreview; }

    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }

    @Override
    public String toString() {
        return "Lesson{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", duration=" + duration +
                '}';
    }
}
