import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

/**
 * A class for the games map, specifies position and collision for each map tile.
 * 
 * @author Husein Hassan
 * @version 2025-05-08
 */
public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxScreenRow];

        getTileImage();
        loadMap();
    }

    /**
     * Getter method for tile sprites, also specifies if a tile has collision or not.
     * 
     * @throws IOException if a sprite can't be found.
     */
    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/Sprites/FloorTile1.png"));
            tile[0].collision = true;

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/Sprites/FloorTile2.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/Sprites/BackgroundTile1V2.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/Sprites/BackgroundTileLit.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/Sprites/BackgroundTileTorch1.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/Sprites/BackgroundTileTorch2.png"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the map tiles in their positions specified in a txt file.
     * 
     * @throws Exception if txt file can't be found.
     */
    public void loadMap() {
        try {
            InputStream is = getClass().getResourceAsStream("/Data/MapData.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int row = 0;
            
            String line;
            while ((line = br.readLine()) != null && row < gp.maxScreenRow) {
                String[] numbers = line.split(" ");
                
                for (int col = 0; col < gp.maxWorldCol && col < numbers.length; col++) {
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                }
                
                row++;
            }

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Draws the map
     * 
     * @param g2 the 2d graphics to draw.
     */
    public void draw(Graphics2D g2) {

        int col = 0;
        int row = 0;
        int x = -gp.tileSize;
        int y = 0;
        
        while (col < gp.maxWorldCol && row < gp.maxScreenRow) {
            int tileNum = mapTileNum[col][row]; 

            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize,  null);
            col++;
            x += gp.tileSize;

            if (col == gp.maxWorldCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }
}
