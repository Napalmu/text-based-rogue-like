package game.model.rooms;

import game.controller.RoomType;
import game.model.Enemy;
import game.model.EntityManager;
import game.model.Item;
import game.model.Player;

class BossRoom extends EnemyRoom{
    private final Item key;

    public BossRoom(Enemy enemy, Item key) {
        super(enemy);
        this.key = key;
    }

    @Override
    public void enterRoom() {
        roomText.setContent("Olet bossi huoneessa nyt!",
                "Bossin nimi: " + this.enemy.getName());
        EntityManager.getPlayer().removeItem(this.key);
        this.moveToNextRoom();
    }

    @Override
    public boolean canEnter() {
        //jos huoneessa on jo aiemmin käyty, avainta ei tarvita
        if (hasBeenEntered()) return true;
        //tarkistetaan onko pelaajalla vaadittu avain
        Player player = EntityManager.getPlayer();
        boolean hasKey = player.hasItem(key);
        if (!hasKey) {
            roomText.setContent("Et voi mennä bossi huoneeseen ilman avainta");
        }
        return hasKey;
    }

    @Override
    public RoomType getType() {
        return RoomType.BOSS;
    }
}
