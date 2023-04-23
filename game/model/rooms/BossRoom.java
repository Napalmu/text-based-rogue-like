package game.model.rooms;

import java.util.Arrays;

import game.controller.GameController;
import game.controller.RoomType;
import game.model.*;

class BossRoom extends EnemyRoom{
    private final Item key;

    public BossRoom(Enemy enemy, Item key) {
        super(enemy);
        this.key = key;
    }

    @Override
    public void enterRoom() {
        EntityManager.getPlayer().disposeItem(this.key);
        GameController.view.enterBossRoom(this, enemy);
    }

    @Override
    public boolean canEnter() {
        //jos huoneessa on jo aiemmin käyty, avainta ei tarvita
        if (hasBeenEntered()) return true;
        //tarkistetaan onko pelaajalla vaadittu avain
        Player player = EntityManager.getPlayer();
        boolean hasKey = player.hasItem(key);
        if (!hasKey) {
            GameEventManager.emitRoomEnteredEvent(this, false);
            GameController.view.enterMessageRoom(this, Arrays.asList("Höh, näyttää siltä, että tarvitset", "avaimen päästäksesi sisään"));
             
            //roomText.setContent("Et voi mennä bossi huoneeseen ilman avainta");
        }
        return hasKey;
    }

    @Override
    public RoomType getType() {
        return RoomType.BOSS;
    }
}
