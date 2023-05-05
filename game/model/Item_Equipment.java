package game.model;

import game.controller.ItemType;

 class Item_Equipment extends Item {
    private int durability;
    private final int MaxDurability;

     Item_Equipment(ItemType type) {
        super(type);
        this.durability = type.getDurability();
        this.MaxDurability = type.getDurability();
    }
    
    public int getDurability(){
        return this.durability;
    }
    public int getMaxDurability(){
        return this.MaxDurability;
    }
}
