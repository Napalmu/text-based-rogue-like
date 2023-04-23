package game.view;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import game.controller.InputManager;
import game.model.GameEventManager;
import game.model.Item;

public class ScreenShop extends Screen{

    private Item selectedItem;
        void shopEntered(ArrayList<Item> items, Runnable onExit) {
        //tallennetaan näppäinkuuntelijat, jotta ne voidaan poistaa, kun kaupasta lähdetään
        ArrayList<InputManager.KeyPressedEvent> shopEvents = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            InputManager.KeyPressedEvent event = new InputManager.KeyPressedEvent(KeyEvent.VK_1+i,
                    () -> selectItem(item));
            shopEvents.add(event);
            InputManager.registerListener(event);
        }
        //välilyönnillä ostetaan
        InputManager.KeyPressedEvent close = new InputManager.KeyPressedEvent(KeyEvent.VK_SPACE,
                this::buyItem);
        //Painamalla nollaa lähdetään kaupasta
        InputManager.KeyPressedEvent exit = new InputManager.KeyPressedEvent(KeyEvent.VK_0,
                ()-> exitShop(onExit, shopEvents));
        shopEvents.add(close); shopEvents.add(exit);

        InputManager.registerListener(close);
        InputManager.registerListener(exit);

        this.mainDrawArea.drawShopItems(items);
    }

    private void exitShop(Runnable onExit, ArrayList<InputManager.KeyPressedEvent> events) {
        //poistetaan kauppaan liittyvät kuuntelijat
        for (InputManager.KeyPressedEvent shopEvent : events) {
            InputManager.unregisterListener(shopEvent);
        }
        this.mainDrawArea.drawMessages("Minne päin haluaisit painua?");
        onExit.run();
        //ViewController.this.moveToNextPlace();
    }

    private void selectItem(Item item) {
        this.selectedItem = item;
        String msg = item.getType().getDescription() + " (osta välilyönnillä)";
        this.infoDrawArea.setMessage(msg);
    }
    private void buyItem() {
        if (this.selectedItem == null) return; //ei itemiä valittuna vielä
        GameEventManager.emitBuyItem(this.selectedItem);
    }
    
}
