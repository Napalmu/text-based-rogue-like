package game.model.rooms;

import java.util.Arrays;
import java.util.Random;

import game.controller.ItemType;
import game.model.Enemy;
import game.model.EntityManager;
import game.model.Item;
import game.view.ascii_art.AsciiDrawing;

public class RoomFactory {
    private final int level;
    private final Random random = new Random();
    public RoomFactory(int level) {
        this.level = level;
    }
    public RoomFactory() {
        this.level = 1;
    }
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
    public Enterable createTreasureRoom() {
        //todo arvo random itemeitä
        //siis tämä koodi on täysin väliaikaista. Kaiken voi poistaa ja pitää poistaa
        Item item;
        if (level == 1) {
            item = EntityManager.createItem(ItemType.SWORD);
        } else if (level == 2) {
            item = EntityManager.createItem(ItemType.BLUEBERRY);
        } else {
            item = EntityManager.createItem(ItemType.STRAWBERRY);
        }
        return new TreasureRoom(new Item[] {item});
    }
    public Enterable createBossRoom(Enemy enemy, Item key, Direction destinationAfterWin) {
        return new BossRoom(enemy,key, destinationAfterWin);
    }

    public Enterable createEnemyRoom(Enemy enemy) {
        return new EnemyRoom(enemy);
    }
    public Enterable createEnemyRoom() {
        int hp = random.nextInt(10, 101);
        hp *= level;

        int speed = random.nextInt(1, 5);
        speed *= level/2.0;

        Enemy enemy = (Enemy) EntityManager.createEnemy(hp,"Örkki", speed);
        return new EnemyRoom(enemy);
    }

    public Dungeon createStartingRoom(String... messages){
        return new Dungeon();
    }

    public Enterable createShopRoom(Item... items) {
        return new ShopRoom(Arrays.asList(items));
    }
    public Enterable createShopRoom() {
        Item item;
        Item item2;
        //TODO paranna
        if (level == 1) {
            item = EntityManager.createItem(ItemType.BOW);
            item2 = EntityManager.createItem(ItemType.FIREBALL_SPELL);
        } else if (level == 2) {
            item = EntityManager.createItem(ItemType.CHAINMAIL_ARMOR);
            item2 = EntityManager.createItem(ItemType.PADDED_ARMOR);
        } else {
            item = EntityManager.createItem(ItemType.STICK);
            item2 = EntityManager.createItem(ItemType.HALBERG);
        }
        return new ShopRoom(Arrays.asList(item, item2));
    }

    public Dungeon createStartingDungeon() {
        DungeonGenerator generator = new DungeonGenerator(10,7, 1);
        return generator.generate();
    }
}
