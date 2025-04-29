import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This class creates monsters.
 * 
 * @author Husein Hassan
 * @version 2025-04-28
 */
public class Monster extends Entity{
    // word that needs to typed to kill monster
    String word;
    int i = 0;
    boolean alive = true;
    
    GamePanel gp;
    KeyHandler keyH;

    public Monster(GamePanel gp, KeyHandler keyH, String word) {
        this.gp = gp;
        this.keyH = keyH;
        this.word = word;

        setDefaultValues();
        getMonsterImage();
    }

    public void getMonsterImage() {
        try {
            left1 = ImageIO.read(getClass().getResourceAsStream("/Sprites/TypingMonsterLeft1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/Sprites/TypingMonsterLeft2.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void setDefaultValues() {
        x = 500;
        y = 400;
        speed = 1;
    }

    public void update() {
        char[] chars = word.toCharArray();
        if (this.i < chars.length && chars[this.i] == keyH.c) {
            this.i++;
        }
        else if (this.i >= chars.length) {
            alive = false;
        }
        x -= speed;

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

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        if (spriteNum == 1) {
            image = left1;
        } else if (spriteNum == 2) {
            image = left2;
        }

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
