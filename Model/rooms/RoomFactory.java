package Model.rooms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import View.ascii_art.AsciiDrawing;

public class RoomFactory {
    public Room createAdventureRoom(Room[] rooms, AsciiDrawing map){
        ArrayList<Direction> directions = Arrays.stream(rooms).map(r -> new Direction("lol", r))
                .collect(Collectors.toCollection(ArrayList::new));
        return new AdventureRoom(directions, map);
    }

    public Room createMessageRoom(Room[] rooms, String... messages) {
        ArrayList<Direction> directions = Arrays.stream(rooms).map(r -> new Direction("lol", r))
                .collect(Collectors.toCollection(ArrayList::new));
        return new MessageRoom(directions, Arrays.asList(messages));
    }
}
