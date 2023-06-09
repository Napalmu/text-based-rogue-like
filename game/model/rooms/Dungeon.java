package game.model.rooms;

import static game.model.rooms.CompassPoints.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

import game.controller.GameController;
import game.model.Item_Weapon;
import game.controller.ItemType;
import game.controller.RoomType;
import game.model.Enemy;
import game.model.EntityManager;
import game.model.Item;
import game.view.MapRoom;
import game.view.ascii_art.AsciiDrawing;

public class Dungeon implements Enterable {
    private final Item keyToBossRoom = EntityManager.createItem(ItemType.KEY);
    private Room startingRoom;
    private Room[][] rooms;

    public Dungeon() {
        testDungeon();
    }
    public Dungeon(Room[][] rooms, Room startingRoom) {
        this.rooms = rooms;
        this.startingRoom = startingRoom;
    }

    private Room room(RoomFactory f, char c) {        
        switch (c) {
            case 'T':
                return (Room) f.createTreasureRoom(this.keyToBossRoom);
            case 'E':
                return (Room) f.createEnemyRoom((Enemy) EntityManager.createEnemy(100, "Örkki",3));
            case 'M':
                return (Room) f.createMessageRoom("Viesti huone...");
            case 'A':
                return (Room) f.createAdventureRoom(AsciiDrawing.OUTSIDE);
            case 'S':
                Item item = EntityManager.createItem(ItemType.BLUEBERRY);
                Item item2 = EntityManager.createItem(ItemType.STRAWBERRY);
                return (Room) f.createShopRoom(item,item2); 
            case '*':
                this.startingRoom = (Room) f.createMessageRoom("Aloitushuone");
                return this.startingRoom;
            case 'B':
                Enterable winRoom = f.createMessageRoom("Voitit pelin!");
                return (Room) f.createBossRoom(
                        (Enemy) EntityManager.createEnemy(100, "Bossi", 5)
                        , this.keyToBossRoom,new Direction("Win", winRoom));
            default:
                return null;
        }
    }

    public void testDungeon() {
        String[] s = new String[]{
                "T B  ",
                "EMEMS",
                "*"
        };
        RoomFactory f = new RoomFactory();
        this.rooms = new Room[s.length][s[0].length()];
        for (int y = 0; y < s.length; y++) {
            for (int x = 0; x < s[y].length(); x++) {
                rooms[y][x] = room(f, s[y].charAt(x));
                if (this.rooms[y][x] == null) continue;
                this.addNeighbour(y - 1, x, NORTH, SOUTH, rooms[y][x]);
                this.addNeighbour(y + 1, x, SOUTH, NORTH, rooms[y][x]);
                this.addNeighbour(y, x - 1, WEST, EAST, rooms[y][x]);
                this.addNeighbour(y, x + 1, EAST, WEST, rooms[y][x]);
            }
        }
    }

    private void addNeighbour(int y, int x, String dir, String dir2, Room room) {
            
        if (y < 0 || 
            x < 0 || 
            y >= this.rooms.length || 
            x >= this.rooms[0].length || 
            this.rooms[y][x] == null) 
            return;

        this.rooms[y][x].addDirection(new Direction(dir2, room));
        room.addDirection(new Direction(dir, this.rooms[y][x]));
    }

    public MapRoom[][] getMap() {
        MapRoom[][] map = new MapRoom[rooms.length][rooms[0].length];

        for (int y = 0; y < this.rooms.length; y++) {
            for (int x = 0; x < this.rooms[y].length; x++) {
                Room r = this.rooms[y][x];
                if (r == null) continue;
                map[y][x] = new MapRoom() {
                    @Override
                    public RoomType getRoomType() {return r.getType();}

                    @Override
                    public boolean hasPlayerInside() {return r.hasPlayerInside();}

                    @Override
                    public ArrayList<Direction> neighbours() {return r.getDestinations();}
                };
            }
        }
        return map;
    }

    @Override
    public void enter() {
        GameController.model.moveToDungeon(this);
        this.startingRoom.enter();
    }

    @Override
    public boolean canEnter() {
        return true;
    }
}
