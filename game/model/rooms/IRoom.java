package game.model.rooms;

import game.controller.RoomType;

public interface IRoom {
    RoomType getType();
    boolean hasBeenEntered();
}
