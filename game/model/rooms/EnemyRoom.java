package game.model.rooms;

import game.controller.GameController;
import game.controller.RoomType;
import game.model.Enemy;

import java.util.Arrays;

class EnemyRoom extends Room{
    protected Enemy enemy;

    public EnemyRoom(Enemy enemy) {
        this.enemy = enemy;
    }

    @Override
    public void enterRoom() {
        if (this.enemy.getHp() > 0) {
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
