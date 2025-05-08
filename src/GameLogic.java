import java.util.Random;

public class GameLogic {
    private static final Random rand = new Random();
    private static long lastSpawnTime = System.currentTimeMillis();

    
    private static final String[] words = {
        "sword", "knight", "castle", "shield", "dragon",
        "battle", "armor", "quest", "honor", "lance", 
        "apple", "banana", "car", "dog", "elephant",
        "flower", "guitar", "house", "ice", "jungle",
        "kite", "lamp", "mountain", "notebook", "ocean",
        "pencil", "queen", "river", "sun", "tree",
        "umbrella", "violin", "window", "xylophone", "zebra"
    };
    // Returns random word from index
    public static String getRandomWord() {
        int index = rand.nextInt(words.length);
        return words[index];
    }

    public static void entityRandomSpawn(GamePanel panel) {
        long now = System.currentTimeMillis();
        if (now - lastSpawnTime >= 3000) {
            if (Math.random() < 0.5 && !panel.monster.alive) {
                panel.monster.alive = true;
                panel.monster.word = getRandomWord();
                System.out.println("Monster spawned");
            } else if (!panel.knight.alive) {
                panel.knight.alive = true;
                panel.knight.word = getRandomWord();
                System.out.println("Knight spawned");
            }
            lastSpawnTime = now;
        }
    }

    // Checks what entity is closest to player
    public static boolean[] entityClosest(Entity a, Entity b, Entity c) {
        double distToB = Math.abs(a.x - b.x);
        double distToC = Math.abs(a.x - c.x);
    
        boolean[] result = new boolean[2];
        if (distToB <= distToC) {
            result[0] = true;
        } else {
            result[1] = true;
        }
        return result;
    }


    public static void ifGameOver(GamePanel gp){
        gp.collision.checkEntity(gp.player, gp.monster); // when there are more monsters, create a loop that checks for every monster.
        gp.collision.checkEntity(gp.player, gp.knight);

        if (gp.player.collisionOn == true) {
            GameState.setGameState(GameState.GAMEOVER);
            reset(gp);
        }
    }

    public static void reset(GamePanel gp){
        gp.player.collisionOn = false;
        gp.monster.alive = false;
        gp.knight.alive = false;
        gp.monster.setDefaultValues();
        gp.knight.setDefaultValues();
        gp.first[0] = false;
        gp.first[1] = false;
        gp.timer = System.currentTimeMillis();
    }
}