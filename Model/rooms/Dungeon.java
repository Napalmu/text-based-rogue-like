package Model.rooms;
import Model.Item;
import View.ascii_art.AsciiDrawing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Dungeon implements Enterable {
    private Room startingRoom;
    private Room[][] rooms;
    public Dungeon(){
        testDungeon();
        //RoomFactory f = new RoomFactory();
        //Room nextRoom = f.createMessageRoom(new Room[0], "XDDDD");
        //this.startingRoom = f.createMessageRoom(new Room[] {nextRoom}, "Tervetuloa2!", "Mene pois2!");
    }
    public void testDungeon() {
        String[] s = new String[] {
                "T B S",
                "EMEMA",
                "*"
        };
        RoomFactory f = new RoomFactory();
        this.rooms = new Room[s.length][s[0].length()];
        for (int y = 0; y < s.length; y++) {
            for (int x = 0; x < s[y].length(); x++) {
                rooms[y][x] = switch (s[y].charAt(x)) {
                    case 'T' -> f.createTreasureRoom(new Item[]{new Item("Avain")});
                    case 'E' -> f.createMessageRoom("Vihollishuone...");
                    case 'M' -> f.createMessageRoom("Viesti huone...");
                    case 'A' -> f.createMessageRoom("Seikkailuhuone...");
                    case 'S' -> f.createMessageRoom("Kauppa huone...");
                    case '*' -> {
                        this.startingRoom = f.createMessageRoom("Aloitushuone");
                        yield this.startingRoom;
                    }
                    case 'B' -> f.createMessageRoom("Bossi huone...");
                    default -> null;
                };
                if (this.rooms[y][x] == null) continue;
                this.addNeighbour(y-1,x,"Pohjoiseen", "Etelään", rooms[y][x]);
                this.addNeighbour(y+1,x,"Etelään", "Pohjoiseen", rooms[y][x]);
                this.addNeighbour(y,x-1,"Länteen", "Itään", rooms[y][x]);
                this.addNeighbour(y,x+1,"Itään", "Länteen", rooms[y][x]);
            }
        }
    }
    private void addNeighbour(int y, int x, String dir, String dir2, Room room) {
        if (y<0 || x < 0 || y >= this.rooms.length || x >= this.rooms[0].length || this.rooms[y][x] == null) return;
        this.rooms[y][x].addDirection(new Direction(dir2, room));
        room.addDirection(new Direction(dir, this.rooms[y][x]));
    }
    @Override
    public void enter() {
        this.startingRoom.enter();
    }
}
