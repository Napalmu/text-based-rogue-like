package game.view;

import java.util.List;

import game.controller.InputManager.KeyPressedEvent;
import game.model.rooms.IRoom;

public class ScreenMessage extends ScreenThreePart{

    public ScreenMessage(IRoom room, List<String> messages) {
        super(room);
        getMainArea().drawMessages(messages.toArray(new String[0]));
    }

    @Override
    List<KeyPressedEvent> getListenersForScreen() {
        return null;
    }

    @Override
    void enterScreen() {
        
    }
    
}
