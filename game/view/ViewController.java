package game.view;

import game.controller.InputManager;
import game.model.rooms.CompassPoints;
import game.model.rooms.Direction;
import game.model.rooms.Enterable;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.TreeSet;
import java.util.function.Consumer;

 public class ViewController {
    private final Terminal t = new Terminal(80,24);

    private DrawMainMenu mainMenu;
    private Stack<Screen> uiStack;
    
    public ViewController(){        
        uiStack = new Stack<>();
        uiStack.add(new ScreenMessage());
        //GameEventManager.registerListener(this.shop::shopEntered);
    }


    /**
     * Näyttää mainMenun, eli näkymän, joka näkyy, kun peli avataan
     */
     public void startGame(){
        this.mainMenu = new DrawMainMenu(0,0);
        this.setContent(this.mainMenu);
    }

     void refresh(){
        t.redraw();
    }

    /**
     * Draws the given command directly on the terminal once.
     * 
     * @param content the content to draw
     */
     void drawContentOneShot(DrawCommand content){
        t.drawContent(content);
    }

    /**
     * Adds the given command to the draw queue to be drawn every frame.
     * 
     * @param content the content to draw
     */
     void setContent(DrawCommand content){
        content.Activate();
        t.addContent(content);
    }

     void clearContent(DrawCommand content){
        t.removeContent(content);
    }

     public void shutDown(){
        t.dispose();
    }



     void onPlay() {
        clearContent(this.mainMenu);
        setContent(uiStack.peek().getArt());
        setContent(uiStack.peek().getMainArea());
        setContent(uiStack.peek().getDataArea());
        setContent(uiStack.peek().getInfoArea());
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
        uiStack.peek().getMainArea().drawOptions(rooms.toArray(new String[0]));
        InputManager.registerListenerList(choices, true);
    }

     public enum Area{
        infoArea,
        mainArea,
        dataArea
    }
}
