package game.model;

import java.util.ArrayList;
import java.util.Arrays;

class Battle implements IBattle{
    private ArrayList<Fighter> fighters;
    private Fighter player;

    public Battle(ArrayList<Fighter> fighters) {
        this.fighters = fighters;        
    }
    public Battle(Fighter player, Fighter... enemies) {
        this.fighters = new ArrayList<>(Arrays.asList(enemies));
        this.fighters.add(player);
    }

    public void StartBattle() {
        // Tuloste: Taistelu fighter.nimeä vastaan!

        proceedBattle();
    }


    /* Tarkastetaan onko taistelijoita jäljellä, yli yksi,
     * Tarkastetaan onko jonkun vuoro toimia ja tehdään (work in progress)
     * Vähennetään taistelijoiden odotusaikaa.
     */

    public void proceedBattle(){
        for (Fighter fighter : fighters) {
            if (fighters.size() <= 1){
                endBattle();
            }
            if (fighter.getSpeed() == 0){
                Attack(fighter.TargetAndAction(fighters));
            }

            fighter.proceed();
        }

        // Input continue -> proceedBattle()
    }   

    /** Work in progress varsinaiset aktiojutut
     * @param targetAndDmg int[] joka kertoo kohteen ja damagen määrän. tähän vois olla ehkä joku parempi
     * ku int[] nii voi ehdottaa sellasta :)
    */

    public void Attack(int[] targetAndDmg) {
        fighters.get(targetAndDmg[0]).takeDamage(targetAndDmg[1]);
    }

    public void endBattle() {}

    @Override
    public IEnemy[] getEnemies() {
        IEnemy[] enemies = new IEnemy[this.fighters.size()-1]; //ei pelaajaa
        int i = -1;
        for (Fighter fighter : this.fighters) {
            i++;
            if (fighter instanceof Player) continue;
            enemies[i] = (IEnemy) fighter; //todo fix hack
        }
        return enemies;
    }
}
