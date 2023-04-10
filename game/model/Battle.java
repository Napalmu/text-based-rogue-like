package game.model;

import java.util.ArrayList;

public class Battle {
    private ArrayList<Fighter> fighters;

    public Battle(ArrayList<Fighter> fighters) {
        this.fighters = fighters;        
    }

    public void StartBattle() {
        // Tuloste: Taistelu fighter.nimeä vastaan!

        proceedBattle();
    }

    public void proceedBattle(){
        for (Fighter fighter : fighters) {
            fighter.proceed();

            // if fighter.proceed() == 0 { fighter.doAction(); }
        }

        // Input continue -> proceedBattle()
    }

    public void endBattle() {}
}
