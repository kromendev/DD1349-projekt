import java.awt.Color;
import java.awt.Graphics2D;

/**
 * This class creates a knight enemy that dies when a word is typed correctly.
 * Inherits behavior from the Entity superclass.
 * 
 * Handles movement, animation, collision, and typing logic.
 * 
 * @author Husein Hassan
 * @author Gustav Dyrcz
 * @version 2025-05-09
 */
public class Knight extends Entity{
    Player player;
    public int lives;
    public int livesSave;

    /**
     * Constructs a new knight enemy.
     * 
     * @param gp the game panel instance
     * @param keyH the keyboard input handler
     * @param word the word required to defeat the knight
     * @param player the player instance
     * @param lives how many words the knight must survive before dying
     */
    public Knight(GamePanel gp, KeyHandler keyH, String word, Player player, int lives) {
        super(gp, keyH);
        this.word = word;
        this.player = player;
        this.lives = lives;
        this.livesSave = lives;

        setHitbox(18, 12, 24, 35);
        setDefaultValues();

        // Loads knight walking animation
        loadSprites("/Sprites/Knight(1).png", "/Sprites/Knight(2).png", "/Sprites/Knight(3).png", "/Sprites/Knight(4).png");
    }

    /**
     * Sets default position and speed for knight
     */
    public void setDefaultValues() {
        i = 0;
        x = 836;
        y = 384;
        speed = 1;
        direction = "Left";
    }

    /**
     * Updates state of knight
     * Handles movement, collision, and typing logic.
     */
    public void update() {
        //if closest to player
        if (gp.first[1] == true) {
            char[] chars = word.toCharArray();
            char key = keyH.readKey();
            if (this.i < chars.length && chars[this.i] == key) {
                this.i++;
            }
            else if (this.i < chars.length && chars[this.i] != key && key != 0) {
                this.i = 0;
            }
            else if (this.i >= chars.length) {
                lives--;
                if (lives <= 0) {
                    setDefaultValues();
                    gp.playSFX(4);
                    alive = false;
                    lives = livesSave;
                    gp.killed++;
                    GameLogic.checkAndSaveHighScore(gp.killed);
                } else {
                    i = 0;
                    this.word = GameLogic.getRandomWord();
                }
            }
        }

        if (alive) {
            // NOTE, should check bounds before checking collision, for monster coming in from outside the map.
            collisionOn = false;
            gp.collision.checkTile(this);
            gp.collision.checkEntity(this, player);

            if (collisionOn == false) {
                x -= speed;
            }

            spriteCounter++;
            if (spriteCounter > 15) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                }
                else if (spriteNum == 2) {
                    spriteNum = 1;
                    gp.playSFX(2); // play knight walking sound
                }
                spriteCounter = 0;
            }
        }
    }

    /**
     * Draws the knight and its typing word on screen.
     * 
     * @param g2 graphic to draw
     */
    public void draw(Graphics2D g2) {
        super.draw(g2);

        // if closest to player
        if (gp.first[1] == true) {
            String typedPart = word.substring(0, i);
            String untypedPart = word.substring(i);
    
            int totalWidth = g2.getFontMetrics().stringWidth(word);
            int wordStartX = x + (gp.tileSize - totalWidth) / 2;
            int wordY = y - 10;
    
            g2.drawString(untypedPart, wordStartX + g2.getFontMetrics().stringWidth(typedPart), wordY);
    
            if (this.i > 0) {
                g2.setColor(Color.GREEN);
                g2.drawString(typedPart, wordStartX, wordY);
            }
        }
    }
}
