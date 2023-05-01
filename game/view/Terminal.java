package game.view;

import javax.swing.*;

import game.controller.InputManager;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.stream.Stream;

class Terminal extends JFrame {

    private final JLabel[][] screen;

    private Drawable screenBuffer;

    Terminal(int width, int height) {
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.addKeyListener(InputManager.instance);

        screen = new JLabel[width][height];
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setBounds(0, 0, width * 10, height * 18);
        panel.setLayout(new GridLayout(height, width));
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                JLabel l = new JLabel();
                l.setFont(new Font("Courier new", 0, 15));
                panel.add(l);
                l.setForeground(Color.WHITE);
                screen[x][y] = l;
            }
        }

        add(panel);
        setSize(panel.getSize());
        setVisible(true);
    }

    void redraw() {
        Stream<JLabel> labels = Stream.of(screen).flatMap(s -> Stream.of(s));
        labels.forEach(l -> l.setText(""));
        drawContent();
    }

    private void setChar(int x, int y, String c) {
        screen[x][y].setText(c);
    }

    private void setChar(int x, int y, char c) {
        setChar(x, y, c + "");
    }

    void setContent(Drawable content) {
        this.screenBuffer = content;
        drawContent();
    }

    private void drawContent() {
        if (screenBuffer == null) return;

        DrawCommand dc = screenBuffer.getDrawCommand();
        dc.getStream().forEach((c) -> {
            setChar(c.x, c.y, c.c);
        });
        repaint();
    }
}
