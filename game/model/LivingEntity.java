package game.model;

import game.controller.AttackType;
import game.controller.ItemType;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class LivingEntity extends Entity implements Fighter, InventoryHolder{
    private final Inventory inventory = new Inventory();
    private Item_Weapon weapon = new Item_Weapon(game.controller.ItemType.NYRKIT);
    private Item_Armor armor = new Item_Armor(ItemType.NO_ARMOR);
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

    LivingEntity(int hp, String name, int speed){
        super(name);
        this.hp = hp;
        this.speed = speed;
        this.stamina = speed;
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
    public void changeArmor(Item_Armor item) {
        this.armor = item;
    }

    @Override
    public ArrayList<Item> getItems() {
        return inventory.getDataList();
    }

    @Override
    public int itemcount() {
        return inventory.getDataList().size();
    }
    
    @Override
    public Stream<Item> items() {
        return inventory.getDataList().stream();
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
