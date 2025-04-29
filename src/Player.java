import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This class creates the player
 * 
 * @author Husein Hassan
 * @version 2025-04-28
 */
public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();
    }

    public void getPlayerImage() {
        try {
            right1 = ImageIO.read(getClass().getResourceAsStream("/Sprites/TypingPlayerRight1V1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Sprites/TypingPlayerRight2V1.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void setDefaultValues() {
        x = 100;
        y = 400;
        speed = 4;
    }

    public void update() {
        /*if (keyH.upPressed == true) {
            y -= speed;
        } 
        else if (keyH.leftPressed == true) {
            x -= speed;
        } 
        else if (keyH.downPressed == true) {
            y += speed;
        } 
        else if (keyH.rightPressed == true) {
            x += speed;
        }*/
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = right1;

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
