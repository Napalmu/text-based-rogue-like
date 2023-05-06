package game.view;

import game.controller.InputManager.KeyPressedEvent;
import game.model.EnemyFighter;
import game.model.Fighter;
import game.model.IBattle;
import game.model.rooms.IRoom;

import java.util.List;
class ScreenBossroom extends ScreenEnemy {

    Fighter boss;

    ScreenBossroom(IRoom room, IBattle battle, Fighter boss) {
        super(room, battle, boss);
        this.boss = boss;
    }

    @Override
    protected List<KeyPressedEvent> getListenersForScreen() {
        return null;
    }

    @Override
    protected void enterScreen() {
        if (!getRoom().canEnter()) {
            getMainArea().addMessage("Höh, näyttää siltä, että tarvitset", "avaimen päästäksesi sisään");
            this.registerDirections();
            return;
        }
        if (getRoom().hasBeenEntered()) {
            getMainArea().addMessage(
                    "Tässä huoneessa asui aikoinaan hirveä mörkö",
                    "onneksi tapoit hänet jo");
            registerDirections();
        } else {
            getMainArea().addMessage("Tässä huoneessa asuu hirveä mörkö");
            for (EnemyFighter enemy : battle.getEnemies()) {
                //päivitetään näkymä aina, kun jokin vihollisten ominaisuus muuttuu
                enemy.registerHpListener((hp)-> update());
                enemy.registerStaminaListener((st) -> update());
            }
            update();
            //tehdään ensimmäinen siirto
            chooseAttack();
        }
    }


}
