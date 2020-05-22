package Interface;

public interface PongPlayer extends Element {
    /**
     * Move player right
     */
    public void moveRight();

    /**
     * Stop moving player right
     */
    public void stopRight();

    /**
     * Move player left
     */
    public void moveLeft();

    /**
     * Stop moving player left
     */
    public void stopLeft();
}
