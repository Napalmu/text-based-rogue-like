package game.model;

import game.controller.ItemType;

 public class Item_Weapon extends Item_Equipment {
    private int dmg;

     public Item_Weapon(ItemType type) {
        super(type);
        this.dmg = type.getDmg();
    }

    public int getWeaponDmg(){
        return dmg;
    }
    
}
