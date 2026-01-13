package challenges;

import data.SampleDataGenerator;
import model.*;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * ═══════════════════════════════════════════════════════════════════════════════
 * CHALLENGE 03: String API Enhancements (Java 11+)
 * ═══════════════════════════════════════════════════════════════════════════════
 *
 * SCENARIO:
 * You are building the Text Processing Module for EduMaster's content management
 * system. You need to clean user input, format course descriptions, process
 * multiline text blocks, and generate formatted reports. Java 11+ introduced
 * powerful new String methods that make text processing much simpler and more
 * efficient than legacy approaches.
 *
 * FOCUS:
 * - isBlank() - Check if string is empty or only whitespace
 * - strip(), stripLeading(), stripTrailing() - Unicode-aware whitespace removal
 * - lines() - Split multiline strings into Stream<String>
 * - repeat(n) - Repeat string n times
 * - indent(n) - Add or remove indentation
 * - transform(Function) - Apply transformations in a fluent way
 *
 * ═══════════════════════════════════════════════════════════════════════════════
 */
public class StringEnhancementsChallenge {

    private final List<Student> students;
    private final List<Course> courses;
    private final List<Instructor> instructors;

    public StringEnhancementsChallenge() {
        this.students = SampleDataGenerator.generateStudents(30);
        this.instructors = SampleDataGenerator.generateInstructors(10);
        this.courses = SampleDataGenerator.generateCourses(15, instructors);
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 1: Checking and Validating Strings
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 1.1: Check if String is Blank
     *
     * Check if a string is empty or contains only whitespace characters.
     * isBlank() returns true for "", "   ", "\t\n", etc.
     * This is more thorough than isEmpty() which only checks for "".
     *
     * EXAMPLE:
     * isInputEmpty("") -> true
     * isInputEmpty("   ") -> true
     * isInputEmpty("\t\n") -> true
     * isInputEmpty("Hello") -> false
     *
     * @param input String to check
     * @return true if blank, false otherwise
     */
    public boolean isInputEmpty(String input) {
        return input == null || input.isBlank();
    }

    /**
     * TASK 1.2: Validate Student Names
     *
     * Filter out students whose names are blank (empty or whitespace only).
     * Return list of students with valid names.
     *
     * @return List of students with non-blank names
     */
    public List<Student> getStudentsWithValidNames() {
        return students.stream()
                    .filter(s -> !s.getFullName().isBlank())
                    .toList();
    }

    /**
     * TASK 1.3: Validate Course Descriptions
     *
     * Check if any course has a blank description.
     * Return true if at least one course has blank description.
     *
     * @return true if any course has blank description
     */
    public boolean hasCoursesWithBlankDescriptions() {
        return courses.stream()
                    .anyMatch(c -> c.getTitle().isBlank());
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 2: Cleaning and Trimming Text
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 2.1: Strip Whitespace
     *
     * Remove leading and trailing whitespace from a string.
     * strip() is Unicode-aware (handles Unicode whitespace characters).
     * trim() only handles ASCII whitespace.
     *
     * EXAMPLE:
     * cleanup("  Hello World  ") -> "Hello World"
     * cleanup("\t\nJava\n\t") -> "Java"
     *
     * @param input String to clean
     * @return Cleaned string
     */
    public String cleanup(String input) {
        return input.strip();
    }

    /**
     * TASK 2.2: Strip Leading Whitespace Only
     *
     * Remove only leading whitespace, keep trailing whitespace.
     * Useful for processing indented code while preserving line endings.
     *
     * EXAMPLE:
     * stripLeading("  Hello  ") -> "Hello  "
     *
     * @param input String to process
     * @return String with leading whitespace removed
     */
    public String stripLeading(String input) {
        return input.stripLeading();
    }

    /**
     * TASK 2.3: Clean All Student Names
     *
     * Clean all student names by stripping whitespace.
     * Return list of cleaned full names in format "FirstName LastName".
     *
     * @return List of cleaned student names
     */
    public List<String> getCleanedStudentNames() {
        return students.stream()
                .map(s -> s.getFirstName().strip() + s.getLastName().strip())
                .toList();
    }

    /**
     * TASK 2.4: Clean Course Descriptions
     *
     * Strip whitespace from all course descriptions.
     * Return list of cleaned descriptions.
     *
     * @return List of cleaned descriptions
     */
    public List<String> getCleanedDescriptions() {
        return courses.stream()
                .map(c -> c.getDescription().strip())
                .toList();
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 3: Processing Multiline Strings
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 3.1: Split Multiline String into Lines
     *
     * Split a multiline string into a Stream of individual lines.
     * lines() handles \n, \r, and \r\n line terminators.
     *
     * EXAMPLE:
     * String text = "Line 1\nLine 2\nLine 3";
     * streamLines(text).forEach(System.out::println);
     * // Output:
     * // Line 1
     * // Line 2
     * // Line 3
     *
     * @param multiline Multiline string
     * @return Stream of individual lines
     */
    public Stream<String> streamLines(String multiline) {
        return multiline.lines();
    }

    /**
     * TASK 3.2: Count Lines in Text
     *
     * Count the number of lines in a multiline string.
     *
     * @param multiline Multiline string
     * @return Number of lines
     */
    public long countLines(String multiline) {
        return multiline.lines().count();
    }

    /**
     * TASK 3.3: Filter Lines by Keyword
     *
     * Extract lines containing a specific keyword from multiline text.
     * Case-insensitive search.
     *
     * EXAMPLE:
     * String text = "Java is great\nPython is popular\nJava is powerful";
     * filterLinesByKeyword(text, "java") -> ["Java is great", "Java is powerful"]
     *
     * @param multiline Multiline string
     * @param keyword Keyword to search for
     * @return List of lines containing the keyword
     */
    public List<String> filterLinesByKeyword(String multiline, String keyword) {
        return multiline.lines()
                .filter(line -> line.toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }

    /**
     * TASK 3.4: Process Course Description Lines
     *
     * Take the first course description, split it into lines, and return
     * only non-blank lines.
     *
     * @return List of non-blank lines from first course description
     */
    public List<String> getCourseDescriptionLines() {
        return courses.getFirst().getDescription().lines()
                    .filter(Predicate.not(String::isBlank))
                    .toList();
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 4: String Repetition
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 4.1: Repeat String
     *
     * Repeat a string n times.
     *
     * EXAMPLE:
     * repeatString("*", 5) -> "*****"
     * repeatString("ab", 3) -> "ababab"
     *
     * @param str String to repeat
     * @param times Number of times to repeat
     * @return Repeated string
     */
    public String repeatString(String str, int times) {
        return str.repeat(times);
    }

    /**
     * TASK 4.2: Create Separator Line
     *
     * Create a separator line of given character and length.
     * Useful for formatting console output.
     *
     * EXAMPLE:
     * createSeparator('=', 50) -> "=================================================="
     *
     * @param character Character to repeat
     * @param length Length of separator
     * @return Separator string
     */
    public String createSeparator(char character, int length) {
        return String.valueOf(character).repeat(length);
    }

    /**
     * TASK 4.3: Generate ASCII Art Border
     *
     * Create a bordered text block with decorative characters.
     *
     * EXAMPLE:
     * createBorder("Hello", '*') ->
     * "*******
     *  *Hello*
     *  *******"
     *
     * @param text Text to border
     * @param borderChar Border character
     * @return Bordered text
     */
    public String createBorder(String text, char borderChar) {
        int borderLength = text.length() + 2;
        String border = String.valueOf(borderChar).repeat(borderLength);

        return border + (borderChar + text + borderChar) + border;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 5: Indentation and Formatting
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 5.1: Indent Text
     *
     * Add indentation to text. Positive values add spaces, negative removes.
     * indent() adds indentation to each line in multiline strings.
     *
     * EXAMPLE:
     * formatCode("public class Test {}") ->
     * "    public class Test {}"
     *
     * @param code Code to indent
     * @return Indented code
     */
    public String formatCode(String code) {
        return code.indent(5);
    }

    /**
     * TASK 5.2: Indent Multiline Code Block
     *
     * Indent a multiline code block by specified number of spaces.
     *
     * EXAMPLE:
     * String code = "public class Test {\n  void method() {}\n}";
     * indentBlock(code, 2) ->
     * "  public class Test {
     *    void method() {}
     *  }"
     *
     * @param code Multiline code
     * @param spaces Number of spaces to indent
     * @return Indented code block
     */
    public String indentBlock(String code, int spaces) {
        return code.indent(spaces);
    }

    /**
     * TASK 5.3: Format Course Description
     *
     * Format a course description by:
     * 1. Cleaning whitespace
     * 2. Indenting by 2 spaces
     * 3. Adding a title line
     *
     * @param course Course to format
     * @return Formatted description
     */
    public String formatCourseDescription(Course course) {
        return "Title: " + course.getTitle().strip()
                .indent(2);
    }

    /**
     * TASK 5.4: Remove Indentation
     *
     * Remove indentation from text using negative indent value.
     *
     * @param indentedText Text with indentation
     * @param spaces Number of spaces to remove (use negative value)
     * @return Text with reduced indentation
     */
    public String removeIndentation(String indentedText, int spaces) {
        return indentedText.indent(spaces);
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 6: String Transformation
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 6.1: Transform String
     *
     * Apply a transformation function to a string in a fluent way.
     * transform() allows chaining transformations.
     *
     * EXAMPLE:
     * "hello".transform(String::toUpperCase) -> "HELLO"
     *
     * @param input String to transform
     * @return Transformed string (uppercase + trimmed)
     */
    public String transformToUpperCase(String input) {
        return input.transform(String::toUpperCase);
    }

    /**
     * TASK 6.2: Chain Multiple Transformations
     *
     * Transform student name by:
     * 1. Strip whitespace
     * 2. Convert to uppercase
     * 3. Replace spaces with underscores
     *
     * Use transform() for fluent chaining.
     *
     * @param name Student name
     * @return Transformed name
     */
    public String transformStudentName(String name) {
        return name.transform(String::strip)
                .transform(String::toUpperCase)
                .transform(n -> n.replace(" ", "_"));
    }

    /**
     * TASK 6.3: Custom Transformation Pipeline
     *
     * Create a custom transformation that:
     * 1. Strips whitespace
     * 2. Capitalizes first letter of each word
     * 3. Removes extra spaces between words
     *
     * @param text Text to transform
     * @return Transformed text
     */
    public String transformToTitleCase(String text) {
        return Arrays.stream(text.trim().split("\\s+"))
            .map(word -> word.substring(0, 1).toUpperCase() + 
                        word.substring(1).toLowerCase())
            .collect(Collectors.joining(" "));
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 7: Practical Text Processing Examples
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 7.1: Generate Student Report
     *
     * Create a formatted report for a student using new String methods.
     *
     * EXAMPLE OUTPUT:
     * ==========================================
     * Student Report
     * ==========================================
     *   Name: John Doe
     *   Email: john.doe@example.com
     *   Level: INTERMEDIATE
     * ==========================================
     *
     * @param student Student to report on
     * @return Formatted report
     */
    public String generateStudentReport(Student student) {
        StringBuilder report = new StringBuilder();
        String separator = "=".repeat(40) + "\n";
        final int indentSpaces = 2;

        report.append(separator);
        report.append("Student Report\n");
        report.append(separator);
        report.append("Name: ".indent(indentSpaces)).append(student.getFullName().strip()).append("\n");
        report.append("Email: ".indent(indentSpaces)).append(student.getEmail().strip()).append("\n");
        report.append("Level: ".indent(indentSpaces)).append(student.getLevel().name()).append("\n");
        report.append(separator);

        return report.toString();
    }

    /**
     * TASK 7.2: Format Course Listing
     *
     * Create a formatted list of all course titles with numbering.
     *
     * EXAMPLE OUTPUT:
     * Course Catalog
     * ====================
     *   1. Complete Java Masterclass
     *   2. Python for Data Science
     *   3. Web Development Bootcamp
     *
     * @return Formatted course listing
     */
    public String formatCourseListing() {
        StringBuilder listing = new StringBuilder();
        String title = "Course Catalog\n";
        String separator = "=".repeat(20) + "\n";

        listing.append(title);
        listing.append(separator);

        for (int i = 0; i < courses.size(); i++) {
            String courseLine = String.format("%d. %s", i + 1, courses.get(i).getTitle().strip());
            listing.append(courseLine.indent(2));
        }

        return listing.toString();
    }

    /**
     * TASK 7.3: Clean and Format User Input
     *
     * Process user input by:
     * 1. Check if blank
     * 2. Strip whitespace
     * 3. Validate minimum length
     * 4. Capitalize first letter
     *
     * @param input User input
     * @param minLength Minimum required length
     * @return Processed input or error message
     */
    public String processUserInput(String input, int minLength) {
        if (input == null || input.isBlank()) {
            return "Error: Input cannot be blank";
        }

        String processed = input.strip();

        if (processed.length() < minLength) {
            return "Error: Input must be at least " + minLength + " characters";
        }

        return processed.substring(0, 1).toUpperCase() + processed.substring(1);
    }

    /**
     * TASK 7.4: Generate Instructor Bio
     *
     * Create a formatted bio section for an instructor with proper indentation.
     *
     * @param instructor Instructor
     * @return Formatted bio
     */
    public String generateInstructorBio(Instructor instructor) {
        StringBuilder bio = new StringBuilder();
        String separator = "-".repeat(40) + "\n";

        bio.append("Instructor Profile\n");
        bio.append(separator);
        bio.append("Name: ".indent(2)).append(instructor.getFullName().strip()).append("\n");
        bio.append("Specialization: ".indent(2)).append(instructor.getSpecialization().strip()).append("\n");
        bio.append("Rating: ".indent(2)).append(String.format("%.1f", instructor.getRating())).append("\n");

        if (instructor.getBio() != null && !instructor.getBio().isBlank()) {
            bio.append("\nBio:\n");
            bio.append(instructor.getBio().strip().indent(2));
        }

        bio.append(separator);

        return bio.toString();
    }

    /**
     * TASK 7.5: Process Multiline Course Description
     *
     * Take a multiline course description and:
     * 1. Split into lines
     * 2. Remove blank lines
     * 3. Strip each line
     * 4. Number each line
     * 5. Indent the result
     *
     * @param description Multiline description
     * @return Processed description
     */
    public String processMultilineDescription(String description) {
        final int[] lineNumber = {1};

        return description.lines()
                .filter(Predicate.not(String::isBlank))
                .map(String::strip)
                .map(line -> lineNumber[0]++ + ". " + line)
                .collect(Collectors.joining("\n"))
                .indent(2);
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // TEST RUNNER
    // ═══════════════════════════════════════════════════════════════════════════

    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════════════════════════════");
        System.out.println("         String API Enhancements Challenge - Modern Text Processing");
        System.out.println("═══════════════════════════════════════════════════════════════════════════════");

        StringEnhancementsChallenge challenge = new StringEnhancementsChallenge();

        // Test 1.1: Check if Blank
        System.out.println("\n[TEST 1.1] Check if String is Blank:");
        System.out.println("Empty string is blank: " + challenge.isInputEmpty(""));
        System.out.println("Whitespace is blank: " + challenge.isInputEmpty("   "));
        System.out.println("Text is blank: " + challenge.isInputEmpty("Hello"));

        // Test 1.2: Validate Student Names
        System.out.println("\n[TEST 1.2] Students with Valid Names:");
        List<Student> validStudents = challenge.getStudentsWithValidNames();
        System.out.println("Found " + validStudents.size() + " students with valid names");

        // Test 2.1: Strip Whitespace
        System.out.println("\n[TEST 2.1] Strip Whitespace:");
        String cleaned = challenge.cleanup("   Hello World   ");
        System.out.println("Cleaned: '" + cleaned + "'");

        // Test 2.3: Clean Student Names
        System.out.println("\n[TEST 2.3] Cleaned Student Names:");
        List<String> cleanedNames = challenge.getCleanedStudentNames();
        cleanedNames.stream().limit(5).forEach(name -> System.out.println("  - " + name));

        // Test 3.1: Stream Lines
        System.out.println("\n[TEST 3.1] Stream Multiline Text:");
        String multiline = "Line 1\nLine 2\nLine 3";
        challenge.streamLines(multiline).forEach(line -> System.out.println("  " + line));

        // Test 3.2: Count Lines
        System.out.println("\n[TEST 3.2] Count Lines:");
        long lineCount = challenge.countLines(multiline);
        System.out.println("Total lines: " + lineCount);

        // Test 4.1: Repeat String
        System.out.println("\n[TEST 4.1] Repeat String:");
        String repeated = challenge.repeatString("*", 10);
        System.out.println(repeated);

        // Test 4.2: Create Separator
        System.out.println("\n[TEST 4.2] Create Separator:");
        String separator = challenge.createSeparator('=', 50);
        System.out.println(separator);

        // Test 5.1: Indent Code
        System.out.println("\n[TEST 5.1] Indent Code:");
        String code = "public class Test {}";
        String indented = challenge.formatCode(code);
        System.out.println("Original: '" + code + "'");
        System.out.println("Indented: '" + indented + "'");

        // Test 5.3: Format Course Description
        System.out.println("\n[TEST 5.3] Format Course Description:");
        if (!challenge.courses.isEmpty()) {
            String formatted = challenge.formatCourseDescription(challenge.courses.get(0));
            System.out.println(formatted);
        }

        // Test 6.1: Transform String
        System.out.println("\n[TEST 6.1] Transform to Uppercase:");
        String transformed = challenge.transformToUpperCase("  hello world  ");
        System.out.println("Transformed: '" + transformed + "'");

        // Test 6.3: Title Case
        System.out.println("\n[TEST 6.3] Transform to Title Case:");
        String titleCase = challenge.transformToTitleCase("  hello   world   from   java  ");
        System.out.println("Title Case: '" + titleCase + "'");

        // Test 7.1: Generate Student Report
        System.out.println("\n[TEST 7.1] Generate Student Report:");
        if (!challenge.students.isEmpty()) {
            String report = challenge.generateStudentReport(challenge.students.get(0));
            System.out.println(report);
        }

        // Test 7.2: Format Course Listing
        System.out.println("\n[TEST 7.2] Format Course Listing:");
        String listing = challenge.formatCourseListing();
        System.out.println(listing);

        // Test 7.4: Generate Instructor Bio
        System.out.println("\n[TEST 7.4] Generate Instructor Bio:");
        if (!challenge.instructors.isEmpty()) {
            String bio = challenge.generateInstructorBio(challenge.instructors.get(0));
            System.out.println(bio);
        }

        System.out.println("\n═══════════════════════════════════════════════════════════════════════════════");
        System.out.println("Challenge completed! Implement remaining TODOs to master String API.");
        System.out.println("═══════════════════════════════════════════════════════════════════════════════");
    }
}
