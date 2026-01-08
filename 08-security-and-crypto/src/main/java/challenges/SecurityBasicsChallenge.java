package challenges;

import data.SampleDataGenerator;
import model.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HexFormat;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * ═══════════════════════════════════════════════════════════════════════════════
 * CHALLENGE 01: Security Basics & Cryptographic Utilities
 * ═══════════════════════════════════════════════════════════════════════════════
 *
 * SCENARIO:
 * You are building the Security & Privacy Module for the EduMaster platform.
 * The system must protect sensitive student data, secure payment transactions,
 * and ensure password security. You need to implement encoding, hashing, and
 * data anonymization using modern Java security APIs.
 *
 * FOCUS:
 * - Base64 encoding/decoding for tokens and binary data
 * - HexFormat (Java 17+) for cryptographic hash display
 * - MessageDigest for SHA-256/SHA-512 hashing
 * - SecureRandom for cryptographically strong random values
 * - Real-world security scenarios with student and payment data
 *
 * ═══════════════════════════════════════════════════════════════════════════════
 */
public class SecurityBasicsChallenge {

    private final List<Student> students;
    private final List<Payment> payments;

    public SecurityBasicsChallenge() {
        this.students = SampleDataGenerator.generateStudents(100);
        var instructors = SampleDataGenerator.generateInstructors(20);
        var courses = SampleDataGenerator.generateCourses(50, instructors);
        this.payments = SampleDataGenerator.generatePayments(students, courses, 200);
    }

    // ═══════════════════════════════════════════════════════════════════════════════
    // PART 1: Base64 Encoding & Decoding
    // ═══════════════════════════════════════════════════════════════════════════════

    /**
     * TASK 1.1: Encode Session Token to Base64
     *
     * Encode a session token string to Base64 for URL-safe transmission.
     * This is used for authentication tokens, API keys, and session identifiers.
     *
     * EXAMPLE:
     * Input: "SESSION-12345-USER-TOKEN"
     * Output: "U0VTU0lPTi0xMjM0NS1VU0VSLVRPS0VO"
     *
     * @param input The plain text token to encode
     * @return Base64-encoded string
     */
    public String encodeToBase64(String input) {
        // Placeholder: original implementation removed for challenge reset
        return null;
    }

    /**
     * TASK 1.2: Decode Base64 Token
     *
     * Decode a Base64-encoded token back to the original string.
     * Used when receiving tokens from clients or external APIs.
     *
     * EXAMPLE:
     * Input: "U0VTU0lPTi0xMjM0NS1VU0VSLVRPS0VO"
     * Output: "SESSION-12345-USER-TOKEN"
     *
     * @param encoded Base64-encoded string
     * @return Decoded plain text string
     */
    public String decodeFromBase64(String encoded) {
        // Placeholder: original implementation removed for challenge reset
        return null;
    }

    /**
     * TASK 1.3: Generate Payment Token
     *
     * Generate a unique, Base64-encoded payment token from payment ID and timestamp.
     * Format: "PAY-{paymentId}-{timestamp}"
     * This token is used for secure payment verification and tracking.
     *
     * EXAMPLE:
     * Input: payment with ID "PAY-5001"
     * Output: Base64 encoded string like "UEFZLTVMDA3LTE3MDY3ODQwMDA="
     *
     * @param payment Payment to generate token for
     * @return Base64-encoded payment token
     */
    public String generatePaymentToken(Payment payment) {
        // Placeholder: original implementation removed for challenge reset
        return null;
    }

    // ═══════════════════════════════════════════════════════════════════════════════
    // PART 2: Hexadecimal Formatting (Java 17+)
    // ═══════════════════════════════════════════════════════════════════════════════

    /**
     * TASK 2.1: Convert Bytes to Hex String
     *
     * Convert a byte array to a hexadecimal string representation.
     * This is essential for displaying cryptographic hashes in human-readable format.
     *
     * EXAMPLE:
     * Input: [10, 20, 255]
     * Output: "0a14ff"
     *
     * @param data Byte array to convert
     * @return Lowercase hexadecimal string
     */
    public String toHexString(byte[] data) {
        // Placeholder: original implementation removed for challenge reset
        return null;
    }

    /**
     * TASK 2.2: Parse Hex String to Bytes
     *
     * Parse a hexadecimal string back into a byte array.
     * Used when processing hash values stored as hex strings.
     *
     * EXAMPLE:
     * Input: "cafe"
     * Output: byte array [202, 254]
     *
     * @param hex Hexadecimal string (can be uppercase or lowercase)
     * @return Parsed byte array
     */
    public byte[] fromHexString(String hex) {
        // Placeholder: original implementation removed for challenge reset
        return null;
    }

    /**
     * TASK 2.3: Format Hex with Delimiters
     *
     * Format a byte array as uppercase hex with colon delimiters.
     * Example output: "A1:B2:C3:D4"
     * This format is commonly used for certificate fingerprints and MAC addresses.
     *
     * HINT: Use HexFormat.ofDelimiter(":").withUpperCase()
     *
     * EXAMPLE:
     * Input: [161, 178, 195, 212]
     * Output: "A1:B2:C3:D4"
     *
     * @param data Byte array to format
     * @return Uppercase hex string with colon delimiters
     */
    public String toFormattedHex(byte[] data) {
        // Placeholder: original implementation removed for challenge reset
        return null;
    }

    // ═══════════════════════════════════════════════════════════════════════════════
    // PART 3: Cryptographic Hashing (SHA-256, SHA-512)
    // ═══════════════════════════════════════════════════════════════════════════════

    /**
     * TASK 3.1: Hash Password with SHA-256
     *
     * Hash a password using SHA-256 algorithm and return hex-encoded string.
     * This is a basic password hashing approach (note: production systems should
     * use bcrypt, scrypt, or Argon2 for password hashing with salt).
     *
     * EXAMPLE:
     * Input: "SecurePass123"
     * Output: "a4e624d686e03ed2767c0abd85c14426b0b1157d2ce81d27bb4fe4f6f01d688a"
     *
     * @param password Plain text password to hash
     * @return SHA-256 hash as hexadecimal string
     * @throws NoSuchAlgorithmException if SHA-256 is not available
     */
    public String hashPassword(String password) throws NoSuchAlgorithmException {
        // Placeholder: original implementation removed for challenge reset
        return null;
    }

    /**
     * TASK 3.2: Generate Transaction Hash (SHA-512)
     *
     * Generate a SHA-512 hash for a payment transaction to ensure integrity.
     * Hash format: SHA-512(paymentId + studentId + courseId + amount)
     *
     * HINT:
     * 1. Concatenate payment details
     * 2. Use MessageDigest.getInstance("SHA-512")
     * 3. Convert result to hex using HexFormat
     *
     * EXAMPLE:
     * Input: payment with ID "PAY-5001", student "STU-1001", amount "99.99"
     * Output: 128-character hex string (SHA-512 produces 64 bytes)
     *
     * @param payment Payment transaction to hash
     * @return SHA-512 hash as hexadecimal string
     * @throws NoSuchAlgorithmException if SHA-512 is not available
     */
    public String generateTransactionHash(Payment payment) throws NoSuchAlgorithmException {
        // Placeholder: original implementation removed for challenge reset
        return null;
    }

    /**
     * TASK 3.3: Verify Password Hash
     *
     * Verify if a provided password matches a stored hash.
     * Hash the input password and compare with the stored hash.
     *
     * EXAMPLE:
     * Input: password="MyPassword", storedHash=SHA256("MyPassword")
     * Output: true
     *
     * @param password Plain text password to verify
     * @param storedHash Previously stored password hash
     * @return true if password matches, false otherwise
     * @throws NoSuchAlgorithmException if SHA-256 is not available
     */
    public boolean verifyPassword(String password, String storedHash) throws NoSuchAlgorithmException {
        // Placeholder: original implementation removed for challenge reset
        return false;
    }

    // ═══════════════════════════════════════════════════════════════════════════════
    // PART 4: Data Anonymization & Privacy
    // ═══════════════════════════════════════════════════════════════════════════════

    /**
     * TASK 4.1: Anonymize Email
     *
     * Anonymize a student's email by hashing it with SHA-256.
     * Used for analytics and reporting while preserving privacy (GDPR compliance).
     *
     * EXAMPLE:
     * Input: student with email "john.doe@email.com"
     * Output: "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8"
     *
     * @param student Student whose email should be anonymized
     * @return SHA-256 hash of the email
     * @throws NoSuchAlgorithmException if SHA-256 is not available
     */
    public String anonymizeEmail(Student student) throws NoSuchAlgorithmException {
        // Placeholder: original implementation removed for challenge reset
        return null;
    }

    /**
     * TASK 4.2: Mask Credit Card Number
     *
     * Mask all but the last 4 digits of a credit card number.
     * Example: "4532-1234-5678-9010" becomes "****-****-****-9010"
     *
     * HINT: Use String.replaceAll() or substring operations
     *
     * EXAMPLE:
     * Input: "4532123456789010"
     * Output: "************9010"
     *
     * @param cardNumber Full credit card number
     * @return Masked card number showing only last 4 digits
     */
    public String maskCreditCard(String cardNumber) {
        // Placeholder: original implementation removed for challenge reset
        return null;
    }

    /**
     * TASK 4.3: Generate Anonymized Student Report
     *
     * Create a privacy-safe report of students with anonymized emails.
     * Return a list of strings in format: "Student ID: {id}, Email Hash: {hash}"
     *
     * EXAMPLE:
     * Output: ["Student ID: STU-1001, Email Hash: 5e884898...", ...]
     *
     * @return List of anonymized student records
     * @throws NoSuchAlgorithmException if SHA-256 is not available
     */
    public List<String> generateAnonymizedReport() throws NoSuchAlgorithmException {
        // Placeholder: original implementation removed for challenge reset
        return null;
    }

    // ═══════════════════════════════════════════════════════════════════════════════
    // PART 5: Secure Random Generation
    // ═══════════════════════════════════════════════════════════════════════════════

    /**
     * TASK 5.1: Generate Secure Token
     *
     * Generate a cryptographically secure random token using SecureRandom.
     * Create 32 random bytes and encode as Base64.
     * Used for password reset tokens, API keys, and session identifiers.
     *
     * HINT:
     * 1. Create SecureRandom instance
     * 2. Generate byte array: secureRandom.nextBytes(bytes)
     * 3. Encode to Base64
     *
     * EXAMPLE:
     * Output: "x3FqA9K8mNpQ7vHjL2dR5wY8zT6eB1cV4nM0oP3sU2i="
     *
     * @return Secure random token as Base64 string
     */
    public String generateSecureToken() {
        // Placeholder: original implementation removed for challenge reset
        return null;
    }

    /**
     * TASK 5.2: Generate Random Salt
     *
     * Generate a random salt for password hashing (16 bytes).
     * Salt should be different for each user and stored with the password hash.
     *
     * EXAMPLE:
     * Output: "a1b2c3d4e5f6a7b8c9d0e1f2a3b4c5d6"
     *
     * @return 16-byte salt as hexadecimal string
     */
    public String generateSalt() {
        // Placeholder: original implementation removed for challenge reset
        return null;
    }

    /**
     * TASK 5.3: Generate Payment Reference Code
     *
     * Generate a unique payment reference code combining:
     * - 8-character uppercase alphanumeric random string
     * - Payment ID
     * Format: "REF-{random}-{paymentId}"
     *
     * HINT: Generate random bytes, convert to hex, take first 8 chars, uppercase
     *
     * EXAMPLE:
     * Input: payment with ID "PAY-5001"
     * Output: "REF-A3F7B9E2-PAY-5001"
     *
     * @param payment Payment to generate reference for
     * @return Unique payment reference code
     */
    public String generatePaymentReference(Payment payment) {
        // Placeholder: original implementation removed for challenge reset
        return null;
    }

    // ═══════════════════════════════════════════════════════════════════════════════
    // PART 6: Real-World Security Operations
    // ═══════════════════════════════════════════════════════════════════════════════

    /**
     * TASK 6.1: Hash All Payment Transaction IDs
     *
     * For audit purposes, generate SHA-256 hashes of all payment transaction IDs
     * that have been completed (status = COMPLETED).
     * Return a list of hashed transaction IDs.
     *
     * EXAMPLE:
     * Output: ["a4e624d686e03ed2...", "b7f935e897f14fe3...", ...]
     *
     * @return List of hashed transaction IDs
     * @throws NoSuchAlgorithmException if SHA-256 is not available
     */
    public List<String> hashAllTransactionIds() throws NoSuchAlgorithmException {
        // Placeholder: original implementation removed for challenge reset
        return null;
    }

    /**
     * TASK 6.2: Validate Password Strength
     *
     * Check if a password meets security requirements:
     * - At least 8 characters
     * - Contains at least one digit
     * - Contains at least one uppercase letter
     * - Contains at least one lowercase letter
     *
     * HINT: Use String methods: length(), matches() with regex patterns
     *
     * EXAMPLE:
     * Input: "SecurePass123"
     * Output: true
     *
     * Input: "weak"
     * Output: false
     *
     * @param password Password to validate
     * @return true if password meets all requirements
     */
    public boolean validatePasswordStrength(String password) {
        // Placeholder: original implementation removed for challenge reset
        return false;
    }

    /**
     * TASK 6.3: Generate Secure Student ID
     *
     * Generate a secure, unique student ID by:
     * 1. Creating a UUID
     * 2. Hashing it with SHA-256
     * 3. Taking first 12 characters of hex hash
     * 4. Formatting as "STU-{hash}"
     *
     * EXAMPLE:
     * Output: "STU-a4e624d686e0"
     *
     * @return Secure student ID
     * @throws NoSuchAlgorithmException if SHA-256 is not available
     */
    public String generateSecureStudentId() throws NoSuchAlgorithmException {
        // Placeholder: original implementation removed for challenge reset
        return null;
    }

    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("  Security Basics & Cryptographic Utilities - Test Suite");
        System.out.println("═══════════════════════════════════════════════════════════");

        var challenge = new SecurityBasicsChallenge();

        try {
            // Test PART 1: Base64 Encoding
            System.out.println("\n--- PART 1: Base64 Encoding & Decoding ---");
            String token = "SESSION-12345-USER-TOKEN";
            String encoded = challenge.encodeToBase64(token);
            String decoded = challenge.decodeFromBase64(encoded);
            System.out.println("Original: " + token);
            System.out.println("Encoded:  " + encoded);
            System.out.println("Decoded:  " + decoded);
            System.out.println("Match:    " + token.equals(decoded));

            if (!challenge.payments.isEmpty()) {
                String paymentToken = challenge.generatePaymentToken(challenge.payments.get(0));
                System.out.println("Payment Token: " + paymentToken);
            }

            // Test PART 2: Hex Formatting
            System.out.println("\n--- PART 2: Hexadecimal Formatting ---");
            byte[] testBytes = {10, 20, (byte) 255, (byte) 202, (byte) 254};
            String hex = challenge.toHexString(testBytes);
            System.out.println("Bytes to Hex: " + hex);
            String formatted = challenge.toFormattedHex(new byte[]{(byte) 161, (byte) 178, (byte) 195, (byte) 212});
            System.out.println("Formatted Hex: " + formatted);

            // Test PART 3: Cryptographic Hashing
            System.out.println("\n--- PART 3: Cryptographic Hashing ---");
            String password = "SecurePass123";
            String passwordHash = challenge.hashPassword(password);
            System.out.println("Password: " + password);
            System.out.println("SHA-256 Hash: " + passwordHash);
            System.out.println("Hash Length: " + passwordHash.length() + " characters (64 hex = 32 bytes)");

            boolean isValid = challenge.verifyPassword(password, passwordHash);
            System.out.println("Password Verified: " + isValid);

            if (!challenge.payments.isEmpty()) {
                String txHash = challenge.generateTransactionHash(challenge.payments.get(0));
                System.out.println("Transaction Hash: " + txHash);
            }

            // Test PART 4: Data Anonymization
            System.out.println("\n--- PART 4: Data Anonymization & Privacy ---");
            if (!challenge.students.isEmpty()) {
                Student student = challenge.students.get(0);
                String anonEmail = challenge.anonymizeEmail(student);
                System.out.println("Original Email: " + student.getEmail());
                System.out.println("Anonymized: " + anonEmail);
            }

            String cardNumber = "4532123456789010";
            String masked = challenge.maskCreditCard(cardNumber);
            System.out.println("Original Card: " + cardNumber);
            System.out.println("Masked Card: " + masked);

            // Test PART 5: Secure Random Generation
            System.out.println("\n--- PART 5: Secure Random Generation ---");
            String secureToken = challenge.generateSecureToken();
            System.out.println("Secure Token: " + secureToken);

            String salt = challenge.generateSalt();
            System.out.println("Random Salt: " + salt);

            if (!challenge.payments.isEmpty()) {
                String reference = challenge.generatePaymentReference(challenge.payments.get(0));
                System.out.println("Payment Reference: " + reference);
            }

            // Test PART 6: Real-World Security Operations
            System.out.println("\n--- PART 6: Real-World Security Operations ---");
            List<String> hashedTxIds = challenge.hashAllTransactionIds();
            System.out.println("Hashed Transaction IDs: " + hashedTxIds.size());
            if (!hashedTxIds.isEmpty()) {
                System.out.println("Sample: " + hashedTxIds.get(0));
            }

            System.out.println("\nPassword Strength Tests:");
            System.out.println("'SecurePass123': " + challenge.validatePasswordStrength("SecurePass123"));
            System.out.println("'weak': " + challenge.validatePasswordStrength("weak"));
            System.out.println("'NoDigits': " + challenge.validatePasswordStrength("NoDigits"));

            String secureId = challenge.generateSecureStudentId();
            System.out.println("Secure Student ID: " + secureId);

        } catch (NoSuchAlgorithmException e) {
            System.err.println("Security algorithm not available: " + e.getMessage());
        }

        System.out.println("\n═══════════════════════════════════════════════════════════");
    }
}
