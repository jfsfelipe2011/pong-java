package Original;

import java.awt.*;

public class Enemy {
    private double x;
    private double y;
    private final int width = 40;
    private final int height = 10;

    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void tick(Ball ball) {
        this.x += (ball.getX() - this.x - 6) * 0.07;

        if (this.x + this.width > Game.WIDTH) {
            this.x = Game.WIDTH - this.width;
        } else if (this.x < 0) {
            this.x = 0;
        }
    }

    public void render(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillRect((int) this.x, (int) this.y, this.width, this.height);
    }
}
