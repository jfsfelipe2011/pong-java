package Element;

import Interface.Element;

import java.awt.*;

public class Enemy implements Element {
    private Graphics graphics;
    private int x;
    private int y;
    private final int width;
    private final int height;
    private final int collider;
    private final int[] initialPoint;
    private final Ball ball;

    public Enemy(int x, int y, int collider, Ball ball) {
        this.graphics = null;
        this.x = x;
        this.y = y;
        this.collider = collider;
        this.ball = ball;

        this.width = 40;
        this.height = 10;

        this.initialPoint = new int[]{x, y};
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public void returnInitialPoint() {
        this.x = this.initialPoint[0];
        this.y = this.initialPoint[1];
    }

    @Override
    public void setGraphics(Graphics graphics) {
        this.graphics = graphics;
    }

    @Override
    public void tick() {
        this.x += (this.ball.getX() - this.x - 6) * 0.03;

        if (this.x + this.width > this.collider) {
            this.x = this.collider - this.width;
        } else if (this.x < 0) {
            this.x = 0;
        }
    }

    @Override
    public void render() {
        if (this.graphics != null) {
            this.graphics.setColor(Color.WHITE);
            this.graphics.fillRect(this.x, this.y, this.width, this.height);
        }
    }
}
