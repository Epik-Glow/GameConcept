package gameconcept;

public class Entity {
    protected int worldx;
    protected int worldy;
    protected int dx;
    protected int dy;
    
    public Entity(int x, int y) {
        worldx = x;
        worldy = y;
        this.dx = 0;
        this.dy = 0;
    }
    
    public void setPosition(int x, int y) {
        worldx = x;
        worldy = y;
    }
    
    public void setWorldX(int x) {
        worldx = x;
    }
    
    public void setWorldY(int y) {
        worldy = y;
    }
    
    public void setDX(int dx) {
        this.dx = dx;
    }
    
    public void setDY(int dy) {
        this.dy = dy;
    }
    
    public int getWorldX() {
        return worldx;
    }
    
    public int getWorldY() {
        return worldy;
    }
    
    public int getDX() {
        return dx;
    }
    
    public int getDY() {
        return dy;
    }
}
