import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 * This class handles keystrokes, checks if keys
 * are pressed or not so the game can read inputs and manipulate
 * the draw according to the inputs.
 * 
 * @author Husein Hassan
 * @version 2025-04-28
 */
public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, rightPressed, leftPressed;

    @Override
    public void keyTyped(KeyEvent e) {
        // nothing, for now...
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }
     
}
