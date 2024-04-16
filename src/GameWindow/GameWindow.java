package GameWindow;

import GameWindow.Level1.Level1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;

public class GameWindow extends JFrame {

    public GameWindow() {
        setTitle("Game Menu");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        this.setVisible(true);

        ImageIcon backgroundImage = new ImageIcon("shared/backgrounds/background-menu.png");

        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(new GridBagLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        Font burbank;

        try {
            InputStream is = Font.class.getResourceAsStream("shared/font/burbank.ttf");
            burbank = Font.createFont(Font.TRUETYPE_FONT, new File("shared/font/burbank.ttf"));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("shared/font/burbank.ttf")));
            burbank = burbank.deriveFont(Font.PLAIN, 25);
        } catch (IOException | FontFormatException e) {
            burbank = Font.getFont("Arial");
        }

        Color color = new Color(54, 48, 59);
        Icon icon = new ImageIcon("shared/backgrounds/background-button.png");

        JButton playButton = createButton("Play", burbank, icon, color);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Schimbăm conținutul ferestrei la pagina de joc
                getContentPane().removeAll(); // Eliminăm toate componentele existente
                getContentPane().add(new Level1()); // Adăugăm pagina de joc
                revalidate(); // Revalidăm layout-ul
                repaint(); // Redeschidem fereastra
            }
        });
        buttonPanel.add(playButton);

        JButton optionsButton = createButton("Options", burbank, icon, color);
        optionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Butonul Options a fost apăsat.");
            }
        });
        buttonPanel.add(optionsButton);

        JButton exitButton = createButton("Exit", burbank, icon, color);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonPanel.add(exitButton);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(100, 0, 0, 0); // Margine de sus pentru meniu
        backgroundLabel.add(buttonPanel, gbc);

        getContentPane().add(backgroundLabel);
        getContentPane().setBackground(Color.BLACK);

        setVisible(true);

        this.requestFocusInWindow();

    }

    private JButton createButton(String text, Font font, Icon icon, Color color) {
        JButton button = new JButton(text);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.CENTER);
        button.setForeground(color);
        button.setFont(font);
        button.setIcon(icon);
        button.setAlignmentX(Component.CENTER_ALIGNMENT); // Aliniem butoanele pe centru orizontal
        button.setPreferredSize(new Dimension(300, 95));
        return button;
    }
}
