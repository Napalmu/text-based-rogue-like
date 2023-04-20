package game.view;

import game.controller.GameController;
import game.controller.InputManager;
import game.model.GameEventManager;
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
    private Terminal t = new Terminal(80,24);

    private InfoArea infoDrawArea;

    private MainArea mainDrawArea;

    private DrawArea dataDrawArea;

    public DrawCommand createAreaContent(DrawCommand drawCommand, Area area){
        switch (area) {
            case infoArea:
                return infoDrawArea.createContent(drawCommand);
            case mainArea:
                return mainDrawArea.createContent(drawCommand);
            case dataArea:
                return dataDrawArea.createContent(drawCommand);
            default:
                return null;
        }
    }

    public void clearArea(Area area){
        switch (area) {
            case infoArea:
                infoDrawArea.clearArea();
                break;
            case mainArea:
                mainDrawArea.clearArea();
                break;
            case dataArea:
                dataDrawArea.clearArea();
                break;
            default:
        }
    }

    public void removeContent(DrawCommand content, Area area){
        switch (area) {
            case infoArea:
                infoDrawArea.removeContent(content);
                break;
            case mainArea:
                mainDrawArea.removeContent(content);
                break;
            case dataArea:
                dataDrawArea.removeContent(content);
                break;
            default:
        }
    }

    public ViewController(){
        infoDrawArea = new InfoArea(2, 16, 0, 0);
        mainDrawArea = new MainArea(2, 1, 0, 0);
        dataDrawArea = new TextArea(59, 1, 0, 0);
        GameEventManager.registerListener((room, success) -> {
            this.drawMap();
        });
    }

    public void startGame(){
        GameController.ui.mainMenu();
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

    public void drawMap() {
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
        setContent(new DrawCommand(0, 0, AsciiDrawing.SCREEN.getArt()));
        setContent(mainDrawArea);
        setContent(dataDrawArea);
        setContent(infoDrawArea);
    }

    public void moveToNextRoom(List<Direction> nextRooms, Consumer<Enterable> onChoice) {
        ArrayList<InputManager.KeyPressedEvent> choices = new ArrayList<>();
        ArrayList<String> rooms = new ArrayList<>();
        //seuraavan koodin idea on, että tietyille ilmansuunnille annetaan aina samat numerot:
        //(hyvin kyseenalaista koodia)
        //järjestetään kohteen, niin että pienemmät numerot tulevat ensin.
        //eli esim. pohjoisen numero on 1 ja etelän 3.
        //jos kohde ei ole ilmansuunta, se laitetaan ilmansuuntien jälkeen
        nextRooms.sort((o1, o2) -> {
            int key1 = CompassPoints.getKeyMatchingDirection(o1.getLabel());
            int key2 = CompassPoints.getKeyMatchingDirection(o2.getLabel());
            if (key1 == -1) return 1;
            if (key2 == -1) return -1;
            return Integer.compare(key1,key2);
        });
        //numerot, jotka on jo asetettu johonkin toimintoon
        TreeSet<Integer> usedKeys = new TreeSet<>();
        for (Direction nextRoom : nextRooms) {
            int key = CompassPoints.getKeyMatchingDirection(nextRoom.getLabel());
            usedKeys.add(key);
        }
        for (int i = 0; i < nextRooms.size(); i++) {
            Direction nextRoom = nextRooms.get(i);
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
