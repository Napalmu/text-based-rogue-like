package game.model;

import java.util.ArrayList;

class DummyPlayer implements Fighter {
    private int speed;
    private int storeSpeed;
    private int hp;

    public DummyPlayer (){
        this.speed = 2;
        this.storeSpeed = speed;
        this.hp = 4;
    }

    
    public int getSpeed() {
        return speed;
    }

    public void proceed(){
        speed--;
    }

    /**
     * @param fighters ArrayList Fighter-rajapintoja
     * @return int[] itegerin taulukko.
     */

    public int[] TargetAndAction(ArrayList<Fighter> fighters)
    {
        speed = storeSpeed;
        int[] choices = {2, 2};
        return choices;
    }

    public void takeDamage(int dmg) {
        this.hp -= dmg;
    }

    public void die(){
        if(this.hp<=0){
        // täs kohtaa sä kuolet :)
        }
    }

}
