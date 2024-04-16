package Characters.Rex;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import inputs.KeyboardInputs;

public class Rex extends JPanel {
    private int xDelta = 0;
    private int yDelta = 420;
    private BufferedImage img;
    private BufferedImage[] idleAnimation;
    private BufferedImage[] walkingRightAnimation;
    private BufferedImage[] walkingLeftAnimation;
    private int aniIndex = 0;
    private final int aniSpeed = 40;

    private long lastUpdateTime = System.currentTimeMillis();
    private int playerAction = 1;
    private int playerDir = -1;
    private boolean moving = false;

    private Rectangle hitbox;

    public Rex() {

        setPanelSize();
        addKeyListener(new KeyboardInputs(this));

        setFocusable(true);
        requestFocusInWindow();

        // Load the idle animation
        loadIdleAnimation();
        loadRightWalkingAnimation();
        loadLeftWalkingAnimation();

        hitbox = new Rectangle(0, 0, 70, 110);

        new Timer(aniSpeed, e -> {
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - lastUpdateTime;
            lastUpdateTime = currentTime;

            // Calculate the new frame index based on the elapsed time
            aniIndex = (aniIndex + (int)(elapsedTime / aniSpeed)) % idleAnimation.length;
            repaint();
        }).start();
    }

    public void setCharacterPosition(int x, int y) {
        // Set the hitbox location
        hitbox.setLocation(x, y);

        // Set the character's position relative to the hitbox
        this.setBounds(hitbox.x, hitbox.y, this.getWidth(), this.getHeight());
    }

    public void setPosition(int x, int y) {
        this.setBounds(x, y, this.getWidth(), this.getHeight());
        hitbox.setLocation(x, y); // Move the hitbox 20 pixels to the left
    }
    private void loadIdleAnimation() {
        idleAnimation = loadAnimationFrames("/characters/Rex/Idle/Character-Idle_", "png", 20);
    }

    private void loadRightWalkingAnimation() {
        walkingRightAnimation = loadAnimationFrames("/characters/Rex/Walk/Character-Walk_", "png", 30);
    }

    private void loadLeftWalkingAnimation() {
        walkingLeftAnimation = loadAnimationFrames("/characters/Rex/LeftWalk/Character-Walk_", "png", 30);
    }
    private BufferedImage[] loadAnimationFrames(String path, String fileType, int frameCount) {
        BufferedImage[] frames = new BufferedImage[frameCount];
        for (int i = 0; i < frameCount; i++) {
            String fileName = String.format("%s%02d.%s", path, i, fileType);
            //System.out.println(fileName);
            try {
                frames[i] = ImageIO.read(getClass().getResourceAsStream(fileName));
            } catch (IOException e) {
                System.out.println("Error loading file: " + fileName);
                e.printStackTrace();
            }
        }
        return frames;
    }


    @Override
    public void doLayout() {
        for (Component c : getComponents()) {
            c.setBounds(0, 0, getWidth(), getHeight());
        }
    }


    private void setPanelSize() {
        // Set the size of the Rex JPanel to the size of the character
        setPreferredSize(new Dimension(188, 188));
        setSize(new Dimension(188, 188));
    }

    private void setAnimation(){
        if (moving)
            playerAction = Constants.PlayerConstants.WALKKING;
        else
            playerAction = Constants.PlayerConstants.IDLE;
    }

    private void updatePos() {
        if (moving){
            switch (playerDir){
                case Constants.Directions.LEFT:
                    changeXDelta(-3);
                    break;
                case Constants.Directions.RIGHT:
                    changeXDelta(3);
                    break;
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        setAnimation();
        updatePos();

        BufferedImage[] currentAnimation;
        if (playerAction == Constants.PlayerConstants.WALKKING) {
            if (playerDir == Constants.Directions.RIGHT) {
                currentAnimation = walkingRightAnimation;
            } else {
                currentAnimation = walkingLeftAnimation;
            }
        } else {
            currentAnimation = idleAnimation;
        }

        // Draw the hitbox
        g.setColor(Color.RED); // Set the color to red
        g.drawRect(xDelta + 90, yDelta + 90, hitbox.width, hitbox.height); // Draw the hitbox at the desired offset

        // Draw the Rex character
        g.drawImage(currentAnimation[aniIndex], xDelta, yDelta, 264, 264, null);
    }
    public void changeXDelta(int value) {
        xDelta += value;
        repaint();
    }

    public void setDirection(int direction) {
        this.playerDir = direction;
        moving = true;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }


}