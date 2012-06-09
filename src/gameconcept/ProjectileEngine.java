package gameconcept;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.opengl.GL11;

public class ProjectileEngine {
    private int numProjectiles;
    private ArrayList projectiles = new ArrayList();
    private GameConcept gameConcept;
    
    public ProjectileEngine(GameConcept instance) {
        numProjectiles = 0;
        gameConcept = instance;
    }
    
    public void update(int delta) {
        int index = 0;
        Object projectileArray[] = projectiles.toArray();
        
        while(index < projectileArray.length) {
            if(projectileArray[index] instanceof Projectile) {
                Projectile projectile = (Projectile) projectileArray[index];
                
                if((projectile.getX() < GameConcept.width && projectile.getX() > 0) && (projectile.getY() < GameConcept.height && projectile.getY() > 0)) {
                    projectile.setX(projectile.getX() + (delta * (projectile.getVelocity() * Math.cos(projectile.getAngle())) / 1000));
                    projectile.setY(projectile.getY() + (delta * (projectile.getVelocity() * Math.sin(projectile.getAngle())) / 1000));
                    projectiles.set(index, projectile);
                    
                    projectileArray = projectiles.toArray();
                    index++;
                }
                else {
                    projectile = null;
                    remove(index);
                    projectiles.trimToSize();
                    projectileArray = projectiles.toArray();
                }
            }
        }
    }
    
    public void render() {
        int index = 0;
        Object projectileArray[] = projectiles.toArray();
        
        while(index < projectileArray.length) {
            if(projectileArray[index] instanceof Projectile) {
                Projectile projectile = (Projectile) projectileArray[index];
                try {
                    gameConcept.loadTexture("bullet");
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ProjectileEngine.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ProjectileEngine.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                GL11.glBindTexture(GL11.GL_TEXTURE_2D, gameConcept.texture.getTextureID());
                
                GL11.glBegin(GL11.GL_QUADS);
                    GL11.glTexCoord2f(0, 1);
                    GL11.glVertex2f((float) projectile.getX() - 6, (float) projectile.getY() - 4);
                    GL11.glTexCoord2f(0, 0);
                    GL11.glVertex2f((float) projectile.getX() - 6, (float) projectile.getY() + 4);
                    GL11.glTexCoord2f(1, 0);
                    GL11.glVertex2f((float) projectile.getX() + 6, (float) projectile.getY() + 4);
                    GL11.glTexCoord2f(1, 1);
                    GL11.glVertex2f((float) projectile.getX() + 6, (float) projectile.getY() - 4);
                GL11.glEnd();
            }
            
            index++;
        }
    }
    
    public void add(Projectile projectile) {
        numProjectiles++;
        projectiles.add(projectile);
    }
    
    public void remove(int index) {
        projectiles.remove(index);
    }
    
    public void destroy() {
        projectiles = null;
    }
}