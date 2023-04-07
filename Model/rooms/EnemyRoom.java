package Model.rooms;

import Controller.RoomType;
import View.DrawCommand;

public class EnemyRoom extends Room{

    @Override
    public void enterRoom() {
        mainDrawArea.setContent("Roominfo","Tulit vihollishuoneeseen!");
        this.moveToNextRoom();
    }

    @Override
    public RoomType getType() {
        return RoomType.ENEMY;
    }
}
