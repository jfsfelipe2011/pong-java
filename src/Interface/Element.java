package Interface;

import java.awt.*;

public interface Element extends Playable {
    /**
     * Return position x
     *
     * @return int
     */
    int getX();

    /**
     * Return position y
     *
     * @return int
     */
    int getY();

    /**
     * Return width
     *
     * @return int
     */
    int getWidth();

    /**
     * Return height
     *
     * @return int
     */
    int getHeight();

    /**
     * Returns to the initial position set at startup
     */
    void returnInitialPoint();

    /**
     * Set the render graphic object
     */
    void setGraphics(Graphics graphics);
}
