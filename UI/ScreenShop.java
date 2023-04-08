package UI;

import Controller.GameController;
import Controller.InputManager;
import View.DrawArea;
import View.DrawCommand;
import View.DrawText;
import View.ascii_art.AsciiDrawing;

import java.awt.event.KeyEvent;

class ScreenShop extends Screen{
 

    private DrawText description;
    private DrawText items;
    public ScreenShop(){
        GameController.view.setContent(new DrawCommand(0, 0, AsciiDrawing.SCREEN.getArt()));


         
        description = (DrawText)GameController.view.CreateInfoAreaContent(new DrawText("Tervetuloa kauppaan!","Mitä saisi olla?"));

        items = (DrawText)GameController.view.CreateDataAreaContent(new DrawText("1: Hyppykeppi","2: Banaanisplitti"));
        //TODO: korvaa teksti oliolla itemeistä
        
        InputManager.registerListener(KeyEvent.VK_1, this::desc1);
        InputManager.registerListener(KeyEvent.VK_2, this::desc2);
    }

    public void desc1(){
        description.setContent(new String[]{"Jousilla toimiva hyppylaite"});
    }
    public void desc2(){
        description.setContent(new String[]{"Banaanista ja jäätelöstä koostuva jälkiruoka"});
    }
}
