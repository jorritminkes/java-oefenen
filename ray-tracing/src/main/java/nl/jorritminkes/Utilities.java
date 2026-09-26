package nl.jorritminkes;

public class Utilities {
    
    public static double intersectSphere(Vector sphereCenter, double radius, Line ray) {
        Vector originToCenter = ray.getOrigin().subtract(sphereCenter);
        double a = ray.getDirection().dot(ray.getDirection());
        double b = 2.0 * originToCenter.dot(ray.getDirection());
        double c = originToCenter.dot(originToCenter) - (radius * radius);
        double discriminant = (b * b) - (4 * a * c);
        
        if (discriminant < 0.0) {
            return -1.0;
        } else {
            return (-b - Math.sqrt(discriminant)) / (2.0 * a);
        }
    }
}