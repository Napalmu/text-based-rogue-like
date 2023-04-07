package Model.rooms;

import Controller.RoomType;

public class Kauppahuone extends Room{

    @Override
    public void enter() {
        super.enter();
    }

    @Override
    public RoomType getType() {
        return RoomType.SHOP;
    }
}
