package model;

/**
 * Represents the status of a course enrollment.
 */
public enum EnrollmentStatus {
    PENDING("Pending", "Enrollment is pending payment"),
    ACTIVE("Active", "Student is actively learning"),
    PAUSED("Paused", "Student paused the course"),
    COMPLETED("Completed", "Course has been completed"),
    CANCELLED("Cancelled", "Enrollment was cancelled"),
    EXPIRED("Expired", "Enrollment has expired");

    private final String name;
    private final String description;

    EnrollmentStatus(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
}
