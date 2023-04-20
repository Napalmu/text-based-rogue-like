package game.model;

import game.model.Inventory.InventoryListener;

import java.util.ArrayList;

public class Player extends Entity implements Fighter{
    private final Inventory inventory = new Inventory();
    public Player(int hp, String name){
        super(hp, name);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void addInventoryListener(InventoryListener listener) {
        inventory.addListener(listener);
    }

    @Override
    public void proceed() {

    }

    @Override
    public int getSpeed() {
        return 0;
    }

    @Override
    public int[] TargetAndAction(ArrayList<Fighter> fighters) {
        return new int[0];
    }

    @Override
    public void takeDamage(int dmg) {

    }

    @Override
    public void die() {

    }
}

