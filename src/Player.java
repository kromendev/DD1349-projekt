import java.awt.Graphics2D;

/**
 * This class creates the player.
 * 
 * @author Husein Hassan
 * @version 2025-05-04
 */
public class Player extends Entity{
    // helper field
    private boolean aniCycle = false; // checks if an animation cycle has been triggered

    /**
     * Creates the player.
     * 
     * @param gp gamepanel that the player is associated with.
     * @param keyH keyhandler that the player is associated with.
     */
    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp, keyH);

        setHitbox(3, 12, 30, 35);

        setDefaultValues();
        
        //Getter for the player sprites.
        loadSprites("/Sprites/TypingPlayerRight1.png", "/Sprites/TypingPlayerRight2.png", "/Sprites/TypingPlayerRight3.png");
    }

    /**
     * Sets the players default values
     */
    public void setDefaultValues() {
        x = -gp.tileSize;
        y = 384;
        speed = 4; // not used for player, implement in future versions?
        direction = "Right";
    }

    /**
     * Updates the state of the player
     */
    public void update() {
        if (x < 100) {
            x += speed;
        }

        if (keyH.anythingTyped && !aniCycle) {
            aniCycle = true;
            spriteNum = 2;
            spriteCounter = 0;
        }

        if (aniCycle) {
            spriteCounter++;

            if (spriteCounter > 10) {
                gp.playSFX(1);
                if (spriteNum == 2) {
                    spriteNum = 3;
                } else if (spriteNum == 3) {
                    spriteNum = 2;
                    aniCycle = false;
                    keyH.anythingTyped = false;
                }
                spriteCounter = 0;
            }
        } else {
            spriteNum = 1;
        }
    }

    /**
     * Draws the player
     * 
     * @param g2 graphic to draw
     */
    public void draw(Graphics2D g2) {
        super.draw(g2);
    }
}
