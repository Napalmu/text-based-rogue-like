package game.model.rooms;

import game.controller.RoomType;
import game.model.GameEventManager;
import game.model.Item;

import java.util.ArrayList;

class ShopRoom extends Room{
    private ArrayList<Item> items;

    ShopRoom(ArrayList<Item> items) {
        this.items = items;
    }

    @Override
    protected void enterRoom() {
        GameEventManager.emitShopEntered(this.items);
        //this.moveToNextRoom();
    }

    @Override
    public RoomType getType() {
        return RoomType.SHOP;
    }
}
