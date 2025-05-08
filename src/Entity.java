import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Explain this class
 * 
 * @author Husein Hassan
 * @version 2025-04-28
 */
public class Entity {
    public Rectangle hitbox;
    public int x, y;

    public int speed;
    public String direction;

    public boolean alive;
    public boolean collisionOn = false;

    protected BufferedImage[] pictures = new BufferedImage[5];
    public int spriteCounter = 0;
    public int spriteNum = 1;

    protected GamePanel gp;
    protected KeyHandler keyH;

    public Entity(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
    }

    public void setHitbox(int x, int y, int width, int height) {
        hitbox = new Rectangle(x, y, width, height);
    }

    public void loadSprites(String... paths) {
        try {
            for (int i = 0; i < paths.length; i++) {
                pictures[i] = ImageIO.read(getClass().getResourceAsStream(paths[i]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = pictures[spriteNum - 1];
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
