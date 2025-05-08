// import java.awt.RenderingHints.Key;
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
    public boolean anythingTyped;

    private char lastKeyPressed = 0;
    private boolean hasNewKey = false;

    @Override
    public void keyTyped(KeyEvent e) {
        // not needed
    }

    @Override
    public void keyPressed(KeyEvent e) {
        char keyChar = e.getKeyChar();

        if (Character.isDefined(keyChar) && !Character.isISOControl(keyChar)) {
            lastKeyPressed = keyChar;
            hasNewKey = true;
            anythingTyped = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // not needed
    }

    /**
     * Reads a keyboard input once.
     * 
     * @return input key
     */
    public char readKey() {
        if (!hasNewKey) {
            return 0;
        }

        char key = lastKeyPressed;

        hasNewKey = false;

        return key;
    }
     
}
