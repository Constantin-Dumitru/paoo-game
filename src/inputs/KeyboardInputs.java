package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import Characters.Rex.Rex;

public class KeyboardInputs implements KeyListener {
    private Rex rex;

    public KeyboardInputs(Rex rex) {
        this.rex = rex;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_A:
                rex.setDirection(0);
                break;
            case KeyEvent.VK_D:
                rex.setDirection(1);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_A:
            case KeyEvent.VK_D:
                rex.setMoving(false);
                break;
        }
    }
}
