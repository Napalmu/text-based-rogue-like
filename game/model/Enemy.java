package game.model;

import java.util.ArrayList;

public class Enemy extends Entity implements Fighter, IEnemy{
    public Enemy(int hp, String name){
        super(hp, name);
    }

    //TODO implement
    @Override
    public void proceed() {

    }

    @Override
    public int getSpeed() {
        return 0;
    }

    @Override
    public int[] TargetAndAction(ArrayList<Fighter> fighters) {
        return new int[0];
    }

    @Override
    public void takeDamage(int dmg) {

    }

    @Override
    public void die() {

    }
}
