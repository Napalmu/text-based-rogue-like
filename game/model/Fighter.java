package game.model;

import java.util.ArrayList;
import game.controller.*;
// Kaikille taisteluun osallistuville suunnattu rajapintaluokka. x

interface Fighter {
    public void proceed();
    public int getSpeed();
    public ItemType getCurrentWeapon();
    public void changeWeapon(ItemType item);
    public Battle.Action getAction(ArrayList<Fighter> fighters);
    public void takeDamage(int dmg);
    public void die();
}                                                                                                         