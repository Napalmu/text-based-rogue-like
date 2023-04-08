package Model.rooms;

import java.util.Arrays;

import Model.Enemy;
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
    public Room createBossRoom(Enemy enemy, Item key) {
        return new BossRoom(enemy,key);
    }

    public Room createEnemyRoom(Enemy enemy) {
        return new EnemyRoom(enemy);
    }
}
