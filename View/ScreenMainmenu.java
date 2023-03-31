package View;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Controller.GameController;
import Managers.InputManager;

public class ScreenMainmenu extends Screen{
    private DrawCommand title;
    private DrawCommand menu;

    InputManager.KeyConsumer a = this::onPlay;
    InputManager.KeyConsumer b = this::onExit;

    public ScreenMainmenu(){
        title = new DrawCommand(10, 10, "Rosmonkaltainen","Peli");
        menu = new DrawCommand(10, 14, "1: Pelaa","2: Poistu");

        GameController.view.setContent(title);
        GameController.view.setContent(menu);

        InputManager.registerListener(KeyEvent.VK_1, a);
        InputManager.registerListener(KeyEvent.VK_2, b);
    }

    public void onPlay(){
        System.out.println("Fhbasfvhnfsdghds");
        GameController.view.clearContent(title);
        GameController.view.clearContent(menu);

        InputManager.unregisterListener(KeyEvent.VK_1, a);
        InputManager.unregisterListener(KeyEvent.VK_2, b);

        new ScreenGame();
        GameController.model.setDungeon();
    }

    public void onExit(){
        GameController.exitGame();
    }
    
}
