package game.model.rooms;

import game.controller.GameController;
import game.controller.RoomType;
import game.model.Enemy;

class EnemyRoom extends Room{
    protected Enemy enemy;

    public EnemyRoom(Enemy enemy) {
        this.enemy = enemy;
    }

    @Override
    public void enterRoom() {
        GameController.model.startBattle(this, enemy);
        //TODO taistelun lopputuloski pitäis selvittää
    }

    @Override
    public RoomType getType() {
        return RoomType.ENEMY;
    }
}
