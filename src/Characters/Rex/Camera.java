package Characters.Rex;

public class Camera {
    private int x;
    private int y;

    public Camera(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update(Rex rex) {
        this.x = rex.getX() - (1280 / 2); // 1280 is the width of the window
        this.y = rex.getY() - (720 / 2); // 720 is the height of the window
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}