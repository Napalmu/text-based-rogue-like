package game.model;


import game.controller.GameController;
import game.model.rooms.Dungeon;
import game.model.rooms.RoomFactory;
import game.view.MapRoom;
import game.view.ViewController.Area;

public class ModelController {
    private Dungeon dungeon;

    public ModelController(){
        
    }

    //Kuuntelee muutoksia inventoryyn ja ilmoittaa UI:lle niistä
    private void listenForInventoryChanges() {
        // invText = (DrawText)GameController.view.createAreaContent(new DrawText(), Area.dataArea);
        // invText.setContent("Inventory:");
        Player player = EntityManager.getPlayer();
        player.addInventoryListener(items -> {
            String[] itemNames = new String[items.length+1];
            itemNames[0] = "Inventory:";
            for (int i = 0; i < items.length; i++) {
                itemNames[i+1] = items[i].getName();
            }
            // invText.setContent(itemNames);
        });
    }

    /**
     * Aloittaa taistelun vihollisia vastaan. Kertoo näkymälle taistelun alkamisesta.
     * @param enemies viholliset, joita vastaan taistella
     */
    public void startBattle(Enemy... enemies) {
        IBattle battle = new Battle(EntityManager.getPlayer(), enemies);
        GameEventManager.emitBattleStarted(battle);
    }

    /**
     * Asettaa tän hetkiseksi dungeoniksi parametri dungeonin.
     * Tarvitaan, jotta kartta voitaisiin piirtää siitä dungeonista, jossa ollaan
     * @param dungeon dungeon
     */
    public void moveToDungeon(Dungeon dungeon) {
        this.dungeon = dungeon;
    }
    public void startGame(){
        EntityManager.createPlayer(100, "Pekka");
        listenForInventoryChanges();
        this.dungeon = new RoomFactory().createStartingRoom();
        dungeon.enter();
    }
    public MapRoom[][] getMap() {
        return dungeon.getMap();
    }

}
