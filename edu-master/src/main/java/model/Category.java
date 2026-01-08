package model;

/**
 * Represents course categories in the platform.
 */
public enum Category {
    PROGRAMMING("Programming", "Software development and coding"),
    DATA_SCIENCE("Data Science", "Data analysis, ML, and AI"),
    WEB_DEVELOPMENT("Web Development", "Frontend and backend web technologies"),
    MOBILE_DEVELOPMENT("Mobile Development", "iOS and Android development"),
    DEVOPS("DevOps", "CI/CD, containers, and cloud"),
    DATABASE("Database", "SQL, NoSQL, and data management"),
    SECURITY("Security", "Cybersecurity and ethical hacking"),
    DESIGN("Design", "UI/UX and graphic design"),
    BUSINESS("Business", "Management and entrepreneurship"),
    MARKETING("Marketing", "Digital marketing and SEO");

    private final String displayName;
    private final String description;

    Category(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    public String getDisplayName() { return displayName; }
    public String getDescription() { return description; }
}
