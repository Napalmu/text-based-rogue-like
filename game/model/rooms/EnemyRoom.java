package game.model.rooms;

import game.controller.GameController;
import game.controller.RoomType;
import game.model.Enemy;
import game.model.Fighter;

class EnemyRoom extends Room{
    protected Enemy enemy;

    public EnemyRoom(Enemy enemy) {
        this.enemy = enemy;
    }

    @Override
    public void enterRoom() {
        GameController.model.startBattle(this, (Fighter)enemy);
        //TODO taistelun lopputuloski pitäis selvittää
    }

    @Override
    public RoomType getType() {
        return RoomType.ENEMY;
    }
}
