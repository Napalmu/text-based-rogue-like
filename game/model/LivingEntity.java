package game.model;

import game.controller.AttackType;
import game.controller.ItemType;

import java.util.ArrayList;
import java.util.function.Consumer;

public class LivingEntity extends Entity implements Fighter, InventoryHolder{
    private final Inventory inventory = new Inventory();
    private Item_Weapon weapon = new Item_Weapon(game.controller.ItemType.NYRKIT);
    private int hp;
    private final int speed;
    private int stamina;

    @Override
    public void receiveItems(Item... item) {
        inventory.addItems(item);
    }
    @Override
    public void disposeItem(Item item) {
        inventory.removeItem(item);
    }
    @Override
    public boolean hasItem(Item item) {
        return inventory.containsItem(item);
    }
    public boolean hasItem(Item item, int amount) {
        return inventory.containsItem(item, amount);
    }

    LivingEntity(int hp, String name, int speed, Item_Weapon weapon){
        super(name);
        this.hp = hp;
        this.speed = speed;
        this.stamina = speed;
        this.weapon = weapon;
    }
    protected void setStamina(int newValue) {
        this.stamina = newValue;
    }
    public void recover(int amount) {
        int val = this.stamina += amount;
        this.setStamina(Math.min(val, this.speed));
    }
    @Override
    public void decreaseStamina(int amount) {
        this.setStamina(this.stamina - amount);
    }
    public int getStamina() {
        return stamina;
    }

    @Override
    public Item_Weapon getCurrentWeapon() {
        return weapon;
    }

    @Override
    public void changeWeapon(Item_Weapon item) {
        this.weapon = item;
    }
    @Override
    public ArrayList<Item> getItems() {
        return inventory.getDataList();
    }

    public int getHp(){
        return hp;
    }


    public int getSpeed() {
        return this.speed;
    }
    protected void setHp(int hp){
        this.hp = hp;
    }
    public void takeDamage(int dmg) {
        this.setHp(this.getHp()-dmg);
    }
}
