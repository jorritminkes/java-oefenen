package nl.jorritminkes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// ============================================================================
// Transcript.java
// ============================================================================

public class Transcript {
    private Student student;
    private List<CourseRecord> courseRecords;
    private LocalDate generatedDate;
    
    public static class CourseRecord {
        private String courseCode;
        private String courseName;
        private int credits;
        private String grade;
        private double gradePoints;
        
        public CourseRecord(String courseCode, String courseName, int credits, String grade, double gradePoints) {
            this.courseCode = courseCode;
            this.courseName = courseName;
            this.credits = credits;
            this.grade = grade;
            this.gradePoints = gradePoints;
        }
        
        // Getters
        public String getCourseCode() { return courseCode; }
        public String getCourseName() { return courseName; }
        public int getCredits() { return credits; }
        public String getGrade() { return grade; }
        public double getGradePoints() { return gradePoints; }
    }
    
    public Transcript(Student student) {
        this.student = student;
        this.courseRecords = new ArrayList<>();
        this.generatedDate = LocalDate.now();
    }
    
    public void addCourseRecord(CourseRecord record) {
        courseRecords.add(record);
    }
    
    public double calculateGPA() {
        if (courseRecords.isEmpty()) return 0.0;
        double totalPoints = 0;
        int totalCredits = 0;
        for (CourseRecord record : courseRecords) {
            totalPoints += record.getGradePoints() * record.getCredits();
            totalCredits += record.getCredits();
        }
        return totalCredits > 0 ? totalPoints / totalCredits : 0.0;
    }
    
    public int getTotalCredits() {
        return courseRecords.stream().mapToInt(CourseRecord::getCredits).sum();
    }
    
    public String printTranscript() {
        // Format and return transcript as string
        return "";
    }
    
    // Getters
    public Student getStudent() { return student; }
    public List<CourseRecord> getCourseRecords() { return new ArrayList<>(courseRecords); }
    public LocalDate getGeneratedDate() { return generatedDate; }
}