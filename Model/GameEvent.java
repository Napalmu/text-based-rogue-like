package Model;

import Controller.GameEventType;

public interface GameEvent {
    /**
     * returns the game event that happened
     * @return a value from GameEventType
     */
    GameEventType getEventType();
    /**
     * The corresponding event text.
     * @return The value that X in the event type should be replaced with.
     */
    String getEventText();
}
