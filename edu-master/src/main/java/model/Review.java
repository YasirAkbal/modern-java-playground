package model;

import java.time.LocalDateTime;

/**
 * Represents a course review by a student.
 */
public class Review {
    private String id;
    private String studentId;
    private String courseId;
    private String studentName;
    private int rating; // 1-5
    private String title;
    private String comment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int helpfulCount;
    private boolean isVerifiedPurchase;

    public Review() {
        this.createdAt = LocalDateTime.now();
    }

    public Review(String id, String studentId, String courseId, String studentName, 
                  int rating, String title, String comment) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.studentName = studentName;
        this.rating = rating;
        this.title = title;
        this.comment = comment;
        this.createdAt = LocalDateTime.now();
        this.helpfulCount = 0;
        this.isVerifiedPurchase = false;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = Math.min(5, Math.max(1, rating)); }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public int getHelpfulCount() { return helpfulCount; }
    public void setHelpfulCount(int helpfulCount) { this.helpfulCount = helpfulCount; }

    public void incrementHelpful() { this.helpfulCount++; }

    public boolean isVerifiedPurchase() { return isVerifiedPurchase; }
    public void setVerifiedPurchase(boolean verifiedPurchase) { isVerifiedPurchase = verifiedPurchase; }

    public boolean isPositive() { return rating >= 4; }
    public boolean isNegative() { return rating <= 2; }

    @Override
    public String toString() {
        return "Review{" +
                "id='" + id + '\'' +
                ", courseId='" + courseId + '\'' +
                ", rating=" + rating +
                ", title='" + title + '\'' +
                '}';
    }
}
