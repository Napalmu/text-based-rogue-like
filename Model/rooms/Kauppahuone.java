package Model.rooms;

import java.util.List;

public class Kauppahuone extends Room{
    public Kauppahuone(List<Direction> nextRooms) {
        super(nextRooms);
    }

    @Override
    public void enter() {

    }
}
