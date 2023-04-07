package Model.rooms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import Model.Item;
import View.ascii_art.AsciiDrawing;

public class RoomFactory {
    public Room createAdventureRoom(AsciiDrawing map){
        return new AdventureRoom(map);
    }

    public Room createMessageRoom(String... messages) {
        return new MessageRoom(Arrays.asList(messages));
    }
    public Room createTreasureRoom(Item[] items) {
        return new TreasureRoom(items);
    }
}
