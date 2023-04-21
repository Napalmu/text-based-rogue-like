package game.view;

import game.controller.GameController;
import game.controller.InputManager;
import game.model.GameEventManager;
import game.model.Item;
import game.model.rooms.CompassPoints;
import game.model.rooms.Direction;
import game.model.rooms.Enterable;
import game.view.ascii_art.AsciiDrawing;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.function.Consumer;

public class ViewController {
    private final Terminal t = new Terminal(80,24);

    private final InfoArea infoDrawArea;

    private final MainArea mainDrawArea;

    private final TextArea dataDrawArea;

    private final DrawCommand art;

    private DrawMainMenu mainMenu;

    public ViewController(){
        this.infoDrawArea = new InfoArea(2, 16);
        this.mainDrawArea = new MainArea(2, 1);
        this.dataDrawArea = new TextArea(59, 1);
        this.art = new DrawCommand(0, 0, AsciiDrawing.SCREEN.getArt());

        //huone vaihtuu, joten kartta pitää piirtää uudestaan
        GameEventManager.registerListener((room, success) -> {
            this.drawMap();
        });
        //GameEventManager.registerListener(this::shopEntered);
    }
    private Item selectedItem;
    private ArrayList<InputManager.KeyPressedEvent> shopEvents;
    public void shopEntered(ArrayList<Item> items, Runnable onExit) {
        this.shopEvents = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            InputManager.KeyPressedEvent event = new InputManager.KeyPressedEvent(KeyEvent.VK_1+i,
                    () -> selectItem(item));
            shopEvents.add(event);
            InputManager.registerListener(event);
        }
        InputManager.KeyPressedEvent close = new InputManager.KeyPressedEvent(KeyEvent.VK_SPACE,
                this::buyItem);
        InputManager.KeyPressedEvent exit = new InputManager.KeyPressedEvent(KeyEvent.VK_0,
                ()-> exitShop(onExit));
        shopEvents.add(close); shopEvents.add(exit);

        InputManager.registerListener(close);
        InputManager.registerListener(exit);
    }

    private void exitShop(Runnable onExit) {
        for (InputManager.KeyPressedEvent shopEvent : this.shopEvents) {
            InputManager.unregisterListener(shopEvent);
        }
        onExit.run();
    }

    private void selectItem(Item item) {
        this.selectedItem = item;
        this.infoDrawArea.setMessage(item.getType().getDescription());
    }
    private void buyItem() {
        System.out.println("Lol");
        if (this.selectedItem == null) return;
        GameEventManager.emitBuyItem(this.selectedItem);
    }

    public void startGame(){
        this.mainMenu = new DrawMainMenu(0,0);
        this.setContent(this.mainMenu);
        //GameController.ui.mainMenu();
    }

    public void refresh(){
        t.redraw();
    }

    /**
     * Draws the given command directly on the terminal once.
     * 
     * @param content the content to draw
     */
    public void drawContentOneShot(DrawCommand content){
        t.drawContent(content);
    }

    /**
     * Adds the given command to the draw queue to be drawn every frame.
     * 
     * @param content the content to draw
     */
    public void setContent(DrawCommand content){
        content.Activate();
        t.addContent(content);
    }

    public void clearContent(DrawCommand content){
        t.removeContent(content);
    }

    public void shutDown(){
        t.dispose();
    }

    private void drawMap() {
        MapRoom[][] map = GameController.model.getMap();
        String[] stringMap = new String[map.length];
        for (int y = 0; y < map.length; y++) {
            char[] chars = new char[map[y].length];
            for (int x = 0; x < map[y].length; x++) {
                if (map[y][x] == null) {
                    chars[x] = ' ';
                    continue;
                }
                chars[x] = map[y][x].getRoomType().symbol;
                if (map[y][x].hasPlayerInside()) {
                    chars[x] = '$';
                }
            }
            stringMap[y] = new String(chars);
        }
        setContent(new DrawCommand(50, 16, stringMap));
    }

    public void onPlay() {
        clearContent(this.mainMenu);
        setContent(this.art);
        setContent(mainDrawArea);
        setContent(dataDrawArea);
        setContent(infoDrawArea);
    }

    /**
     * Kysyy käyttäjältä kohteen, johon seuraavaksi siirrytään
     * @param nextPlaces Mahdolliset kohteet
     * @param onChoice kutsutaan, sillä kohteella, jonka käyttäjä valitsi
     */
    public void moveToNextPlace(List<Direction> nextPlaces, Consumer<Enterable> onChoice) {
        ArrayList<InputManager.KeyPressedEvent> choices = new ArrayList<>();
        ArrayList<String> rooms = new ArrayList<>();
        //seuraavan koodin idea on, että tietyille ilmansuunnille annetaan aina samat numerot:
        //(hyvin kyseenalaista koodia)
        //järjestetään kohteen, niin että pienemmät numerot tulevat ensin.
        //eli esim. pohjoisen numero on 1 ja etelän 3.
        //jos kohde ei ole ilmansuunta, se laitetaan ilmansuuntien jälkeen
        nextPlaces.sort((o1, o2) -> {
            int key1 = CompassPoints.getKeyMatchingDirection(o1.getLabel());
            int key2 = CompassPoints.getKeyMatchingDirection(o2.getLabel());
            if (key1 == -1) return 1;
            if (key2 == -1) return -1;
            return Integer.compare(key1,key2);
        });
        //numerot, jotka on jo asetettu johonkin toimintoon
        TreeSet<Integer> usedKeys = new TreeSet<>();
        for (Direction nextRoom : nextPlaces) {
            int key = CompassPoints.getKeyMatchingDirection(nextRoom.getLabel());
            usedKeys.add(key);
        }
        for (int i = 0; i < nextPlaces.size(); i++) {
            Direction nextRoom = nextPlaces.get(i);
            //esim pohjoista vastaa numero 1
            int key = CompassPoints.getKeyMatchingDirection(nextRoom.getLabel());
            if (key == -1) //ei ilmansuunta
                key = usedKeys.isEmpty() ? 1 : usedKeys.last() + 1;//etsitään seuraava vapaa numero
            usedKeys.add(key);
            rooms.add(key + ": " + nextRoom.getLabel());
            InputManager.KeyConsumer consumer = () -> {
                onChoice.accept(nextRoom.getDestination());
            };
            choices.add(new InputManager.KeyPressedEvent(KeyEvent.VK_1 + (key-1), consumer));
        }
        this.mainDrawArea.drawOptions(rooms.toArray(new String[0]));
        InputManager.registerListenerList(choices, true);
    }

    public enum Area{
        infoArea,
        mainArea,
        dataArea
    }
}
