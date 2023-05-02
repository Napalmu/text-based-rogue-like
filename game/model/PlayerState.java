package game.model;

/**
 * Kuvaa pelaajan tilaa tiettynä hetkenä. Käytetään näkymän päivittämiseen, kun jokin pelaajan atribuutti muuttuu.
 */
public class PlayerState {
    private final int hp;
    private final Item[] inventory;
    private final int stamina;
    private final int maxStamina;
    private final Item_Weapon currentWeapon;

    public PlayerState(int hp, Item[] inventory, int stamina, int maxStamina, Item_Weapon currentWeapon) {
        this.hp = hp;
        this.inventory = inventory;
        this.stamina = stamina;
        this.maxStamina = maxStamina;
        this.currentWeapon = currentWeapon;
    }
    public Item_Weapon getcurrentWeapon(){
        return currentWeapon;
    }

    public int getStamina() {
        return stamina;
    }

    public int getMaxStamina() {
        return maxStamina;
    }

    public int getHp() {
        return hp;
    }

    public Item[] getItems() {
        return inventory;
    }
}
