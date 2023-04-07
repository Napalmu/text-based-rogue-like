package Model.rooms;

import Controller.GameController;
import Controller.InputManager;
import Controller.RoomType;
import View.DrawCommand;

import java.awt.event.KeyEvent;
import java.util.*;

public abstract class Room implements Enterable{
    private final List<Direction> nextRooms = new ArrayList<>();
    private final ArrayList<DrawCommand> commands = new ArrayList<>();
    private boolean playerInside = false;
    private boolean hasBeenEntered = false;

    public boolean hasBeenEntered() {
        return hasBeenEntered;
    }

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

    private void exit(Enterable target) {
        if (target.canEnter()) {
            clear();
            this.playerInside = false;
            target.enter();
        } else {
            this.moveToNextRoom();
        }

    }
    protected void moveToNextRoom() {
        ArrayList<InputManager.KeyPressedEvent> choices = new ArrayList<>();
        String[] rooms = new String[nextRooms.size()];
        //seuraavan koodin idea on, että tietyille ilmansuunnille annetaan aina samat numerot:
        //(hyvin kyseenalaista koodia)
        //järjestetään kohteen, niin että pienemmät numerot tulevat ensin.
        //eli esim. pohjoisen numero on 1 ja etelän 3.
        //jos kohde ei ole ilmansuunta, se laitetaan ilmansuuntien jälkeen
        nextRooms.sort((o1, o2) -> {
            int key1 = CompassPoints.getKeyMatchingDirection(o1.getLabel());
            int key2 = CompassPoints.getKeyMatchingDirection(o2.getLabel());
            if (key1 == -1) return 1;
            if (key2 == -1) return -1;
            return Integer.compare(key1,key2);
        });
        //numerot, jotka on jo asetettu johonkin toimintoon
        TreeSet<Integer> usedKeys = new TreeSet<>();
        for (Direction nextRoom : nextRooms) {
            int key = CompassPoints.getKeyMatchingDirection(nextRoom.getLabel());
            usedKeys.add(key);
        }
        for (int i = 0; i < nextRooms.size(); i++) {
            Direction nextRoom = nextRooms.get(i);
            //esim pohjoista vastaa numero 1
            int key = CompassPoints.getKeyMatchingDirection(nextRoom.getLabel());
            if (key == -1) //ei ilmansuunta
                key = usedKeys.isEmpty() ? 1 : usedKeys.last() + 1;//etsitään seuraava vapaa numero
            usedKeys.add(key);
            rooms[i] = key + ": " + nextRoom.getLabel();
            InputManager.KeyConsumer consumer = () -> {
                this.exit(nextRoom.getDestination());
            };
            choices.add(new InputManager.KeyPressedEvent(KeyEvent.VK_1 + (key-1), consumer));
        }
        render(new DrawCommand(5, 6, rooms));

        InputManager.registerListenerList(choices, true);
    }

    public final void enter() {
        this.playerInside = true;
        this.hasBeenEntered = true;
        GameController.view.drawMap();
        this.enterRoom();
    }
    protected abstract void enterRoom();

    public abstract RoomType getType();
    public boolean canEnter() {
        return true;
    }
}
