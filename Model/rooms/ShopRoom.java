package Model.rooms;

import Controller.RoomType;
import View.DrawCommand;

public class ShopRoom extends Room{
    @Override
    protected void enterRoom() {
        this.render(new DrawCommand(5,1, "Tulit kauppaan!"));
        this.moveToNextRoom();
    }

    @Override
    public RoomType getType() {
        return RoomType.SHOP;
    }
}
