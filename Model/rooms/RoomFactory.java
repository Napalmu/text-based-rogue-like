package Model.rooms;

import java.util.Arrays;

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
    public Room createBossRoom(Item key) {
        return new BossRoom(key);
    }

}
