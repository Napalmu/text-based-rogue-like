package game.model;

import java.util.ArrayList;

import game.model.DrawCommandInterfaces.DataIconList;

class Inventory implements DataIconList {
    public interface InventoryListener {
        void onChange(Item[] items);
    }
    private final ArrayList<InventoryListener> listeners = new ArrayList<>();
    private final ArrayList<Item> items = new ArrayList<>();

    private void update() {
        for (InventoryListener listener : this.listeners) {
            listener.onChange(items.toArray(new Item[0]));
        }
    }
    public void addItems(Item... items) {
        for (Item i : items){
            this.items.add(i);
            GameEventManager.emitItemReceivedEvent(i);
        }
        update();
    }

    public void removeItem(Item item) {
        items.remove(item);
        update();
    }
    public boolean containsItem(Item item) {
        return items.contains(item);
    }
    public void addListener(InventoryListener listener) {
        this.listeners.add(listener);
    }
    public void removeListener(InventoryListener listener) {
        this.listeners.remove(listener);
    }

    @Override
    public String getHeader() {
        return "Inventory";
    }
    @Override
    public ArrayList<Item> getDataList() {
        return items;
    }
}
