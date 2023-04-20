package game.model.rooms;

import game.controller.GameController;
import game.controller.InputManager;
import game.controller.RoomType;
import game.model.GameEventManager;
import game.view.DrawText;
import game.view.ViewController.Area;

import java.util.*;

abstract class Room implements Enterable, IRoom{
    private final List<Direction> directions = new ArrayList<>();
    private boolean playerInside = false;
    private boolean hasBeenEntered = false;

    protected DrawText roomSelect;
    protected DrawText roomText; 

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
        this.directions.add(direction);
    }

    /**
     * Tätä metodia täytyy kutsua, kun huoneesta lähdetään
     * @param target kohde, johon seuraavaksi mennään
     */
    protected final void exit(Enterable target) {
        if (target.canEnter()) {
            GameController.view.clearArea(Area.mainArea);
            this.playerInside = false;
            target.enter();
        } else {
            this.moveToNextRoom();
        }
    }
    protected void moveToNextRoom() {
        GameController.view.moveToNextRoom(this.directions, this::exit);
    }

    /**
     * Metodi palauttaa huonekohtaiset napinpainalluseventit. Huone perii metodin tarvittaessa.
     *      
     * @return Lista eventeistä
     */
    protected ArrayList<InputManager.KeyPressedEvent> getKeyEvents(){return new ArrayList<>();}

    /**
     * Metodi palauttaa huonekohtaiset kohteet, joihin huoneesta voi mennä. Huone perii metodin tarvittaessa.
     * 
     * @return Lista kohteista
     */
    protected ArrayList<Direction> getDestinations(){
        return new ArrayList<>();}

    /**
     * Kutsutaan aina kun huoneeseen tullaan.
     * Ero enterRoom metodiin on, että tätä metodia aliluokat ei voi muokata
     * varmistaen että täällä olevaa koodia ei ylikirjoiteta
     */
    public final void enter() {
        //roomText = (DrawText)GameController.view.createAreaContent(new DrawText(4, 0), Area.mainArea);
        this.playerInside = true;
        GameEventManager.emitRoomEnteredEvent(this);
        this.enterRoom();

        //tämä ennen enter room kutsua aiheutti itemin epätoimivuutta, koitin korjata näin
        this.hasBeenEntered = true;
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
