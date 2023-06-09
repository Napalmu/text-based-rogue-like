package game.view;

import java.util.List;

import game.controller.InputManager.KeyPressedEvent;
import game.model.rooms.IRoom;

class ScreenMessage extends ScreenThreePart {
    private final String[] messages;

    public ScreenMessage(IRoom room, List<String> messages) {
        super(room);
        this.messages = messages.toArray(new String[0]);
    }

    @Override
    protected List<KeyPressedEvent> getListenersForScreen() {
        return null;
    }

    @Override
    protected void enterScreen() {
        getMainArea().drawMessages(this.messages);
        this.registerDirections();
    }

}
