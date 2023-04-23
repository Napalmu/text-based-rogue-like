package game.view;
import javax.swing.*;

import game.controller.InputManager;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.stream.Stream;

class Terminal extends JFrame{
    
    private JLabel[][] screen;

    private Drawable screenBuffer;
     Terminal(int width, int height){    
        addWindowListener (new WindowAdapter() {    
             public void windowClosing (WindowEvent e) {    
                dispose();    
            }    
        }); 
        this.addKeyListener(InputManager.instance);
                
        screen = new JLabel[width][height];
        JPanel panel = new JPanel();     
        panel.setBackground(Color.BLACK); 
        panel.setBounds(0, 0, width*10, height*18);
        panel.setLayout(new GridLayout(height, width));
        for(int y=0; y<height; y++){
            for(int x=0; x<width;x++){
                JLabel l = new JLabel();
                l.setFont(new Font("Courier new", 0, 15));
                panel.add(l);
                l.setForeground(Color.WHITE);
                screen[x][y]=l;
            }
        }
        
        add(panel);
        setSize(panel.getSize());      
        setVisible(true); 
    }

     void redraw(){        
        Stream<JLabel> labels = Stream.of(screen).flatMap(s -> Stream.of(s));
        labels.forEach(l -> l.setText(""));        
        drawContent();
    }

     void setChar(int x, int y, String c){
        screen[x][y].setText(c);
    }
    
     void setChar(int x, int y, char c){
        setChar(x,y,c+"");
    }

     void setContent(Drawable content){
        this.screenBuffer = content;
        drawContent();
    }

     void drawContent(){
        if (screenBuffer==null) return;
        
        DrawCommand dc = screenBuffer.getDrawCommand();
        dc.activate();
        dc.getStream().forEach((c) -> {
             setChar(c.x, c.y, c.c);
        });
        repaint();
    }
}
