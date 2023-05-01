package game.view;

import game.controller.RoomType;

/**
 * Käytetään kartan piirtämiseen. Model lähettää taulukon tämän tyyppisiä olioita
 * ja niiden avulla voi sitten piirtää kartan.
 */
 public interface MapRoom {
    RoomType getRoomType();
    boolean hasPlayerInside();
}
