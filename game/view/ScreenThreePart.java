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
    private final DrawInfoArea infoDrawArea;
    private final DrawMainArea mainDrawArea;
    private final DrawTextArea dataDrawArea;
    private final DrawMapArea mapDrawArea;
    private final DrawCommand art;
    private final IRoom room;
    private ArrayList<InputManager.KeyPressedEvent> directionEvents;
    

    ScreenThreePart(IRoom room){
        this.room = room;
        this.infoDrawArea = new DrawInfoArea();
        this.mainDrawArea = new DrawMainArea();
        this.dataDrawArea = new DrawTextArea();
        this.mapDrawArea = new DrawMapArea();
        this.art = new DrawTextCommand(0, 0, AsciiDrawing.SCREEN.getArt());
    }

    final DrawInfoArea getInfoArea(){return infoDrawArea; }
    final DrawMainArea getMainArea(){return mainDrawArea;}
    final DrawTextArea getDataArea(){return dataDrawArea;}    
    final DrawCommand getArt() {return art;}

    @Override
    public DrawCommand getDrawCommand(){
        return new DrawCommand(0, 0, art, infoDrawArea, mainDrawArea, dataDrawArea, mapDrawArea);
    }
    protected void unregisterDirections() {
        if (this.directionEvents == null) {
            return; //eventtejä ei olla rekisteröity vielä
        }
        for (InputManager.KeyPressedEvent directionEvent : this.directionEvents) {
            InputManager.unregisterListener(directionEvent);
        }
        infoDrawArea.setMessage("");
    }
    protected void registerDirections(){
        List<Direction> nextPlaces = this.room.getDestinations();

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
        for (Direction nextRoom : nextPlaces) {
            Enterable onChoice = nextRoom.getDestination();
            //esim pohjoista vastaa numero 1
            int key = CompassPoints.getKeyMatchingDirection(nextRoom.getLabel());
            if (key == -1) //ei ilmansuunta
                key = usedKeys.isEmpty() ? 1 : usedKeys.last() + 1;//etsitään seuraava vapaa numero
            usedKeys.add(key);
            rooms.add(key + ": " + nextRoom.getLabel());
            InputManager.KeyConsumer consumer = () -> {
                for (InputManager.KeyPressedEvent choice : choices) {
                    InputManager.unregisterListener(choice);
                }
                room.exit();
                onChoice.enter();
            };
            choices.add(new InputManager.KeyPressedEvent(KeyEvent.VK_1 + (key - 1), consumer));
        }
        infoDrawArea.addMessage(rooms.toArray(new String[0]));
        this.directionEvents = choices;
        for (InputManager.KeyPressedEvent choice : choices) {
            InputManager.registerListener(choice);
        }
        //InputManager.registerListenerList(choices, true);
    }
    protected IRoom getRoom() {
        return this.room;
    }
    @Override
    void exitScreen() {}
}