package Model.rooms;
import Controller.RoomType;
import Model.Item;
import View.MapRoom;
import View.ascii_art.AsciiDrawing;

public class Dungeon implements Enterable {
    public static Dungeon currentDungeon;

    private Room startingRoom;
    private Room[][] rooms;
    private final Item keyToBossRoom = new Item("Mahtava avain");
    public Dungeon(){
        testDungeon();
        //RoomFactory f = new RoomFactory();
        //Room nextRoom = f.createMessageRoom(new Room[0], "XDDDD");
        //this.startingRoom = f.createMessageRoom(new Room[] {nextRoom}, "Tervetuloa2!", "Mene pois2!");
    }
    private Room room(RoomFactory f, char c) {
        switch (c) {
            case 'T':
                return f.createTreasureRoom(new Item[]{this.keyToBossRoom});
            case 'E':
                return new EnemyRoom(); //TODO käytä factory
            case 'M':
                return f.createMessageRoom("Viesti huone...");
            case 'A':
                return f.createAdventureRoom(AsciiDrawing.OUTSIDE);
            case 'S':
                return new ShopRoom(); //TODO käytä factory
            case '*':
                this.startingRoom = f.createMessageRoom("Aloitushuone");
                return this.startingRoom;
            case 'B':
                return f.createBossRoom(this.keyToBossRoom);
            default:
                return null;
        }
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
                rooms[y][x] = room(f, s[y].charAt(x));
                if (this.rooms[y][x] == null) continue;
                this.addNeighbour(y-1,x,CompassPoints.NORTH, CompassPoints.SOUTH, rooms[y][x]);
                this.addNeighbour(y+1,x,CompassPoints.SOUTH, CompassPoints.NORTH, rooms[y][x]);
                this.addNeighbour(y,x-1,CompassPoints.WEST, CompassPoints.EAST, rooms[y][x]);
                this.addNeighbour(y,x+1,CompassPoints.EAST, CompassPoints.WEST, rooms[y][x]);
            }
        }
    }
    private void addNeighbour(int y, int x, String dir, String dir2, Room room) {
        if (y<0 || x < 0 || y >= this.rooms.length || x >= this.rooms[0].length || this.rooms[y][x] == null) return;
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
                    public RoomType getRoomType() {
                        return r.getType();
                    }

                    @Override
                    public boolean hasPlayerInside() {
                        return r.hasPlayerInside();
                    }
                };
            }
        }
        return map;
    }
    @Override
    public void enter() {
        Dungeon.currentDungeon = this;
        this.startingRoom.enter();
    }

    @Override
    public boolean canEnter() {
        return true;
    }
}
