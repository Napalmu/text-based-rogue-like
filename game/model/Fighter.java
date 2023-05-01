package game.model;

import java.util.ArrayList;
import game.controller.*;
// Kaikille taisteluun osallistuville suunnattu rajapintaluokka. x

public interface Fighter {
    String getName();
    void decreaseStamina(int amount);
    int getSpeed();
    void recover(int amount);
    int getStamina();
    Item_Weapon getCurrentWeapon();
    void changeWeapon(Item_Weapon item);
    void takeDamage(int dmg);
    int getHp();
    ArrayList<Item> getItems();
    void receiveItems(Item... items);
}                                                                                                         