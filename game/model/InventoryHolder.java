package game.model;

import java.util.stream.Stream;

public interface InventoryHolder {
    public void receiveItems(Item... item);
    public void disposeItem(Item item);
    public boolean hasItem(Item item);
    public int itemcount();
    public Stream<Item> items();
}
