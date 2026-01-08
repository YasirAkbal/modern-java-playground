package model;

import java.time.LocalDateTime;

/**
 * Represents a certificate earned by completing a course.
 */
public class Certificate {
    private String id;
    private String studentId;
    private String courseId;
    private String studentName;
    private String courseName;
    private String instructorName;
    private LocalDateTime issuedAt;
    private LocalDateTime expiresAt;
    private String certificateNumber;
    private Double finalScore;
    private String verificationUrl;

    public Certificate() {
        this.issuedAt = LocalDateTime.now();
    }

    public Certificate(String id, String studentId, String courseId, String studentName, 
                       String courseName, String instructorName, Double finalScore) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.studentName = studentName;
        this.courseName = courseName;
        this.instructorName = instructorName;
        this.finalScore = finalScore;
        this.issuedAt = LocalDateTime.now();
        this.certificateNumber = generateCertificateNumber();
    }

    private String generateCertificateNumber() {
        return "CERT-" + System.currentTimeMillis();
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

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public String getInstructorName() { return instructorName; }
    public void setInstructorName(String instructorName) { this.instructorName = instructorName; }

    public LocalDateTime getIssuedAt() { return issuedAt; }
    public void setIssuedAt(LocalDateTime issuedAt) { this.issuedAt = issuedAt; }

    public LocalDateTime getExpiresAt() { return expiresAt; }
    public void setExpiresAt(LocalDateTime expiresAt) { this.expiresAt = expiresAt; }

    public String getCertificateNumber() { return certificateNumber; }
    public void setCertificateNumber(String certificateNumber) { this.certificateNumber = certificateNumber; }

    public Double getFinalScore() { return finalScore; }
    public void setFinalScore(Double finalScore) { this.finalScore = finalScore; }

    public String getVerificationUrl() { return verificationUrl; }
    public void setVerificationUrl(String verificationUrl) { this.verificationUrl = verificationUrl; }

    public boolean isExpired() {
        return expiresAt != null && LocalDateTime.now().isAfter(expiresAt);
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "certificateNumber='" + certificateNumber + '\'' +
                ", studentName='" + studentName + '\'' +
                ", courseName='" + courseName + '\'' +
                ", finalScore=" + finalScore +
                ", issuedAt=" + issuedAt +
                '}';
    }
}
