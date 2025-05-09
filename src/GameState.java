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

    // Variable holding current game state
    private static GameState currentState = MENU;

    /**
     * Returns the current game state.
     * 
     * @return the current GameState
     */
    public static GameState getGameState() {
        return currentState;
    }

    /**
     * Sets the current game state.
     * 
     * @param newState the new GameState to apply
     */
    public static void setGameState(GameState newState) {
        currentState = newState;
    }
}