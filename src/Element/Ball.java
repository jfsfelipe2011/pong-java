package Element;

import Interface.Element;

import java.awt.*;

public class Ball implements Element {
    public static double SPEED = 1.8;

    private Graphics graphics;
    private int x;
    private int y;
    private final int width;
    private final int height;
    private double directionX;
    private double directionY;
    private final int collider;
    private final int[] initialPoint;
    private final double[] initialDirection;

    public Ball(int x, int y, int collider) {
        this.graphics = null;
        this.x = x;
        this.y = y;
        this.collider = collider;

        this.width = 4;
        this.height = 4;
        this.directionX = Math.cos(Math.toRadians(90));
        this.directionY = Math.sin(Math.toRadians(90));

        this.initialPoint = new int[]{x, y};
        this.initialDirection = new double[]{directionX, directionY};
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

    public double getDirectionX() {
        return directionX;
    }

    public double getDirectionY() {
        return directionY;
    }

    public void setDirectionX(double directionX) {
        this.directionX = directionX;
    }

    public void setDirectionY(double directionY) {
        this.directionY = directionY;
    }

    public void reflect() {
        this.directionY *= -1;
    }

    @Override
    public void returnInitialPoint() {
        this.x = this.initialPoint[0];
        this.y = this.initialPoint[1];
        this.directionX = this.initialDirection[0];
        this.directionY = this.initialDirection[1];
    }

    @Override
    public void setGraphics(Graphics graphics) {
        this.graphics = graphics;
    }

    @Override
    public void tick() {
        double direction = this.x + (this.directionX * SPEED);

        if (direction + this.width >= this.collider || direction < 0) {
            this.directionX *= -1;
        }

        this.x += this.directionX * SPEED;
        this.y += this.directionY * SPEED;
    }

    @Override
    public void render() {
        if (this.graphics != null) {
            this.graphics.setColor(Color.WHITE);
            this.graphics.fillRect(this.x, this.y, this.width, this.height);
        }
    }
}
