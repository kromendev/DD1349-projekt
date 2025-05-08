import java.util.Random;

public class GameLogic {
    private static final Random rand = new Random();
    private static long gamestart = System.currentTimeMillis();
    private static long timer = System.currentTimeMillis();
    
    private static final String HIGH_SCORE_FILE = "Src/Data/highscore.txt";
    private static int highScore = getHighScore(); // initialize when class loads
    
    
    public static int getHighScore() {
        if (highScore == 0) {
            try (java.util.Scanner scanner = new java.util.Scanner(new java.io.File(HIGH_SCORE_FILE))) {
                highScore = scanner.nextInt();
            } catch (Exception e) {
                highScore = 0; // if file not found or invalid
            }
        }
        return highScore;
    }

    public static void checkAndSaveHighScore(int currentScore) {
        if (currentScore > highScore) {
            highScore = currentScore;
            try (java.io.PrintWriter writer = new java.io.PrintWriter(HIGH_SCORE_FILE)) {
                writer.println(highScore);
            } catch (Exception e) {
                System.out.println("Failed to save high score");
            }
        }
    }

    public static void resetHighScore() {
        highScore = 0;
        try (java.io.PrintWriter writer = new java.io.PrintWriter(HIGH_SCORE_FILE)) {
            writer.println(highScore);
        } catch (Exception e) {
            System.out.println("Failed to save high score");
        }
    }



    //word bank for the entitys
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

    // Spawns A random entity every 3 seconds
    public static void entityRandomSpawn(GamePanel panel) {
        long now = System.currentTimeMillis();
        if (now - timer >= 1500) {
            if (now - gamestart >= 5000) {
                if (Math.random() < 0.5 && !panel.monster.alive) {
                    panel.monster.alive = true;
                    panel.monster.word = getRandomWord();
                } else if (!panel.knight.alive) { 
                    panel.knight.alive = true;
                    panel.knight.word = getRandomWord();
                }
            } else {
                if (!panel.monster.alive) {
                    panel.monster.alive = true;
                    panel.monster.word = getRandomWord();
                }
            }
            timer = now;
        }
    }

    // Checks what entity is closest to player
    public static boolean[] entityClosest(Entity a, Entity b, Entity c) {
        double distToB = Math.abs(a.x - b.x);
        double distToC = Math.abs(a.x - c.x);
    
        boolean[] result = new boolean[2];
        if (distToB <= distToC) {
            result[0] = true;
            result[1] = false;
        } else {
            result[0] = false;
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
        gp.player.x = -gp.tileSize;
        gp.first[0] = false;
        gp.first[1] = false;
        gp.timer = System.currentTimeMillis();
        timer = System.currentTimeMillis();
        gp.killed = 0;
    }
}