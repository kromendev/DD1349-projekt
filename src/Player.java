import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This class creates the player.
 * 
 * @author Husein Hassan
 * @version 2025-05-04
 */
public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;
    // helper field
    private boolean aniCycle = false; // checks if an animation cycle has been triggered

    /**
     * Creates the player.
     * 
     * @param gp gamepanel that the player is associated with.
     * @param keyH keyhandler that the player is associated with.
     */
    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        hitbox = new Rectangle();
        hitbox.x = 3; // x coordinate in upscaled version
        hitbox.y = 12; // y coord
        hitbox.width = 30;
        hitbox.height = 36;

        setDefaultValues();
        getPlayerImage();
    }

    /**
     * Getter for the player sprites.
     * 
     * @throws IOException if program can't find the image files
     */
    public void getPlayerImage() {
        try {
            right1 = ImageIO.read(getClass().getResourceAsStream("/Sprites/TypingPlayerRight1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Sprites/TypingPlayerRight2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/Sprites/TypingPlayerRight3.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the players default values
     */
    public void setDefaultValues() {
        x = 100;
        y = 383;
        speed = 4; // not used for player, implement in future versions?
        direction = "Right";
    }

    /**
     * Updates the state of the player
     */
    public void update() {
        if (keyH.anythingTyped && !aniCycle) {
            aniCycle = true;
            spriteNum = 2;
            spriteCounter = 0;
        }

        // 2 following lines unnecessary currently.
        collisionOn = false;
        gp.collision.checkTile(this);

        if (aniCycle) {
            spriteCounter++;

            if (spriteCounter > 10) {
                if (spriteNum == 2) {
                    spriteNum = 3;
                } else if (spriteNum == 3) {
                    spriteNum = 2;
                    aniCycle = false;
                    keyH.anythingTyped = false;
                }
                spriteCounter = 0;
            }
        } else {
            spriteNum = 1;
        }
    }

    /**
     * Draws the player
     * 
     * @param g2 graphic to draw
     */
    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        if (spriteNum == 1) {
            image = right1;
        } else if (spriteNum == 2) {
            image = right2;
        }
        else if (spriteNum == 3) {
            image = right3;
        }

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
