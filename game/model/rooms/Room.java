package game.model.rooms;

import game.controller.InputManager;
import game.controller.RoomType;
import game.model.GameEventManager;

import java.util.ArrayList;

abstract class Room implements Enterable, IRoom{
    private final ArrayList<Direction> directions = new ArrayList<>();
    private boolean playerInside = false;
    private boolean hasBeenEntered = false;

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
     */
    public void exit() {
        this.playerInside = false;
        this.hasBeenEntered = true;
    }

    /**
     * Metodi palauttaa huonekohtaiset napinpainalluseventit. Huone perii metodin tarvittaessa.
     * todo remove
     * @return Lista eventeistä
     */
    protected ArrayList<InputManager.KeyPressedEvent> getKeyEvents(){return new ArrayList<>();}

    /**
     * Metodi palauttaa huonekohtaiset kohteet, joihin huoneesta voi mennä. Huone perii metodin tarvittaessa.
     * todo remove
     * @return Lista kohteista
     */
    public ArrayList<Direction> getDestinations(){return directions;}

    /**
     * Kutsutaan aina kun huoneeseen tullaan.
     * Ero enterRoom metodiin on, että tätä metodia aliluokat ei voi muokata
     * varmistaen että täällä olevaa koodia ei ylikirjoiteta
     */
    public final void enter() {
        this.playerInside = true;
        GameEventManager.emitRoomEnteredEvent(this);
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
