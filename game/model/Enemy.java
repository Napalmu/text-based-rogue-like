package game.model;

import java.util.ArrayList;

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
    public void receiveItem(Item item) { inventory.addItems(item); }
}
