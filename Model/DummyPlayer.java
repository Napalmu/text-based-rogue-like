package Model;

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
        return 0;
    }

    public void takeDamage(int dmg) {
        this.hp -= dmg;
    }

    public void die(){
        if(this.hp<=0){
        // täs kohtaa sä kuolet
        }
    }
}
