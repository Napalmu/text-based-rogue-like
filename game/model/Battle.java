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
                fighter.TargetAndAction(fighters);
            }

            fighter.proceed();
        }

        // Input continue -> proceedBattle()
    }   

    /** Work in progress varsinaiset aktiojutut
     * @param Target keneen isku tehdään
     * @param dmg tehtävän damadgn määrä
    */

    public void Attack(int target, int dmg) {
        fighters.get(target).takeDamage(dmg);
    }

    public void endBattle() {}
}
