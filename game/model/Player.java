package game.model;
import java.util.ArrayList;

public class Player extends Entity implements Fighter, InventoryHolder{
    private final Inventory inventory = new Inventory();
    public Player(int hp, String name){
        super(hp, name);
        GameEventManager.registerListener((GameEventManager.BuyItemListener) inventory::addItems);
    }

    public Inventory getInventory() {
        return inventory;
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
    public void receiveItem(Item item) { inventory.addItems(item); }
}

