package Model;

public class Player extends Entity {
    private Inventory inventory = new Inventory();
    public Player(int hp, String name){
        super(hp, name);
    }

    public Inventory getInventory() {
        return inventory;
    }
}

