package Original;

import java.awt.*;
import java.util.Random;

public class Ball {
    private double x;
    private double y;
    private final int width = 4;
    private final int height = 4;
    private double directionX;
    private double directionY;
    private final double speed = 1.8;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;

        this.directionX = Math.cos(Math.toRadians(90));
        this.directionY = Math.sin(Math.toRadians(90));
    }

    public double getX() {
        return this.x;
    }

    public void tick(Player player, Enemy enemy, Game game) {
        if (this.x + (this.directionX * this.speed) + this.width >= Game.WIDTH) {
            this.directionX *= -1;
        } else if (this.x + (this.directionX * this.speed) < 0) {
            this.directionX *= -1;
        }

        if (this.y >= Game.HEIGHT) {
            if (game.getEnemyScore() == 5 || game.getPlayerScore() == 5) {
                game.stop();
            }

            game.incrementScore(false);
            game.reload();
            return;
        } else if (this.y < 0) {
            if (game.getEnemyScore() == 5 || game.getPlayerScore() == 5) {
                game.stop();
            }

            game.incrementScore(true);
            game.reload();
            return;
        }

        Rectangle bounds = new Rectangle(
                (int) (this.x + (this.directionX * this.speed)),
                (int) (this.y + (this.directionY * this.speed)),
                this.width,
                this.height
        );

        Rectangle boundsPlayer = new Rectangle(
                player.getX(),
                player.getY(),
                player.getWidth(),
                player.getHeight()
        );

        Rectangle boundsEnemy = new Rectangle(
                (int) enemy.getX(),
                (int) enemy.getY(),
                enemy.getWidth(),
                enemy.getHeight()
        );

        if (bounds.intersects(boundsPlayer)) {
            int angle = new Random().nextInt(120 - 45) + 46;
            this.directionX = Math.cos(Math.toRadians(angle));
            this.directionY = Math.sin(Math.toRadians(angle));

            if (directionY > 0) {
                this.directionY *= -1;
            }
        } else if (bounds.intersects(boundsEnemy)) {
            int angle = new Random().nextInt(120 - 45) + 46;
            this.directionX = Math.cos(Math.toRadians(angle));
            this.directionY = Math.sin(Math.toRadians(angle));

            if (directionY < 0) {
                this.directionY *= -1;
            }
        }

        this.x += this.directionX * this.speed;
        this.y += this.directionY * this.speed;
    }

    public void render(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillRect((int) this.x, (int) this.y, this.width, this.height);
    }
}
