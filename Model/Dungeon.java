package Model;
import java.awt.event.KeyEvent;

import Controller.GameController;
import Managers.InputManager;
import View.GUIContent;

class Dungeon {
    
    public Dungeon(){
        
        GameController.view.setContent(new GUIContent(4, 4, "XX"));        
        GameController.view.setContent(new GUIContent(4,5, "X"));
        GameController.view.setContent(new GUIContent(4,6, "S"));
        
        GameController.view.setContent(new GUIContent(5,3, "X"));

        InputManager.registerListener(KeyEvent.VK_A, () -> {
            GameController.view.setContent(new GUIContent(5,5, "X"));
        });
    }
}
