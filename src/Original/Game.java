package Original;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable, KeyListener {
    public static JFrame frame;

    public static final int WIDTH = 160;
    public static final int HEIGHT = 120;
    private final int SCALE = 3;

    private Thread thread;
    private boolean isRunning;
    private final BufferedImage image;
    private Player player;
    private Enemy enemy;
    private Ball ball;

    private int playerScore;
    private int enemyScore;

    public Game() {
        this.setPreferredSize(new Dimension(
                WIDTH * this.SCALE,
                HEIGHT * this.SCALE
        ));

        this.initFrame();
        this.addKeyListener(this);
        this.image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        this.player = new Player(60, HEIGHT - 5);
        this.enemy = new Enemy(60, -5);
        this.ball = new Ball(80, (HEIGHT / 2) - 1);
        this.playerScore = 0;
        this.enemyScore = 0;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public int getEnemyScore() {
        return enemyScore;
    }

    public void initFrame() {
        frame = new JFrame("Pong");
        frame.add(this);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public synchronized void start() {
        this.isRunning = true;
        this.thread = new Thread(this);
        this.thread.start();
    }

    public synchronized void stop() {
        this.isRunning = false;

        try {
            this.thread.join();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    public synchronized void reload() {
        this.ball = new Ball(80, (HEIGHT / 2) - 1);
        this.player = new Player(60, HEIGHT - 5);
        this.enemy = new Enemy(60, -5);
    }

    /*public static void main(String[] args) {
        Original.Game game = new Original.Game();
        game.start();
    }*/

    public void tick() {
        this.player.tick();
        this.enemy.tick(this.ball);
        this.ball.tick(this.player, this.enemy, this);
    }

    public void render() {
        BufferStrategy bufferStrategy = this.getBufferStrategy();

        if (bufferStrategy == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics graphics = image.getGraphics();
        graphics.setColor(new Color(0, 0, 0));
        graphics.fillRect(0, 0, WIDTH, HEIGHT);

        Font font = new Font("Arial", Font.BOLD, 14);
        graphics.setFont(font);
        graphics.setColor(Color.white);
        graphics.drawString(String.valueOf(this.playerScore), 5, 60);
        graphics.drawString(String.valueOf(this.enemyScore), 148, 60);

        if (this.enemyScore == 5) {
            font = new Font("Arial", Font.BOLD, 8);
            graphics.setFont(font);
            graphics.drawString("Que pena você perdeu!!", 38, 60);
        } else if (this.playerScore == 5) {
            font = new Font("Arial", Font.BOLD, 8);
            graphics.setFont(font);
            graphics.drawString("Parabéns, você ganhou!!", 33, 60);
        } else {
            this.player.render(graphics);
            this.enemy.render(graphics);
            this.ball.render(graphics);
        }

        graphics.dispose();
        graphics = bufferStrategy.getDrawGraphics();
        graphics.drawImage(
                this.image,
                0,
                0,
                WIDTH * this.SCALE,
                HEIGHT * this.SCALE,
                null
        );

        bufferStrategy.show();
    }

    @Override
    public void run() {
        this.requestFocus();

        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;

        int frames = 0;
        double timer = System.currentTimeMillis();

        while (this.isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            if (delta >= 1) {
                this.tick();
                this.render();
                delta--;

                frames++;
            }

            if (System.currentTimeMillis() - timer >= 1000) {
                System.out.println("FPS: " + frames);
                frames = 0;
                timer += 1000;
            }
        }

        this.stop();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.player.moveRight();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            this.player.moveLeft();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.player.stopRight();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            this.player.stopLeft();
        }
    }

    public void incrementScore(boolean player) {
        if (player) {
            this.playerScore++;
            return;
        }

        this.enemyScore++;
    }
}
