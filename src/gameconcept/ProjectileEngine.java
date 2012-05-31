package gameconcept;

import java.util.ArrayList;
import org.lwjgl.opengl.GL11;

public class ProjectileEngine {
    private int numProjectiles;
    private ArrayList projectiles = new ArrayList();
    private GameConcept gameconcept;
    
    public ProjectileEngine(GameConcept instance) {
        numProjectiles = 0;
        gameconcept = instance;
    }
    
    public void update(int delta) {
        int index = 0;
        Object projectileArray[] = projectiles.toArray();
        
        while(index < projectileArray.length) {
            if(projectileArray[index] instanceof Projectile) {
                Projectile projectile = (Projectile) projectileArray[index];
                System.out.println(projectile.getX() + ", " + projectile.getY());
                
                if(projectile.getX() < gameconcept.width || projectile.getX() > 0 || projectile.getY() < gameconcept.height || projectile.getY() > 0) {
                    projectile.setX(projectile.getX() + ((delta * projectile.getDX()) / 1000));
                    projectile.setY(projectile.getY() + ((delta * projectile.getDY()) / 1000));
                    projectiles.set(index, projectile);
                }
                else {
                    projectile = null;
                    remove(projectile);
                }
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
                    GL11.glVertex2f(projectile.getX() - 20, projectile.getY() - 10);
                    GL11.glVertex2f(projectile.getX() - 20, projectile.getY() + 10);
                    GL11.glVertex2f(projectile.getX() + 20, projectile.getY() + 10);
                    GL11.glVertex2f(projectile.getX() + 20, projectile.getY() - 10);
                GL11.glEnd();
            }
            
            index++;
        }
    }
    
    public void add(Projectile projectile) {
        numProjectiles++;
        projectiles.add(projectile);
    }
    
    public void remove(Projectile projectile) {
        projectiles.remove(projectile);
    }
}
