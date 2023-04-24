package game.view;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import game.controller.InputManager;
import game.controller.InputManager.KeyPressedEvent;
import game.model.GameEventManager;
import game.model.Item;
import game.model.rooms.IRoom;

public class ScreenShop extends ScreenThreePart{

    private Item selectedItem;
    List<Item> items;

    ScreenShop(IRoom room, List<Item> items){
        super(room);
        this.items = items;
        System.out.println("Shop constructor");
    }

    @Override
    void enterScreen() {
        String[] strings = new String[items.size()+2];
        strings[0] = "Kaupan valikoima:";
        strings[1] = "0: Pois kaupasta";
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            strings[i+2] = (i+1) +": "+ item.getType().getName() + " " + item.getType().price() + "€";
        }
        getMainArea().addMessage(strings);
    }

    private void selectItem(Item item) {
        this.selectedItem = item;
        String msg = item.getType().getDescription() + " (osta välilyönnillä)";
        getInfoArea().setMessage(msg);
    }
    private void buyItem() {
        if (this.selectedItem == null) return; //ei itemiä valittuna vielä
        GameEventManager.emitBuyItem(this.selectedItem);
    }

    @Override
    List<KeyPressedEvent> getListenersForScreen() {
        ArrayList<KeyPressedEvent> shopEvents = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            InputManager.KeyPressedEvent event = new InputManager.KeyPressedEvent(KeyEvent.VK_1+i,
                    () -> selectItem(item));
            shopEvents.add(event);
        }
        //välilyönnillä ostetaan
        InputManager.KeyPressedEvent close = new InputManager.KeyPressedEvent(KeyEvent.VK_SPACE,
                this::buyItem);
        //Painamalla nollaa lähdetään kaupasta
        InputManager.KeyPressedEvent exit = new InputManager.KeyPressedEvent(KeyEvent.VK_0,
                ()-> exit());
        shopEvents.add(close); 
        shopEvents.add(exit);

        return shopEvents;
    }

}
