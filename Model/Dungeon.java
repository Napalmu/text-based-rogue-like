package Model;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import Controller.GameController;
import Managers.InputManager;
import View.DrawCommand;

class Dungeon {
    private Room startingRoom;
    public Room createMessageRoom(Room[] rooms, String... messages) {
        ArrayList<Direction> directions = Arrays.stream(rooms).map(r -> new Direction("lol", r))
                .collect(Collectors.toCollection(ArrayList::new));
        return new MessageRoom(directions, Arrays.asList(messages));
    }

    public Dungeon(){
        Room nextRoom = createMessageRoom(new Room[0], "XDDDD");
        this.startingRoom = createMessageRoom(new Room[] {nextRoom}, "Tervetuloa2!", "Mene pois2!");
        startingRoom.enter();
    }
}
