package Model;

import Controller.GameController;
import Managers.InputManager;
import View.DrawCommand;

import java.util.ArrayList;
import java.util.List;

public class MessageRoom extends Room{
    private final List<String> messages;

    public MessageRoom(List<Direction> nextRooms, List<String> messages) {
        super(nextRooms);
        this.messages = messages;
    }

    @Override
    public void enter() {
        DrawCommand msg = new DrawCommand(5, 1, messages.toArray(new String[0]));
        GameController.view.setContent(msg);
        super.enter();
    }
}
