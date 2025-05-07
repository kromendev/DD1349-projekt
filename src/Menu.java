import java.awt.Color;
import java.awt.Graphics;

public class Menu {
    GamePanel gp;

    public Menu(GamePanel gp) {
        this.gp = gp;
    }

    void drawMenu(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, gp.getWidth(), gp.getHeight());
    
        int buttonY = 50;
        int buttonX = 250;
        int center = gp.getWidth() / 2 - 125;
        
        g.setColor(Color.WHITE);
        g.drawString("My Game", gp.getWidth() / 2 - g.getFontMetrics().stringWidth("My Game")/2, 150);
    
        g.drawRect(center, 200, buttonX, buttonY);
        g.drawString("Start Game", gp.getWidth() / 2 - g.getFontMetrics().stringWidth("Start Game")/2, 235);

        g.drawRect(center, 300, buttonX, buttonY);
        g.drawString("Settings", gp.getWidth() / 2 - g.getFontMetrics().stringWidth("Settings")/2, 335);

        g.drawRect(center, 400, buttonX, buttonY);
        g.drawString("Credits", gp.getWidth() / 2 - g.getFontMetrics().stringWidth("Credits")/2, 435);
    }
}