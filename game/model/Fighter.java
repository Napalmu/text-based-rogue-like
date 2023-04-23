package game.model;

import java.util.ArrayList;
import game.controller.*;
// Kaikille taisteluun osallistuville suunnattu rajapintaluokka. x

interface Fighter {
     void proceed();
     int getSpeed();
     ItemType getCurrentWeapon();
     void changeWeapon(ItemType item);
     Battle.Action getAction(ArrayList<Fighter> fighters);
     void takeDamage(int dmg);
     void die();
}                                                                                                         