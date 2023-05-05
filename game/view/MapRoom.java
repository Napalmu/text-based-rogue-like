package game.view;

import game.controller.RoomType;
import game.model.rooms.Direction;
import java.util.ArrayList;

/**
 * Käytetään kartan piirtämiseen. Model lähettää taulukon tämän tyyppisiä olioita
 * ja niiden avulla voi sitten piirtää kartan.
 */
 public interface MapRoom {
    RoomType getRoomType();
    boolean hasPlayerInside();
    ArrayList<Direction> neighbours();
}
