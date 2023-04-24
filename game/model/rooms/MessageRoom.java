package game.model.rooms;

import java.util.List;

import game.controller.GameController;
import game.controller.RoomType;

class MessageRoom extends Room{
    private final List<String> messages;

    public MessageRoom(List<String> messages) {
        this.messages = messages;
    }

    @Override
    protected void enterRoom() {
        GameController.view.enterMessageRoom(this, messages);
    }

    @Override
    public RoomType getType() {
        return RoomType.MSG;
    }
}
