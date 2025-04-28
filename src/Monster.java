import java.awt.Color;
import java.awt.Graphics2D;

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
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.RED);
        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
    }
}
