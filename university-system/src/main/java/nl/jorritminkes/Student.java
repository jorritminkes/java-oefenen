package nl.jorritminkes;

import java.util.*;

import nl.jorritminkes.exception.EnrollmentException;

public class Student extends Person {
    private String studentId;
    private List<Course> enrolledCourses;
    private double gpa;
    
    public Student(String id, String name, String email, String phone, String studentId) {
        super(id, name, email, phone);
        this.studentId = studentId;
        this.enrolledCourses = new ArrayList<>();
        this.gpa = 0.0;
    }
    
    @Override
    public String getRole() {
        return "Student";
    }
    
    // TODO: Implement enrollCourse, dropCourse, getTranscript, calculateGPA, hasPrerequisites
    
    public void enrollCourse(Course course) throws EnrollmentException {
        // Check if already enrolled
        // Check prerequisites
        // Check capacity
        // Add to list
    }
    
    public void dropCourse(String courseCode) {
        // Find and remove course
    }
    
    public Map<String, String> getTranscript() {
        // Return map of course codes to grades
        return new HashMap<>();
    }
    
    public boolean hasPrerequisites(Course course) {
        // Check if student has completed all prerequisites
        return true;
    }
    
    public void calculateGPA() {
        // Recalculate GPA from all graded courses
    }
    
    // Getters
    public String getStudentId() { return studentId; }
    public List<Course> getEnrolledCourses() { return new ArrayList<>(enrolledCourses); }
    public double getGPA() { return gpa; }
}