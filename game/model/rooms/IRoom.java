package game.model.rooms;

import java.util.ArrayList;

import game.controller.RoomType;

public interface IRoom extends Enterable{
    void exit();
    RoomType getType();
    boolean hasBeenEntered();
    ArrayList<Direction> getDestinations();

}
