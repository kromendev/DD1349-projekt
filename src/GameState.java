/**
 * Represents the current state of the game
 * 
 * @author Gustav Dyrcz
 * @author Husein Hassan
 * @version 2025-05-07
 */
public enum GameState {
    MENU,
    PLAY,
    CREDITS,
    PAUSE,
    GAMEOVER,
    QUIT;

    private static GameState currentState = MENU;

    /**
     * Getter method for current game state.
     * 
     * @return current game state
     */
    public static GameState getGameState() {
        return currentState;
    }

    /**
     * Sets the current game state.
     */
    public static void setGameState(GameState newState) {
        currentState = newState;
    }
}