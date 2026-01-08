package model;

import java.time.LocalDateTime;

/**
 * Represents a student's enrollment in a course.
 */
public class Enrollment {
    private String id;
    private String studentId;
    private String courseId;
    private Course course;
    private LocalDateTime enrolledAt;
    private LocalDateTime completedAt;
    private EnrollmentStatus status;
    private int progressPercentage;
    private Double score;
    private int lessonsCompleted;

    public Enrollment() {
        this.enrolledAt = LocalDateTime.now();
        this.status = EnrollmentStatus.ACTIVE;
        this.progressPercentage = 0;
        this.lessonsCompleted = 0;
    }

    public Enrollment(String id, String studentId, Course course) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = course.getId();
        this.course = course;
        this.enrolledAt = LocalDateTime.now();
        this.status = EnrollmentStatus.ACTIVE;
        this.progressPercentage = 0;
        this.lessonsCompleted = 0;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }

    public LocalDateTime getEnrolledAt() { return enrolledAt; }
    public void setEnrolledAt(LocalDateTime enrolledAt) { this.enrolledAt = enrolledAt; }

    public LocalDateTime getCompletedAt() { return completedAt; }
    public void setCompletedAt(LocalDateTime completedAt) { this.completedAt = completedAt; }

    public EnrollmentStatus getStatus() { return status; }
    public void setStatus(EnrollmentStatus status) { this.status = status; }

    public int getProgressPercentage() { return progressPercentage; }
    public void setProgressPercentage(int progressPercentage) { this.progressPercentage = progressPercentage; }

    public Double getScore() { return score; }
    public void setScore(Double score) { this.score = score; }

    public int getLessonsCompleted() { return lessonsCompleted; }
    public void setLessonsCompleted(int lessonsCompleted) { this.lessonsCompleted = lessonsCompleted; }

    public boolean isCompleted() { return status == EnrollmentStatus.COMPLETED; }

    @Override
    public String toString() {
        return "Enrollment{" +
                "id='" + id + '\'' +
                ", courseId='" + courseId + '\'' +
                ", status=" + status +
                ", progress=" + progressPercentage + "%" +
                '}';
    }
}
