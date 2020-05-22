import Engine.Game;
import Engine.GameLoop.SixtyFPSGameLoop;

public class Main {
    public static void main(String[] args) {
        Game game = new Game(
                new SixtyFPSGameLoop()
        );
        game.start();
    }
}
