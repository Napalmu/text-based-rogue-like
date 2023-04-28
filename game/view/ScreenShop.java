package game.view;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import game.controller.GameController;
import game.controller.InputManager;
import game.controller.InputManager.KeyPressedEvent;
import game.model.GameEventManager;
import game.model.Item;
import game.model.rooms.IRoom;

public class ScreenShop extends ScreenThreePart{
    List<Item> items;
    IRoom room;
    ArrayList <KeyPressedEvent> enterShop = new ArrayList<>();
    ScreenShop(IRoom room, List<Item> items){
        super(room);
        this.items = items;
        this.room = room;
        System.out.println("Shop constructor");
        KeyPressedEvent event = new KeyPressedEvent(
                KeyEvent.VK_SPACE,
                () -> enterShop());
        enterShop.add(event);
        for (int i= 0; i<enterShop.size(); i++) {
            InputManager.registerListener(enterShop.get(i));
        }

    }

    @Override
    List<KeyPressedEvent> getListenersForScreen() {
        return null;
    }

    @Override
    void enterScreen() {
        getMainArea().drawMessages("köysit kaupan", "astu sisään painamalla välilyöntiä");

    }
    void enterShop(){
        for (int i= 0; i<enterShop.size(); i++) {
            InputManager.unregisterListener(enterShop.get(i));
        }

        ShopInside shop = new ShopInside();
        shop.enterScreen();
        List<KeyPressedEvent> shopKeys = shop.getListenersForScreen();
        for (int i= 0; i<shopKeys.size(); i++) {
            InputManager.registerListener(shopKeys.get(i));
        }
    }

    class ShopInside{
    private Item selectedItem;

    ShopInside() {
    }

        void enterScreen () {
            String[] strings = new String[items.size() + 2];
            strings[0] = "Kaupan valikoima:";
            strings[1] = "0: Pois kaupasta";
            for (int i = 0; i < items.size(); i++) {
                Item item = items.get(i);
                strings[i + 2] = (i + 1) + ": " + item.getType().getName() + " " + item.getType().price() + "€";
            }
            getMainArea().addMessage(strings);
        }

        private void selectItem (Item item){
            this.selectedItem = item;
            String msg = item.getType().getDescription() + " (osta välilyönnillä)";
            getInfoArea().setMessage(msg);
        }
        private void buyItem () {
            if (this.selectedItem == null) return; //ei itemiä valittuna vielä
            GameEventManager.emitBuyItem(this.selectedItem);
        }

        List<KeyPressedEvent> getListenersForScreen () {
            ArrayList<KeyPressedEvent> shopEvents = new ArrayList<>();
            for (int i = 0; i < items.size(); i++) {
                Item item = items.get(i);
                KeyPressedEvent event = new KeyPressedEvent(
                        KeyEvent.VK_1 + i,
                        () -> selectItem(item));
                shopEvents.add(event);
            }
            //välilyönnillä ostetaan
            KeyPressedEvent buy = new KeyPressedEvent(
                    KeyEvent.VK_SPACE,
                    this::buyItem);
            //Painamalla nollaa lähdetään kaupasta
            KeyPressedEvent exit = new KeyPressedEvent(
                    KeyEvent.VK_0,
                    () -> GameController.view.enterShopRoom(room, items));

            shopEvents.add(buy);
            shopEvents.add(exit);
            return shopEvents;
        }

}
}
