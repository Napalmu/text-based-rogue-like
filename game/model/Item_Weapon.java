package game.model;

import game.controller.ItemType;

 public class Item_Weapon extends Item_Equipment {
    private int dmg;

     public Item_Weapon(ItemType type) {
        super(type);
        this.dmg = 5;
        //TODO Auto-generated constructor stub
    }

    public int getWeaponDmg(){
        return dmg;
    }
    
}
