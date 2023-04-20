package game.model;

import java.util.ArrayList;

// Älä lue tätä pls tää on placeholder mikä tulee iloisesti poistumaan mahd. pian


class DummyEnemy implements Fighter{
    private int initiative;
    private final int storeInitiative;
    private int hp;

    public DummyEnemy (){
        this.initiative = 2;
        this.storeInitiative = initiative;
        this.hp = 2;
    }

    public void proceed(){
        initiative--;
    }

    public void takeDamage(int dmg) {
        this.hp -= dmg;
    }

    public void die(){
        if (this.hp <=0){
            // jotain luokkaa Battle.removeFighter(this.fighter);
        }
    }

    @Override
    public int getSpeed() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSpeed'");
    }

    @Override
    public int[] TargetAndAction(ArrayList<Fighter> fighters) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'TargetAndAction'");
    }

    public void takeDamage() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'takeDamage'");
    }
}
