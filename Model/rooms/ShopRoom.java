package Model.rooms;

import Controller.RoomType;
import View.DrawCommand;

public class ShopRoom extends Room{
    @Override
    protected void enterRoom() {
        mainDrawArea.setContent("RoomText", "Tulit kauppaan");
        this.moveToNextRoom();
    }

    @Override
    public RoomType getType() {
        return RoomType.SHOP;
    }
}
