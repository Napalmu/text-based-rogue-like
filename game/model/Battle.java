package game.model;

import game.controller.AttackType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Battle implements IBattle{
    //Actionit kuvaavat tiettyä hyökkäystä, jolla on kohteita
    public abstract static class Action {
        protected Fighter[] targets;
        private final AttackType type;
        Action(Fighter[] targets, AttackType type) {
            this.targets = targets;
            this.type = type;
        }
        Action(Fighter fighter, AttackType type) {
            this.targets = new Fighter[] {fighter};
            this.type = type;
        }
        public abstract void doAction();

        public AttackType getType() {
            return type;
        }

        public abstract String toString(Fighter source);
    }

    static class MeleeAction extends Action {
        private final int dmg;
        public MeleeAction(Fighter target, int dmg, AttackType type) {
            super(target, type);
            this.dmg = dmg;
        }

        @Override
        public void doAction() {
            for (Fighter target : this.targets) {
                target.takeDamage(this.dmg);
            }
        }

        @Override
        public String toString(Fighter source) {
            return source.getName() + " löi " + this.targets[0].getName() + " (-"+dmg+"hp) -"+getType().getStaminaCost()+"st";
        }
    }
    //Action, jossa taistelija ei tee mitään vaan odottaa
    //Silloin nousee stamina
    static class NoneAction extends Action {
        private int before;
        NoneAction(Fighter fighter, AttackType type) {
            super(fighter, type);
        }

        @Override
        public void doAction() {
            this.before = this.targets[0].getStamina();
            this.targets[0].recover(1);
        }

        @Override
        public String toString(Fighter source) {
            if (this.before < source.getStamina()) {
                return source.getName() + " ei tehnyt mitään (+1st)";
            }
            return source.getName() + " ei tehnyt mitään (+0st)";
        }
    }

    private final ArrayList<EnemyFighter> enemies;
    private final Fighter player;

    private boolean isOnGoing = true;

    Battle(Fighter player, EnemyFighter... enemies) {
        this.player = player;
        this.enemies = new ArrayList<>(Arrays.asList(enemies));
    }
    //Palauttaa hyökkäystyyppiä vastaavan toiminnon
    private Action getAction(AttackType type,Fighter caster, Fighter... targets) {
        //HUOM toimii vain väliaikaisesti näin
        //tulevaisuudessa katsotaan mikä ase tekijällä on
        int meleeDmg = caster instanceof Player ? 30 : 10;
        switch (type) {
            case NONE:
                return new NoneAction(caster, AttackType.NONE);
            case MELEE:
                return new MeleeAction(targets[0],meleeDmg, AttackType.MELEE);
            case RANGED:
                return new MeleeAction(targets[0], 15, AttackType.RANGED);
            case INSTAKILL:
                return new MeleeAction(targets[0], 1000, AttackType.INSTAKILL);
        }
        throw new IllegalArgumentException("Unknown attack type!");
    }
    @Override
    public boolean isOnGoing() {
        return isOnGoing;
    }

    @Override
    public List<AttackType> getPossibleAttackTypesForPlayer() {
        AttackType[] types = new AttackType[] {AttackType.NONE, AttackType.MELEE, AttackType.RANGED, AttackType.INSTAKILL};
        ArrayList<AttackType> result = new ArrayList<>();
        int stamina = this.player.getStamina();
        for (AttackType type : types) {
            if (type.getStaminaCost() <= stamina) result.add(type);
        }
        return result;
    }

    @Override
    public void meleeAttack(EnemyFighter enemy) {
        int weaponDmg = this.player.getCurrentWeapon().getWeaponDmg();
        MeleeAction meleeAction = new MeleeAction(enemy, weaponDmg, AttackType.MELEE);
        Attack(meleeAction, this.player);
        this.enemyMoves();
    }

    @Override
    public void instaKill(EnemyFighter enemy) {
        MeleeAction meleeAction = new MeleeAction(enemy, 10000, AttackType.MELEE);
        Attack(meleeAction, this.player);
        this.enemyMoves();
    }

    //Suoritetaan vihollisten siirrot
    private void enemyMoves(){
        //kopio listasta, jotta sitä voi muokata
        for (EnemyFighter enemy : new ArrayList<>(enemies)) {
            AttackType type = enemy.getAttackType(enemy.getStamina());
            Attack(getAction(type, enemy, this.player), enemy);
        }
        
        //Peli sammuu, kun pelaaja kuolee
        if (this.player.getHp() <= 0) {System.exit(0);}
        
        if (enemies.isEmpty()){
            endBattle();
        }
    }   

    /** Work in progress varsinaiset aktiojutut
     * @param action action
    */

    void Attack(Action action, Fighter source) {
        source.decreaseStamina(action.getType().getStaminaCost());
        action.doAction();
        //tarkistetaan kuoliko vihollisia (tehdään kopio, jotta listasta voi poistaa)
        for (EnemyFighter fighter : new ArrayList<>(enemies)) {
            if (fighter.getHp() <= 0){
                removeFighter(fighter);
            }
        }
        GameEventManager.emitBattleAction(action.toString(source));
    }

    void endBattle() {
        this.isOnGoing = false;
        this.player.recover(100000);
    }

    @Override
    public EnemyFighter[] getEnemies() {
        return this.enemies.toArray(new EnemyFighter[0]);
    }

    void removeFighter(EnemyFighter fighter){
        ArrayList<Item> drops = fighter.getItems();
        //fighter.die();
        this.enemies.remove(fighter);
        player.receiveItems(drops.toArray(new Item[0]));
    }
}
