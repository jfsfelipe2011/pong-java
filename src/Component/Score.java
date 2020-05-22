package Component;

import Interface.Component;

import java.awt.*;

public class Score implements Component {
    private final int[] score;
    private final Graphics graphics;

    public Score(Graphics graphics, int[] score) {
        this.graphics = graphics;
        this.score = score;
    }

    @Override
    public void render() {
        graphics.setFont(new Font("Arial", Font.BOLD, 14));
        graphics.setColor(Color.white);
        graphics.drawString(String.valueOf(this.score[0]), 5, 60);
        graphics.drawString(String.valueOf(this.score[1]), 148, 60);
    }
}
