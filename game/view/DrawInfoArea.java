
package game.view;

import game.model.GameEventManager;
import game.model.Item;

/**
 * Ruudun alin ruutu, jossa näkyy erinäistä infoa liittyen pelin kulkuun
 */
class DrawInfoArea extends DrawScrollingArea {


    DrawInfoArea() {
        this(2, 16, new String[5]);
    }

    DrawInfoArea(int x, int y, String... content) {
        super(x, y, 7, content);

        GameEventManager.registerListener((GameEventManager.ItemReceivedListener) this::itemReceived);
        GameEventManager.registerListener((GameEventManager.BattleActionListener) this::battleAction);
    }

    private void itemReceived(Item item) {
        this.addMessage("Saatiin tavara: " + item.getName());
    }

    private void battleAction(String str) {
        this.addMessage(str);
    }


}
