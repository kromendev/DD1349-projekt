import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
/**
 * Explain class..
 */
public class GamePanel extends JPanel {
    
    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 Tile, so the map, characters and everything will be built through these 16x16 tiles.
    final int scale = 3; // scale up tiles to look normal on bigger resolutions.

    final int tileSize = originalTileSize * scale; // 48x48 is the true tilesize.
    // create 4:3 game window
    final int maxScreenCol = 16; // max screen size column
    final int maxScreenRow = 12; // max screen size row
    final int screenWidth = tileSize * maxScreenCol; // 768 px
    final int screenHeight = tileSize * maxScreenRow; // 576 px

    /**
     * Creates a game panel.
     */
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // set size
        this.setBackground(Color.BLACK); // might be unnecessary, sets background color
        this.setDoubleBuffered(true);
    }
}
