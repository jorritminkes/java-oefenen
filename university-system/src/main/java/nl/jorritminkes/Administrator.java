package nl.jorritminkes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Administrator extends Person {
    private String position;
    private String department;
    private LocalDate startDate;
    
    public Administrator(String id, String name, String email, String phone,
                        String position, String department, LocalDate startDate) {
        super(id, name, email, phone);
        this.position = position;
        this.department = department;
        this.startDate = startDate;
    }
    
    @Override
    public String getRole() {
        return "Administrator";
    }
    
    public boolean approveStudentEnrollment(Student student) {
        // Implement approval logic
        return true;
    }
    
    public long getYearsOfService() {
        return ChronoUnit.YEARS.between(startDate, LocalDate.now());
    }
    
    // Getters
    public String getPosition() { return position; }
    public String getDepartment() { return department; }
    public LocalDate getStartDate() { return startDate; }
}
