import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This class interprets mouse click input.
 * 
 * @author Gustav dyrcz
 * @version 2025-05-07
 */
public class MouseListener extends MouseAdapter {
    GamePanel gp;

    public MouseListener(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (gp.gameState == GamePanel.GameState.MENU) {
            //mouse click position
            int x = e.getX();
            int y = e.getY();

            // Button dimensions from Main menu
            int center = gp.getWidth() / 2 - 125;
            int buttonX = 250;
    
            // Start Game button
            if (x >= center && x <= center + buttonX &&
                y >= 200 && y <= 250) {
                gp.setGameState(GamePanel.GameState.PLAY);
                gp.requestFocusInWindow();
            }
    
            // Settings button
            else if (x >= center && x <= center + buttonX && y >= 300 && y <= 350) {
                gp.setGameState(GamePanel.GameState.SETTINGS);
            }
    
            // Credits button
            else if (x >= center && x <= center + buttonX && y >= 400 && y <= 450) {
                gp.setGameState(GamePanel.GameState.CREDITS);
            }
        }
    }        
}