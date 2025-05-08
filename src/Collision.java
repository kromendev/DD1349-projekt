/**
 * This class checks collisions between solid objects and entities.
 * 
 * @author Husein Hassan
 * @version 2025-05-04
 */
public class Collision {
    GamePanel gp;

    /**
     * Constructor that specifies the gamepanel for a Collision object.
     * 
     * @param gp specified gamepanel.
     */
    public Collision(GamePanel gp) {
        this.gp = gp;
    }

    /**
     * Checks for collisions between an entity and a map tile.
     * 
     * @param entity to check collision for.
     */
    public void checkTile(Entity entity) {
        // find hitbox coords on all 4 sides
        int entityLeftX = entity.x + entity.hitbox.x;
        int entityRightX = entity.x + entity.hitbox.x + entity.hitbox.width;
        int entityTopY = entity.y + entity.hitbox.y;
        int entityBottomY = entity.y + entity.hitbox.y + entity.hitbox.height;

        int entityLeftCol = entityLeftX / gp.tileSize;
        int entityRightCol = entityRightX / gp.tileSize;
        int entityTopRow = entityTopY / gp.tileSize;
        int entityBottomRow = entityBottomY / gp.tileSize;

        int tileNum1, tileNum2;
        
        switch (entity.direction) {
            case "Left":
                entityLeftCol = (entityLeftX - entity.speed) / gp.tileSize; // predict where entity is going to be
                tileNum1 = gp.tm.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tm.mapTileNum[entityLeftCol][entityBottomRow];
    
                if (gp.tm.tile[tileNum1].collision == true || gp.tm.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "Right":
                entityRightCol = (entityRightX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tm.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tm.mapTileNum[entityRightCol][entityBottomRow];

                if (gp.tm.tile[tileNum1].collision == true || gp.tm.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "Up":
                entityTopRow = (entityTopY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tm.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tm.mapTileNum[entityRightCol][entityTopRow];

                if (gp.tm.tile[tileNum1].collision == true || gp.tm.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "Down":
                entityBottomRow = (entityBottomY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tm.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tm.mapTileNum[entityRightCol][entityBottomRow];

                if (gp.tm.tile[tileNum1].collision == true || gp.tm.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
        }
    }

    /**
     * Checks for collisions between 2 entities.
     * 
     * @param entityOne first entity
     * @param entityTwo second entity
     */
    public void checkEntity(Entity entityOne, Entity entityTwo) {
        int entityOneLeftX = entityOne.x + entityOne.hitbox.x; // hitbox left side x coordinate
        int entityOneRightX = entityOne.x + entityOne.hitbox.x + entityOne.hitbox.width; // hitbox right side x coordinate
        int entityOneTopY = entityOne.y + entityOne.hitbox.y; // hitbox top y coordinate
        int entityOneBottomY = entityOne.y + entityOne.hitbox.y + entityOne.hitbox.height; // hitbox bottom y coordinate

        int entityTwoLeftX = entityTwo.x + entityTwo.hitbox.x;
        int entityTwoRightX = entityTwo.x + entityTwo.hitbox.x + entityTwo.hitbox.width;
        int entityTwoTopY = entityTwo.y + entityTwo.hitbox.y;
        int entityTwoBottomY = entityTwo.y + entityTwo.hitbox.y + entityTwo.hitbox.height;

        int entityOneLeftCol = entityOneLeftX / gp.tileSize; // The column of pixels the left side of the hitbox is in
        int entityOneRightCol = entityOneRightX / gp.tileSize; // The column of pixels the right side of the hitbox is in
        int entityOneTopRow = entityOneTopY / gp.tileSize; // The row of pixels the top of the hitbox is in
        int entityOneBottomRow = entityOneBottomY / gp.tileSize; // The row of pixels the bottom of the hitbox is in


        int entityTwoLeftCol = entityTwoLeftX / gp.tileSize;
        int entityTwoRightCol = entityTwoRightX / gp.tileSize; 
        int entityTwoTopRow = entityTwoTopY / gp.tileSize; 
        int entityTwoBottomRow = entityTwoBottomY / gp.tileSize;
        
        switch (entityOne.direction) {
            case "Left":
                if (entityOneLeftCol == entityTwoRightCol) {
                    entityOne.collisionOn = true;
                }
                break;
            case "Right":
                if (entityOneRightCol == entityTwoLeftCol) {
                    entityOne.collisionOn = true;
                }
                break;
            case "Up":
                if (entityOneTopRow == entityTwoBottomRow) {
                    entityOne.collisionOn = true;
                }
                break;
            case "Down":
                if (entityOneBottomRow == entityTwoTopRow) {
                    entityOne.collisionOn = true;
                }
                break;
        }
    }
}
