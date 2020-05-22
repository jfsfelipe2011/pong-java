package Control;

import Interface.PongPlayer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Control implements KeyListener {
    private final PongPlayer player;

    public Control(PongPlayer player) {
        this.player = player;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new NotImplementedException();
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
}
