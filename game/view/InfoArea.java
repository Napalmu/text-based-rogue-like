package game.view;

import game.model.GameEventManager;
import game.model.Item;

import java.util.Arrays;
import java.util.stream.Stream;

public class InfoArea extends DrawArea{
    private final ScrollingDrawArea messages;
    public InfoArea(int x, int y) {
        super(x, y);
        String[] empty = new String[5];
        Arrays.fill(empty, "");
        this.messages = new ScrollingDrawArea(x+1,y+1,empty);
        GameEventManager.registerListener(this::itemReceived);
    }
    private void itemReceived(Item item) {
        this.messages.addMessage("Saatiin tavara: " + item.getName());
    }

     @Override
     public Stream<CharacterPosition> getStream() {
         return this.messages.getStream();
     }
}
