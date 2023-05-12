package game.model.rooms;

import game.controller.GameController;
import game.controller.RoomType;
import game.model.InventoryHolder;
import game.model.Item;

import java.util.List;
import java.util.stream.Stream;

class ShopRoom extends Room implements InventoryHolder{
    private final List<Item> items;

    ShopRoom(List<Item> items) {
        this.items = items;
    }

    @Override
    protected void enterRoom() {
        GameController.view.enterShopRoom(this, this);
    }

    @Override
    public RoomType getType() {
        return RoomType.SHOP;
    }

    @Override
    public void receiveItems(Item... item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'receiveItems'");
    }

    @Override
    public void disposeItem(Item item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'disposeItem'");
    }

    @Override
    public boolean hasItem(Item item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hasItem'");
    }

    @Override
    public int itemcount() {
        return items.size();
    }

    @Override
    public Stream<Item> items() {
        return items.stream();
    }
}
