package Model.rooms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Dungeon implements Enterable {
    private final Room startingRoom;

    public Dungeon(){
        RoomFactory f = new RoomFactory();
        Room nextRoom = f.createMessageRoom(new Room[0], "XDDDD");
        this.startingRoom = f.createMessageRoom(new Room[] {nextRoom}, "Tervetuloa2!", "Mene pois2!");
    }

    @Override
    public void enter() {
        this.startingRoom.enter();
    }
}
