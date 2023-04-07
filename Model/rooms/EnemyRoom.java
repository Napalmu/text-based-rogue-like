package Model.rooms;

import Controller.RoomType;

public class EnemyRoom extends Room{

    @Override
    public void enter() {
        super.enter();
    }

    @Override
    public RoomType getType() {
        return RoomType.ENEMY;
    }
}
