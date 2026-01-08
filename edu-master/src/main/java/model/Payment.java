package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represents a payment transaction in the platform.
 */
public class Payment {
    private String id;
    private String studentId;
    private String courseId;
    private BigDecimal amount;
    private BigDecimal discount;
    private BigDecimal finalAmount;
    private String currency;
    private PaymentMethod paymentMethod;
    private PaymentStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime processedAt;
    private String transactionId;
    private String failureReason;

    public Payment() {
        this.createdAt = LocalDateTime.now();
        this.status = PaymentStatus.PENDING;
        this.currency = "USD";
    }

    public Payment(String id, String studentId, String courseId, BigDecimal amount, 
                   PaymentMethod paymentMethod) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.amount = amount;
        this.discount = BigDecimal.ZERO;
        this.finalAmount = amount;
        this.paymentMethod = paymentMethod;
        this.createdAt = LocalDateTime.now();
        this.status = PaymentStatus.PENDING;
        this.currency = "USD";
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public BigDecimal getDiscount() { return discount; }
    public void setDiscount(BigDecimal discount) { this.discount = discount; }

    public BigDecimal getFinalAmount() { return finalAmount; }
    public void setFinalAmount(BigDecimal finalAmount) { this.finalAmount = finalAmount; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public PaymentMethod getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(PaymentMethod paymentMethod) { this.paymentMethod = paymentMethod; }

    public PaymentStatus getStatus() { return status; }
    public void setStatus(PaymentStatus status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getProcessedAt() { return processedAt; }
    public void setProcessedAt(LocalDateTime processedAt) { this.processedAt = processedAt; }

    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }

    public String getFailureReason() { return failureReason; }
    public void setFailureReason(String failureReason) { this.failureReason = failureReason; }

    public void applyDiscount(BigDecimal discountAmount) {
        this.discount = discountAmount;
        this.finalAmount = this.amount.subtract(discountAmount);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id='" + id + '\'' +
                ", amount=" + amount +
                ", finalAmount=" + finalAmount +
                ", status=" + status +
                ", paymentMethod=" + paymentMethod +
                '}';
    }
}
