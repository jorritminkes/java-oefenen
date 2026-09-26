package nl.jorritminkes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

// ============================================================================
// Part 4: UNIVERSITY & DATA MANAGEMENT
// ============================================================================

// University.java
public class University {
    private String universityName;
    private Map<String, Student> students;
    private Map<String, Instructor> instructors;
    private Map<String, Administrator> administrators;
    private Map<String, Course> courses;
    
    public University(String universityName) {
        this.universityName = universityName;
        this.students = new HashMap<>();
        this.instructors = new HashMap<>();
        this.administrators = new HashMap<>();
        this.courses = new HashMap<>();
    }
    
    public void addStudent(Student student) throws DuplicatePersonException {
        if (students.containsKey(student.getId())) {
            throw new DuplicatePersonException("Student with ID " + student.getId() + " already exists");
        }
        students.put(student.getId(), student);
    }
    
    public void addInstructor(Instructor instructor) throws DuplicatePersonException {
        if (instructors.containsKey(instructor.getId())) {
            throw new DuplicatePersonException("Instructor with ID " + instructor.getId() + " already exists");
        }
        instructors.put(instructor.getId(), instructor);
    }
    
    public void addCourse(Course course) throws InvalidCourseException {
        if (courses.containsKey(course.getCourseCode())) {
            throw new InvalidCourseException("Course with code " + course.getCourseCode() + " already exists");
        }
        courses.put(course.getCourseCode(), course);
    }
    
    public Optional<Student> findStudent(String studentId) {
        return Optional.ofNullable(students.get(studentId));
    }
    
    public Optional<Instructor> findInstructor(String employeeId) {
        return Optional.ofNullable(instructors.get(employeeId));
    }
    
    public Optional<Course> findCourse(String courseCode) {
        return Optional.ofNullable(courses.get(courseCode));
    }
    
    // TODO: Implement getEnrollmentStatistics, getCoursesWithCapacity, generateTranscriptsForAllStudents
    
    public Map<String, Integer> getEnrollmentStatistics() {
        Map<String, Integer> stats = new HashMap<>();
        // Calculate statistics
        return stats;
    }
    
    // Getters
    public String getUniversityName() { return universityName; }
    public Collection<Student> getAllStudents() { return new ArrayList<>(students.values()); }
    public Collection<Instructor> getAllInstructors() { return new ArrayList<>(instructors.values()); }
    public Collection<Course> getAllCourses() { return new ArrayList<>(courses.values()); }
}