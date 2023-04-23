package game.view;

import java.util.List;

import game.controller.GameController;
import game.controller.InputManager;
import game.controller.InputManager.KeyPressedEvent;

public abstract class Screen implements Drawable{
    
    List<KeyPressedEvent> listeners;

    abstract List<KeyPressedEvent> getListenersForScreen();
    abstract void enterScreen();
    
    Screen(){}

    final void enter(){
        listeners = getListenersForScreen();
        if (listeners!=null){
            listeners.forEach(l->InputManager.registerListener(l));
        }
        enterScreen();
        GameController.view.refresh();
    }
    final void exit(){
        listeners.forEach(l->InputManager.unregisterListener(l));
    }    
}
