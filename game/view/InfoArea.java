package game.view;

import game.model.GameEventManager;
import game.model.Item;

 class InfoArea extends ScrollingDrawArea{
        

    InfoArea(){ this(2,16, new String[5]); }
    InfoArea(int x, int y){ this(x,y, new String[5]); }
    InfoArea(int x, int y, String[] content) {
        super(x, y, 7, content);

        GameEventManager.registerListener((GameEventManager.ItemReceivedListener) this::itemReceived);
    }
    
    private void itemReceived(Item item) {
        this.addMessage("Saatiin tavara: " + item.getName());
    }

    
}
