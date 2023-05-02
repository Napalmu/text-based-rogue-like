package game.model;


import game.controller.GameController;
import game.controller.ItemType;
import game.model.rooms.Dungeon;
import game.model.rooms.IRoom;
import game.model.rooms.RoomFactory;
import game.view.MapRoom;
import game.view.ViewController;

public class ModelController {
    private Dungeon dungeon;

    public ModelController() {

    }

    /**
     * Aloittaa taistelun vihollisia vastaan. Kertoo näkymälle taistelun alkamisesta.
     *
     * @param enemies viholliset, joita vastaan taistella
     */
    public void startBattle(IRoom room, EnemyFighter... enemies) {
        IBattle battle = new Battle(EntityManager.getPlayer(), enemies);
        GameController.view.enterEnemyRoom(room, battle, enemies);

        //GameEventManager.emitBattleStarted(battle);
    }

    /**
     * Asettaa tän hetkiseksi dungeoniksi parametri dungeonin.
     * Tarvitaan, jotta kartta voitaisiin piirtää siitä dungeonista, jossa ollaan
     *
     * @param dungeon dungeon
     */
    public void moveToDungeon(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    public void startGame() {
        EntityManager.createPlayer(100, 100, 2, new Item_Weapon(game.controller.ItemType.NYRKIT));
        this.dungeon = new RoomFactory().createStartingRoom();
        dungeon.enter();
    }

    //tää on tässä väliaikaisesti, jotta ihmiset voi testaa proseduraalista generaatiota
    public void startGame2() {
        EntityManager.createPlayer(100, 100, 2, new Item_Weapon(game.controller.ItemType.NYRKIT));
        this.dungeon = new RoomFactory().createStartingDungeon();
        dungeon.enter();
    }

    public boolean canBuyItem(Item item) {
        Player player = EntityManager.getPlayer();
        int price = item.getType().price();
        return player.canAfford(price);
    }

    public void buyItem(Item item) {
        Player player = EntityManager.getPlayer();
        int price = item.getType().price();
        player.receiveItems(item);
        for (int i = 0; i < price; i++) {
            player.disposeItem(EntityManager.createItem(ItemType.COIN));
        }
    }

    public PlayerState getPlayerState() {
        return EntityManager.getPlayer().getState();
    }

    public MapRoom[][] getMap() {
        return dungeon.getMap();
    }

}
