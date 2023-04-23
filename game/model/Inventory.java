package game.model;

import java.util.ArrayList;

import game.model.DrawCommandInterfaces.DataIconList;

 class Inventory implements DataIconList {
    private final ArrayList<Item> items = new ArrayList<>();

     Inventory() {

    }
    private void update() {
        GameEventManager.inventory.emit(items.toArray(new Item[0]));
    }
     void addItems(Item... items) {
        for (Item i : items){
            this.items.add(i);
            GameEventManager.emitItemReceivedEvent(i);
        }
        update();
    }

     void removeItem(Item item) {
        items.remove(item);
        update();
    }
     boolean containsItem(Item item) {
        return items.contains(item);
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
