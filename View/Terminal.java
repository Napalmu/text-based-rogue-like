package View;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.stream.Stream;

import Managers.InputManager;

class Terminal extends Frame{
    
    private Label[][] screen;

    private ArrayList<GUIContent> content = new ArrayList<>();

    public Terminal(int width, int height){    
        addWindowListener (new WindowAdapter() {    
            public void windowClosing (WindowEvent e) {    
                dispose();    
            }    
        }); 
        this.addKeyListener(InputManager.instance);
                
        screen = new Label[height][width];
        setBackground(Color.BLACK); 
        setLayout(null);   
        for(int x=0; x<width;x++){
            for(int y=0; y<height; y++){
                Label l = new Label();
                l.setForeground(Color.WHITE);
                l.setBounds(x*10, (y+2)*20, 10, 20);  
                add(l);
                screen[y][x]=l;
            }
        }
        
        setSize(810,600);      
        setVisible(true); 
    }

    public void redraw(){        
        Stream<Label> labels = Stream.of(screen).flatMap(s -> Stream.of(s));
        labels.forEach(l -> l.setText(""));

        content.forEach(c -> drawContent(c));
    }

    public void setChar(int x, int y, String c){
        screen[y][x].setText(c);
    }
    
    public void setChar(int x, int y, char c){
        setChar(x,y,c+"");
    }

    public void addContent(GUIContent content){
        this.content.add(content);
        drawContent(content);
    }

    public void removeContent(GUIContent content){
        this.content.remove(content);
        redraw();
    }

    public void drawContent(GUIContent content){
        content.getStream().forEach((c) -> {
            setChar(c.x, c.y, c.c);
        });
    }
}
