package game.model.rooms;

import game.controller.GameController;
import game.controller.InputManager;
import game.controller.RoomType;
import game.model.GameEventManager;

import java.util.ArrayList;
import java.util.List;

abstract class Room implements Enterable, IRoom{
    private final List<Direction> directions = new ArrayList<>();
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
     * @param target kohde, johon seuraavaksi mennään
     */
    protected final void exit(Enterable target) {
        if (target.canEnter()) {
            //ei tarvita enään, koska näkymä kuuntelee RoomEnter tapahtumia
            //GameController.view.clearArea(Area.mainArea);
            this.playerInside = false;
            this.hasBeenEntered = true;
            target.enter();
        } else {
            //kysytään uusi valinta
            this.moveToNextRoom();
        }
    }

    /**
     * Kysyy käyttäjältä minne siirrytään ja siirtyy sinne
     */
    protected void moveToNextRoom() {
        GameController.view.moveToNextPlace(this.directions, this::exit);
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
