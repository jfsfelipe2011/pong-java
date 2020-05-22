package Component;

import Engine.Game;
import Interface.Component;
import Interface.Element;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Window extends Canvas implements Component {
    public static JFrame frame;

    private final BufferedImage image;
    private final Color color;
    private final String name;
    private final int width;
    private final int height;
    private final int scale;
    private final ArrayList<Element> elements;
    private final int[] score;

    public Window(
            String name,
            int width,
            int height,
            int scale,
            int[] score,
            Color color,
            ArrayList<Element> elements,
            KeyListener keyListener
    ) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.scale = scale;
        this.color = color;
        this.elements = elements;
        this.score = score;

        this.setPreferredSize(new Dimension(
                this.width * this.scale,
                this.height * this.scale
        ));

        this.initFrame();
        this.image = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
        this.addKeyListener(keyListener);
        this.requestFocus();
    }

    private void initFrame() {
        frame = new JFrame(this.name);
        frame.add(this);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void render() {
        BufferStrategy bufferStrategy = this.getBufferStrategy();

        if (bufferStrategy == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics graphics = image.getGraphics();
        graphics.setColor(color);
        graphics.fillRect(0, 0, this.width, this.height);

        GameOver gameOver = new GameOver(graphics, this.score);
        Score scoreComponent = new Score(graphics, this.score);
        scoreComponent.render();

        if (this.score[1] == 5) {
            gameOver.render();
        } else if (this.score[0] == 5) {
            gameOver.render();
        } else {
            renderElements(graphics);
        }


        graphics.dispose();
        graphics = bufferStrategy.getDrawGraphics();
        graphics.drawImage(
                image,
                0,
                0,
                this.width * this.scale,
                this.height * this.scale,
                null
        );

        bufferStrategy.show();
    }

    private void renderElements(Graphics graphics) {
        for (Element element: this.elements) {
            element.setGraphics(graphics);

            element.render();
        }
    }
}
