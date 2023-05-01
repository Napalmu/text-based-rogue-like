package game.view;

import java.util.List;

import game.controller.GameController;
import game.controller.InputManager;
import game.controller.InputManager.KeyPressedEvent;

abstract class Screen implements Drawable{
    
    List<KeyPressedEvent> listeners;

    protected abstract List<KeyPressedEvent> getListenersForScreen();
    protected abstract void enterScreen();
    protected abstract void exitScreen();
    
    Screen(){}

    final void enter(){
        listeners = getListenersForScreen();
        if (listeners!=null){
            listeners.forEach(InputManager::registerListener);
        }
        enterScreen();
        GameController.view.refresh();
    }
    final void exit(){
        if (listeners!=null) {
            listeners.forEach(InputManager::unregisterListener);
        }
        exitScreen();
    }    
}
