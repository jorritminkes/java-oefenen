package nl.jorritminkes;

import java.util.ArrayList;
import java.util.List;

public class Instructor extends Person {
    private String employeeId;
    private String department;
    private String specialization;
    private List<Course> coursesTaught;
    private double salary;
    
    public Instructor(String id, String name, String email, String phone,
                     String employeeId, String department, String specialization, double salary) {
        super(id, name, email, phone);
        this.employeeId = employeeId;
        this.department = department;
        this.specialization = specialization;
        this.coursesTaught = new ArrayList<>();
        this.salary = salary;
    }
    
    @Override
    public String getRole() {
        return "Instructor";
    }
    
    // TODO: Implement assignCourse, removeCourse, getCoursesTeaching, raiseSalary
    
    public void assignCourse(Course course) {
        // Add course to coursesTaught
    }
    
    public void removeCourse(String courseCode) {
        // Remove course from coursesTaught
    }
    
    public List<Course> getCoursesTeaching() {
        return new ArrayList<>(coursesTaught);
    }
    
    public void raiseSalary(double percentage) {
        salary *= (1 + percentage / 100);
    }
    
    // Getters
    public String getEmployeeId() { return employeeId; }
    public String getDepartment() { return department; }
    public String getSpecialization() { return specialization; }
    public double getSalary() { return salary; }
    
    @Override
    public String toString() {
        return super.toString() + String.format("\nEmployee ID: %s, Department: %s, Salary: $%.2f",
                employeeId, department, salary);
    }
}
