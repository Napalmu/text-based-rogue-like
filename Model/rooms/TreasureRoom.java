package Model.rooms;

import Controller.RoomType;
import Model.EntityManager;
import Model.Item;
import Model.Player;
import View.DrawCommand;

public class TreasureRoom extends Room{
    private final Item[] items;
    //huoneessa ollaan jo käyty
    private boolean hasEntered = false;
    public TreasureRoom(Item[] items) {
        this.items = items;
    }

    @Override
    public void enterRoom() {
        if (hasEntered) {
            this.render(new DrawCommand(1,1, "Täältä et enää löydä uusia aarteita!"));
            this.moveToNextRoom();
            return;
        }
        hasEntered = true;
        Player player = EntityManager.getPlayer();
        String[] messages = new String[this.items.length+1];
        messages[0] = "Tervetuloa aarrehuoneeseen!";
        Item[] items1 = this.items;
        for (int i = 0; i < items1.length; i++) {
            Item item = items1[i];
            messages[i+1] = "Sait: " + item.getName();
        }
        this.render(new DrawCommand(1, 1, messages));
        player.getInventory().addItems(this.items);

        this.moveToNextRoom();
    }

    @Override
    public RoomType getType() {
        return RoomType.TREASURE;
    }
}
