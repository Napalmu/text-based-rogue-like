package Model.rooms;

import Controller.GameController;
import Controller.RoomType;
import UI.UIController;
import View.DrawArea;
import View.DrawCommand;

import java.util.List;

public class MessageRoom extends Room{
    private final List<String> messages;

    public MessageRoom(List<String> messages) {
        this.messages = messages;
    }

    @Override
    protected void enterRoom() {
        andreinDrawArea.drawMessages(messages.toArray(new String[0]));
        //vanha:
        //mainDrawArea.setContent("RoomText", messages.toArray(new String[0]));
        this.moveToNextRoom();
    }

    @Override
    public RoomType getType() {
        return RoomType.MSG;
    }
}
