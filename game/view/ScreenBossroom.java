package game.view;

import java.util.List;

import game.controller.InputManager.KeyPressedEvent;
import game.model.Fighter;
import game.model.rooms.IRoom;

public class ScreenBossroom extends ScreenThreePart{

    Fighter boss;
    ScreenBossroom(IRoom room, Fighter boss) {
        super(room);        
        this.boss = boss;

        if (room.hasBeenEntered()) {
            getMainArea().addMessage(
                "Tässä huoneessa asui aikoinaan hirveä mörkö",
                "onneksi tapoit hänet jo");
        } else {
            getMainArea().addMessage("Tässä huoneessa asuu hirveä mörkö");
        }
    }

    @Override
    List<KeyPressedEvent> getListenersForScreen() {
        return null;
    }

    @Override
    void enterScreen() {
        
    }
    
}
