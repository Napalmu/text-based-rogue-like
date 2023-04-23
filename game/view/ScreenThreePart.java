package game.view;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import game.controller.GameController;
import game.controller.InputManager;
import game.model.GameEventManager;
import game.model.rooms.CompassPoints;
import game.model.rooms.Direction;
import game.model.rooms.Enterable;
import game.model.rooms.IRoom;
import game.view.ascii_art.AsciiDrawing;

abstract class ScreenThreePart extends Screen{
    private final InfoArea infoDrawArea;
    private final MainArea mainDrawArea;
    private final TextArea dataDrawArea;
    private final DrawCommand art;
    

    ScreenThreePart(IRoom room){
        this.infoDrawArea = new InfoArea();
        this.mainDrawArea = new MainArea();
        this.dataDrawArea = new TextArea();
        this.art = new DrawTextCommand(0, 0, AsciiDrawing.SCREEN.getArt());

        //huone vaihtuu, joten kartta pitää piirtää uudestaan
        GameEventManager.registerListener((GameEventManager.RoomEnteredListener) (r, success) -> {
            this.drawMap();
        });
        registerDirections(room);
    }

    final InfoArea getInfoArea(){return infoDrawArea; }
    final MainArea getMainArea(){return mainDrawArea;}
    final TextArea getDataArea(){return dataDrawArea;}    
    final DrawCommand getArt() {return art;}

    @Override
    public DrawCommand getDrawCommand(){
        return new DrawCommand(0, 0, art, infoDrawArea, mainDrawArea, dataDrawArea);
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
        //Tälle pitää keksiä jokin toinen paikka
        //setContent(new DrawCommand(50, 16, stringMap));
    }

    private void registerDirections(IRoom room){
        List<Direction> nextPlaces = room.getDestinations();
        

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
            Enterable onChoice = nextPlaces.get(i).getDestination();
            //esim pohjoista vastaa numero 1
            int key = CompassPoints.getKeyMatchingDirection(nextRoom.getLabel());
            if (key == -1) //ei ilmansuunta
                key = usedKeys.isEmpty() ? 1 : usedKeys.last() + 1;//etsitään seuraava vapaa numero
            usedKeys.add(key);
            rooms.add(key + ": " + nextRoom.getLabel());
            InputManager.KeyConsumer consumer = () -> {
                if (onChoice.canEnter())
                    onChoice.enter();
            };
            choices.add(new InputManager.KeyPressedEvent(KeyEvent.VK_1 + (key-1), consumer));
        }
        infoDrawArea.addMessage(rooms.toArray(new String[0]));
        InputManager.registerListenerList(choices, true);
    }
}