package game.model.rooms;

import game.controller.RoomType;

class ShopRoom extends Room{
    @Override
    protected void enterRoom() {
        this.moveToNextRoom();
    }

    @Override
    public RoomType getType() {
        return RoomType.SHOP;
    }
}
