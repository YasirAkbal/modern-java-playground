package model;

/**
 * Represents the experience level of a student.
 */
public enum StudentLevel {
    BEGINNER("Beginner", 1, 0.0),
    INTERMEDIATE("Intermediate", 2, 10.0),
    ADVANCED("Advanced", 3, 20.0),
    EXPERT("Expert", 4, 30.0);

    private final String displayName;
    private final int rank;
    private final double discountPercentage;

    StudentLevel(String displayName, int rank, double discountPercentage) {
        this.displayName = displayName;
        this.rank = rank;
        this.discountPercentage = discountPercentage;
    }

    public String getDisplayName() { return displayName; }
    public int getRank() { return rank; }
    public double getDiscountPercentage() { return discountPercentage; }
}
