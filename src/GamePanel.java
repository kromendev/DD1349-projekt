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
 * @author Gustav dyrcz
 * @version 2025-05-08
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

    public final int maxWorldCol = 18; // max world column for monster spawning

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);
    Sound music= new Sound();
    Sound sfx = new Sound();

    private boolean musicStarted = false;
    private boolean gameOver = false;

    int killed = 0;

    Monster monster = new Monster(this, keyH, GameLogic.getRandomWord(), player, 1);
    Knight knight = new Knight(this, keyH, GameLogic.getRandomWord(), player, 2);

  
    TileManager tm = new TileManager(this);
    Menu menu = new Menu(this);
    public Collision collision = new Collision(this);

    // Font
    private Font gameFont;

    // FPS
    final int fps = 60;

    //needed for knight to spawn in later
    long timer = System.currentTimeMillis();

    //Boolean to check witch entity is first (monster or knight)
    boolean[] first = {false, false}; 


    /**
     * Creates a game panel.
     */
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // set size
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true); // makes panel able to capture inputs

        // Getting the custom font from the /src folder
        try {
            gameFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/Data/PressStart2P-vaV7.ttf")).deriveFont(20f);
        } catch (Exception e) {
            gameFont = new Font("Arial", Font.BOLD, 20); // fallback
        }

        // Registers the MouseListener class
        addMouseListener(new MouseListener(this)); 
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
        final double drawInterval = 1000000000.0 / fps;
        double tick = 0;
        long lastTime = System.nanoTime();

        while (gameThread != null) {
            long now = System.nanoTime();
            tick += (now - lastTime) / drawInterval;
            lastTime = now;
    
            if (tick >= 1) {
                if (GameState.getGameState() == GameState.PLAY) {
                    if (!musicStarted) {
                        playMusic(0);
                        musicStarted = true;
                    }
                    update();
                }
                repaint();
                tick--;
            }
        }
    }

    /**
     * Updates game information
     */
    public void update() {
        monster.update();
        knight.update();
        player.update();

        GameLogic.entityRandomSpawn(this);

        first = GameLogic.entityClosest(player, monster, knight);

        GameLogic.ifGameOver(this);
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

        if (GameState.getGameState() == GameState.MENU) {
            menu.drawMenu(g2);
        } 
        if (GameState.getGameState() == GameState.PLAY) {
            gameOver = false;

            // draws map
            tm.draw(g2); // whatever is drawn first will be the bottom layer of the drawn images

            menu.drawInPlay(g2);
            
            if (monster.alive) {
                monster.draw(g2);
            }
            if (knight.alive) {
                knight.draw(g2);
            }
            player.draw(g2);
    
            g2.dispose();
        } 
        if (GameState.getGameState() == GameState.PAUSE) {
            menu.drawPause(g2);
            stopMusic();
            musicStarted = false;
        }
        if (GameState.getGameState() == GameState.CREDITS) {
            menu.drawCredits(g2);
        }
        if(GameState.getGameState() == GameState.GAMEOVER){
            menu.drawGameOver(g2);
            stopMusic();
            if (gameOver == false) {
                playSFX(5);
                gameOver = true;
            }
            musicStarted = false;
        }
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSFX(int i) {
        sfx.setFile(i);
        sfx.play();
    }
}
