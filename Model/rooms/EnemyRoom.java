package Model.rooms;

import Controller.RoomType;
import View.DrawCommand;

public class EnemyRoom extends Room{

    @Override
    public void enterRoom() {
        this.render(new DrawCommand(1,1, "Tulit vihollishuoneeseen!"));
        this.moveToNextRoom();
    }

    @Override
    public RoomType getType() {
        return RoomType.ENEMY;
    }
}
