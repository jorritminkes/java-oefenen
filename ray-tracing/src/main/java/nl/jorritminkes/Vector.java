package nl.jorritminkes;

public class Vector {
    private double x;
    private double y;
    private double z;
    
    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public Vector add(Vector vector) {
        double result_x = x + vector.x;
        double result_y = y + vector.y;
        double result_z = z + vector.z;
        
        Vector resultVector = new Vector(result_x, result_y, result_z);
        return resultVector;
    }
    
    public Vector subtract(Vector vector) {
        double result_x = x - vector.x;
        double result_y = y - vector.y;
        double result_z = z - vector.z;
        
        Vector resultVector = new Vector(result_x, result_y, result_z);
        return resultVector;
    }
    
    public Vector multiply(double multiplier) {
        double result_x = multiplier * x;
        double result_y = multiplier * y;
        double result_z = multiplier * z;
        
        Vector resultVector = new Vector(result_x, result_y, result_z);
        return resultVector;
    }
    
    public double length() {
        double length = Math.sqrt(x*x + y*y + z*z);
        return length;
    }
    
    public Vector normalize() {
        double length = this.length();
        double result_x = x / length;
        double result_y = y / length;
        double result_z = z / length;
        
        Vector normalizedVector = new Vector(result_x, result_y, result_z);
        return normalizedVector;
    }
    
    public double dot(Vector vector) {
        double other_x = vector.x;
        double other_y = vector.y;
        double other_z = vector.z;
        
        double dot = other_x * this.x + other_y * this.y + other_z * this.z;
        return dot;
    }
    
    public double getX() { return this.x; }
    
    public double getY() { return this.y; }
    
    public double getZ() { return this.z; }
}