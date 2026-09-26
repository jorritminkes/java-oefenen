package nl.jorritminkes;

public abstract class Person {
    private String id;
    private String name;
    private String email;
    private String phone;
    
    // Constructor
    public Person(String id, String name, String email, String phone) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
    
    // Abstract method - must be implemented by subclasses
    public abstract String getRole();
    
    // Getters and Setters
    public String getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    // Object methods
    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s, Email: %s, Phone: %s, Role: %s",
                id, name, email, phone, getRole());
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return id.equals(person.id);
    }
    
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
