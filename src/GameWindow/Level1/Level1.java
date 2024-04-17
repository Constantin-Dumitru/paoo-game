package GameWindow.Level1;

import Characters.Rex.*;

import javax.swing.*;
import java.awt.*;

public class Level1 extends JLayeredPane {

    private Camera camera;
    private GameMap gameMap;

    private Rex rex;
    public Level1() {
        setLayout(null);

        // Încarcă imaginea de fundal
        ImageIcon backgroundIcon = new ImageIcon("shared/backgrounds/level1-background.png"); // înlocuiește cu calea către imaginea ta
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setBounds(0, 0, backgroundIcon.getIconWidth(), backgroundIcon.getIconHeight());

        // Adaugă eticheta de fundal la panou
        this.add(backgroundLabel, Integer.valueOf(-1)); // adaugă la un strat inferior

        rex = new Rex();
        rex.setFocusable(true);
        rex.requestFocusInWindow();
        rex.setOpaque(false);

        // Set the character's position based on the hitbox's position
        rex.setCharacterPosition(100, 200); // replace 100, 200 with the desired x and y coordinates

        camera = new Camera(rex, 0, 0); // Initialize camera with placeholder width and height
        gameMap = new GameMap("shared/map/sample.txt", "shared/map/tiledata.txt", "shared/map/tiles", camera);
        camera.setMapWidth(gameMap.getMapWidth()); // Update width and height
        camera.setMapHeight(gameMap.getMapHeight());
        gameMap.setOpaque(false);


        // Add the GameMap object first
        this.add(gameMap, Integer.valueOf(1));

        this.add(rex, Integer.valueOf(2));

        this.setVisible(true);
        gameLoop();
    }

    public void gameLoop() {
        new Thread(() -> {
            while (true) {
                camera.update();
                gameMap.repaint();
                try {
                    Thread.sleep(1000 / 60); // 60 FPS
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void doLayout() {
        for (Component c : getComponents()) {
            c.setBounds(0, 0, getWidth(), getHeight());
        }
    }
}