package game.model.rooms;

import game.controller.RoomType;
import game.model.EntityManager;
import game.model.Item;
import game.model.Player;

class TreasureRoom extends Room{
    private final Item[] items;
    public TreasureRoom(Item[] items) {
        this.items = items;
    }

    @Override
    public void enterRoom() {
        if (hasBeenEntered()) {
            this.moveToNextRoom();
            return;
        }
        Player player = EntityManager.getPlayer();
        player.receiveItems(this.items);

        this.moveToNextRoom();
    }

    @Override
    public RoomType getType() {
        return RoomType.TREASURE;
    }
}
