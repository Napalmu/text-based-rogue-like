package Model.rooms;

import Controller.GameController;
import Controller.InputManager;
import Controller.RoomType;
import View.DrawText;
import View.DrawArea;
import View.MainAreaI;

import java.awt.event.KeyEvent;
import java.util.*;

public abstract class Room implements Enterable {
    private final List<Direction> nextRooms = new ArrayList<>();
    private boolean playerInside = false;
    private boolean hasBeenEntered = false;

    protected DrawArea mainDrawArea = GameController.view.getMainDrawArea();
    protected MainAreaI andreinDrawArea = GameController.view.getMainArea();
    protected DrawArea infoDrawArea = GameController.view.getInfoDrawArea();

    public boolean hasBeenEntered() {
        return hasBeenEntered;
    }

    public boolean hasPlayerInside() {
        return playerInside;
    }

    /**
     * Lisää paikan, jonne huoneesta voi siirtyä seuraavaksi
     * Esim, että pohjoisesta löytyy toinen huone
     * @param direction kohde
     */
    public void addDirection(Direction direction) {
        this.nextRooms.add(direction);
    }

    /**
     * Tätä metodia täytyy kutsua, kun huoneesta lähdetään
     * @param target kohde, johon seuraavaksi mennään
     */
    protected final void exit(Enterable target) {
        if (target.canEnter()) {
            mainDrawArea.clearArea();
            andreinDrawArea.hide();
            this.playerInside = false;
            target.enter();
        } else {
            this.moveToNextRoom();
        }
    }
    protected void moveToNextRoom() {
        ArrayList<InputManager.KeyPressedEvent> choices = new ArrayList<>();
        String[] rooms = new String[nextRooms.size()];
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
            rooms[i] = key + ": " + nextRoom.getLabel();
            InputManager.KeyConsumer consumer = () -> {
                this.exit(nextRoom.getDestination());
            };
            choices.add(new InputManager.KeyPressedEvent(KeyEvent.VK_1 + (key-1), consumer));
        }
        andreinDrawArea.drawOptions(rooms);
        //vanha:
        //mainDrawArea.createContent("RoomSelect", new DrawText(5, 6, rooms));

        InputManager.registerListenerList(choices, true);
    }

    /**
     * Kutsutaan aina kun huoneeseen tullaan.
     * Ero enterRoom metodiin on, että tätä metodia aliluokat ei voi muokata
     * varmistaen että täällä olevaa koodia ei ylikirjoiteta
     */
    public final void enter() {
        mainDrawArea.createContent("RoomText", new DrawText(4, 0));
        andreinDrawArea.show();
        this.playerInside = true;
        this.hasBeenEntered = true;
        //ilmoittaa näkymälle, että muutos sijainnissa on tapahtunut
        GameController.view.drawMap();
        this.enterRoom();
    }

    /**
     * Huonekohtainen enter metodi, tätä metodia ei koskaan kuulu kutsua enter metodin ulkopuolella
     */
    protected abstract void enterRoom();

    public abstract RoomType getType();
    public boolean canEnter() {
        return true;
    }
}
