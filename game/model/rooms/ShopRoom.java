package game.model.rooms;

import game.controller.RoomType;

class ShopRoom extends Room{
    @Override
    protected void enterRoom() {
        roomText.setContent("Tulit kauppaan");
        this.moveToNextRoom();
    }

    @Override
    public RoomType getType() {
        return RoomType.SHOP;
    }
}
