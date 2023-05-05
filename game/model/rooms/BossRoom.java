package game.model.rooms;

import game.controller.GameController;
import game.controller.RoomType;
import game.model.Enemy;
import game.model.EntityManager;
import game.model.Item;
import game.model.Player;

import java.util.Arrays;
import java.util.List;

class BossRoom extends EnemyRoom{
    private final Item key;
    private boolean keyUsed = false;

    public BossRoom(Enemy enemy, Item key) {
        super(enemy);
        this.key = key;
    }

    @Override
    public void enterRoom() {
        Player player = EntityManager.getPlayer();
        if (player.hasItem(this.key)) keyUsed = true;
        player.disposeItem(this.key);
        if (hasBeenEntered()) {
            GameController.view.enterMessageRoom(this, Arrays.asList("Olet jo tuhonnut pomon!"));
        } else {
            GameController.model.startBattle(this, enemy);
        }
    }

    @Override
    public boolean canEnter() {
        return keyUsed;
    }

    @Override
    public RoomType getType() {
        return RoomType.BOSS;
    }
}
