package Characters.Rex;

public class Camera {
    private int x, y;
    private int mapWidth, mapHeight;
    private Rex rex;

    public Camera(Rex rex, int mapWidth, int mapHeight) {
        this.rex = rex;
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
    }

    public void update() {
        x = rex.getX() - 640; // 640 is half the width of the screen
        y = rex.getY() - 360; // 360 is half the height of the screen

        // Ensure the camera doesn't go out of bounds (assuming mapWidth and mapHeight are the dimensions of your map)
        x = Math.max(0, Math.min(x, mapWidth - 1280));
        y = Math.max(0, Math.min(y, mapHeight - 720));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setMapWidth(int mapWidth) {
        this.mapWidth = mapWidth;
    }

    public void setMapHeight(int mapHeight) {
        this.mapHeight = mapHeight;
    }














}