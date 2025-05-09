import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This class interprets mouse click input.
 * Detects button clicks based on screen coordinates and updates game state accordingly.
 * 
 * @author Gustav dyrcz
 * @version 2025-05-09
 */
public class MouseListener extends MouseAdapter {
    GamePanel gp;

    /**
     * Constructs a MouseListener tied to a specific GamePanel.
     * 
     * @param gp the GamePanel that this class interprets
     */
    public MouseListener(GamePanel gp) {
        this.gp = gp;
    }

    /**
     * Called when the mouse is pressed.
     * Handles clicks on buttons in different game states.
     * 
     * @param e the mouse click
     */
    @Override
    public void mousePressed(MouseEvent e) {
        //mouse click position
        int x = e.getX();
        int y = e.getY();

        if (GameState.getGameState() == GameState.MENU) {

            // Button dimensions from Main menu
            int center = gp.getWidth() / 2 - 125;
            int buttonX = 250;
    
            // Start Game button
            if (x >= center && x <= center + buttonX && y >= 200 && y <= 250) {
                GameState.setGameState(GameState.PLAY);
                GameLogic.reset(gp);
            }
    
            // Credits button
            else if (x >= center && x <= center + buttonX && y >= 300 && y <= 350) {
                GameState.setGameState(GameState.CREDITS);
            }
    
            // Credits button
            else if (x >= center && x <= center + buttonX && y >= 400 && y <= 450) {
                GameState.setGameState(GameState.QUIT);
                System.exit(0);
            }

            // Reset high score
            else if (x >= center + 25 && x <= center + 225 && y >= 525 && y <= 555) {
                GameLogic.resetHighScore();
            }
        }

        if (GameState.getGameState() == GameState.PLAY) {
            //pause button
            if (x >= 20 && x <= 170 && y >= 20 && y <= 70) {
                GameState.setGameState(GameState.PAUSE);
            }
        }

        if (GameState.getGameState() == GameState.PAUSE) {
            // Button dimensions from Main menu
            int center = gp.getWidth() / 2 - 125;
            int buttonX = 250;
    
            // Resume Game button
            if (x >= center && x <= center + buttonX && y >= 200 && y <= 250) {
                GameState.setGameState(GameState.PLAY);
            }
    
            // Menu button
            else if (x >= center && x <= center + buttonX && y >= 300 && y <= 350) {
                GameState.setGameState(GameState.MENU);
            }
        }

        if (GameState.getGameState() == GameState.CREDITS) {
            // Button dimensions from Main menu
            int center = gp.getWidth() / 2 - 125;
            int buttonX = 250;
    
            // Menu button
            if (x >= center && x <= center + buttonX && y >= 400 && y <= 450) {
                GameState.setGameState(GameState.MENU);
            }
        }

        if (GameState.getGameState() == GameState.GAMEOVER) {
            // Button dimensions from Main menu
            int center = gp.getWidth() / 2 - 125;
            int buttonX = 250;
    
            // Menu button
            if (x >= center && x <= center + buttonX && y >= 300 && y <= 350) {
                GameState.setGameState(GameState.MENU);
            }
        }
    }        
}