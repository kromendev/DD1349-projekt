import java.util.Random;

public class GameLogic {

    private static final String[] words = {
        "sword", "knight", "castle", "shield", "dragon",
        "battle", "armor", "quest", "honor", "lance", 
        "apple", "banana", "car", "dog", "elephant",
        "flower", "guitar", "house", "ice", "jungle",
        "kite", "lamp", "mountain", "notebook", "ocean",
        "pencil", "queen", "river", "sun", "tree",
        "umbrella", "violin", "window", "xylophone", "zebra"
    };

    private static final Random rand = new Random();

    public static String getRandomWord() {
        int index = rand.nextInt(words.length);
        return words[index];
    }

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
}