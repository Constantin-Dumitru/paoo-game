package GameWindow.Level1;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.imageio.ImageIO;
import Characters.Rex.Camera;

public class GameMap extends JPanel {

    private Camera camera;
    private int[][] map;
    private int height;
    private int width;
    private List<BufferedImage> tilesetImages = new ArrayList<>();

    // Define your custom tile width and height
    private int tileWidth = 44; // replace with your custom tile width
    private int tileHeight = 44; // replace with your

    public GameMap(String mapFilename, String tileDataFilename, String tilesFolder, Camera camera) {
        this.camera = camera;
        try {
            loadMap(mapFilename);
            loadTileset(tileDataFilename, tilesFolder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadMap(String filename) throws IOException {
        List<String[]> lines = new ArrayList<>();
        Scanner scanner = new Scanner(new File(filename));

        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().trim().split(" ");
            lines.add(line);
        }
        scanner.close();

        height = lines.size();
        width = lines.get(0).length;

        map = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                map[i][j] = Integer.parseInt(lines.get(i)[j]);
            }
        }
    }

    private void loadTileset(String tileDataFilename, String tilesFolder) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(tileDataFilename));
        for (int i = 0; i < lines.size(); i += 2) {
            String line = lines.get(i);
            Path imagePath = Paths.get(tilesFolder, line);
            System.out.println("Attempting to load tile from path: " + imagePath.toString());
            BufferedImage image = ImageIO.read(imagePath.toFile());
            System.out.println("Loaded tile: " + line);
            tilesetImages.add(image);
        }
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int startX = Math.max(0, camera.getX() / tileWidth);
        int startY = Math.max(0, camera.getY() / tileHeight);
        int endX = Math.min(width, (camera.getX() + 1280) / tileWidth);
        int endY = Math.min(height, (camera.getY() + 720) / tileHeight);
        for (int i = startY; i < endY; i++) {
            for (int j = startX; j < endX; j++) {
                int tile = map[i][j];
                if (tile < tilesetImages.size()) {
                    g2d.drawImage(tilesetImages.get(tile), j * tileWidth - camera.getX(), i * tileHeight - camera.getY(), tileWidth, tileHeight, null);
                } else {
                    System.out.println("Tile value is out of bounds: " + tile);
                }
            }
        }
    }

    public int getMapWidth() {
        return width * tileWidth;
    }

    public int getMapHeight() {
        return height * tileHeight;
    }

}