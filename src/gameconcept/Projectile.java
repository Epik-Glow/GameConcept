package gameconcept;

public class Projectile {
    private double x;
    private double y;
    private int velocity;
    private double angle;
    private double slope;
    private ProjectileType type;
    
    public Projectile(int x, int y, ProjectileType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }
    
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    public double getAngle() {
        return angle;
    }
    
    public double getSlope() {
        return slope;
    }
    
    public int getVelocity() {
        return velocity;
    }
    
    public void setX(double x) {
        this.x = x;
    }
    
    public void setY(double y) {
        this.y = y;
    }
    
    public void setAngle(double angle) {
        this.angle = angle;
    }
    
    public void setSlope(double slope) {
        this.slope = slope;
    }
    
    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }
}