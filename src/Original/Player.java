package Original;

import java.awt.*;

public class Player {
    private int x;
    private final int y;
    private boolean right;
    private boolean left;
    private final int width = 40;
    private final int height = 10;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        this.right = false;
        this.left = false;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void moveRight() {
        this.right = true;
    }

    public void stopRight() {
        this.right = false;
    }

    public void moveLeft() {
        this.left = true;
    }

    public void stopLeft() {
        this.left = false;
    }

    public void tick() {
        if (this.right) {
            this.x++;
        } else if (this.left) {
            this.x--;
        }

        if (this.x + this.width > Game.WIDTH) {
            this.x = Game.WIDTH - this.width;
        } else if (this.x < 0) {
            this.x = 0;
        }
    }

    public void render(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(this.x, this.y, this.width, this.height);
    }
}
