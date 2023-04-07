package Model.rooms;

import Controller.RoomType;
import View.DrawCommand;

import java.util.List;

public class MessageRoom extends Room{
    private final List<String> messages;

    public MessageRoom(List<String> messages) {
        this.messages = messages;
    }

    @Override
    protected void enterRoom() {
        this.render(new DrawCommand(5,1, messages.toArray(new String[0])));

        this.moveToNextRoom();
    }

    @Override
    public RoomType getType() {
        return RoomType.MSG;
    }
}
