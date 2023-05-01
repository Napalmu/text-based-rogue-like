package game.view;

import game.controller.InputManager.KeyPressedEvent;
import game.model.Fighter;
import game.model.rooms.IRoom;

import java.util.List;
//todo käytä samaa logiikkaa kuin ScreenEnemy
class ScreenBossroom extends ScreenThreePart {

    Fighter boss;

    ScreenBossroom(IRoom room, Fighter boss) {
        super(room);
        this.boss = boss;
    }

    @Override
    protected List<KeyPressedEvent> getListenersForScreen() {
        return null;
    }

    @Override
    protected void enterScreen() {
        if (!getRoom().canEnter()) {
            System.out.println("Bruh");
            getMainArea().addMessage("Höh, näyttää siltä, että tarvitset", "avaimen päästäksesi sisään");
            this.registerDirections();
            return;
        }
        System.out.println("Jou?");
        if (getRoom().hasBeenEntered()) {
            getMainArea().addMessage(
                    "Tässä huoneessa asui aikoinaan hirveä mörkö",
                    "onneksi tapoit hänet jo");
        } else {
            getMainArea().addMessage("Tässä huoneessa asuu hirveä mörkö");
        }
        //BossScreen ja EnemyScreen todennäköisesti tulevat jakamaan toiminnallisuutta, joten voisi harkita
        //jonkinlaista perintä suhdetta
        //todo tee tämä vasta, kun taistelu loppu.
        this.registerDirections();
    }

}
