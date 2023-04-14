package game.model;

import java.util.ArrayList;

class Battle {
    private ArrayList<Fighter> fighters;

    public Battle(ArrayList<Fighter> fighters) {
        this.fighters = fighters;        
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
}
