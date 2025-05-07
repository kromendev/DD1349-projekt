import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * Explain this class
 * 
 * @author Husein Hassan
 * @version 2025-04-28
 */
public class Entity {
    public int x, y;
    public int speed;
    public String direction;
    public boolean alive = true;

    public BufferedImage picture1, picture2, picture3, picture4, picture5;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle hitbox;

    public boolean collisionOn = false;
}
