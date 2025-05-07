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

    private int getCenteredX(Graphics g, String text) {
        return gp.getWidth() / 2 - g.getFontMetrics().stringWidth(text) / 2;
    }

    void drawMenu(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;

        gp.tm.draw(g2);

        int buttonY = 50;
        int buttonX = 250;
        int center = gp.getWidth() / 2 - 125;

        g.setColor(Color.BLACK);
        g.fillRect(center-25, 100, 300, 400);
        
        g.setColor(Color.WHITE);
        g.drawString("Typing Game", getCenteredX(g, "Typing Game"), 150);
    
        g.drawRect(center, 200, buttonX, buttonY);
        g.drawString("Start Game", getCenteredX(g, "Start Game"), 235);

        g.drawRect(center, 300, buttonX, buttonY);
        g.drawString("Credits", getCenteredX(g, "Credits"), 335);

        g.drawRect(center, 400, buttonX, buttonY);
        g.drawString("Quit", getCenteredX(g, "Quit"), 435);
    }

    void drawPauseButton(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(20, 20, 150, 50);
        g.setColor(Color.WHITE);
        g.drawRect(20, 20, 150, 50);
        g.drawString("Pause", 20 + (150 - g.getFontMetrics().stringWidth("Pause"))/2, 55);
    }

    void drawPause(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;

        gp.tm.draw(g2);

        int buttonY = 50;
        int buttonX = 250;
        int center = gp.getWidth() / 2 - 125;

        g.setColor(Color.BLACK);
        g.fillRect(center-25, 100, 300, 300);
        
        g.setColor(Color.WHITE);
        g.drawString("Typing Game", getCenteredX(g, "Typing Game"), 150);
    
        g.drawRect(center, 200, buttonX, buttonY);
        g.drawString("Continue", getCenteredX(g, "Continue"), 235);

        g.drawRect(center, 300, buttonX, buttonY);
        g.drawString("Main menu", getCenteredX(g, "Main menu"), 335);
    }

    void drawCredits(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;

        gp.tm.draw(g2);

        int buttonY = 50;
        int buttonX = 250;
        int center = gp.getWidth() / 2 - 125;

        g.setColor(Color.BLACK);
        g.fillRect(center-25, 100, 300, 400);

        g.setColor(Color.WHITE);
        g.drawString("Typing Game", getCenteredX(g, "Typing Game"), 150);

        g.drawString("Created by", getCenteredX(g, "Created by"), 250);
        g.drawString("Husein Hassan", getCenteredX(g, "Husein Hassan"), 290);
        g.drawString("Gustav Dyrcz", getCenteredX(g, "Gustav Dyrcz"), 330);

        g.drawRect(center, 400, buttonX, buttonY);
        g.drawString("Main menu", getCenteredX(g, "Main menu"), 435);
    }

    void drawGameOver(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;

        gp.tm.draw(g2);

        int buttonY = 50;
        int buttonX = 250;
        int center = gp.getWidth() / 2 - 125;

        g.setColor(Color.BLACK);
        g.fillRect(center-25, 100, 300, 300);
        
        g.setColor(Color.WHITE);
        g.drawString("Typing Game", getCenteredX(g, "Typing Game"), 150);
    
        g.drawString("Game Over", getCenteredX(g, "Continue"), 235);

        g.drawRect(center, 300, buttonX, buttonY);
        g.drawString("Main menu", getCenteredX(g, "Main menu"), 335);
    }
}