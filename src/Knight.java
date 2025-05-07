import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This class creates monsters.
 * 
 * @author Husein Hassan
 * @version 2025-04-28
 */
public class Knight extends Entity{
    // word that needs to typed to kill monster
    String word;
    int i = 0;
    
    GamePanel gp;
    KeyHandler keyH;
    Player player;

    /**
     * Creates a monster
     * 
     * @param gp gamepanel that the monster is associated with.
     * @param keyH keyhandler that the monster is associated with.
     * @param word word that player needs to type to eliminate the monster.
     */
    public Knight(GamePanel gp, KeyHandler keyH, String word, Player player) {
        this.gp = gp;
        this.keyH = keyH;
        this.word = word;
        this.player = player;

        hitbox = new Rectangle();
        hitbox.x = 18; // x coordinate in upscaled version
        hitbox.y = 12; // y coord
        hitbox.width = 24;
        hitbox.height = 35;

        setDefaultValues();
        getMonsterImage();
    }
    /**
     * Getter for monster sprites
     * 
     * @throws IOException if program can't find the image files
     */
    public void getMonsterImage() {
        try {
            picture1 = ImageIO.read(getClass().getResourceAsStream("/Sprites/Knight(1).png"));
            picture2 = ImageIO.read(getClass().getResourceAsStream("/Sprites/Knight(2).png"));
            picture3 = ImageIO.read(getClass().getResourceAsStream("/Sprites/Knight(3).png"));
            picture4 = ImageIO.read(getClass().getResourceAsStream("/Sprites/Knight(4).png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets default position and speed for Monster
     */
    public void setDefaultValues() {
        x = 500;
        y = 384;
        speed = 1;
        direction = "Left";
    }

    /**
     * Updates state of a monster object
     */
    public void update() {
        char[] chars = word.toCharArray();
        char key = keyH.readKey();
        if (this.i < chars.length && chars[this.i] == key) {
            this.i++;
        }
        else if (this.i < chars.length && chars[this.i] != key && key != 0) {
            this.i = 0;
        }
        else if (this.i >= chars.length) {
            alive = false;
        }

        if (alive) {
            // NOTE, should check bounds before checking collision, for monster coming in from outside the map.
            collisionOn = false;
            gp.collision.checkTile(this);
            gp.collision.checkEntity(this, player);

            if (collisionOn == false) {
                x -= speed;
            }

            spriteCounter++;
            if (spriteCounter > 15) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                }
                else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    /**
     * Draws monster
     * 
     * @param g2 graphic to draw
     */
    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        if (spriteNum == 1) {
            image = picture1;
        } else if (spriteNum == 2) {
            image = picture2;
        }else if (spriteNum == 3) {
            image = picture3;
        }else if (spriteNum == 4) {
            image = picture4;
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

        String typedPart = word.substring(0, i);
        String untypedPart = word.substring(i);

        int totalWidth = g2.getFontMetrics().stringWidth(word);
        int wordStartX = x + (gp.tileSize - totalWidth) / 2;
        int wordY = y - 10;

        g2.drawString(untypedPart, wordStartX + g2.getFontMetrics().stringWidth(typedPart), wordY);

        if (this.i > 0) {
            g2.setColor(Color.GREEN);
            g2.drawString(typedPart, wordStartX, wordY);
        }
    }
}
