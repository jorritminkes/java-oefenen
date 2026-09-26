package nl.jorritminkes;

import java.util.ArrayList;
import java.util.List;

import nl.jorritminkes.exception.EnrollmentException;

public abstract class Course {
    private String courseCode;
    private String courseName;
    private int credits;
    private int maxCapacity;
    private List<Student> enrolledStudents;
    private Instructor instructor;
    private List<Course> prerequisites;
    private String syllabus;
    
    public Course(String courseCode, String courseName, int credits, int maxCapacity, String syllabus) {
        if (courseCode == null || courseCode.isEmpty()) {
            throw new IllegalArgumentException("Course code cannot be null or empty");
        }
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.credits = credits;
        this.maxCapacity = maxCapacity;
        this.enrolledStudents = new ArrayList<>();
        this.prerequisites = new ArrayList<>();
        this.syllabus = syllabus;
    }
    
    // Abstract methods
    public abstract double calculateGradePoints(double rawScore);
    public abstract boolean isGraded();
    
    // Concrete methods
    public void addStudent(Student student) throws EnrollmentException {
        if (isFull()) {
            throw new EnrollmentException("Course is at full capacity");
        }
        if (enrolledStudents.contains(student)) {
            throw new EnrollmentException("Student already enrolled in this course");
        }
        enrolledStudents.add(student);
    }
    
    public void removeStudent(String studentId) {
        enrolledStudents.removeIf(s -> s.getId().equals(studentId));
    }
    
    public boolean isFull() {
        return enrolledStudents.size() >= maxCapacity;
    }
    
    public int getEnrolledCount() {
        return enrolledStudents.size();
    }
    
    public void assignInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
    
    public void addPrerequisite(Course course) {
        prerequisites.add(course);
    }
    
    // Getters and Setters
    public String getCourseCode() { return courseCode; }
    public String getCourseName() { return courseName; }
    public int getCredits() { return credits; }
    public int getMaxCapacity() { return maxCapacity; }
    public Instructor getInstructor() { return instructor; }
    public List<Student> getEnrolledStudents() { return new ArrayList<>(enrolledStudents); }
    public List<Course> getPrerequisites() { return new ArrayList<>(prerequisites); }
    public String getSyllabus() { return syllabus; }
    
    @Override
    public String toString() {
        return String.format("Course: %s (%s), Credits: %d, Enrollment: %d/%d, Graded: %s",
                courseName, courseCode, credits, enrolledStudents.size(), maxCapacity, isGraded());
    }
}
