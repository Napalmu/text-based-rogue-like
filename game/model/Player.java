package game.model;
import java.util.ArrayList;

import game.controller.ItemType;

 public class Player extends Entity implements Fighter, InventoryHolder{
    private final Inventory inventory = new Inventory();
     Player(int hp, String name){
        super(hp, name);
        GameEventManager.registerListener((GameEventManager.BuyItemListener) inventory::addItems);
    }

    @Override
    public void proceed() {

    }

    @Override
    public int getSpeed() {
        return 0;
    }

    @Override
    public Battle.Action getAction(ArrayList<Fighter> fighters) {
        //todo kysy ui:lta
        return new Battle.MeleeAction(fighters.get(1), 10);
    }

    @Override
    public void takeDamage(int dmg) {
        this.setHp(this.getHp()-dmg);

    }

    @Override
    public void die() {

    }

    @Override
    public void receiveItems(Item... items) { inventory.addItems(items); }
    @Override
    public void disposeItem(Item item) { inventory.removeItem(item); }
    @Override
    public boolean hasItem(Item item) { return inventory.containsItem(item); }
    public Item[] getItems() {return inventory.getDataList().toArray(new Item[0]);}

    @Override
    public ItemType getCurrentWeapon() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCurrentWeapon'");
    }

    @Override
    public void changeWeapon(ItemType item) {

    }


}

