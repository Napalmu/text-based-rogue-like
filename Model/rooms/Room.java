package Model.rooms;

import Controller.GameController;
import Controller.InputManager;
import Controller.RoomType;
import View.DrawCommand;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public abstract class Room implements Enterable{
    private final List<Direction> nextRooms = new ArrayList<>();
    private final ArrayList<DrawCommand> commands = new ArrayList<>();
    private boolean playerInside = false;
    public boolean hasPlayerInside() {
        return playerInside;
    }

    public void addDirection(Direction direction) {
        this.nextRooms.add(direction);
    }
    protected void render(DrawCommand command) {
        this.commands.add(command);
        GameController.view.setContent(command);
    }

    protected void clear() {
        for (DrawCommand command : this.commands) {
            GameController.view.clearContent(command);
        }
    }
    private void exit() {
        clear();
        this.playerInside = false;
    }
    protected void moveToNextRoom() {
        ArrayList<InputManager.KeyPressedEvent> choices = new ArrayList<>();
        String[] rooms = new String[nextRooms.size()];
        for (int i = 0; i < nextRooms.size(); i++) {
            Direction nextRoom = nextRooms.get(i);
            rooms[i] = (i + 1) + ": " + nextRoom.getName();
            InputManager.KeyConsumer consumer = () -> {
                if (nextRoom.getDestination().canEnter()) {
                    exit();
                    nextRoom.getDestination().enter();
                } else {
                    this.moveToNextRoom();
                }
            };
            choices.add(new InputManager.KeyPressedEvent(KeyEvent.VK_1 + i, consumer));
        }
        render(new DrawCommand(5, 6, rooms));

        InputManager.registerListenerList(choices, true);
    }

    public void enter() {
        this.playerInside = true;
        GameController.view.drawMap();
    }
    public abstract RoomType getType();
    public boolean canEnter() {
        return true;
    }
}
