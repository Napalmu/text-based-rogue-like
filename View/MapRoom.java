package View;

import Controller.RoomType;

public interface MapRoom {
    RoomType getRoomType();
    boolean hasPlayerInside();
}
