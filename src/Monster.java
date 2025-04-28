import java.awt.Color;
import java.awt.Graphics2D;

/**
 * This class creates monsters.
 * 
 * @author Husein Hassan
 * @version 2025-04-28
 */
public class Monster extends Entity{
    // word that need to typed to kill monster
    String word;
    
    GamePanel gp;
    KeyHandler keyH;

    public Monster(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
    }

    public void setDefaultValues() {
        x = 500;
        y = 400;
        speed = 1;
    }

    public void update() {
        x -= speed;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.RED);
        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
    }
}
