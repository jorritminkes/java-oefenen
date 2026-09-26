package nl.jorritminkes;

import java.util.HashMap;
import java.util.Map;

import nl.jorritminkes.exception.InvalidGradeException;

public class GradedCourse extends Course implements Gradable {
    private Map<String, Double> studentGrades;
    private Map<String, Map<String, Double>> studentAssignments;
    
    public GradedCourse(String courseCode, String courseName, int credits, int maxCapacity, String syllabus) {
        super(courseCode, courseName, credits, maxCapacity, syllabus);
        this.studentGrades = new HashMap<>();
        this.studentAssignments = new HashMap<>();
    }
    
    @Override
    public double calculateGradePoints(double rawScore) {
        // A=4.0, B=3.0, C=2.0, D=1.0, F=0
        if (rawScore >= 90) return 4.0;
        if (rawScore >= 80) return 3.0;
        if (rawScore >= 70) return 2.0;
        if (rawScore >= 60) return 1.0;
        return 0.0;
    }
    
    @Override
    public boolean isGraded() {
        return true;
    }
    
    public void assignGrade(Student student, double score) throws InvalidGradeException {
        if (score < 0 || score > 100) {
            throw new InvalidGradeException("Score must be between 0 and 100");
        }
        studentGrades.put(student.getId(), score);
    }
    
    public String getLetterGrade(double score) {
        if (score >= 90) return "A";
        if (score >= 80) return "B";
        if (score >= 70) return "C";
        if (score >= 60) return "D";
        return "F";
    }
    
    public Double getStudentGrade(String studentId) {
        return studentGrades.get(studentId);
    }
    
    // Implement Gradable interface
    @Override
    public void submitAssignment(String assignmentName, String submission) {
        // Implementation for tracking submissions
    }
    
    @Override
    public double getAssignmentScore(String assignmentName) {
        // Implementation for retrieving assignment scores
        return 0.0;
    }
    
    @Override
    public Map<String, String> getAllAssignments() {
        // Implementation for getting all assignments
        return new HashMap<>();
    }
}
