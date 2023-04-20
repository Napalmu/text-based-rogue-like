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
            roomText.setContent("Täältä et enää löydä uusia aarteita!");
            this.moveToNextRoom();
            return;
        }
        Player player = EntityManager.getPlayer();
        String[] messages = new String[this.items.length+1];
        messages[0] = "Tervetuloa aarrehuoneeseen!";
        Item[] items1 = this.items;
        for (int i = 0; i < items1.length; i++) {
            Item item = items1[i];
            messages[i+1] = "Sait: " + item.getName();
        }
        roomText.setContent( messages);
        player.addItems(this.items);

        this.moveToNextRoom();
    }

    @Override
    public RoomType getType() {
        return RoomType.TREASURE;
    }
}