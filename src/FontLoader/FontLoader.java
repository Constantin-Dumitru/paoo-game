package FontLoader;

import java.awt.*;
import java.io.*;
import java.awt.font.*;

public class FontLoader {
    public static Font loadFont(float size) {
        try {
            Font burbank = Font.createFont(Font.TRUETYPE_FONT, new File("shared/font/burbank.ttf"));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("shared/font/burbank.ttf")));
            return burbank;
        }
        catch (IOException | FontFormatException e){
            System.out.println(e);
            return Font.getFont("Arial");
        }
    }
}
