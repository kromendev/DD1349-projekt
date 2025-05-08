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
                gp.reset();
            }
    
            // Credits button
            else if (x >= center && x <= center + buttonX && y >= 300 && y <= 350) {
                GameState.setGameState(GameState.CREDITS);
            }
    
            // Credits button
            else if (x >= center && x <= center + buttonX && y >= 400 && y <= 450) {
                GameState.setGameState(GameState.QUIT);
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