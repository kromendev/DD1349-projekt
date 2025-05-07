import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
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
    public final int maxScreenCol = 16; // max screen size column
    public final int maxScreenRow = 12; // max screen size row
    public final int screenWidth = tileSize * maxScreenCol; // 768 px
    public final int screenHeight = tileSize * maxScreenRow; // 576 px

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);
    Monster monster = new Monster(this, keyH, "ooooooooooo o", player);
    TileManager tm = new TileManager(this);
    Menu menu = new Menu(this);
    public Collision collision = new Collision(this);

    public GameState gameState = GameState.MENU;

    // Font
    private Font gameFont;

    // FPS
    final int fps = 60;

    /**
     * Creates a game panel.
     */
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // set size
        this.setBackground(Color.BLACK); // sets background color
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true); // makes panel able to capture inputs

        // Getting the custom font from the /src folder
        try {
            gameFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/PressStart2P-vaV7.ttf")).deriveFont(20f);
        } catch (Exception e) {
            gameFont = new Font("Arial", Font.BOLD, 20); // fallback
        }

        // Registers the MouseListener class
        addMouseListener(new MouseListener(this)); 
    }

    /**
     * Values for current state of the game.
     */
    public enum GameState {
        MENU,
        PLAY,
        SETTINGS,
        CREDITS
    }
    
    public GameState getGameState() {
        return gameState;
    }
    
    public void setGameState(GameState newState) {
        this.gameState = newState;
    }

    /**
     * Starts the game thread, calls run() automatically.
     */
    public void startGameThread() {
        gameThread = new Thread(this); // passing gamepanel class into threads constructor.
        gameThread.start();
    }

    /**
     * Runs main game loop, overrides run() from Runnable interface.
     */
    @Override
    public void run() { 

        double drawInterval = 1000000000 / fps; // 0.016666s
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;

            timer += currentTime - lastTime;

            lastTime = currentTime;

            if (delta > 1) {
                // update infromation
                update();

                // draw the screen with the updated information
                repaint(); // inbuilt method that calls paintcomponent

                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    /**
     * Updates game information
     */
    public void update() {
        if (gameState == GameState.PLAY) {
            player.update();

            monster.update();

            player.collisionOn = false;
            this.collision.checkEntity(player, monster); // when there are more monsters, create a loop that checks for every monster.

            if (player.collisionOn) {
                // resets the game
                player.setDefaultValues();
                monster.setDefaultValues();
                monster.i = 0;
            }
        }
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
        g2.setFont(gameFont);
        g2.setColor(Color.WHITE);

        if (getGameState() == GameState.MENU) {
            menu.drawMenu(g2);
        } else if (getGameState() == GameState.PLAY) {
            // draws map
            tm.draw(g2); // whatever is drawn first will be the bottom layer of the drawn images
            
            if (monster.alive) {
                monster.draw(g2);
            }
            player.draw(g2);
    
            g2.dispose();
        }
    }
}
