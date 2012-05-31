package gameconcept;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class GameConcept {
    public static int width = 1024;
    public static int height = 576;
    private static int fps = 60;
    private int delta = 0;
    private long lastFrame;
    private GameState state = GameState.INITIALIZATION;
    private ProjectileEngine projectileEngine = new ProjectileEngine(this);
    private boolean isRunning = true;
    private float translate_x = 0.0f;
    private float translate_y = 0.0f;
    private Player player = new Player(width / 2, height / 2, projectileEngine);
    Texture texture = null;
    private boolean leftButtonDown;
    
    
    public GameConcept() {        
        while(isRunning) {
            if(state == GameState.INITIALIZATION) {
                setUpDisplay();
                setUpOpenGL();
                setUpTimer();
                leftButtonDown = Mouse.isButtonDown(0);
                
                try {
                    loadTexture("sample");
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(GameConcept.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(GameConcept.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                state = GameState.MAIN_MENU;
            }
            else if(state == GameState.MAIN_MENU) {
                pollInput();
                logic(getDelta());
                render();
                Display.update();
                Display.sync(fps);
            }
            
            if(Display.isCloseRequested()) {
                isRunning = false;
            }
        }
        Display.destroy();
    }
    
    private void setUpDisplay() {
        try {
            Display.setDisplayMode(new DisplayMode(width, height));
            Display.setTitle("GameConcept");
            Display.create();
        } catch(LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
    
    private void setUpOpenGL() {
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, width, 0, height, -1, 1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }
    
    public long getTime() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }
    
    private void setUpTimer() {
        lastFrame = getTime();
    }
    
    private void loadTexture(String texName) throws FileNotFoundException, IOException {
        texture = TextureLoader.getTexture("PNG", this.getClass().getResourceAsStream(texName + ".png"));
    }
    
    public int getDelta() {
        long currentTime = getTime();
        delta = (int) (currentTime - lastFrame);
        lastFrame = getTime();
        return delta;
    }
    
    private void logic(int delta) {
        player.update(delta);
        projectileEngine.update(delta);
    }
    
    public void render() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        
        GL11.glPushMatrix();
        
        GL11.glTranslatef(translate_x, translate_y, 0);
        GL11.glTranslatef(translate_x, translate_y, 0);
        
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
        GL11.glColor3f(0.25f, 0.5f, 1.0f);
        GL11.glBegin(GL11.GL_QUADS);
            GL11.glVertex2f(100, 100);
            GL11.glVertex2f(100, 400);
            GL11.glVertex2f(400, 400);
            GL11.glVertex2f(400, 100);
        GL11.glEnd();
        
        GL11.glColor3f(1.0f, 1.0f, 1.0f);
        
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
        GL11.glBegin(GL11.GL_QUADS);
            // Bottom-left
            GL11.glTexCoord2f(0, 1);
            GL11.glVertex2f(player.getWorldX() - 50, player.getWorldY() - 50);
            // Top-left
            GL11.glTexCoord2f(0, 0);
            GL11.glVertex2f(player.getWorldX() - 50, player.getWorldY() + 50);
            // Top-right
            GL11.glTexCoord2f(1, 0);
            GL11.glVertex2f(player.getWorldX() + 50, player.getWorldY() + 50);
            // Bottom-right
            GL11.glTexCoord2f(1, 1);
            GL11.glVertex2f(player.getWorldX() + 50, player.getWorldY() - 50);
        GL11.glEnd();
        
        projectileEngine.render();
        
        GL11.glPopMatrix();
    }
    
    public void pollInput() {
        if(Keyboard.isKeyDown(Keyboard.KEY_W) && Keyboard.isKeyDown(Keyboard.KEY_S))
            player.setDY(0);
        else if(Keyboard.isKeyDown(Keyboard.KEY_W))
            player.setDY(200);
        else if(Keyboard.isKeyDown(Keyboard.KEY_S))
            player.setDY(-200);
        else
            player.setDY(0);
        
        if(Keyboard.isKeyDown(Keyboard.KEY_D) && Keyboard.isKeyDown(Keyboard.KEY_A))
            player.setDX(0);
        else if(Keyboard.isKeyDown(Keyboard.KEY_D))
            player.setDX(200);
        else if(Keyboard.isKeyDown(Keyboard.KEY_A))
            player.setDX(-200);
        else
            player.setDX(0);
        
        if(Mouse.isButtonDown(0)) {
            if(!leftButtonDown) {
                player.shoot();
                leftButtonDown = true;
            }
        }
        else
            leftButtonDown = false;
    }
    
    public static void main(String[] args) {
        GameConcept game = new GameConcept();
    }
}
