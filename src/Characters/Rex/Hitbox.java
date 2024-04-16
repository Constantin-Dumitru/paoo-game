package Characters.Rex;

import javax.swing.*;
import java.awt.*;

public class Hitbox extends JPanel {
    public Hitbox(int x, int y, int width, int height) {
        setBounds(x, y, width, height);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
    }
}
