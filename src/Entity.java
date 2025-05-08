import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Parent class for creating entities.
 * 
 * @author Husein Hassan
 * @author Gustav Dyrcz
 * @version 2025-05-08
 */
public class Entity {
    public Rectangle hitbox;
    public int x, y;
    int i = 0;

    public int speed;
    public String direction;

    public boolean alive;
    public boolean collisionOn = false;

    protected BufferedImage[] pictures = new BufferedImage[5];
    public int spriteCounter = 0;
    public int spriteNum = 1;

    protected GamePanel gp;
    protected KeyHandler keyH;
    public String word;

    /**
     * Constructor for entities. Specifies the gamepanel and keyhandler.
     * 
     * @param gp specified gamepanel.
     * @param keyH specified keyhandler.
     */
    public Entity(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
    }

    /**
     * Sets the hitbox of an entity.
     * 
     * @param x x coordinate inside the tile.
     * @param y y coordinate inside the tile.
     * @param width hitbox width.
     * @param height hitbox height.
     */
    public void setHitbox(int x, int y, int width, int height) {
        hitbox = new Rectangle(x, y, width, height);
    }

    /**
     * Loads sprites into an array.
     * 
     * @param paths paths to the sprites.
     * @throws IOException if sprite can't be found in the path.
     */
    public void loadSprites(String... paths) {
        try {
            for (int i = 0; i < paths.length; i++) {
                pictures[i] = ImageIO.read(getClass().getResourceAsStream(paths[i]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Draws the entity.
     * 
     * @param g2 graphics to draw.
     */
    public void draw(Graphics2D g2) {
        BufferedImage image = pictures[spriteNum - 1];
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }

    /**
     * Sets the default values of the entity.
     */
    public void setDefaultValues() {
       i = 0;
    }
}
