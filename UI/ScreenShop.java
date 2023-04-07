package UI;

import Controller.GameController;
import Controller.InputManager;
import View.DrawCommand;
import View.ascii_art.AsciiDrawing;

import java.awt.event.KeyEvent;

class ScreenShop extends Screen{
 
    DrawCommand description;
    DrawCommand items;
    public ScreenShop(){
        GameController.view.setContent(new DrawCommand(0, 0, AsciiDrawing.SCREEN.getArt()));
        description=new DrawCommand(2, 16, "Tervetuloa kauppaan!","Mitä saisi olla?");
        items=new DrawCommand(60, 1, "1: Hyppykeppi","2: Banaanisplitti");
        drawDescription();
        drawItems();
        InputManager.registerListener(KeyEvent.VK_1, this::desc1);
        InputManager.registerListener(KeyEvent.VK_2, this::desc2);
    }

    public void desc1(){

        description.setContent(new String[]{"Jousilla toimiva hyppylaite"});
    }
    public void desc2(){
        description.setContent(new String[]{"Banaanista ja jäätelöstä koostuva jälkiruoka"});

    }
    public void drawDescription(){
        GameController.view.setContent(description);
    }
    public void drawItems(){
        GameController.view.setContent(items);
    }
}
