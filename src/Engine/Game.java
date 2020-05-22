package Engine;

import Component.Window;
import Control.Control;
import Element.*;
import Interface.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Game implements Playable, GameControl {
    private final GameLoop gameLoop;
    private final Window window;
    private final Player player;
    private final Ball ball;
    private final Enemy enemy;
    private final ArrayList<Element> elements;
    private Thread thread;
    private final int[] score;

    public Game(GameLoop gameLoop) {
        this.gameLoop = gameLoop;
        this.gameLoop.setPlayable(this);
        this.score = new int[]{0, 0};

        this.player = new Player(60, 115, 160);
        this.ball = new Ball(80, 59, 160);
        this.enemy = new Enemy(60, -5, 160, this.ball);

        this.elements = new ArrayList<>();
        this.elements.add(this.player);
        this.elements.add(this.ball);
        this.elements.add(this.enemy);

        this.window = new Window(
                "Pong",
                160,
                120,
                3,
                this.score,
                new Color(0, 0,0),
                this.elements,
                new Control(this.player)
        );
    }

    @Override
    public void tick() {
        if (this.ball.getY() >= 160) {
            if (this.score[0] == 5 || this.score[1] == 5) {
                this.stop();
            }

            this.score[1]++;
            this.reload();
        } else if (this.ball.getY() < 0) {
            if (this.score[0] == 5 || this.score[1] == 5) {
                this.stop();
            }

            this.score[0]++;
            this.reload();
        }

        for (Element element: this.elements) {
            element.tick();
        }

        Rectangle bounds = new Rectangle(
                (int) (this.ball.getX() + (this.ball.getDirectionX() * Ball.SPEED)),
                (int) (this.ball.getY() + (this.ball.getDirectionY() * Ball.SPEED)),
                this.ball.getWidth(),
                this.ball.getHeight()
        );

        Rectangle boundsPlayer = new Rectangle(
                this.player.getX(),
                this.player.getY(),
                this.player.getWidth(),
                this.player.getHeight()
        );

        Rectangle boundsEnemy = new Rectangle(
                this.enemy.getX(),
                this.enemy.getY(),
                this.enemy.getWidth(),
                this.enemy.getHeight()
        );

        this.redirectBall(bounds, boundsPlayer, '>');
        this.redirectBall(bounds, boundsEnemy, '<');
    }

    private void redirectBall(Rectangle bounds, Rectangle colliderBound, char operator) {
        if (bounds.intersects(colliderBound)) {
            int angle = new Random().nextInt(120 - 45) + 46;
            this.ball.setDirectionX(Math.cos(Math.toRadians(angle)));
            this.ball.setDirectionY(Math.sin(Math.toRadians(angle)));

            switch (operator) {
                case '>':
                    if (this.ball.getDirectionY() > 0) {
                        this.ball.reflect();
                    }
                    break;
                case '<':
                    if (this.ball.getDirectionY() < 0) {
                        this.ball.reflect();
                    }
                    break;
            }
        }
    }

    @Override
    public void render() {
        this.window.render();
    }

    @Override
    public synchronized void start() {
        this.thread = new Thread(this.gameLoop);
        this.thread.start();
    }

    @Override
    public synchronized void stop() {
        this.gameLoop.stopRun();

        try {
            this.thread.join();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public synchronized void reload() {
        for (Element element: this.elements) {
            element.returnInitialPoint();
        }
    }
}
