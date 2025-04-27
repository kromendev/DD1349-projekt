import javax.swing.JFrame;
/**
 * Explain videogame here..
 */
public class Game {

    /**
     * Main method that runs the game process.
     */
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes window when user clicks X
        window.setResizable(false);
        window.setTitle("TYPING GAME"); // sets window title

        window.setLocationRelativeTo(null); // window pops up in the center of screen
        window.setVisible(true);
    }
    
}
