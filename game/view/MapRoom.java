package game.view;

import game.controller.RoomType;

public interface MapRoom {
    RoomType getRoomType();
    boolean hasPlayerInside();
}
