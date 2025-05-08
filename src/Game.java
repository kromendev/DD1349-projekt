import javax.swing.JFrame;
/**
 * This a word typing videogame where monsters walk into frame from right to left.
 * If these monsters reach the player the player dies and you get the option to restart.
 * However these monsters have a word over their heads, write these words to eliminate
 * the monsters. How many can you take out?
 * 
 * @author Husein Hassan
 * @version 2025-04-28
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

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack(); // sets window to preferred size

        window.setLocationRelativeTo(null); // window pops up in the center of screen
        window.setVisible(true);
        
        gamePanel.startGameThread();
    }
    
}
