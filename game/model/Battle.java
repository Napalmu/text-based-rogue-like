package game.model;

import java.util.ArrayList;
import java.util.Arrays;

class Battle implements IBattle{

    public abstract static class Action {
        protected Fighter[] targets;
        public Action(Fighter[] targets) {
            this.targets = targets;
        }
        public Action(Fighter fighter) {
            this.targets = new Fighter[] {fighter};
        }
        public abstract void doAction();
    }
    
    //  note to self: tästä kohtaa alkaa

    public static class MeleeAction extends Action {
        private final int dmg;
        public MeleeAction(Fighter target, int dmg) {
            super(target);
            this.dmg = dmg;
        }

        @Override
        public void doAction() {
            for (Fighter target : this.targets) {
                target.takeDamage(this.dmg);
            }
        }
    }
    private final ArrayList<Fighter> fighters;
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

    private void proceedBattle(){

        for (Fighter fighter : fighters) {
            if (fighters.size() <= 1){
                endBattle();
            }
            if (fighter.getSpeed() == 0){
                Attack(fighter.getAction(fighters));
            }

            fighter.proceed();
        }

        // Input continue -> proceedBattle()
    }   

    /** Work in progress varsinaiset aktiojutut
     * @param action action
    */

    public void Attack(Action action) {
        action.doAction();
        for (Fighter fighter : fighters) {
            if (fighter.getHp() <= 0){
                RemoveFighter(fighter);
            }
        }
    }

    public void endBattle() {
        // player inputin jälkeen sulkeutuu taistelu ja tulee taas mappi esille
    }

    @Override
    public IEnemy[] getEnemies() {
        IEnemy[] enemies = new IEnemy[this.fighters.size() - 1]; //ei pelaajaa
        int i = -1;
        for (Fighter fighter : this.fighters) {
            i++;
            if (fighter instanceof Player) continue;
            enemies[i] = (IEnemy) fighter; //todo fix hack
        }
        return enemies;
    }

    public void RemoveFighter (Fighter fighter){
        ArrayList<Item> drops = fighter.getItems();
        fighter.die();

        player.addItems(drops);
    }
}
