package Interface;

public interface GameControl {
    /**
     * Start the game engine
     */
    void start();

    /**
     * Stops the game engine
     */
    void stop();

    /**
     * Resets the game state to the starting point
     */
    void reload();
}
