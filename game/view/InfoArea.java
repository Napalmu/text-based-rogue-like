package game.view;

import game.model.GameEventManager;
import game.model.Item;

import java.util.Arrays;

public class InfoArea extends ParentDrawCommand{
    private final ScrollingDrawArea messages;
    public InfoArea(int x, int y) {
        super(x, y);
        String[] empty = new String[5];
        Arrays.fill(empty, "");

        this.messages = new ScrollingDrawArea(x+1,y+1,empty);
        this.addChildren(this.messages);
        GameEventManager.registerListener((GameEventManager.ItemReceivedListener) this::itemReceived);
    }
    private void itemReceived(Item item) {
        this.messages.addMessage("Saatiin tavara: " + item.getName());
    }
    public void setMessage(String msg) {
        this.messages.setMessage(msg);
    }
}
