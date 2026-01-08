package challenges;

import model.Course;
import model.Student;

public class DefaultMethodsChallenge {

    /**
     * ═══════════════════════════════════════════════════════════════════════════════
     * CHALLENGE 06: Default Methods in Interfaces (Interface Evolution)
     * ═══════════════════════════════════════════════════════════════════════════════
     * 
     * SCENARIO:
     * You have an old interface `ReportGenerator` implemented by many classes.
     * You need to add a new capability `exportToCsv` WITHOUT breaking existing implementations.
     * Also, handle the "Diamond Problem" where a class implements two interfaces 
     * with the same default method.
     * 
     * ═══════════════════════════════════════════════════════════════════════════════
     */

    interface ReportGenerator {
         void generate();
         
         /**
          * TASK 1: Add a default method 'exportToCsv'
          * It should print "Exporting to CSV..." by default.
          */
         // TODO: default void exportToCsv() { ... }
    }

    interface AuditLog {
        /**
         * TASK 2: Add a default method 'log'
         * It should print "Logging action..."
         */
        default void log() {
            System.out.println("Logging action...");
        }
    }

    interface SecureLog {
        /**
         * TASK 3: Add a default method 'log'
         * It should print "Secure logging..."
         */
        default void log() {
            System.out.println("Secure logging...");
        }
    }

    /**
     * TASK 4: Solve the Conflict
     * Create a class that implements both AuditLog and SecureLog.
     * Compiler will complain about duplicate 'log' method.
     * Override 'log' and choose one (or both) specific implementation to call.
     * Use: AuditLog.super.log()
     */
    // TODO: class TransactionService implements AuditLog, SecureLog { ... }

}
