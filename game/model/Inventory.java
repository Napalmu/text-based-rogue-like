package game.model;

import java.util.ArrayList;

import game.controller.GameController;
import game.model.DrawCommandInterfaces.DataIconList;

public class Inventory implements DataIconList {
    private final ArrayList<Item> items = new ArrayList<>();

    public Inventory() {
        GameEventManager.registerListener((GameEventManager.BuyItemListener) this::addItems);
    }
    private void update() {
        GameEventManager.inventory.emit(items.toArray(new Item[0]));
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
    @Override
    public String getHeader() {
        return "Inventory";
    }
    @Override
    public ArrayList<Item> getDataList() {
        return items;
    }
}
