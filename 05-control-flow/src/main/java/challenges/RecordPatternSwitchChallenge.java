package challenges;

import data.SampleDataGenerator;
import model.*;

import java.time.LocalDateTime;
import java.util.*;

/**
 * ═══════════════════════════════════════════════════════════════════════════════
 * CHALLENGE 03: Record Patterns - Destructuring and Deep Matching
 * ═══════════════════════════════════════════════════════════════════════════════
 *
 * SCENARIO:
 * You are building an Analytics Engine for the EduMaster platform that processes
 * complex nested data structures (enrollments containing courses, students with
 * scores, etc.). You need to extract and analyze nested information without
 * verbose getter chains using record patterns and destructuring.
 *
 * FOCUS:
 * - Record pattern matching (Java 21+)
 * - Destructuring records in pattern matching
 * - Nested pattern matching for deep data structures
 * - Combining record patterns with guards
 * - Type-safe data extraction without null checks
 *
 * ═══════════════════════════════════════════════════════════════════════════════
 */
public class RecordPatternSwitchChallenge {

    private final List<Student> students;
    private final List<Course> courses;
    private final List<Instructor> instructors;
    private final List<Enrollment> enrollments;

    public RecordPatternSwitchChallenge() {
        this.students = SampleDataGenerator.generateStudents(80);
        this.instructors = SampleDataGenerator.generateInstructors(12);
        this.courses = SampleDataGenerator.generateCourses(35, instructors);
        this.enrollments = SampleDataGenerator.generateEnrollments(students, courses, 150);
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 1: Basic Record Patterns - Geometric Shapes
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * Simple record types for demonstrating pattern matching
     */
    record Point(int x, int y) {}
    record Line(Point start, Point end) {}
    record Circle(Point center, int radius) {}
    record Rectangle(Point topLeft, Point bottomRight) {}

    /**
     * TASK 1.1: Basic Record Destructuring
     *
     * Extract and analyze shape properties using record patterns:
     * - Circle at origin (0,0) -> "Origin Circle R=" + radius
     * - Circle at any point -> "Circle at (" + x + "," + y + ") R=" + radius
     * - Line -> "Line from (" + x1 + "," + y1 + ") to (" + x2 + "," + y2 + ")"
     * - Point -> "Point at (" + x + "," + y + ")"
     * - Other -> "Unknown Shape"
     *
     * Use record patterns to destructure directly in switch.
     *
     * EXAMPLE:
     * analyzeShape(new Circle(new Point(0,0), 5)) -> "Origin Circle R=5"
     * analyzeShape(new Line(new Point(0,0), new Point(5,5))) -> "Line from (0,0) to (5,5)"
     *
     * @param shape Shape object to analyze
     * @return Description string
     */
    public String analyzeShape(Object shape) {
        return switch(shape) {
            case Circle c when c.center.equals(new Point(0, 0)) -> "Origin Circle R=" + c.radius;
            case Circle c -> "Circle at (%d,%d) R=%d".formatted(c.center.x, c.center.y, c.radius);
            case Line l -> "Line from (%d,%d) to (%d,%d)".formatted(
                    l.start.x, l.start.y, l.end.x, l.end.y);
            case Point p -> "Point at (%d,%d)".formatted(p.x, p.y);
            default -> "Unknown Shape";
        };
    }

    /**
     * TASK 1.2: Deep Destructuring with instanceof
     *
     * Check if a line lies on the X-axis (both Y coordinates are 0).
     * Use instanceof with record pattern destructuring.
     *
     * EXAMPLE:
     * isXAxisLine(new Line(new Point(0,0), new Point(5,0))) -> true
     * isXAxisLine(new Line(new Point(0,1), new Point(5,0))) -> false
     *
     * @param obj Object to check
     * @return True if line is on X-axis
     */
    public boolean isXAxisLine(Object obj) {
        if(obj instanceof Line(Point start, Point end)) {
            return start.y == 0 && end.y == 0;
        }
        
        return false;
    }

    /**
     * TASK 1.3: Guarded Record Pattern
     *
     * Check if a Rectangle is actually a square (width equals height).
     * Use pattern matching with guards.
     *
     * EXAMPLE:
     * isSquare(new Rectangle(new Point(0,0), new Point(5,5))) -> true
     * isSquare(new Rectangle(new Point(0,0), new Point(5,3))) -> false
     *
     * @param shape Shape to check
     * @return True if rectangle is a square
     */
    public boolean isSquare(Object shape) {
        return shape instanceof Rectangle(Point topLeft, Point bottomRight)
                && (bottomRight.x - topLeft.x) == (bottomRight.y - topLeft.y);
    }

    /**
     * TASK 1.4: Calculate Shape Area
     *
     * Calculate area for different shapes:
     * - Circle -> π * r²
     * - Rectangle -> width * height
     * - Line -> 0 (lines have no area)
     * - Point -> 0 (points have no area)
     * - Other -> -1
     *
     * EXAMPLE:
     * calculateArea(new Circle(new Point(0,0), 5)) -> ~78.54
     * calculateArea(new Rectangle(new Point(0,0), new Point(4,3))) -> 12.0
     *
     * @param shape Shape object
     * @return Area value
     */
    public double calculateArea(Object shape) {
        return switch(shape) {
            case Circle(Point _, int radius) -> Math.PI * radius * radius;
            case Rectangle(Point topLeft, Point bottomRight) ->
                (bottomRight.x - topLeft.x) * (bottomRight.y - topLeft.y);
            case Line _ -> 0.0;
            case Point _ -> 0.0;
            default -> -1.0;
        };
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 2: Domain-Specific Records for EduMaster
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * Records representing analytics events and summaries
     */
    record EnrollmentEvent(String studentId, String courseId, String courseName,
                          LocalDateTime timestamp, int progress) {}

    record StudentScore(String studentId, String studentName, double score,
                       StudentLevel level) {}

    record CourseStats(String courseId, String title, int enrollments,
                      double rating, boolean published) {}

    record CompletionSummary(String studentId, String courseId, double score,
                            LocalDateTime completedAt, boolean certified) {}

    /**
     * TASK 2.1: Event Classification
     *
     * Classify enrollment events based on progress:
     * - EnrollmentEvent with progress >= 100 -> "COMPLETED: " + courseName
     * - EnrollmentEvent with progress >= 75 -> "NEAR_COMPLETION: " + courseName
     * - EnrollmentEvent with progress >= 50 -> "HALFWAY: " + courseName
     * - EnrollmentEvent with progress >= 25 -> "IN_PROGRESS: " + courseName
     * - EnrollmentEvent with progress > 0 -> "JUST_STARTED: " + courseName
     * - EnrollmentEvent with progress == 0 -> "NOT_STARTED: " + courseName
     * - Other -> "UNKNOWN_EVENT"
     *
     * EXAMPLE:
     * classifyEvent(new EnrollmentEvent("S1", "C1", "Java 101", now, 85))
     *   -> "NEAR_COMPLETION: Java 101"
     *
     * @param event Event object
     * @return Classification string
     */
    public String classifyEvent(Object event) {
        return switch(event) {
            case EnrollmentEvent(_, _, String courseName, _, int progress) when progress >= 100 ->
                "COMPLETED: " + courseName;
            case EnrollmentEvent(_, _, String courseName, _, int progress) when progress >= 75 ->
                "NEAR_COMPLETION: " + courseName;
            case EnrollmentEvent(_, _, String courseName, _, int progress) when progress >= 50 ->
                "HALFWAY: " + courseName;
            case EnrollmentEvent(_, _, String courseName, _, int progress) when progress >= 25 ->
                "IN_PROGRESS: " + courseName;
            case EnrollmentEvent(_, _, String courseName, _, int progress) when progress > 0 ->
                "JUST_STARTED: " + courseName;
            case EnrollmentEvent(_, _, String courseName, _, int progress) when progress == 0 ->
                "NOT_STARTED: " + courseName;
            default -> "UNKNOWN_EVENT";
        };
    }

    /**
     * TASK 2.2: Score-Based Student Categorization
     *
     * Categorize students based on score and level:
     * - ADVANCED student, score >= 90 -> "EXPERT"
     * - ADVANCED student, score >= 70 -> "PROFICIENT"
     * - INTERMEDIATE student, score >= 80 -> "ADVANCING"
     * - INTERMEDIATE student, score >= 60 -> "PROGRESSING"
     * - BEGINNER student, score >= 70 -> "FAST_LEARNER"
     * - BEGINNER student -> "LEARNING"
     * - Not a StudentScore -> "INVALID"
     *
     * EXAMPLE:
     * categorizeStudent(new StudentScore("S1", "Alice", 92, ADVANCED)) -> "EXPERT"
     *
     * @param obj Object to categorize
     * @return Category string
     */
    public String categorizeStudent(Object obj) {
        return switch(obj) {
            case StudentScore(_, _, double score, StudentLevel level)
                when level == StudentLevel.ADVANCED && score >= 90 -> "EXPERT";
            case StudentScore(_, _, double score, StudentLevel level)
                when level == StudentLevel.ADVANCED && score >= 70 -> "PROFICIENT";
            case StudentScore(_, _, double score, StudentLevel level)
                when level == StudentLevel.INTERMEDIATE && score >= 80 -> "ADVANCING";
            case StudentScore(_, _, double score, StudentLevel level)
                when level == StudentLevel.INTERMEDIATE && score >= 60 -> "PROGRESSING";
            case StudentScore(_, _, double score, StudentLevel level)
                when level == StudentLevel.BEGINNER && score >= 70 -> "FAST_LEARNER";
            case StudentScore(_, _, _, StudentLevel level)
                when level == StudentLevel.BEGINNER -> "LEARNING";
            default -> "INVALID";
        };
    }

    /**
     * TASK 2.3: Course Statistics Analysis
     *
     * Analyze course performance:
     * - Published, rating > 4.5, enrollments > 1000 -> "BESTSELLER"
     * - Published, rating > 4.0, enrollments > 500 -> "POPULAR"
     * - Published, enrollments > 100 -> "GROWING"
     * - Published, enrollments <= 100 -> "NEW"
     * - Not published -> "DRAFT"
     * - Not CourseStats -> "INVALID"
     *
     * EXAMPLE:
     * analyzeCourse(new CourseStats("C1", "Java", 1200, 4.7, true)) -> "BESTSELLER"
     *
     * @param obj Object to analyze
     * @return Analysis result
     */
    public String analyzeCourse(Object obj) {
        return switch(obj) {
            case CourseStats(_, _, int enrollments, double rating, boolean published)
                when published && rating > 4.5 && enrollments > 1000 -> "BESTSELLER";
            case CourseStats(_, _, int enrollments, double rating, boolean published)
                when published && rating > 4.0 && enrollments > 500 -> "POPULAR";
            case CourseStats(_, _, int enrollments, _, boolean published)
                when published && enrollments > 100 -> "GROWING";
            case CourseStats(_, _, int enrollments, _, boolean published)
                when published && enrollments <= 100 -> "NEW";
            case CourseStats(_, _, _, _, boolean published)
                when !published -> "DRAFT";
            default -> "INVALID";
        };
    }

    /**
     * TASK 2.4: Completion Certificate Validator
     *
     * Validate completion and determine certification:
     * - CompletionSummary (score >= 90, certified true) -> "HONORS_CERTIFICATE"
     * - CompletionSummary (score >= 80, certified true) -> "MERIT_CERTIFICATE"
     * - CompletionSummary (score >= 70, certified true) -> "COMPLETION_CERTIFICATE"
     * - CompletionSummary (score >= 70, certified false) -> "PENDING_VERIFICATION"
     * - CompletionSummary (score < 70) -> "INSUFFICIENT_SCORE"
     * - Other -> "INVALID"
     *
     * EXAMPLE:
     * validateCertificate(new CompletionSummary("S1", "C1", 92, now, true))
     *   -> "HONORS_CERTIFICATE"
     *
     * @param obj Object to validate
     * @return Certificate type
     */
    public String validateCertificate(Object obj) {
        return switch(obj) {
            case CompletionSummary(_, _, double score, _, boolean certified)
                when score >= 90 && certified -> "HONORS_CERTIFICATE";
            case CompletionSummary(_, _, double score, _, boolean certified)
                when score >= 80 && certified -> "MERIT_CERTIFICATE";
            case CompletionSummary(_, _, double score, _, boolean certified)
                when score >= 70 && certified -> "COMPLETION_CERTIFICATE";
            case CompletionSummary(_, _, double score, _, boolean certified)
                when score >= 70 && !certified -> "PENDING_VERIFICATION";
            case CompletionSummary(_, _, double score, _, _)
                when score < 70 -> "INSUFFICIENT_SCORE";
            default -> "INVALID";
        };
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 3: Nested Record Patterns - Complex Data Structures
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * Nested records for complex scenarios
     */
    record Address(String city, String country) {}
    record ContactInfo(String email, Address address) {}
    record StudentProfile(String id, String name, ContactInfo contact, StudentLevel level) {}

    record InstructorInfo(String id, String name, String specialization, boolean verified) {}
    record CourseWithInstructor(String courseId, String title, InstructorInfo instructor,
                               int enrollments) {}

    record EnrollmentDetails(StudentProfile student, CourseWithInstructor course,
                            int progress, Double score) {}

    /**
     * TASK 3.1: Deep Nested Extraction
     *
     * Extract location information from deeply nested student profile:
     * - StudentProfile with city and country -> "Student from " + city + ", " + country
     * - StudentProfile with null address -> "Location unknown"
     * - Other -> "Invalid profile"
     *
     * EXAMPLE:
     * extractLocation(new StudentProfile("S1", "Alice",
     *   new ContactInfo("alice@email.com", new Address("Boston", "USA")), BEGINNER))
     *   -> "Student from Boston, USA"
     *
     * @param obj Profile object
     * @return Location string
     */
    public String extractLocation(Object obj) {
        if (obj instanceof StudentProfile sp) {
            ContactInfo contact = sp.contact();
            if (contact != null && contact.address() != null) {
                Address addr = contact.address();
                return "Student from " + addr.city() + ", " + addr.country();
            } else {
                return "Location unknown";
            }
        }
        return "Invalid profile";
    }

    /**
     * TASK 3.2: Course Quality Assessment
     *
     * Assess course quality based on instructor and enrollments:
     * - Verified instructor, enrollments > 1000 -> "PREMIUM_QUALITY"
     * - Verified instructor, enrollments > 500 -> "HIGH_QUALITY"
     * - Verified instructor -> "QUALITY_ASSURED"
     * - Not verified, enrollments > 1000 -> "POPULAR_UNVERIFIED"
     * - Other -> "STANDARD"
     * - Not CourseWithInstructor -> "INVALID"
     *
     * EXAMPLE:
     * assessQuality(new CourseWithInstructor("C1", "Java",
     *   new InstructorInfo("I1", "John", "Java", true), 1200))
     *   -> "PREMIUM_QUALITY"
     *
     * @param obj Course object
     * @return Quality assessment
     */
    public String assessQuality(Object obj) {
        return switch(obj) {
            case CourseWithInstructor(_, _, InstructorInfo(_, _, _, boolean verified), int enrollments)
                when verified && enrollments > 1000 -> "PREMIUM_QUALITY";
            case CourseWithInstructor(_, _, InstructorInfo(_, _, _, boolean verified), int enrollments)
                when verified && enrollments > 500 -> "HIGH_QUALITY";
            case CourseWithInstructor(_, _, InstructorInfo(_, _, _, boolean verified), _)
                when verified -> "QUALITY_ASSURED";
            case CourseWithInstructor(_, _, InstructorInfo(_, _, _, boolean verified), int enrollments)
                when !verified && enrollments > 1000 -> "POPULAR_UNVERIFIED";
            case CourseWithInstructor(_, _, _, _) -> "STANDARD";
            default -> "INVALID";
        };
    }

    /**
     * TASK 3.3: Enrollment Status Reporter
     *
     * Generate detailed enrollment status report:
     * - Student (ADVANCED), verified instructor, score >= 90 ->
     *   "EXCELLENT: [name] scored [score] in [title]"
     * - Student (ADVANCED), verified instructor, score >= 80 ->
     *   "VERY_GOOD: [name] scored [score] in [title]"
     * - Progress >= 75, no score yet ->
     *   "NEAR_COMPLETION: [name] is at [progress]% in [title]"
     * - Progress < 25 ->
     *   "EARLY_STAGE: [name] just started [title]"
     * - Other ->
     *   "IN_PROGRESS: [name] is learning [title]"
     * - Not EnrollmentDetails -> "INVALID"
     *
     * EXAMPLE:
     * reportStatus(new EnrollmentDetails(
     *   new StudentProfile("S1", "Alice", contact, ADVANCED),
     *   new CourseWithInstructor("C1", "Java 101", verifiedInstructor, 1000),
     *   100, 95.0))
     *   -> "EXCELLENT: Alice scored 95.0 in Java 101"
     *
     * @param obj Enrollment object
     * @return Status report
     */
    public String reportStatus(Object obj) {
        return switch(obj) {
            case EnrollmentDetails(
                StudentProfile(_, String name, _, StudentLevel level),
                CourseWithInstructor(_, String title, InstructorInfo(_, _, _, boolean verified), _),
                _, Double score)
                when level == StudentLevel.ADVANCED && verified && score != null && score >= 90 ->
                "EXCELLENT: " + name + " scored " + score + " in " + title;
            case EnrollmentDetails(
                StudentProfile(_, String name, _, StudentLevel level),
                CourseWithInstructor(_, String title, InstructorInfo(_, _, _, boolean verified), _),
                _, Double score)
                when level == StudentLevel.ADVANCED && verified && score != null && score >= 80 ->
                "VERY_GOOD: " + name + " scored " + score + " in " + title;
            case EnrollmentDetails(
                StudentProfile(_, String name, _, _),
                CourseWithInstructor(_, String title, _, _),
                int progress, Double score)
                when progress >= 75 && score == null ->
                "NEAR_COMPLETION: " + name + " is at " + progress + "% in " + title;
            case EnrollmentDetails(
                StudentProfile(_, String name, _, _),
                CourseWithInstructor(_, String title, _, _),
                int progress, _)
                when progress < 25 ->
                "EARLY_STAGE: " + name + " just started " + title;
            case EnrollmentDetails(
                StudentProfile(_, String name, _, _),
                CourseWithInstructor(_, String title, _, _),
                _, _) ->
                "IN_PROGRESS: " + name + " is learning " + title;
            default -> "INVALID";
        };
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 4: Pattern Matching with Collections in Records
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * Records containing collections
     */
    record StudentWithScores(String id, String name, List<Double> scores) {}
    record CourseBundle(String bundleId, List<String> courseTitles, double totalPrice) {}
    record InstructorPortfolio(String instructorId, List<String> courseIds,
                              List<String> specializations) {}

    /**
     * TASK 4.1: Score Statistics
     *
     * Calculate statistics from student scores:
     * - Empty score list -> "NO_SCORES"
     * - All scores >= 80 -> "EXCELLENT_RECORD"
     * - Average >= 70 -> "GOOD_PERFORMANCE"
     * - Average >= 50 -> "PASSING"
     * - Average < 50 -> "NEEDS_IMPROVEMENT"
     * - Not StudentWithScores -> "INVALID"
     *
     * EXAMPLE:
     * analyzeScores(new StudentWithScores("S1", "Alice", List.of(85.0, 90.0, 88.0)))
     *   -> "EXCELLENT_RECORD"
     *
     * @param obj Student with scores
     * @return Performance category
     */
    public String analyzeScores(Object obj) {
        return switch(obj) {
            case StudentWithScores s when s.scores() == null || s.scores().isEmpty() ->
                "NO_SCORES";
            case StudentWithScores s when s.scores().stream().allMatch(score -> score >= 80) ->
                "EXCELLENT_RECORD";
            case StudentWithScores s -> {
                double avg = s.scores().stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
                if (avg >= 70) {
                    yield "GOOD_PERFORMANCE";
                } else if (avg >= 50) {
                    yield "PASSING";
                } else {
                    yield "NEEDS_IMPROVEMENT";
                }
            }
            default -> "INVALID";
        };
    }

    /**
     * TASK 4.2: Bundle Value Assessment
     *
     * Assess course bundle value:
     * - 5+ courses, price < 100 -> "BARGAIN"
     * - 5+ courses -> "COMPREHENSIVE_BUNDLE"
     * - 3-4 courses, price < 50 -> "VALUE_PACK"
     * - 3-4 courses -> "STANDARD_BUNDLE"
     * - 1-2 courses -> "MINI_BUNDLE"
     * - Empty -> "EMPTY_BUNDLE"
     * - Not CourseBundle -> "INVALID"
     *
     * EXAMPLE:
     * assessBundle(new CourseBundle("B1", List.of("C1", "C2", "C3", "C4", "C5"), 85.0))
     *   -> "BARGAIN"
     *
     * @param obj Bundle object
     * @return Value assessment
     */
    public String assessBundle(Object obj) {
        return switch(obj) {
            case CourseBundle(_, List<String> courseTitles, double totalPrice)
                when courseTitles.size() >= 5 && totalPrice < 100 -> "BARGAIN";
            case CourseBundle(_, List<String> courseTitles, _)
                when courseTitles.size() >= 5 -> "COMPREHENSIVE_BUNDLE";
            case CourseBundle(_, List<String> courseTitles, double totalPrice)
                when courseTitles.size() >= 3 && courseTitles.size() <= 4 && totalPrice < 50 -> "VALUE_PACK";
            case CourseBundle(_, List<String> courseTitles, _)
                when courseTitles.size() >= 3 && courseTitles.size() <= 4 -> "STANDARD_BUNDLE";
            case CourseBundle(_, List<String> courseTitles, _)
                when courseTitles.size() >= 1 && courseTitles.size() <= 2 -> "MINI_BUNDLE";
            case CourseBundle(_, List<String> courseTitles, _)
                when courseTitles.isEmpty() -> "EMPTY_BUNDLE";
            default -> "INVALID";
        };
    }

    /**
     * TASK 4.3: Instructor Expertise Level
     *
     * Determine instructor expertise based on portfolio:
     * - 10+ courses, 3+ specializations -> "MASTER_INSTRUCTOR"
     * - 5+ courses, 2+ specializations -> "EXPERT_INSTRUCTOR"
     * - 3+ courses, 1+ specializations -> "EXPERIENCED_INSTRUCTOR"
     * - 1-2 courses -> "JUNIOR_INSTRUCTOR"
     * - No courses -> "NEW_INSTRUCTOR"
     * - Not InstructorPortfolio -> "INVALID"
     *
     * EXAMPLE:
     * determineExpertise(new InstructorPortfolio("I1",
     *   List.of("C1", "C2", ..., "C12"),
     *   List.of("Java", "Python", "JavaScript", "Cloud")))
     *   -> "MASTER_INSTRUCTOR"
     *
     * @param obj Portfolio object
     * @return Expertise level
     */
    public String determineExpertise(Object obj) {
        return switch(obj) {
            case InstructorPortfolio(_, List<String> courseIds, List<String> specializations)
                when courseIds.size() >= 10 && specializations.size() >= 3 -> "MASTER_INSTRUCTOR";
            case InstructorPortfolio(_, List<String> courseIds, List<String> specializations)
                when courseIds.size() >= 5 && specializations.size() >= 2 -> "EXPERT_INSTRUCTOR";
            case InstructorPortfolio(_, List<String> courseIds, List<String> specializations)
                when courseIds.size() >= 3 && specializations.size() >= 1 -> "EXPERIENCED_INSTRUCTOR";
            case InstructorPortfolio(_, List<String> courseIds, _)
                when courseIds.size() >= 1 && courseIds.size() <= 2 -> "JUNIOR_INSTRUCTOR";
            case InstructorPortfolio(_, List<String> courseIds, _)
                when courseIds.isEmpty() -> "NEW_INSTRUCTOR";
            default -> "INVALID";
        };
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 5: Advanced Pattern Combinations
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 5.1: Multi-Shape Perimeter Calculator
     *
     * Calculate perimeter for different shapes:
     * - Circle -> 2 * π * r
     * - Rectangle -> 2 * (width + height)
     * - Line -> 2 * distance (straight distance between points)
     * - Point -> 0
     * - Other -> -1
     *
     * EXAMPLE:
     * calculatePerimeter(new Circle(new Point(0,0), 5)) -> ~31.42
     * calculatePerimeter(new Rectangle(new Point(0,0), new Point(3,4))) -> 14.0
     *
     * @param shape Shape object
     * @return Perimeter value
     */
    public double calculatePerimeter(Object shape) {
        return switch(shape) {
            case Circle(Point _, int radius) -> 2 * Math.PI * radius;
            case Rectangle(Point topLeft, Point bottomRight) -> {
                int width = bottomRight.x - topLeft.x;
                int height = bottomRight.y - topLeft.y;
                yield 2 * (width + height);
            }
            case Line(Point start, Point end) -> {
                int dx = end.x - start.x;
                int dy = end.y - start.y;
                double distance = Math.sqrt(dx * dx + dy * dy);
                yield 2 * distance;
            }
            case Point _ -> 0.0;
            default -> -1.0;
        };
    }

    /**
     * TASK 5.2: Shape Containment Check
     *
     * Check if a point is contained within a shape:
     * - Circle: distance from center <= radius
     * - Rectangle: point within bounds
     * - Line: always false (lines don't contain area)
     * - Point: exact match
     * - Other -> false
     *
     * EXAMPLE:
     * containsPoint(new Circle(new Point(0,0), 5), new Point(3,0)) -> true
     * containsPoint(new Circle(new Point(0,0), 5), new Point(10,0)) -> false
     *
     * @param shape Shape to check
     * @param point Point to test
     * @return True if point is contained
     */
    public boolean containsPoint(Object shape, Point point) {
        return switch(shape) {
            case Circle(Point center, int radius) -> {
                int dx = point.x - center.x;
                int dy = point.y - center.y;
                double distance = Math.sqrt(dx * dx + dy * dy);
                yield distance <= radius;
            }
            case Rectangle(Point topLeft, Point bottomRight) ->
                point.x >= topLeft.x && point.x <= bottomRight.x &&
                point.y >= topLeft.y && point.y <= bottomRight.y;
            case Line _ -> false;
            case Point p -> p.x == point.x && p.y == point.y;
            default -> false;
        };
    }

    /**
     * TASK 5.3: Learning Path Validator
     *
     * Validate if student is on track with their learning path.
     * Create a record combining multiple entities and validate:
     *
     * record LearningPath(StudentProfile student, List<CourseWithInstructor> courses,
     *                     List<Integer> progressList) {}
     *
     * - ADVANCED student, 5+ courses, average progress > 75 -> "EXCELLENT_PROGRESS"
     * - INTERMEDIATE student, 3+ courses, average progress > 60 -> "ON_TRACK"
     * - BEGINNER student, 1+ courses, average progress > 50 -> "GOOD_START"
     * - Any level, average progress < 25 -> "NEEDS_MOTIVATION"
     * - Other -> "REVIEW_NEEDED"
     *
     * @param obj Learning path object
     * @return Validation result
     */
    record LearningPath(StudentProfile student, List<CourseWithInstructor> courses,
                       List<Integer> progressList) {}

    public String validateLearningPath(Object obj) {
        return switch(obj) {
            case LearningPath(StudentProfile(_, _, _, StudentLevel level), List<CourseWithInstructor> courses, List<Integer> progressList)
                when level == StudentLevel.ADVANCED && courses.size() >= 5 -> {
                double avgProgress = progressList.stream().mapToInt(Integer::intValue).average().orElse(0);
                yield avgProgress > 75 ? "EXCELLENT_PROGRESS" : "REVIEW_NEEDED";
            }
            case LearningPath(StudentProfile(_, _, _, StudentLevel level), List<CourseWithInstructor> courses, List<Integer> progressList)
                when level == StudentLevel.INTERMEDIATE && courses.size() >= 3 -> {
                double avgProgress = progressList.stream().mapToInt(Integer::intValue).average().orElse(0);
                yield avgProgress > 60 ? "ON_TRACK" : "REVIEW_NEEDED";
            }
            case LearningPath(StudentProfile(_, _, _, StudentLevel level), List<CourseWithInstructor> courses, List<Integer> progressList)
                when level == StudentLevel.BEGINNER && courses.size() >= 1 -> {
                double avgProgress = progressList.stream().mapToInt(Integer::intValue).average().orElse(0);
                yield avgProgress > 50 ? "GOOD_START" : "REVIEW_NEEDED";
            }
            case LearningPath(_, _, List<Integer> progressList) -> {
                double avgProgress = progressList.stream().mapToInt(Integer::intValue).average().orElse(0);
                yield avgProgress < 25 ? "NEEDS_MOTIVATION" : "REVIEW_NEEDED";
            }
            default -> "REVIEW_NEEDED";
        };
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // MAIN METHOD - Test Your Solutions
    // ═══════════════════════════════════════════════════════════════════════════

    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("  Record Pattern Challenge - Destructuring and Deep Matching");
        System.out.println("═══════════════════════════════════════════════════════════════\n");

        RecordPatternSwitchChallenge challenge = new RecordPatternSwitchChallenge();

        // Test PART 1: Basic record patterns with geometric shapes
        System.out.println("PART 1: Basic Record Patterns - Geometric Shapes");
        System.out.println("─────────────────────────────────────────────────────────");

        Circle originCircle = new Circle(new Point(0, 0), 5);
        Circle otherCircle = new Circle(new Point(3, 4), 7);
        Line xAxisLine = new Line(new Point(0, 0), new Point(5, 0));
        Line normalLine = new Line(new Point(0, 1), new Point(5, 3));
        Rectangle square = new Rectangle(new Point(0, 0), new Point(5, 5));
        Rectangle rect = new Rectangle(new Point(0, 0), new Point(6, 4));

        System.out.println(challenge.analyzeShape(originCircle));
        System.out.println(challenge.analyzeShape(otherCircle));
        System.out.println(challenge.analyzeShape(xAxisLine));

        System.out.println("\nX-axis line checks:");
        System.out.println("Is X-axis line (should be true): " +
            challenge.isXAxisLine(xAxisLine));
        System.out.println("Is X-axis line (should be false): " +
            challenge.isXAxisLine(normalLine));

        System.out.println("\nSquare checks:");
        System.out.println("Is square (should be true): " +
            challenge.isSquare(square));
        System.out.println("Is square (should be false): " +
            challenge.isSquare(rect));

        // Test PART 2: Domain-specific records
        System.out.println("\nPART 2: Domain-Specific Records for EduMaster");
        System.out.println("─────────────────────────────────────────────────────────");

        EnrollmentEvent event1 = new EnrollmentEvent(
            "S001", "C001", "Java Masterclass",
            LocalDateTime.now(), 85);
        EnrollmentEvent event2 = new EnrollmentEvent(
            "S002", "C002", "Python Basics",
            LocalDateTime.now(), 15);

        System.out.println(challenge.classifyEvent(event1));
        System.out.println(challenge.classifyEvent(event2));

        // Test PART 3: Nested record patterns
        System.out.println("\nPART 3: Nested Record Patterns - Complex Data");
        System.out.println("─────────────────────────────────────────────────────────");

        Address address = new Address("Boston", "USA");
        ContactInfo contact = new ContactInfo("alice@email.com", address);
        StudentProfile profile = new StudentProfile(
            "S001", "Alice Smith", contact, StudentLevel.ADVANCED);

        System.out.println(challenge.extractLocation(profile));

        StudentProfile noLocationProfile = new StudentProfile(
            "S002", "Bob Jones",
            new ContactInfo("bob@email.com", null),
            StudentLevel.BEGINNER);
        System.out.println(challenge.extractLocation(noLocationProfile));

        // Test PART 4: Collections in records
        System.out.println("\nPART 4: Pattern Matching with Collections");
        System.out.println("─────────────────────────────────────────────────────────");

        StudentWithScores excellentStudent = new StudentWithScores(
            "S001", "Alice", List.of(85.0, 90.0, 88.0, 92.0));
        StudentWithScores averageStudent = new StudentWithScores(
            "S002", "Bob", List.of(65.0, 70.0, 68.0));
        StudentWithScores strugglingStudent = new StudentWithScores(
            "S003", "Charlie", List.of(45.0, 48.0, 42.0));

        System.out.println("Excellent student: " +
            challenge.analyzeScores(excellentStudent));
        System.out.println("Average student: " +
            challenge.analyzeScores(averageStudent));
        System.out.println("Struggling student: " +
            challenge.analyzeScores(strugglingStudent));

        System.out.println("\n═══════════════════════════════════════════════════════════════");
    }
}
