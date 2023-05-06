package game.model.rooms;

import game.controller.GameController;
import game.controller.RoomType;
import game.model.Enemy;
import game.model.EntityManager;
import game.model.Item;
import game.model.Player;

import java.util.ArrayList;
import java.util.Arrays;

class BossRoom extends EnemyRoom{
    private final Item key;
    private boolean keyUsed = false;
    private Direction destinationAfterWin;

    public BossRoom(Enemy enemy, Item key, Direction destinationAfterWin) {
        super(enemy);
        this.key = key;
        this.destinationAfterWin = destinationAfterWin;
    }

    @Override
    public void enterRoom() {
        Player player = EntityManager.getPlayer();
        if (player.hasItem(this.key)) keyUsed = true;
        player.disposeItem(this.key);
        if (hasBeenEntered()) {
            GameController.view.enterMessageRoom(this, Arrays.asList("Olet jo tuhonnut pomon!"));
        } else {
            GameController.model.startBossFight(this, enemy);
        }
    }

    @Override
    public boolean canEnter() {
        return keyUsed;
    }

    @Override
    public ArrayList<Direction> getDestinations() {
        if (this.enemy.getHp() <= 0) {
            ArrayList<Direction> destinations = new ArrayList<>(super.getDestinations());
            destinations.add(this.destinationAfterWin);
            return destinations;
        }
        return super.getDestinations();
    }

    @Override
    public RoomType getType() {
        return RoomType.BOSS;
    }
}
