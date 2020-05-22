package Interface;

public interface GameLoop extends Runnable{
    /**
     * Method that ends the game loop
     */
    void stopRun();

    /**
     * set a playable class to the game loop context
     *
     * @param playable class plauable
     */
    void setPlayable(Playable playable);
}
