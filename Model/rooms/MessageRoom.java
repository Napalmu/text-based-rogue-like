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
        mainDrawArea.setContent("RoomText", new String[0]);
        //GameController.view.setContent(new DrawCommand(5,1, "Aloitushuone"));
        //GameController.view.getMainDrawArea().setContent(new String[]{"Aloitushuone"});
        this.moveToNextRoom();
    }

    @Override
    public RoomType getType() {
        return RoomType.MSG;
    }
}
