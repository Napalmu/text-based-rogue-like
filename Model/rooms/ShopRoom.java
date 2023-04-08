package Model.rooms;

import Controller.RoomType;

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
