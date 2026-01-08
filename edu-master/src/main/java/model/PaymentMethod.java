package model;

/**
 * Represents available payment methods.
 */
public enum PaymentMethod {
    CREDIT_CARD("Credit Card", 2.5),
    DEBIT_CARD("Debit Card", 1.5),
    PAYPAL("PayPal", 3.0),
    BANK_TRANSFER("Bank Transfer", 0.5),
    CRYPTO("Cryptocurrency", 1.0),
    APPLE_PAY("Apple Pay", 2.0),
    GOOGLE_PAY("Google Pay", 2.0);

    private final String displayName;
    private final double processingFeePercentage;

    PaymentMethod(String displayName, double processingFeePercentage) {
        this.displayName = displayName;
        this.processingFeePercentage = processingFeePercentage;
    }

    public String getDisplayName() { return displayName; }
    public double getProcessingFeePercentage() { return processingFeePercentage; }
}
