/**
 * Represents the current state of the game.
 */
public enum GameState {
    MENU,
    PLAY,
    SETTINGS,
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