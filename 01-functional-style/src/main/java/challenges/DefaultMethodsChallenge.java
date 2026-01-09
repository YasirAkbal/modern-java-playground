package challenges;

import model.Course;
import model.Student;

public class DefaultMethodsChallenge {

    /**
     * ═══════════════════════════════════════════════════════════════════════════════
     * CHALLENGE 06: Default Methods in Interfaces (The Diamond Problem)
     * ═══════════════════════════════════════════════════════════════════════════════
     *
     * SCENARIO:
     * You need to create a logging system where different types of logging are handled
     * by separate interfaces. Handle the "Diamond Problem" where a class implements
     * two interfaces with the same default method signature.
     *
     * ═══════════════════════════════════════════════════════════════════════════════
     */

    interface AuditLog {
        /**
         * It should print "Logging action..."
         */

        default void log() {
            System.out.println("Logging action...");
        }
    }

    interface SecureLog {
        /**
         * It should print "Secure logging..."
         */

        default void log() {
            System.out.println("Secure logging...");
        }
    }

    /**
     * Create a class `TransactionService` that implements both AuditLog and SecureLog.
     *
     * The compiler will complain about duplicate 'log' method from both interfaces.
     *
     * You must:
     * 1. Override the 'log' method explicitly
     * 2. Choose which implementation to use with: AuditLog.super.log() or SecureLog.super.log()
     * 3. Or call both if needed
     *
     * Example skeleton:
     * class TransactionService implements AuditLog, SecureLog {
     *     @Override
     *     public void log() {
     *         // Your implementation here
     *     }
     * }
     */

    static class TransactionService implements AuditLog, SecureLog {

        @Override
        public void log() {
            AuditLog.super.log();
            SecureLog.super.log();
        }

    }

    /**
     * TODO TASK 4: Create a test method
     * Create a static method `main` to demonstrate:
     * - Creating an instance of TransactionService
     * - Calling the log() method to show how the diamond problem was resolved
     */

    public static void main(String[] args) {
        TransactionService transactionService = new TransactionService();
        transactionService.log();
    }
}
