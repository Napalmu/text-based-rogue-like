package game.view;

import java.util.Arrays;
import java.util.List;

import game.controller.InputManager.KeyPressedEvent;
import game.model.Fighter;
import game.model.rooms.IRoom;

public class ScreenEnemy extends ScreenThreePart{

    Fighter[] enemies;
    ScreenEnemy(IRoom room, Fighter... enemies) {
        super(room);
        this.enemies = enemies;
    }

    @Override
    List<KeyPressedEvent> getListenersForScreen() {
        return null;
    }

    @Override
    void enterScreen() {
        System.out.println("Enemy room enter "+Arrays.toString(enemies));
    }

}
