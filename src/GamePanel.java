import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
/**
 * This class draws and refreshes the gamepanel.
 * 
 * @author Husein Hassan
 * @version 2025-04-28
 */
public class GamePanel extends JPanel implements Runnable {
    
    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 Tile, so the map, characters and everything will be built through these 16x16 tiles.
    final int scale = 3; // scale up tiles to look normal on bigger resolutions.

    public final int tileSize = originalTileSize * scale; // 48x48 is the true tilesize.
    // create 4:3 game window
    final int maxScreenCol = 16; // max screen size column
    final int maxScreenRow = 12; // max screen size row
    final int screenWidth = tileSize * maxScreenCol; // 768 px
    final int screenHeight = tileSize * maxScreenRow; // 576 px

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);
    Monster monster = new Monster(this, keyH);

    // Sets default player position and speed
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    // FPS
    final int fps = 60;

    /**
     * Creates a game panel.
     */
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // set size
        this.setBackground(Color.BLACK); // might be unnecessary, sets background color
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true); // makes panel able to capture inputs
    }

    /**
     * Starts the game thread, calls run() automatically.
     */
    public void startGameThread() {
        gameThread = new Thread(this); // passing gamepanel class into thready constructor.
        gameThread.start();
    }

    /**
     * Runs main game loop, overrides run() from Runnable interface.
     */
    @Override
    public void run() {

        double drawInterval = 100000000 / fps; // 0.016666
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null) {
            // update infromation
            update();

            // draw the screen with the updated information
            repaint(); // inbuilt method that calls paintcomponent
            
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000; // convert to milliseconds for the sleep method

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Updates game information
     */
    public void update() {
        monster.update();
        player.update();
    }

    /**
     * Draws game information onto the panel
     * 
     * @param g receives graphics to draw.
     */
    public void paintComponent(Graphics g) {
        // uses inbuilt JPanel method to draw screen.
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g; // setting g as a 2d graphic g2
        
        monster.draw(g2);
        player.draw(g2);

        g2.dispose();
    }
}
