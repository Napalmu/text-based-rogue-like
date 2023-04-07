package Model.rooms;

import Controller.RoomType;
import Model.EntityManager;
import Model.Item;
import Model.Player;
import View.DrawCommand;

public class BossRoom extends EnemyRoom{
    private final Item key;
    private boolean hasEntered = false;

    public BossRoom(Item key) {
        this.key = key;
    }

    @Override
    public void enterRoom() {
        this.hasEntered = true;
        mainDrawArea.setContent("RoomText", "Olet bossi huoneessa nyt!");
        EntityManager.getPlayer().getInventory().removeItem(this.key);
        this.moveToNextRoom();
    }

    @Override
    public boolean canEnter() {
        if (hasEntered) return true;
        Player player = EntityManager.getPlayer();
        boolean hasKey = player.getInventory().containsItem(key);
        if (!hasKey) {
            mainDrawArea.setContent("RoomText", "Et voi mennä bossi huoneeseen ilman avainta");
        }
        return hasKey;
    }

    @Override
    public RoomType getType() {
        return RoomType.BOSS;
    }
}
