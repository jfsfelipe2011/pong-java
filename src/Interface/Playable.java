package Interface;

public interface Playable {
    /**
     * Method that must perform all the logical part of the playable class.
     */
    void tick();

    /**
     * Method responsible for graphical rendering of the playable class
     */
    void render();
}
