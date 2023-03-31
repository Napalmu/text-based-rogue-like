package Model;

import Controller.GameController;
import Managers.InputManager;
import View.DrawCommand;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public abstract class Room {
    private final List<Direction> nextRooms;
    private final ArrayList<DrawCommand> commands = new ArrayList<>();

    protected void render(DrawCommand command) {
        this.commands.add(command);
        GameController.view.setContent(command);
    }

    protected void clear() {
        for (DrawCommand command : this.commands) {
            GameController.view.clearContent(command);
        }
    }

    public Room(List<Direction> nextRooms) {
        this.nextRooms = nextRooms;
    }

    private void moveToNextRoom() {
        ArrayList<InputManager.KeyPressedEvent> choices = new ArrayList<>();
        String[] rooms = new String[nextRooms.size()];
        for (int i = 0; i < nextRooms.size(); i++) {
            Direction nextRoom = nextRooms.get(i);
            rooms[i] = (i + 1) + ": " + nextRoom.getName();
            InputManager.KeyConsumer consumer = () -> {
                clear();
                nextRoom.getDestination().enter();
            };
            choices.add(new InputManager.KeyPressedEvent(KeyEvent.VK_1 + i, consumer));
        }
        render(new DrawCommand(5, 6, rooms));

        InputManager.registerListenerList(choices, true);
    }

    public void enter() {
        moveToNextRoom();
    }
}
