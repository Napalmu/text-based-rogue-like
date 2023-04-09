package Model.rooms;

import java.util.Arrays;

import Model.Enemy;
import Model.Item;
import View.ascii_art.AsciiDrawing;

public class RoomFactory {
    public Enterable createAdventureRoom(AsciiDrawing map){
        return new AdventureRoom(map);
    }
    
    public Enterable createMessageRoom(String... messages) {
        return new MessageRoom(Arrays.asList(messages));
    }
    public Enterable createTreasureRoom(Item... items) {
        return new TreasureRoom(items);
    }
    public Enterable createBossRoom(Enemy enemy, Item key) {
        return new BossRoom(enemy,key);
    }

    public Enterable createEnemyRoom(Enemy enemy) {
        return new EnemyRoom(enemy);
    }

    public Dungeon createStartingRoom(String... messages){
        return new Dungeon();
    }
}
