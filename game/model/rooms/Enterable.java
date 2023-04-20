package game.model.rooms;

/**
 * Sellainen alue mihin pelaaja voi siirtyä. Esim. dungeon tai huone.
 */
public interface Enterable {
    /**
     *
     */
    void enter();
    boolean canEnter();
}
