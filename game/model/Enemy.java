package game.model;

import java.util.ArrayList;

import game.controller.ItemType;

public class Enemy extends Entity implements Fighter, IEnemy, InventoryHolder{
    private final Inventory inventory = new Inventory();
    public Enemy(int hp, String name){
        super(hp, name);
    }

    //TODO implement
    @Override
    public void proceed() {

    }

    @Override
    public int getSpeed() {
        return 0;
    }

    @Override
    public Battle.Action getAction(ArrayList<Fighter> fighters) {
        //todo ottaa tällä hetkellä vain ekan
        return new Battle.MeleeAction(fighters.get(0), 10);
    }

    @Override
    public void takeDamage(int dmg) {

    }

    @Override
    public void die() {

    }
    @Override
    public ArrayList<Item> getItems() {
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(ItemType.BLUEBERRY));
        return items;
    }
    public void addItems(ArrayList<Item> items) {
        System.out.println("En ota lol!");
    }
    @Override
    public void receiveItem(Item item) { inventory.addItems(item); }

    @Override
    public void disposeItem(Item item) { inventory.removeItem(item); }

}
