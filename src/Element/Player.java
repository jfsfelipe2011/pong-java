package Element;

import Interface.PongPlayer;

import java.awt.*;

public class Player implements PongPlayer {
    private Graphics graphics;
    private int x;
    private int y;
    private boolean right;
    private boolean left;
    private final int width;
    private final int height;
    private final int collider;
    private final int[] initialPoint;

    public Player(int x, int y, int collider) {
        this.graphics = null;
        this.x = x;
        this.y = y;
        this.collider = collider;

        this.width = 40;
        this.height = 10;
        this.right = false;
        this.left = false;

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
        if (this.right) {
            this.x++;
        } else if (this.left) {
            this.x--;
        }

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

    @Override
    public void moveRight() {
        this.right = true;
    }

    @Override
    public void stopRight() {
        this.right = false;
    }

    @Override
    public void moveLeft() {
        this.left = true;
    }

    @Override
    public void stopLeft() {
        this.left = false;
    }
}
