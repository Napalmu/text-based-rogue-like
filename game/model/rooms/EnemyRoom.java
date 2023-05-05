package game.model.rooms;

import game.controller.GameController;
import game.controller.RoomType;
import game.model.Enemy;
import game.model.Fighter;

import java.util.Arrays;
import java.util.List;

class EnemyRoom extends Room{
    protected Enemy enemy;

    public EnemyRoom(Enemy enemy) {
        this.enemy = enemy;
    }

    @Override
    public void enterRoom() {
        if (!hasBeenEntered()) {
            GameController.model.startBattle(this, enemy);
        } else {
            GameController.view.enterMessageRoom(this, Arrays.asList("Olet jo tuhonnut vihollisen!"));
        }
    }

    @Override
    public RoomType getType() {
        return RoomType.ENEMY;
    }
}
