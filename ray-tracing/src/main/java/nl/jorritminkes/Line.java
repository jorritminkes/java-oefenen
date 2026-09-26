package nl.jorritminkes;

public class Line {
    private Vector origin;
    private Vector direction;
    
    public Line(Vector origin, Vector direction) {
        this.origin = origin;
        this.direction = direction;
    }
    
    public Vector getOrigin() {
        return this.origin;
    }
    
    public Vector getDirection() {
        return this.direction;
    }
    
    public Vector getPointAt(double t) {
        return origin.add(direction.multiply(t));
    }
}