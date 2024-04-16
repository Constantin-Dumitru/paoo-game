package GameWindow;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class KeyHandler implements KeyListener{

    public boolean leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_D){
            rightPressed = true;
            System.out.println("D apăsat. rightPressed = true");
        }

        if (code == KeyEvent.VK_A){
            leftPressed = true;
            System.out.println("A apăsat. leftPressed = true");

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_A){
            leftPressed = false;
            System.out.println("A apăsat. leftPressed = false");
        }

        if (code == KeyEvent.VK_D){
            rightPressed = false;
            System.out.println("A apăsat. rightPressed = false");
        }
    }
}
