package game.view;

import game.controller.AttackType;
import game.controller.InputManager;
import game.controller.InputManager.KeyPressedEvent;
import game.model.*;
import game.model.rooms.IRoom;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

class ScreenEnemy extends ScreenThreePart{

    private final Fighter[] enemies;
    protected final IBattle battle;

    ScreenEnemy(IRoom room, IBattle battle, Fighter... enemies) {
        super(room);
        this.enemies = enemies;
        this.battle = battle;
    }

    @Override
    protected List<KeyPressedEvent> getListenersForScreen() {
        return null;
    }

    @Override
    protected void enterScreen() {
        for (EnemyFighter enemy : battle.getEnemies()) {
            //päivitetään näkymä aina, kun jokin vihollisten ominaisuus muuttuu
            enemy.registerHpListener((hp)-> update());
            enemy.registerStaminaListener((st) -> update());
        }
        update();
        //tehdään ensimmäinen siirto
        chooseAttack();
    }
    private String attackTypeToString(AttackType type) {
        switch (type) {
            case MELEE:
                return "Lyö";
            case RANGED:
                return "Ammu";
            case NONE:
                return "Odota";
            case INSTAKILL:
                return "INSTAKILL!";
            default:
                return "Hyökkäys";
        }
    }
    //kun käyttäjä on päättänyt mitä hyökkäystä käyttää:
    private void onAttack(AttackType type) {
        getInfoArea().clear();
        switch (type) {
            case MELEE:
                this.battle.meleeAttack(battle.getEnemies()[0]);
                break;
            case INSTAKILL:
                this.battle.instaKill(battle.getEnemies()[0]);
                break;
            case NONE:
                this.battle.recoverStamina(EntityManager.getPlayer());
                break;
            case RANGED:
                this.battle.rangedAttack(battle.getEnemies()[0]);
                break;
            default:
                break;
        }
        //Battle.Action meleeAction = ActionFactory.createMeleeAction(battle.getEnemies()[0], battle.getPlayer());
        //this.battle.move(type);
        //jos kaikki viholliset ovat kuolleet, lopetetaan taistelu
        if (battle.isOnGoing()) chooseAttack(); //rekursiivinen kutsu
        else endBattle();
    }
    protected void chooseAttack() {
        //näytetään vain hyökkäykset, joihin pelaajalla on tarpeeksi kestävyyttä
        List<AttackType> types = this.battle.getPossibleAttackTypesForPlayer();
        ArrayList<Option> options = new ArrayList<>();

        for (int i = 0; i < types.size(); i++) {
            AttackType type = types.get(i);
            options.add(new Option(attackTypeToString(type), ()-> {
                this.onAttack(type);
            }, KeyEvent.VK_1+i));
        }
        this.chooseFromOptions(options, "Valitse hyökkäystapa: ");
    }

    private void endBattle() {
        this.getMainArea().setMessage("Voitit taistelun! Sait tavaroita:");
        for (Fighter enemy : this.enemies) {
            for (Item item : enemy.getItems()) {
                this.getMainArea().addMessage(item.getName());
            }
        }
        //siirrytään seuraavaan huoneeseen
        this.registerDirections();
    }

    protected void update() {
        StringBuilder builder = new StringBuilder();
        for (EnemyFighter enemy : battle.getEnemies()) {
            builder.append(enemy.getName()).append(" ")
                    .append(enemy.getHp()).append("hp ")
                    .append("kestävyys: ").append(enemy.getStamina());
            builder.append("\t");
        }
        //todo vihollisten ascii-grafiikka kuvat
        this.getMainArea().setMessage(builder.toString());
    }
}
