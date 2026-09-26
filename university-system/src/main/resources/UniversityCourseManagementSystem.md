# University Course Management System - Java OOP Exercise

## Overview
Build a comprehensive university course management system that covers inheritance, polymorphism, encapsulation, abstract classes, interfaces, collections, exception handling, and design patterns.

**Time Estimate:** 4-6 hours for complete implementation  
**Difficulty:** Intermediate  
**Core Concepts Covered:** All major Java OOP principles

---

## Project Requirements

### Part 1: Core Classes & Inheritance (Inheritance & Encapsulation)

#### 1.1 Create a `Person` abstract base class
- **Fields:** `id` (String, unique), `name` (String), `email` (String), `phone` (String)
- **Methods:**
  - Abstract method `getRole()` returning a String
  - Constructor with parameters
  - Getters and setters for all fields
  - `toString()` that displays person info
  - `equals()` based on `id`
  - `hashCode()` based on `id`

#### 1.2 Create `Student` class extending `Person`
- **Additional Fields:**
  - `studentId` (String, unique within students)
  - `enrolledCourses` (List<Course>)
  - `gpa` (double, 0.0 to 4.0)
- **Methods:**
  - `getRole()` returns "Student"
  - `enrollCourse(Course course)` - adds course if not already enrolled and capacity allows
  - `dropCourse(String courseCode)` - removes course
  - `getTranscript()` - returns Map of courses with grades
  - `calculateGPA()` - calculates from enrolled courses
  - `hasPrerequisites(Course course)` - checks if student has completed prerequisites

#### 1.3 Create `Instructor` class extending `Person`
- **Additional Fields:**
  - `employeeId` (String)
  - `department` (String)
  - `specialization` (String)
  - `coursesTaught` (List<Course>)
  - `salary` (double)
- **Methods:**
  - `getRole()` returns "Instructor"
  - `assignCourse(Course course)` - adds course to teach
  - `removeCourse(String courseCode)` - removes course
  - `getCoursesTeaching()` - returns all current courses
  - `raiseSalary(double percentage)` - increases salary
  - Override `toString()` with instructor-specific info

#### 1.4 Create `Administrator` class extending `Person`
- **Additional Fields:**
  - `position` (String: "Registrar", "Dean", "System Admin", etc.)
  - `department` (String)
  - `startDate` (LocalDate)
- **Methods:**
  - `getRole()` returns "Administrator"
  - `approveStudentEnrollment(Student student)` - returns boolean
  - `getYearsOfService()` - calculates from startDate

---

### Part 2: Abstract Classes & Interfaces (Polymorphism)

#### 2.1 Create abstract `Course` class
- **Fields:**
  - `courseCode` (String, unique, like "CS101")
  - `courseName` (String)
  - `credits` (int)
  - `maxCapacity` (int)
  - `enrolledStudents` (List<Student>)
  - `instructor` (Instructor, can be null)
  - `prerequisites` (List<Course>)
  - `syllabus` (String, course description)
- **Abstract Methods:**
  - `calculateGradePoints(double rawScore)` - converts raw score to grade points
  - `isGraded()` - returns boolean
- **Concrete Methods:**
  - Constructor
  - Getters/setters
  - `addStudent(Student student)` - adds if capacity allows
  - `removeStudent(String studentId)` - removes student
  - `isFull()` - checks capacity
  - `getEnrolledCount()` - returns number of enrolled students
  - `assignInstructor(Instructor instructor)`
  - `addPrerequisite(Course course)`

#### 2.2 Create `GradedCourse` class extending `Course`
- Courses with letter grades (A, B, C, D, F)
- **Additional Fields:**
  - `studentGrades` (Map<String, Double>) - maps studentId to raw score
- **Concrete Methods:**
  - `calculateGradePoints(double rawScore)` - A=4.0, B=3.0, C=2.0, D=1.0, F=0
  - `isGraded()` returns true
  - `assignGrade(Student student, double score)` - validates 0-100
  - `getLetterGrade(double score)` - converts to letter
  - `getStudentGrade(String studentId)` - returns grade info

#### 2.3 Create `PassFailCourse` class extending `Course`
- Pass/Fail courses (no letter grades)
- **Concrete Methods:**
  - `calculateGradePoints(double rawScore)` - returns 0 (Pass) or 0 (Fail)
  - `isGraded()` returns false
  - `markStudentPass(String studentId)`
  - `markStudentFail(String studentId)`
  - `getPassingPercentage()` - returns % of students who passed

#### 2.4 Create `Gradable` interface
- **Methods:**
  - `submitAssignment(String assignmentName, String submission)` - String submission
  - `getAssignmentScore(String assignmentName)` - returns double
  - `getAllAssignments()` - returns Map of assignments and scores

#### 2.5 Create `GradedCourse` also implements `Gradable`
- Track individual assignments within graded courses
- `Map<String, Double>` for assignment scores per student
- Implement all methods from `Gradable` interface

---

### Part 3: Collections & Data Management

#### 3.1 Create `University` class (Singleton pattern optional)
- **Fields:**
  - `universityName` (String)
  - `students` (Map<String, Student>)
  - `instructors` (Map<String, Instructor>)
  - `administrators` (Map<String, Administrator>)
  - `courses` (Map<String, Course>)
- **Methods:**
  - `addStudent(Student student)` - throws exception if duplicate
  - `addInstructor(Instructor instructor)`
  - `addAdministrator(Administrator admin)`
  - `addCourse(Course course)`
  - `findStudent(String studentId)` - returns Optional<Student>
  - `findInstructor(String employeeId)` - returns Optional<Instructor>
  - `findCourse(String courseCode)` - returns Optional<Course>
  - `getEnrollmentStatistics()` - returns enrollment stats
  - `getCoursesWithCapacity()` - returns courses not full
  - `generateTranscriptsForAllStudents()` - returns Map<String, Transcript>

#### 3.2 Create `Transcript` class
- **Fields:**
  - `student` (Student)
  - `courses` (List<CourseRecord>)
  - `generatedDate` (LocalDate)
- **Inner class:** `CourseRecord`
  - `courseCode`, `courseName`, `credits`, `grade`, `gradePoints`
- **Methods:**
  - `addCourseRecord(CourseRecord record)`
  - `calculateGPA()` - weighted by credits
  - `getTotalCredits()`
  - `getCoursesCompleted()`
  - `printTranscript()` - formatted String

---

### Part 4: Exception Handling

#### 4.1 Create Custom Exceptions
- `InvalidStudentException` - for student validation errors
- `InvalidCourseException` - for course validation errors
- `EnrollmentException` - for enrollment issues (full course, prerequisite not met)
- `InvalidGradeException` - for invalid grades
- `DuplicatePersonException` - for duplicate IDs
- `NoSuchPersonException` - for missing persons

#### 4.2 Implement Exception Handling
- All methods should throw appropriate exceptions
- University class methods validate inputs
- Try-catch blocks in application code
- Custom exception messages with context

---

### Part 5: Advanced Features (Composition & Design Patterns)

#### 5.1 Create `GradeBook` class (Composition)
- **Purpose:** Manages all grades for a course
- **Fields:**
  - `course` (Course)
  - `gradesMap` (Map<String, StudentGradeRecord>)
- **Inner class:** `StudentGradeRecord`
  - `studentId`, `assignments` (Map<String, Double>), `finalScore`, `letterGrade`
- **Methods:**
  - `recordAssignmentScore(String studentId, String assignment, double score)`
  - `recordFinalScore(String studentId, double score)`
  - `getStudentGrades(String studentId)` - returns StudentGradeRecord
  - `getAverageScore()` - course average
  - `getStudentsAboveScore(double score)`
  - `generateGradeReport()` - returns formatted report

#### 5.2 Create `CourseSchedule` class (Composition)
- **Fields:**
  - `course` (Course)
  - `meetingTimes` (List<MeetingTime>)
  - `room` (String)
  - `capacity` (int)
- **Inner class:** `MeetingTime`
  - `dayOfWeek`, `startTime`, `endTime`
- **Methods:**
  - `addMeetingTime(MeetingTime time)`
  - `getMeetingTimes()`
  - `isTimeConflict(MeetingTime time)` - checks against existing times
  - `getScheduleSummary()` - formatted String

#### 5.3 Implement Observer Pattern (Optional but Recommended)
- Create `EnrollmentObserver` interface with `onStudentEnrolled()`, `onStudentDropped()`
- `Course` maintains list of observers
- Notify observers when students enroll/drop
- Create observer implementations that log events or update statistics

---

### Part 6: Application & Testing

#### 6.1 Create `UniversityManagementApp` class
This is your main application class with a menu system:
- Display main menu
- Student registration menu
- Course management menu
- Grade management menu
- Reporting menu
- Administrative functions menu

**Functionalities:**
1. **Student Management:**
   - Register new student
   - Display all students
   - Search student
   - Enroll student in course
   - Drop student from course
   - View student transcript

2. **Instructor Management:**
   - Hire new instructor
   - Assign course to instructor
   - View instructor courses

3. **Course Management:**
   - Create new course (GradedCourse or PassFailCourse)
   - Display all courses
   - View course details (students, instructor, schedule)
   - Set course schedule

4. **Grade Management:**
   - Assign grade to student
   - View gradebook for course
   - Generate grade report

5. **Reporting:**
   - List all students with GPAs
   - Generate transcripts
   - Course enrollment statistics
   - Department statistics
   - Overall university statistics

#### 6.2 Create comprehensive test cases
- Test enrollment with prerequisites
- Test course capacity limits
- Test GPA calculations
- Test transcript generation
- Test exception throwing for invalid inputs
- Test observer notifications

---

## Implementation Roadmap

### Phase 1: Foundation (1-1.5 hours)
- Create all Person classes (Person, Student, Instructor, Administrator)
- Implement all getters/setters and basic methods
- Test Person classes

### Phase 2: Courses & Polymorphism (1-1.5 hours)
- Create abstract Course class
- Create GradedCourse and PassFailCourse implementations
- Create Gradable interface and implement in GradedCourse
- Test course functionality

### Phase 3: Collections & University (1 hour)
- Create University class with all data structures
- Create Transcript and GradeBook classes
- Implement finder methods
- Test university operations

### Phase 4: Exceptions & Validation (45 minutes)
- Create all custom exception classes
- Add validation to all methods
- Implement exception handling

### Phase 5: Advanced Features (45 minutes)
- Create CourseSchedule class
- Implement Observer pattern
- Refine all classes

### Phase 6: Application & Testing (1+ hour)
- Build UniversityManagementApp with menu system
- Write comprehensive test cases
- Manual testing and bug fixes

---

## Bonus Challenges (if you want more)

1. **Serialization:** Make all classes Serializable, save/load university data from file
2. **Persistence:** Integrate with simple file database or JSON storage
3. **Search & Filtering:** Advanced search by GPA range, department, course code, etc.
4. **Statistics Engine:** Calculate complex statistics (avg GPA by department, course difficulty, etc.)
5. **Event System:** Implement full event system with CourseStartedEvent, GradePostedEvent, etc.
6. **Validation Framework:** Create custom validation annotations and processor
7. **Factory Pattern:** Create CourseFactory to handle GradedCourse vs PassFailCourse creation
8. **Strategy Pattern:** Different grading strategies (curved, raw, weighted)
9. **Comparators:** Multiple ways to sort students (by name, GPA, enrollment date)
10. **File I/O:** Export transcripts as PDF or CSV

---

## Key Concepts to Practice

✓ **Encapsulation:** Private fields, public methods, controlled access  
✓ **Inheritance:** Multi-level hierarchy, method overriding  
✓ **Polymorphism:** Abstract classes, interfaces, runtime type checking  
✓ **Collections:** List, Map, Set, streams, sorting  
✓ **Exception Handling:** Custom exceptions, try-catch-finally  
✓ **Composition:** Has-a relationships, inner classes  
✓ **Design Patterns:** Singleton, Observer, Factory (optional)  
✓ **String Handling:** Formatting, parsing, manipulation  
✓ **Object Methods:** equals(), hashCode(), toString(), compareTo()  
✓ **Generics:** Type-safe collections  
✓ **Java 8+ Features:** Optional, Streams, Lambda (if you want to add them)

---

## Tips for Success

1. **Start Simple:** Complete Phase 1 before moving to Phase 2
2. **Use IDE Features:** Use IDE's generate getters/setters and equals/hashCode
3. **Test Early:** Test each class as you build it
4. **Follow Naming Conventions:** Use Java naming conventions (camelCase, PascalCase)
5. **Write Javadoc:** Add documentation comments to public methods
6. **Handle Nulls:** Use Optional or null checks appropriately
7. **Validate Inputs:** Check all parameters in methods
8. **Keep It DRY:** Don't repeat code, use inheritance and composition

---

## Project Structure

```
university-management/
├── src/
│   ├── person/
│   │   ├── Person.java
│   │   ├── Student.java
│   │   ├── Instructor.java
│   │   └── Administrator.java
│   ├── course/
│   │   ├── Course.java
│   │   ├── GradedCourse.java
│   │   ├── PassFailCourse.java
│   │   ├── CourseSchedule.java
│   │   └── Gradable.java
│   ├── university/
│   │   ├── University.java
│   │   ├── Transcript.java
│   │   └── GradeBook.java
│   ├── exception/
│   │   ├── InvalidStudentException.java
│   │   ├── InvalidCourseException.java
│   │   ├── EnrollmentException.java
│   │   ├── InvalidGradeException.java
│   │   ├── DuplicatePersonException.java
│   │   └── NoSuchPersonException.java
│   ├── observer/
│   │   └── EnrollmentObserver.java
│   ├── UniversityManagementApp.java
│   └── Main.java
└── test/
    └── UniversityTest.java
```

---

## Sample Method Signatures to Guide You

```java
// Person (abstract)
public abstract String getRole();
public boolean equals(Object obj);
public int hashCode();

// Student
public void enrollCourse(Course course) throws EnrollmentException;
public void dropCourse(String courseCode);
public Map<String, String> getTranscript();
public boolean hasPrerequisites(Course course);

// Course (abstract)
public abstract double calculateGradePoints(double rawScore);
public abstract boolean isGraded();
public void addStudent(Student student) throws EnrollmentException;

// GradedCourse
public void assignGrade(Student student, double score) throws InvalidGradeException;
public String getLetterGrade(double score);

// University
public void addStudent(Student student) throws DuplicatePersonException;
public Optional<Student> findStudent(String studentId);
public Map<String, Transcript> generateTranscriptsForAllStudents();

// GradeBook
public void recordAssignmentScore(String studentId, String assignment, double score);
public String generateGradeReport();
```

---

## Evaluation Criteria

When you're done, check if you've:
- [ ] Used all OOP principles correctly
- [ ] Created proper class hierarchies
- [ ] Implemented all required classes and methods
- [ ] Handled exceptions appropriately
- [ ] Used collections effectively
- [ ] Created a functional menu-driven application
- [ ] Written clear, documented code
- [ ] Tested all major functionality
- [ ] Used proper naming and formatting
- [ ] Implemented at least one design pattern

Good luck! This is a solid intermediate project that will really solidify your OOP skills.
