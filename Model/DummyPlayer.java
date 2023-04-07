package Model;

import java.util.Random;

// Älä lue tätä pls tää on placeholder mikä tulee iloisesti poistumaan mahd. pian


public class DummyPlayer implements Fighter {
    private int initiative;
    private int storeInitiative;
    private int hp;

    public DummyPlayer (){
        this.initiative = 2;
        this.storeInitiative = initiative;
        this.hp = 4;
    }

    public int proceed(){
        initiative--;
        return initiative;
    }

    public int performAction()
    {
        initiative = storeInitiative;

        Random rand = new Random();
        return 0;
        //return rand.nextInt(0, 5);
    }

    public void takeDamage(int dmg) {
        this.hp -= dmg;
    }

    public void die(){
        // täs kohtaa sä kuolet
    }
}
