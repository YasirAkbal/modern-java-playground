package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a course in the EduMaster platform.
 */
public class Course {
    private String id;
    private String title;
    private String description;
    private Category category;
    private DifficultyLevel difficulty;
    private BigDecimal price;
    private Integer durationInHours;
    private Instructor instructor;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isPublished;
    private double rating;
    private int enrollmentCount;
    private List<Lesson> lessons;
    private List<String> tags;

    public Course() {
        this.lessons = new ArrayList<>();
        this.tags = new ArrayList<>();
        this.createdAt = LocalDateTime.now();
    }

    public Course(String id, String title, String description, Category category, 
                  DifficultyLevel difficulty, BigDecimal price, Instructor instructor) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.difficulty = difficulty;
        this.price = price;
        this.instructor = instructor;
        this.createdAt = LocalDateTime.now();
        this.isPublished = false;
        this.rating = 0.0;
        this.enrollmentCount = 0;
        this.lessons = new ArrayList<>();
        this.tags = new ArrayList<>();
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public DifficultyLevel getDifficulty() { return difficulty; }
    public void setDifficulty(DifficultyLevel difficulty) { this.difficulty = difficulty; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public Integer getDurationInHours() { return durationInHours; }
    public void setDurationInHours(Integer durationInHours) { this.durationInHours = durationInHours; }

    public Instructor getInstructor() { return instructor; }
    public void setInstructor(Instructor instructor) { this.instructor = instructor; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public boolean isPublished() { return isPublished; }
    public void setPublished(boolean published) { isPublished = published; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public int getEnrollmentCount() { return enrollmentCount; }
    public void setEnrollmentCount(int enrollmentCount) { this.enrollmentCount = enrollmentCount; }

    public List<Lesson> getLessons() { return lessons; }
    public void setLessons(List<Lesson> lessons) { this.lessons = lessons; }

    public void addLesson(Lesson lesson) { this.lessons.add(lesson); }

    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }

    public boolean isFree() { return price == null || price.compareTo(BigDecimal.ZERO) == 0; }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", category=" + category +
                ", difficulty=" + difficulty +
                ", price=" + price +
                ", isPublished=" + isPublished +
                '}';
    }
}
