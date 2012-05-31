package gameconcept;

public class Projectile {
    private int x;
    private int y;
    private int dx;
    private int dy;
    private ProjectileType type;
    
    public Projectile(int x, int y, ProjectileType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public int getDX() {
        return dx;
    }
    
    public int getDY() {
        return dy;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public void setDX(int dx) {
        this.dx = dx;
    }
    
    public void setDY(int dy) {
        this.dy = dy;
    }
    
    
}
