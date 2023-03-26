package View;
import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.stream.Stream;

import Managers.InputManager;

class Terminal extends JFrame{
    
    private JLabel[][] screen;

    private ArrayList<DrawCommand> content = new ArrayList<>();

    public Terminal(int width, int height){    
        addWindowListener (new WindowAdapter() {    
            public void windowClosing (WindowEvent e) {    
                dispose();    
            }    
        }); 
        this.addKeyListener(InputManager.instance);
                
        screen = new JLabel[width][height];
        JPanel panel = new JPanel();     
        panel.setBackground(Color.BLACK); 
        panel.setBounds(0, 0, 800, 600);
        panel.setLayout(new GridLayout(height, width));
        for(int y=0; y<height; y++){
            for(int x=0; x<width;x++){
                JLabel l = new JLabel();
                l.setFont(new Font("Courier new", 0, 12));
                panel.add(l);
                l.setForeground(Color.WHITE);
                screen[x][y]=l;
            }
        }
        
        add(panel);
        setSize(810,600);      
        setVisible(true); 
    }

    public void redraw(){        
        Stream<JLabel> labels = Stream.of(screen).flatMap(s -> Stream.of(s));
        labels.forEach(l -> l.setText(""));

        content.forEach(c -> drawContent(c));
    }

    public void setChar(int x, int y, String c){
        screen[x][y].setText(c);
    }
    
    public void setChar(int x, int y, char c){
        setChar(x,y,c+"");
    }

    public void addContent(DrawCommand content){
        this.content.add(content);
        drawContent(content);
    }

    public void removeContent(DrawCommand content){
        this.content.remove(content);
        redraw();
    }

    public void drawContent(DrawCommand content){
        content.getStream().forEach((c) -> {
            setChar(c.x, c.y, c.c);
        });
    }
}
