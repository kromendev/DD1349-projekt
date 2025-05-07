import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * This class Draws the Main menu screen.
 * 
 * @author Gustav dyrcz
 * @version 2025-05-07
 */
public class Menu {
    GamePanel gp;

    public Menu(GamePanel gp) {
        this.gp = gp;
    }

    void drawMenu(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;

        gp.tm.draw(g2);
    
        int buttonY = 50;
        int buttonX = 250;
        int center = gp.getWidth() / 2 - 125;
        
        g.setColor(Color.WHITE);
        g.drawString("Typing Game", gp.getWidth() / 2 - g.getFontMetrics().stringWidth("Typing Game")/2, 150);
    
        g.drawRect(center, 200, buttonX, buttonY);
        g.drawString("Start Game", gp.getWidth() / 2 - g.getFontMetrics().stringWidth("Start Game")/2, 235);

        g.drawRect(center, 300, buttonX, buttonY);
        g.drawString("Credits", gp.getWidth() / 2 - g.getFontMetrics().stringWidth("Credits")/2, 335);

        g.drawRect(center, 400, buttonX, buttonY);
        g.drawString("Quit", gp.getWidth() / 2 - g.getFontMetrics().stringWidth("Quit")/2, 435);
    }
}