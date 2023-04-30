package game.model.rooms;

import java.util.ArrayList;

import game.controller.RoomType;

public interface IRoom {
    void exit();
    RoomType getType();
    boolean hasBeenEntered();
    ArrayList<Direction> getDestinations();

}
