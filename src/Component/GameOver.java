package Component;

import Interface.Component;

import java.awt.*;

public class GameOver implements Component {
    private final int[] score;
    private final Graphics graphics;

    public GameOver(Graphics graphics, int[] score) {
        this.graphics = graphics;
        this.score = score;
    }

    @Override
    public void render() {
        graphics.setFont(new Font("Arial", Font.BOLD, 8));

        if (this.score[1] == 5) {
            graphics.drawString("Que pena você perdeu!!", 38, 60);
        } else if (this.score[0] == 5) {
            graphics.drawString("Parabéns, você ganhou!!", 33, 60);
        }
    }
}
