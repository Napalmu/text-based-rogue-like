package game.model;

public interface InventoryHolder {
    public void receiveItems(Item... item);
    public void disposeItem(Item item);
    public boolean hasItem(Item item);
}
