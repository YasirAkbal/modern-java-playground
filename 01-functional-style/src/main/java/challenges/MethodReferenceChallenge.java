package challenges;

import data.SampleDataGenerator;
import model.*;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

/**
 * ===============
 * CHALLENGE 02: Method References Mastery
 * 
 * 
 * SCENARIO:
 * You are refactoring the data processing layer of EduMaster.
 * Replace verbose lambda expressions with concise Method References.
 * 
 * TYPES:
 * 1. Static: ClassName::staticMethod
 * 2. Bound Instance: instance::method
 * 3. Unbound Instance: ClassName::instanceMethod
 * 4. Constructor: ClassName::new
 * 
 * 
 */
public class MethodReferenceChallenge {
    
    private final List<Student> students;
    private final List<Course> courses;
    
    public MethodReferenceChallenge() {
        this.students = SampleDataGenerator.generateStudents(50);
        this.courses = SampleDataGenerator.generateCourses(30, SampleDataGenerator.generateInstructors(10));
    }
    
    // 
    // PART 1: Static Method References
    // 
    
    /**
     * TASK 1.1: Parse Integers
     * Refactor: s -> Integer.parseInt(s)
     */
    public List<Integer> parseNumbers(List<String> numberStrings) {
        // placeholder - original solution removed
        return null;
    }
    
    /**
     * TASK 1.2: Sort Students by Level using static comparator
     * Use StudentUtils::compareByLevel
     */
    public void sortStudentsByLevel(List<Student> list) {
        // TODO: Use StudentUtils::compareByLevel
        // list.sort(...);
    }
    
    // 
    // PART 2: Bound Instance Method References
    // 
    
    /**
     * TASK 2.1: Append Names
     * Use sb::append where sb is an instance of StringBuilder.
     */
    public String joinStudentNames() {
        StringBuilder sb = new StringBuilder();
        // placeholder - original solution removed
        return "";
    }
    
    // 
    // PART 3: Unbound Instance Method References
    // 
    
    /**
     * TASK 3.1: Extract Emails
     * Refactor: s -> s.getEmail() to Student::getEmail
     */
    public List<String> extractEmails() {
        // placeholder - original solution removed
        return null;
    }
    
    /**
     * TASK 3.2: Uppercase Titles
     * Refactor: s -> s.toUpperCase() to String::toUpperCase
     */
    public List<String> getUpperCaseCourseTitles() {
        // placeholder - original solution removed
        return null;
    }
    
    // 
    // PART 4: Constructor References
    // 
    
    /**
     * TASK 4.1: Convert to DTO
     * Use StudentDTO::new
     */
    public List<StudentDTO> convertToDTO() {
        // placeholder - original solution removed
        return null;
    }
    
    public record StudentDTO(String id, String name, String email) {}
    
    // Helper
    public static class StudentUtils {
        public static int compareByLevel(Student s1, Student s2) {
            return s1.getLevel().compareTo(s2.getLevel());
        }
    }
    
}
