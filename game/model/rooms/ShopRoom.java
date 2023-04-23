package game.model.rooms;

import game.controller.GameController;
import game.controller.RoomType;
import game.model.Item;

import java.util.List;

class ShopRoom extends Room{
    private final List<Item> items;

    ShopRoom(List<Item> items) {
        this.items = items;
    }

    @Override
    protected void enterRoom() {
        GameController.view.enterShopRoom(this, items);
    }

    @Override
    public RoomType getType() {
        return RoomType.SHOP;
    }
}
