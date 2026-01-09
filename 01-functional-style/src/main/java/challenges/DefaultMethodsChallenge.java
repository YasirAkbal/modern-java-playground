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
         * TODO TASK 1: Add a default method 'log'
         * It should print "Logging action..."
         */
    }

    interface SecureLog {
        /**
         * TODO TASK 2: Add a default method 'log'
         * It should print "Secure logging..."
         */
    }

    /**
     * TODO TASK 3: Solve the Diamond Problem
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

    /**
     * TODO TASK 4: Create a test method
     * Create a static method `main` to demonstrate:
     * - Creating an instance of TransactionService
     * - Calling the log() method to show how the diamond problem was resolved
     */

}
