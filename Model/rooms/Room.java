package Model.rooms;

import Controller.GameController;
import Controller.InputManager;
import View.DrawCommand;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public abstract class Room implements Enterable{
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

    protected void moveToNextRoom() {
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

    public abstract void enter();
}
