package UI;

import Controller.GameController;
import Controller.InputManager;
import View.DrawArea;
import View.DrawCommand;
import View.DrawText;
import View.ascii_art.AsciiDrawing;

import java.awt.event.KeyEvent;

class ScreenShop extends Screen{
 
    DrawArea infoDrawArea;
    DrawArea dataDrawArea;

    public ScreenShop(){
        GameController.view.setContent(new DrawCommand(0, 0, AsciiDrawing.SCREEN.getArt()));


         
        infoDrawArea = GameController.view.getInfoDrawArea();
        dataDrawArea = GameController.view.getDataDrawArea();

        infoDrawArea.createContent("Description", new DrawText("Tervetuloa kauppaan!","Mitä saisi olla?"));
        dataDrawArea.createContent("Items", new DrawText("1: Hyppykeppi","2: Banaanisplitti"));
        
        InputManager.registerListener(KeyEvent.VK_1, this::desc1);
        InputManager.registerListener(KeyEvent.VK_2, this::desc2);
    }

    public void desc1(){
        infoDrawArea.setContent("Description", new String[]{"Jousilla toimiva hyppylaite"});
    }
    public void desc2(){
        infoDrawArea.setContent("Description", new String[]{"Banaanista ja jäätelöstä koostuva jälkiruoka"});

    }
}
