package gameconcept;

public class Player extends Entity {
    protected int screenx = worldx;
    protected int screeny = worldy;
    private static ProjectileEngine engine;
    
    public Player(int x, int y, ProjectileEngine engine) {
        super(x, y);
        this.engine = engine;
    }
    
    public void update(int delta) {
        worldx += (delta * dx) / 1000;
        worldy += (delta * dy) / 1000;
    }
    
    public int getScreenX() {
        return screenx;
    }
    
    public int getScreenY() {
        return screeny;
    }
    
    public void setScreenX(int x) {
        screenx = x;
    }
    
    public void setScreenY(int y) {
        screeny = y;
    }
    
    public void shoot() {
        Projectile projectile = new Projectile(worldx, worldy, ProjectileType.BULLET);
        projectile.setDX(200);
        projectile.setDY(0);
        
        engine.add(projectile);
    }
}