package nl.jorritminkes;

import java.util.HashSet;
import java.util.Set;

public class PassFailCourse extends Course {
    private Set<String> passingStudents;
    
    public PassFailCourse(String courseCode, String courseName, int credits, int maxCapacity, String syllabus) {
        super(courseCode, courseName, credits, maxCapacity, syllabus);
        this.passingStudents = new HashSet<>();
    }
    
    @Override
    public double calculateGradePoints(double rawScore) {
        // Pass/Fail - returns 0 regardless (no impact on GPA)
        return 0.0;
    }
    
    @Override
    public boolean isGraded() {
        return false;
    }
    
    public void markStudentPass(String studentId) {
        passingStudents.add(studentId);
    }
    
    public void markStudentFail(String studentId) {
        passingStudents.remove(studentId);
    }
    
    public double getPassingPercentage() {
        if (getEnrolledCount() == 0) return 0.0;
        return (double) passingStudents.size() / getEnrolledCount() * 100;
    }
}
