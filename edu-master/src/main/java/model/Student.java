package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a student in the EduMaster platform.
 * Students can enroll in courses, complete lessons, and earn certificates.
 */
public class Student {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;
    private LocalDate enrollmentDate;
    private StudentLevel level;
    private boolean isActive;
    private Double averageScore;
    private List<Enrollment> enrollments;

    public Student() {
        this.enrollments = new ArrayList<>();
    }

    public Student(String id, String firstName, String lastName, String email, 
                   LocalDate dateOfBirth, StudentLevel level) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.level = level;
        this.enrollmentDate = LocalDate.now();
        this.isActive = true;
        this.enrollments = new ArrayList<>();
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getFullName() { return firstName + " " + lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public LocalDate getEnrollmentDate() { return enrollmentDate; }
    public void setEnrollmentDate(LocalDate enrollmentDate) { this.enrollmentDate = enrollmentDate; }

    public StudentLevel getLevel() { return level; }
    public void setLevel(StudentLevel level) { this.level = level; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }

    public Double getAverageScore() { return averageScore; }
    public void setAverageScore(Double averageScore) { this.averageScore = averageScore; }

    public List<Enrollment> getEnrollments() { return enrollments; }
    public void setEnrollments(List<Enrollment> enrollments) { this.enrollments = enrollments; }

    public void addEnrollment(Enrollment enrollment) {
        this.enrollments.add(enrollment);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + getFullName() + '\'' +
                ", email='" + email + '\'' +
                ", level=" + level +
                ", isActive=" + isActive +
                '}';
    }
}
