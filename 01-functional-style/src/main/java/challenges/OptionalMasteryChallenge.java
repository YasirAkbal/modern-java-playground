package challenges;

import data.SampleDataGenerator;
import model.*;

import java.util.*;

/**
 * 
 * CHALLENGE 05: Optional Mastery - Eliminating NullPointerExceptions
 * 
 * 
 * SCENARIO:
 * Refactor the service layer to handle absent values gracefully using Optional API.
 * Adopt "Defensive Coding" via functional style instead of "if (x != null)".
 * 
 * FOCUS:
 * - Creation (of, ofNullable)
 * - Retrieval (orElse, orElseGet, orElseThrow)
 * - Transformation (map, flatMap, filter)
 * - Java 9+ Action (ifPresentOrElse)
 * 
 * 
 */
public class OptionalMasteryChallenge {
    
    private final List<Student> students;
    private final List<Course> courses;
    
    public OptionalMasteryChallenge() {
        this.students = SampleDataGenerator.generateStudents(100);
        this.courses = SampleDataGenerator.generateCourses(50, SampleDataGenerator.generateInstructors(10));
    }
    
    /**
     * TASK 1: Safe Retrieval
     * Find student by ID. If not found, return an empty Optional.
     */
    public Optional<Student> findStudentById(String id) {
        Optional<Student> student = students
                        .stream()
                        .filter(s -> s.getId().equals(id))
                        .findFirst();

        return student;
    }
    
    /**
     * TASK 2: Default Value
     * Get student name by ID. If student not found, return "Unknown Student".
     */
    public String getStudentNameOrDefault(String id) {
        Optional<String> studentName = students
                            .stream()
                            .filter(s -> s.getId().equals(id))
                            .map(s -> s.getFullName())
                            .findFirst();

        return studentName.orElse("Unknown Student");
    }
    
    /**
     * TASK 3: Exception Handling
     * Get course by ID. If not found, throw IllegalArgumentException.
     */
    public Course getCourseOrThrow(String id) {
        Optional<Course> course = courses
                    .stream()
                    .filter(c -> c.getId().equals(id))
                    .findFirst();

        // Fix: Use orElseThrow with a supplier
        return course.orElseThrow(() -> new IllegalArgumentException("Course is not found"));
    }
    
    /**
     * TASK 4: Email Validation (Filter & Map)
     * Find student -> Get Email -> Check if ends with "@gmail.com" -> Return Email.
     * If any step fails, return empty.
     */
    public Optional<String> getVerifiedGmail(String studentId) {
        Optional<String> email = students
                            .stream()
                            .filter(s -> s.getId().equals(studentId))
                            .map(Student::getEmail)
                            .filter(e -> e != null && e.endsWith("@gmail.com"))
                            .findFirst();

        return email;
    }
    
    /**
     * TASK 5: Java 9 ifPresentOrElse
     * If student exists, print name. If not, print "Not Found".
     */
    public void printStudentStatus(String id) {
        Optional<Student> student = students
                            .stream()
                            .filter(s -> s.getId().equals(id))
                            .findFirst();

        student.ifPresentOrElse(
            s -> System.out.println(s.getFullName()), 
            () -> System.out.println("Not Found")
        );
    }

    /**
     * TASK 6: Chaining Optionals (Java 9 'or')
     * Try to find student in primary list.
     * If empty, try to find in backup list (simulated).
     */
    public Optional<Student> findIdeallyOrBackup(String id) {
        return findStudentById(id).or(() -> findStudentById("1"));
    }

    /**
     * TASK 7: Optional to Stream (Java 9)
     * Convert Optional to Stream to flatMap it into a stream of courses.
     * Return stream of courses the student is enrolled in.
     */
    public java.util.stream.Stream<Course> getStudentCoursesAsStream(String id) {
        return findStudentById(id)
                    .stream()
                    .flatMap(s -> s.getEnrollments().stream())
                    .map(Enrollment::getCourse);
    }


    public static void main(String[] args) {
        OptionalMasteryChallenge svc = new OptionalMasteryChallenge();

        // helper to capture printed output
        java.util.function.Supplier<String> capture = () -> {
            java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
            java.io.PrintStream oldOut = System.out;
            System.setOut(new java.io.PrintStream(baos));
            return null; // placeholder, we'll use inline capture where needed
        };

        System.out.println("=== OptionalMasteryChallenge - AD HOC TESTS ===");

        // TEST 1: findStudentById existing vs missing
        String existingId = "1";
        String missingId = "no-such-id";
        System.out.println("-- TEST 1: findStudentById --");
        System.out.println("EXPECTED (existing): present");
        System.out.println("ACTUAL   (existing): " + (svc.findStudentById(existingId).isPresent() ? "present" : "empty"));
        System.out.println("EXPECTED (missing): empty");
        System.out.println("ACTUAL   (missing): " + (svc.findStudentById(missingId).isPresent() ? "present" : "empty"));

        // TEST 2: getStudentNameOrDefault
        System.out.println("\n-- TEST 2: getStudentNameOrDefault --");
        System.out.println("EXPECTED (missing): Unknown Student");
        System.out.println("ACTUAL   (missing): " + svc.getStudentNameOrDefault(missingId));

        // TEST 3: getCourseOrThrow (existing and missing)
        System.out.println("\n-- TEST 3: getCourseOrThrow --");
        System.out.println("EXPECTED (existing): returns Course");
        try {
            Course c = svc.getCourseOrThrow(existingId);
            System.out.println("ACTUAL   (existing): returned Course id=" + c.getId() + " title=" + c.getTitle());
        } catch (Exception ex) {
            System.out.println("ACTUAL   (existing): threw " + ex);
        }
        System.out.println("EXPECTED (missing): throws IllegalArgumentException");
        try {
            svc.getCourseOrThrow(missingId);
            System.out.println("ACTUAL   (missing): returned Course (unexpected)");
        } catch (Exception ex) {
            System.out.println("ACTUAL   (missing): threw " + ex.getClass().getSimpleName() + " - " + ex.getMessage());
        }

        // TEST 4: getVerifiedGmail (missing)
        System.out.println("\n-- TEST 4: getVerifiedGmail --");
        System.out.println("EXPECTED: empty Optional for missing id");
        System.out.println("ACTUAL  : " + svc.getVerifiedGmail(missingId));

        // TEST 5: printStudentStatus (capture output)
        System.out.println("\n-- TEST 5: printStudentStatus --");
        System.out.println("EXPECTED (existing): prints student name");
        java.io.ByteArrayOutputStream baos1 = new java.io.ByteArrayOutputStream();
        java.io.PrintStream oldOut = System.out;
        System.setOut(new java.io.PrintStream(baos1));
        svc.printStudentStatus(existingId);
        System.out.flush();
        System.setOut(oldOut);
        System.out.println("ACTUAL   (existing): " + baos1.toString().trim());

        System.out.println("EXPECTED (missing): prints Not Found");
        java.io.ByteArrayOutputStream baos2 = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(baos2));
        svc.printStudentStatus(missingId);
        System.out.flush();
        System.setOut(oldOut);
        System.out.println("ACTUAL   (missing): " + baos2.toString().trim());

        // TEST 6: findIdeallyOrBackup
        System.out.println("\n-- TEST 6: findIdeallyOrBackup --");
        System.out.println("EXPECTED (missing -> backup '1'): present");
        System.out.println("ACTUAL   : " + (svc.findIdeallyOrBackup(missingId).isPresent() ? "present" : "empty"));

        // TEST 7: getStudentCoursesAsStream
        System.out.println("\n-- TEST 7: getStudentCoursesAsStream --");
        System.out.println("EXPECTED: stream of courses (may be empty) - show count");
        long count = svc.getStudentCoursesAsStream(existingId).count();
        System.out.println("ACTUAL  : course count = " + count);

        System.out.println("\n=== TESTS COMPLETE ===");
    }
}
