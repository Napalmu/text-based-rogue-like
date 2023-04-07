package Model;

// Älä lue tätä pls tää on placeholder mikä tulee iloisesti poistumaan mahd. pian


public class DummyEnemy implements Fighter{
    private int initiative;
    private final int storeInitiative;
    private int hp;

    public DummyEnemy (){
        this.initiative = 2;
        this.storeInitiative = initiative;
        this.hp = 2;
    }

    public int proceed(){
        initiative--;

        if(initiative == 0){
            // performAttack();
            initiative = storeInitiative;
            System.out.println(":D");
        }
        return 0;
    }

    @Override
    public int performAction() {
        return 0;
    }

    public void performAttack(Fighter target, int dmg){ }

    public void takeDamage(int dmg) {
        this.hp -= dmg;
    }

    public void die(){
        // jotain luokkaa Battle.removeFighter(this.fighter);
    }
}
