package game.model;

import java.util.ArrayList;

// Kaikille taisteluun osallistuville suunnattu rajapintaluokka. x

interface Fighter {
    public void proceed();
    public int getSpeed();

    public int[] TargetAndAction(ArrayList<Fighter> fighters);
    public void takeDamage(int dmg);
    public void die();
}                                                                                                         