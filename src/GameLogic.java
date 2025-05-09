import java.util.Random;

/**
 * This class contains game logic mechanics such as:
 * Spawning, word generation, high score tracking and game state management.
 * 
 * @author Gustav Dyrcz
 * @version 2025-05-09
 */
public class GameLogic {
    private static final Random rand = new Random();
    private static long gamestart = System.currentTimeMillis();
    private static long timer = System.currentTimeMillis();
    
    private static final String HIGH_SCORE_FILE = "Src/Data/highscore.txt";
    private static int highScore;

    // word index for the entitys
    private static final String[] words = {
        "sword", "knight", "castle", "shield", "dragon",
        "battle", "armor", "quest", "honor", "lance", 
        "apple", "banana", "car", "dog", "elephant",
        "flower", "guitar", "house", "ice", "jungle",
        "kite", "lamp", "mountain", "notebook", "ocean",
        "pencil", "queen", "river", "sun", "tree",
        "umbrella", "violin", "window", "xylophone", "zebra"
    };
    
    /**
     * Loads the high score from file.
     * 
     * @return the current high score
     */
    public static int getHighScore() {
        try (java.util.Scanner scanner = new java.util.Scanner(new java.io.File(HIGH_SCORE_FILE))) {
            highScore = scanner.nextInt();
        } catch (Exception e) {
            highScore = 0;
        }
        return highScore;
    }

    /**
     * Checks if the current score is higher than the saved high score.
     * If true updates and saves to file.
     * 
     * @param currentScore the score to compare and save if higher
     */
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

    /**
     * Resets the high score and saves to file.
     */
    public static void resetHighScore() {
        highScore = 0;
        try (java.io.PrintWriter writer = new java.io.PrintWriter(HIGH_SCORE_FILE)) {
            writer.println(highScore);
        } catch (Exception e) {
            System.out.println("Failed to save high score");
        }
    }

    /**
     * Returns a random word from the word index.
     * 
     * @return a random word
     */
    public static String getRandomWord() {
        int index = rand.nextInt(words.length);
        return words[index];
    }

    /**
     * Spawns a monster or knight on a timer.
     * Knight only starts spawning 5 seconds after the game started.
     * 
     * @param panel the GamePanel where entities are spawned
     */
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

    /**
     * Determines which of the two entities is closer to the player.
     * 
     * @param a the player entity
     * @param b the first enemy (monster)
     * @param c the second enemy (knight)
     * @return a boolean array where [0] is true if b is closer, [1] if c is closer
     */
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

    /**
     * Checks if the player has collided with an enemy and ends the game if true.
     * 
     * @param gp the game panel
     */
    public static void ifGameOver(GamePanel gp){
        gp.collision.checkEntity(gp.player, gp.monster); // when there are more monsters, create a loop that checks for every monster.
        gp.collision.checkEntity(gp.player, gp.knight);

        if (gp.player.collisionOn == true) {
            GameState.setGameState(GameState.GAMEOVER);
            reset(gp);
        }
    }

    /**
     * Resets the game state, player and enemy positions, and timers.
     * 
     * @param gp the game panel to reset
     */
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