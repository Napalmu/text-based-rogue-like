package game.view;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import game.controller.AttackType;
import game.controller.InputManager;
import game.controller.InputManager.KeyPressedEvent;
import game.model.EnemyFighter;
import game.model.Fighter;
import game.model.IBattle;
import game.model.Item;
import game.model.rooms.IRoom;

public class ScreenEnemy extends ScreenThreePart{

    private final Fighter[] enemies;
    private final IBattle battle;

    ScreenEnemy(IRoom room, IBattle battle, Fighter... enemies) {
        super(room);
        this.enemies = enemies;
        this.battle = battle;
    }

    @Override
    List<KeyPressedEvent> getListenersForScreen() {
        return null;
    }

    @Override
    void enterScreen() {
        for (EnemyFighter enemy : battle.getEnemies()) {
            enemy.registerHpListener((hp)-> update());
            enemy.registerStaminaListener((st) -> update());
        }
        update();

        chooseAttack();
    }
    private String attackTypeString(AttackType type) {
        switch (type) {
            case MELEE:
                return "Lyö";
            case RANGED:
                return "Ammu";
            case NONE:
                return "Odota";
            default:
                return "Hyökkäys";
        }
    }
    private void onAttack(AttackType type) {
        this.battle.move(type);
        if (battle.isOnGoing()) chooseAttack();
        else endBattle();
    }
    private void chooseAttack() {
        List<AttackType> types = this.battle.getPossibleAttackTypesForPlayer();
        ArrayList<KeyPressedEvent> events = new ArrayList<>();
        for (int i = 0; i < types.size(); i++) {
            AttackType type = types.get(i);
            KeyPressedEvent event = new KeyPressedEvent(KeyEvent.VK_1 + i, () -> {
                for (KeyPressedEvent e : events) {
                    InputManager.unregisterListener(e);
                }
                this.onAttack(type);
            });
            events.add(event);
            InputManager.registerListener(event);
            getInfoArea().addMessage((i+1) + ": " + attackTypeString(type));
        }
    }

    private void endBattle() {
        StringBuilder winMessage = new StringBuilder("Voitit taistelun! Sait tavaroita:");
        for (Fighter enemy : this.enemies) {
            for (Item item : enemy.getItems()) {
                winMessage.append("\n").append(item.getName());
            }
        }
        this.getMainArea().setMessage(winMessage.toString());
        this.registerDirections();
    }

    private void update() {
        StringBuilder builder = new StringBuilder();
        for (EnemyFighter enemy : battle.getEnemies()) {
            builder.append(enemy.getName()).append(" ")
                    .append(enemy.getHp()).append("hp ")
                    .append("kestävyys: ").append(enemy.getStamina());
            builder.append("\t");
        }
        //todo vihollisten kuvat
        this.getMainArea().setMessage(builder.toString());
    }
}
