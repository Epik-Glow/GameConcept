package gameconcept;

import java.util.ArrayList;
import org.lwjgl.opengl.GL11;

public class ProjectileEngine {
    private int numProjectiles;
    private ArrayList projectiles = new ArrayList();
    
    public ProjectileEngine() {
        numProjectiles = 0;
    }
    
    public void update(int delta) {
        int index = 0;
        Object projectileArray[] = projectiles.toArray();
        
        while(index < projectileArray.length) {
            if(projectileArray[index] instanceof Projectile) {
                Projectile projectile = (Projectile) projectileArray[index];
                projectile.setX((delta * projectile.getDX()) / 1000);
                projectile.setY((delta * projectile.getDY()) / 1000);
                projectiles.set(index, projectile);
            }
            
            index++;
        }
    }
    
    public void render() {
        int index = 0;
        Object projectileArray[] = projectiles.toArray();
        
        while(index < projectileArray.length) {
            if(projectileArray[index] instanceof Projectile) {
                Projectile projectile = (Projectile) projectileArray[index];
                
                GL11.glColor3f(1.0f, 1.0f, 0.0f);
                GL11.glBegin(GL11.GL_QUADS);
                    GL11.glVertex2f(projectile.getX() - 5, projectile.getY() - 10);
                    GL11.glVertex2f(projectile.getX() - 5, projectile.getY() + 10);
                    GL11.glVertex2f(projectile.getX() + 5, projectile.getY() + 10);
                    GL11.glVertex2f(projectile.getX() + 5, projectile.getY() - 10);
                GL11.glEnd();
            }
            
            index++;
        }
    }
    
    public void add(Projectile projectile) {
        numProjectiles++;
        projectiles.add(projectile);
    }
    
    public void remove() {
        
    }
}
