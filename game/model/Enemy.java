package game.model;

import java.util.ArrayList;

import game.controller.ItemType;

 public class Enemy extends Entity implements Fighter, IEnemy, InventoryHolder{
    private final Inventory inventory = new Inventory();
     Enemy(int hp, String name){
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
    public void receiveItems(Item... item) { inventory.addItems(item); }

    @Override
    public void disposeItem(Item item) { inventory.removeItem(item); }

    @Override
    public boolean hasItem(Item item) { return inventory.containsItem(item); }
    
    @Override
    public ItemType getCurrentWeapon() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCurrentWeapon'");
    }

    @Override
    public void changeWeapon(ItemType item) {

    }

}
