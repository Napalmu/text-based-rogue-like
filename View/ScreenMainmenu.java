package View;

import java.awt.event.KeyEvent;

import Controller.GameController;
import Managers.InputManager;

public class ScreenMainmenu extends Screen{
    private GUIContent title;
    private GUIContent menu;

    public ScreenMainmenu(){
        title = new GUIContent(10, 10, "Roguelike","Game");
        menu = new GUIContent(10, 14, "1: Play","2: Exit");

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

        GameController.model.setDungeon();
    }

    public void onExit(){
        GameController.exitGame();
    }
    
}
