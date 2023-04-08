package Model;

import Model.Inventory.InventoryListener;

class Player extends Entity {
    private Inventory inventory = new Inventory();
    public Player(int hp, String name){
        super(hp, name);
    }

    public void addItems(Item[] items) {
        inventory.addItems(items);
    }

    public boolean hasItem(Item key) {
        return inventory.containsItem(key);
    }

    public void removeItem(Item key) {
        inventory.removeItem(key);
    }

    public void addInventoryListener(InventoryListener listener) {
        inventory.addListener(listener);
    }
}

