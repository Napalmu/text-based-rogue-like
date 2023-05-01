package game.model;

import game.controller.GameController;
import game.controller.ItemType;

public class Player extends LivingEntity implements Fighter, InventoryHolder{
    public Player(int hp, String name, int speed){
        super(hp, name, speed);
    }

    @Override
    protected void setHp(int hp) {
        super.setHp(hp);
        GameEventManager.emitPlayerStateChanged(getState());
    }

    @Override
    protected void setStamina(int newValue) {
        super.setStamina(newValue);
        GameEventManager.emitPlayerStateChanged(getState());
    }

    @Override
    public void receiveItems(Item... items) {
        super.receiveItems(items);
        for (Item item : items) {
            GameEventManager.emitItemReceivedEvent(item);
        }
        GameEventManager.emitPlayerStateChanged(getState());
    }
    @Override
    public void disposeItem(Item item) {
        super.disposeItem(item);
        GameEventManager.emitPlayerStateChanged(getState());
    }
    public PlayerState getState() {
        return new PlayerState(this.getHp(), this.getItems().toArray(new Item[0]), getStamina(), getSpeed());
    }

    public boolean canAfford(int price) {
        return hasItem(EntityManager.createItem(ItemType.COIN), price);
    }
}



