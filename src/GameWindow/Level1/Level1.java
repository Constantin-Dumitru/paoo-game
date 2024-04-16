package GameWindow.Level1;

import Characters.Rex.Rex;

import javax.swing.*;
import java.awt.*;

public class Level1 extends JLayeredPane {

    public Level1() {
        setLayout(null);

        // Încarcă imaginea de fundal
        ImageIcon backgroundIcon = new ImageIcon("shared/backgrounds/level1-background.png"); // înlocuiește cu calea către imaginea ta
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setBounds(0, 0, backgroundIcon.getIconWidth(), backgroundIcon.getIconHeight());

        // Adaugă eticheta de fundal la panou
        this.add(backgroundLabel, Integer.valueOf(-1)); // adaugă la un strat inferior

        Rex rex = new Rex();
        rex.setFocusable(true);
        rex.requestFocusInWindow();
        rex.setOpaque(false);

        // Set the character's position based on the hitbox's position
        rex.setCharacterPosition(100, 200); // replace 100, 200 with the desired x and y coordinates

        GameMap gameMap = new GameMap("C:\\Users\\Costi\\Desktop\\JocPaoo\\shared\\map\\sample.txt", "C:\\Users\\Costi\\Desktop\\JocPaoo\\shared\\map\\tiledata.txt", "C:\\Users\\Costi\\Desktop\\JocPaoo\\shared\\map\\tiles");
        gameMap.setOpaque(false);

        // Add the GameMap object first
        this.add(gameMap, Integer.valueOf(1));

        this.add(rex, Integer.valueOf(2));

        this.setVisible(true);
    }

    @Override
    public void doLayout() {
        for (Component c : getComponents()) {
            c.setBounds(0, 0, getWidth(), getHeight());
        }
    }
}