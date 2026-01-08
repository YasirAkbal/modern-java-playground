package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an instructor who creates and teaches courses.
 */
public class Instructor {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String bio;
    private String specialization;
    private LocalDate hireDate;
    private double rating;
    private int totalStudents;
    private boolean isVerified;
    private List<Course> courses;

    public Instructor() {
        this.courses = new ArrayList<>();
    }

    public Instructor(String id, String firstName, String lastName, String email, 
                      String specialization) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.specialization = specialization;
        this.hireDate = LocalDate.now();
        this.rating = 0.0;
        this.totalStudents = 0;
        this.isVerified = false;
        this.courses = new ArrayList<>();
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

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public LocalDate getHireDate() { return hireDate; }
    public void setHireDate(LocalDate hireDate) { this.hireDate = hireDate; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public int getTotalStudents() { return totalStudents; }
    public void setTotalStudents(int totalStudents) { this.totalStudents = totalStudents; }

    public boolean isVerified() { return isVerified; }
    public void setVerified(boolean verified) { isVerified = verified; }

    public List<Course> getCourses() { return courses; }
    public void setCourses(List<Course> courses) { this.courses = courses; }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "id='" + id + '\'' +
                ", name='" + getFullName() + '\'' +
                ", specialization='" + specialization + '\'' +
                ", rating=" + rating +
                ", isVerified=" + isVerified +
                '}';
    }
}
