package game.model.rooms;

import java.util.Arrays;

import game.model.Enemy;
import game.model.Item;
import game.view.ascii_art.AsciiDrawing;

public class RoomFactory {
    public Enterable createAdventureRoom(AsciiDrawing map){
        // return new AdventureRoom(map);
        return null;
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

    public Enterable createShopRoom(Item... items) {
        return new ShopRoom(Arrays.asList(items));
    }

    public Dungeon createStartingDungeon() {
        DungeonGenerator2 generator = new DungeonGenerator2(10,7);
        return generator.generate();
    }
}
