# edu-master - Shared Domain Model

This module provides the **common domain model** used across all challenge modules. It represents an **online education platform** with students, courses, enrollments, and instructors.

## 📦 What's Inside

### Domain Models

#### Core Entities

**Student**
- `Long id` - Unique identifier
- `String name` - Student name
- `String email` - Contact email
- `StudentLevel level` - BEGINNER, INTERMEDIATE, ADVANCED
- `List<Enrollment> enrollments` - Courses enrolled in
- `List<Certificate> certificates` - Earned certificates

**Course**
- `Long id` - Unique identifier
- `String title` - Course title
- `String description` - Course description
- `Double price` - Course price
- `Category category` - PROGRAMMING, BUSINESS, DESIGN, MARKETING, PERSONAL_DEVELOPMENT
- `DifficultyLevel difficultyLevel` - BEGINNER, INTERMEDIATE, ADVANCED
- `List<Lesson> lessons` - Course content
- `Optional<Instructor> instructor` - Course instructor
- `List<Review> reviews` - Student reviews

**Enrollment**
- `Long id` - Unique identifier
- `Student student` - Enrolled student
- `Course course` - Enrolled course
- `LocalDate enrollmentDate` - When enrolled
- `EnrollmentStatus status` - IN_PROGRESS, COMPLETED, DROPPED
- `Double progress` - Completion percentage (0-100)

**Instructor**
- `Long id` - Unique identifier
- `String name` - Instructor name
- `String email` - Contact email
- `String bio` - Biography
- `Integer yearsOfExperience` - Teaching experience

**Lesson**
- `Long id` - Unique identifier
- `String title` - Lesson title
- `String content` - Lesson content
- `Integer durationMinutes` - Lesson duration
- `LessonType type` - VIDEO, ARTICLE, QUIZ, EXERCISE
- `Integer orderIndex` - Position in course

**Certificate**
- `Long id` - Unique identifier
- `Student student` - Certificate recipient
- `Course course` - Completed course
- `LocalDate issueDate` - When issued
- `String certificateNumber` - Unique certificate number

**Payment**
- `Long id` - Unique identifier
- `Enrollment enrollment` - Related enrollment
- `BigDecimal amount` - Payment amount
- `LocalDateTime paymentDate` - When paid
- `PaymentMethod method` - CREDIT_CARD, PAYPAL, BANK_TRANSFER
- `PaymentStatus status` - PENDING, COMPLETED, FAILED, REFUNDED

**Review**
- `Long id` - Unique identifier
- `Student student` - Reviewer
- `Course course` - Reviewed course
- `Integer rating` - 1-5 stars
- `String comment` - Review text
- `LocalDate reviewDate` - When reviewed

### Enums

**StudentLevel**
- `BEGINNER` - New to the subject
- `INTERMEDIATE` - Some experience
- `ADVANCED` - Expert level

**Category**
- `PROGRAMMING` - Software development
- `BUSINESS` - Business and management
- `DESIGN` - Creative design
- `MARKETING` - Marketing and sales
- `PERSONAL_DEVELOPMENT` - Self-improvement

**DifficultyLevel**
- `BEGINNER` - Easy to follow
- `INTERMEDIATE` - Moderate difficulty
- `ADVANCED` - Expert level content

**EnrollmentStatus**
- `IN_PROGRESS` - Currently learning
- `COMPLETED` - Finished the course
- `DROPPED` - Abandoned the course

**LessonType**
- `VIDEO` - Video content
- `ARTICLE` - Text content
- `QUIZ` - Assessment
- `EXERCISE` - Practical exercise

**PaymentMethod**
- `CREDIT_CARD` - Card payment
- `PAYPAL` - PayPal payment
- `BANK_TRANSFER` - Direct transfer

**PaymentStatus**
- `PENDING` - Awaiting payment
- `COMPLETED` - Payment successful
- `FAILED` - Payment failed
- `REFUNDED` - Payment refunded

## 🎲 Sample Data Generator

The `SampleDataGenerator` class creates realistic sample data for testing challenges:

```java
SampleDataGenerator generator = new SampleDataGenerator();

List<Student> students = generator.getStudents();
List<Course> courses = generator.getCourses();
List<Enrollment> enrollments = generator.getEnrollments();
List<Instructor> instructors = generator.getInstructors();
List<Certificate> certificates = generator.getCertificates();
List<Payment> payments = generator.getPayments();
```

### Sample Data Includes:

- **20 Students** with varying levels
- **15 Courses** across different categories
- **30+ Enrollments** in various statuses
- **5 Instructors** with experience
- **Certificates** for completed courses
- **Payments** with different methods
- **Reviews** with ratings

## 🔧 Usage in Challenges

All challenge modules depend on edu-master:

```xml
<dependency>
    <groupId>com.yasirakbal</groupId>
    <artifactId>edu-master</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

### Example Usage

```java
import data.SampleDataGenerator;
import model.*;

public class MyChallenge {
    private final List<Student> students;
    private final List<Course> courses;
    
    public MyChallenge() {
        SampleDataGenerator generator = new SampleDataGenerator();
        this.students = generator.getStudents();
        this.courses = generator.getCourses();
    }
    
    public void solve() {
        // Work with the data
        students.stream()
            .filter(s -> s.getLevel() == StudentLevel.ADVANCED)
            .forEach(s -> System.out.println(s.getName()));
    }
}
```

## 📊 Domain Relationships

```
Student (1) ←→ (N) Enrollment (N) ←→ (1) Course
   ↓                                      ↓
Certificate                          Instructor
   ↑                                      ↑
   └─────── (based on) ──────────────────┘

Enrollment (1) ←→ (N) Payment
Course (1) ←→ (N) Review ←→ (1) Student
Course (1) ←→ (N) Lesson
```

## 🎯 Design Principles

This domain model follows:

✅ **Real-world complexity** - Not oversimplified  
✅ **Rich relationships** - Entities connected meaningfully  
✅ **Varied data types** - Strings, numbers, dates, enums  
✅ **Optional relationships** - Course may have instructor  
✅ **Collections** - One-to-many relationships  
✅ **Business logic** - Realistic constraints and rules  

## 💡 Why This Domain?

An education platform is perfect for learning modern Java because:

1. **Relatable** - Everyone understands students and courses
2. **Complex enough** - Has meaningful relationships
3. **Rich operations** - Filtering, grouping, analyzing
4. **Real-world patterns** - DTOs, validations, transformations
5. **Various data types** - Practice different Java features

## 🚀 Building

```bash
cd edu-master
mvn clean install
```

This installs the shared model for other modules to use.

## 📝 Note

This module is **dependency-only** - it contains no challenges. All challenges are in the other modules that depend on this one.

---

**This domain model powers all your learning challenges! 🎓**
