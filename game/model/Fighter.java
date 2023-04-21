package game.model;

import java.util.ArrayList;

// Kaikille taisteluun osallistuville suunnattu rajapintaluokka. x

interface Fighter {
    public void proceed();
    public int getSpeed();

    public Battle.Action getAction(ArrayList<Fighter> fighters);
    public void takeDamage(int dmg);
    public void die();
    public int getHp();
    ArrayList<Item> getItems();
    void addItems(ArrayList<Item> items);
}                                                                                                         