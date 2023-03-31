package View;

import java.awt.event.KeyEvent;

import Controller.GameController;
import Managers.InputManager;

public class ScreenMainmenu extends Screen{
    private DrawCommand title;
    private DrawCommand menu;

    public ScreenMainmenu(){
        title = new DrawCommand(10, 10, "Rosmonkaltainen","Peli");
        menu = new DrawCommand(10, 14, "1: Pelaa","2: Poistu");

        GameController.view.setContent(title);
        GameController.view.setContent(menu);

        InputManager.registerListener(KeyEvent.VK_1, this::onPlay);
        InputManager.registerListener(KeyEvent.VK_2, this::onExit);
    }

    public void onPlay(){
        GameController.view.clearContent(title);
        GameController.view.clearContent(menu);

        InputManager.unregisterListener(KeyEvent.VK_1, this::onPlay);
        InputManager.unregisterListener(KeyEvent.VK_2, this::onExit);

        new ScreenGame();
        GameController.model.setDungeon();
    }

    public void onExit(){
        GameController.exitGame();
    }
    
}
