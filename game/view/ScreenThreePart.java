package game.view;

import game.controller.InputManager;
import game.model.EntityManager;
import game.model.Item;
import game.model.Player;
import game.model.rooms.CompassPoints;
import game.model.rooms.Direction;
import game.model.rooms.Enterable;
import game.model.rooms.IRoom;
import game.view.ascii_art.AsciiDrawing;

import java.awt.event.KeyEvent;
import java.util.*;


/**
 * Näkymä, jossa on kolme eri osaa: MainArea, InfoArea ja TextArea
 * Tälläinen olio luodaan aina, kun huoneeseen mennään.
 * Tämän perivät luokat saavat käyttöönsä pääsyn näkymän eri osiin
 */
abstract class ScreenThreePart extends Screen {
    private final DrawInfoArea infoDrawArea;
    private final DrawMainArea mainDrawArea;
    private final DrawTextArea textDrawArea;
    private final DrawMapArea mapDrawArea;
    private final DrawCommand art;
    protected final IRoom room;
    //näppäinkuuntelijat, jotka liittyvät huoneesta pois lähtemiseen
    private ArrayList<InputManager.KeyPressedEvent> events;



    ScreenThreePart(IRoom room) {
        this.room = room;
        this.infoDrawArea = new DrawInfoArea();
        this.mainDrawArea = new DrawMainArea();
        this.textDrawArea = new DrawTextArea();
        this.mapDrawArea = new DrawMapArea();
        this.art = new DrawTextCommand(0, 0, AsciiDrawing.SCREEN.getArt());

        InputManager.registerListener(new InputManager.KeyPressedEvent(KeyEvent.VK_I, ()-> {
            new InventoryView().accessInventory();
        }));
    }

    final DrawInfoArea getInfoArea() {
        return infoDrawArea;
    }

    final DrawMainArea getMainArea() {
        return mainDrawArea;
    }

    final DrawTextArea getTextArea() {
        return textDrawArea;
    }

    final DrawCommand getArt() {
        return art;
    }

    @Override
    public DrawCommand getDrawCommand() {
        return new DrawCommand(0, 0, art, infoDrawArea, mainDrawArea, textDrawArea, mapDrawArea);
    }

    protected void unregisterDirections() {
        if (this.events == null) {
            return; //eventtejä ei olla rekisteröity vielä
        }
        for (InputManager.KeyPressedEvent directionEvent : this.events) {
            InputManager.unregisterListener(directionEvent);
        }
        //tyhjennetään alue
        infoDrawArea.setMessage("");
    }
    private int arrowKeys(int i) {
        switch (i) {
            case KeyEvent.VK_1:
                return KeyEvent.VK_UP;
            case KeyEvent.VK_2:
                return KeyEvent.VK_RIGHT;
            case KeyEvent.VK_3:
                return KeyEvent.VK_DOWN;
            case KeyEvent.VK_4:
                return KeyEvent.VK_LEFT;
        }
        return i;
    }
    //kysyy käyttäjältä suunnan, johon käyttäjä haluaa siirtyä tästä huoneesta
    protected void registerDirections() {
        List<Direction> nextPlaces = this.room.getDestinations();
        ArrayList<Option> options = new ArrayList<>();
        //seuraavan koodin idea on, että tietyille ilmansuunnille annetaan aina samat numerot:
        //(hyvin kyseenalaista koodia)
        //järjestetään kohteen, niin että pienemmät numerot tulevat ensin.
        //eli esim. pohjoisen numero on 1 ja etelän 3.
        //jos kohde ei ole ilmansuunta, se laitetaan ilmansuuntien jälkeen
        nextPlaces.sort((o1, o2) -> {
            int key1 = CompassPoints.getMatchingInteger(o1.getLabel());
            int key2 = CompassPoints.getMatchingInteger(o2.getLabel());
            if (key1 == -1) return 1;
            if (key2 == -1) return -1;
            return Integer.compare(key1, key2);
        });
        //numerot, jotka on jo asetettu johonkin toimintoon
        TreeSet<Integer> usedKeys = new TreeSet<>();
        for (Direction nextRoom : nextPlaces) {
            int key = CompassPoints.getMatchingInteger(nextRoom.getLabel());
            usedKeys.add(key);
        }
        for (Direction nextRoom : nextPlaces) {
            Enterable onChoice = nextRoom.getDestination();
            //kun nappia painetaan
            Runnable run = () -> {
                room.exit();
                onChoice.enter();
            };
            //esim pohjoista vastaa numero 1
            int key = CompassPoints.getMatchingInteger(nextRoom.getLabel());
            if (key == -1) //ei ilmansuunta
                key = usedKeys.isEmpty() ? 1 : usedKeys.last() + 1;//etsitään seuraava vapaa numero
            usedKeys.add(key);
            options.add(new Option(nextRoom.getLabel(), run, KeyEvent.VK_1+(key-1)));
        }
        chooseFromOptions(options, "Valitse suunta:");
    }
    protected static class Option {
        private String text;
        private Runnable event;
        private int key;

        public Option(String text, Runnable event, int key) {
            this.text = text;
            this.event = event;
            this.key = key;
        }

        public String getLabel() {
            return text;
        }

        public int getKey() {
            return key;
        }

        public void run() {
            this.event.run();
        }
    }


    private class InventoryView {
        private final String[] prevContent;
        private final String[] prevInfoContent;
        private final ArrayList<InputManager.KeyPressedEvent> prevEvents;
        public InventoryView() {
            //tallennetaan mitä ruuduilla oli ennen tähän näkymään tulemista
            this.prevContent = getMainArea().getContent();
            this.prevInfoContent = getInfoArea().getContent();
            this.prevEvents = events;
        }
        //käyttäjä valitsee tietyn itemin
        private void item(Item item) {
            ArrayList<Option> options = new ArrayList<>();
            getMainArea().setMessage(item.getName());
            options.add(new Option("Lähde", this::accessInventory, KeyEvent.VK_0));
            //TODO asioita mitä itemillä voi tehdä. Esim. aseeksi valitseminen
            chooseFromOptions(options, "Valitse toiminto: ");
        }
        private void exit() {
            //palautetaan vanhat tiedot
            getMainArea().setMessage(this.prevContent);
            getInfoArea().setMessage(this.prevInfoContent);
            events = prevEvents;
            for (InputManager.KeyPressedEvent event : events) {
                InputManager.registerListener(event);
            }
        }
        private void accessInventory() {
            getMainArea().setMessage("Reppusi:");
            Player player = EntityManager.getPlayer();
            ArrayList<Option> options = new ArrayList<>();
            ArrayList<Item> items = player.getItems();
            HashSet<Item> set = new HashSet<>(items);
            int i = 0;
            //TODO sama koodi ku DrawTextArea
            for (Item item : set) {
                int freq = Collections.frequency(items, item);
                getMainArea().addMessage((i+1) + ": " + item.getName() + " " + freq+" kpl");
                options.add(new Option(item.getName(),()-> {
                    item(item);
                }, KeyEvent.VK_1+i));
            }
            options.add(new Option("Lähde", this::exit, KeyEvent.VK_0));
            getInfoArea().clear();
            chooseFromOptions(options, "Valitse tavara:");
        }

    }

    protected void chooseFromOptions(ArrayList<Option> options, String title) {
        infoDrawArea.addMessage(title);
        ArrayList<InputManager.KeyPressedEvent> choices = new ArrayList<>();
        for (Option option : options) {
            int key = option.getKey();
            String keyText = KeyEvent.getKeyText(key);
            infoDrawArea.addMessage(keyText + ": " + option.getLabel());
            InputManager.KeyConsumer consumer = () -> {
                for (InputManager.KeyPressedEvent op : choices) {
                    InputManager.unregisterListener(op);
                }
                option.run();
            };
            InputManager.KeyPressedEvent e = new InputManager.KeyPressedEvent(key, consumer);
            //Numero ykkösen painnallusta vastaa myös ylöspäin oleva nuoli
            //Samoin muille ilmansuunnille 1-4
            InputManager.KeyPressedEvent e2 = null;
            if (arrowKeys(key) != key) {
                e2 = new InputManager.KeyPressedEvent(arrowKeys(key), consumer);
            }
            choices.add(e);
            InputManager.registerListener(e);
            if (e2 != null) {
                choices.add(e2);
                InputManager.registerListener(e2);
            }
        }
        if (this.events != null) {
            for (InputManager.KeyPressedEvent event : this.events) {
                InputManager.unregisterListener(event);
            }
        }
        this.events = choices;
    }
    protected IRoom getRoom() {
        return this.room;
    }

    @Override
    protected void exitScreen() {
    }
}