package game.view;

import game.controller.GameController;
import game.controller.InputManager;
import game.controller.InputManager.KeyPressedEvent;
import game.model.Item;
import game.model.rooms.IRoom;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

class ScreenShop extends ScreenThreePart {
    private final List<Item> items;
    private List<KeyPressedEvent> shopKeys;
    private final ArrayList<KeyPressedEvent> enterShop = new ArrayList<>();

    ScreenShop(IRoom room, List<Item> items) {
        super(room);
        this.items = items;
    }

    @Override
    protected List<KeyPressedEvent> getListenersForScreen() {
        return null;
    }

    @Override
    protected void enterScreen() {
        getMainArea().drawMessages("Löysit kaupan", "astu sisään painamalla välilyöntiä");
        //kauppaan mennään välilyönnillä
        KeyPressedEvent event = new KeyPressedEvent(
                KeyEvent.VK_SPACE,
                this::enterShop);
        enterShop.add(event);
        for (KeyPressedEvent keyPressedEvent : enterShop) {
            InputManager.registerListener(keyPressedEvent);
        }
        //ennen välilyönnin painamista on mahdollista vielä yksinkertaisesti ohittaa kauppa
        this.registerDirections();

    }

    @Override
    protected void exitScreen() {
        for (KeyPressedEvent keyPressedEvent : enterShop) {
            InputManager.unregisterListener(keyPressedEvent);
        }
        if (shopKeys != null) {
            for (KeyPressedEvent shopKey : shopKeys) {
                InputManager.unregisterListener(shopKey);
            }
        }

    }

    private void enterShop() {
        //välilyönti ynnä muut eventit pois
        for (KeyPressedEvent keyPressedEvent : enterShop) {
            InputManager.unregisterListener(keyPressedEvent);
        }
        //kaupasta ei voi enään suoraan lähteä toiseen huoneeseen
        //vaan käyttäjän pitää lähteä tavaranäkymästä painamalla nollaa
        this.unregisterDirections();

        ShopInside shop = new ShopInside();
        shop.enterScreen();
        shopKeys = shop.getListenersForScreen();
        for (KeyPressedEvent shopKey : shopKeys) {
            InputManager.registerListener(shopKey);
        }
    }

    private class ShopInside {
        private Item selectedItem;

        private void enterScreen() {
            String[] strings = new String[items.size() + 2];
            strings[0] = "Kaupan valikoima:";
            strings[1] = "0: Pois kaupasta";
            for (int i = 0; i < items.size(); i++) {
                Item item = items.get(i);
                strings[i + 2] = (i + 1) + ": " + item.getType().getName() + " " + item.getType().price() + "€";
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
            boolean canAfford = GameController.model.canBuyItem(this.selectedItem);
            if (canAfford) {
                GameController.model.buyItem(this.selectedItem);
            } else {
                getInfoArea().addMessage("Ei ole varaa!");
            }
        }

        private void exit() {
            for (KeyPressedEvent keyPressedEvent : enterShop) {
                InputManager.unregisterListener(keyPressedEvent);
            }
            for (KeyPressedEvent shopKey : shopKeys) {
                InputManager.unregisterListener(shopKey);
            }
            GameController.view.enterShopRoom(room, items);
        }

        private List<KeyPressedEvent> getListenersForScreen() {
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
                    this::exit);

            shopEvents.add(buy);
            shopEvents.add(exit);
            return shopEvents;
        }

    }
}
