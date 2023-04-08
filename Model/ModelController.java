package Model;


import Controller.GameController;
import Model.rooms.Dungeon;
import Model.rooms.RoomFactory;
import View.DrawText;
import View.MapRoom;
import View.DrawArea;

public class ModelController {
    private DrawArea dataDrawArea;
    private Dungeon dungeon;
    public ModelController(){
        dataDrawArea = GameController.view.getDataDrawArea();
    }
    //Kuuntelee muutoksia inventoryyn ja ilmoittaa UI:lle niistä
    private void listenForInventoryChanges() {
        dataDrawArea.createContent("Inventory", new DrawText("Inventory:"));
        Player player = EntityManager.getPlayer();
        player.addInventoryListener(items -> {
            String[] itemNames = new String[items.length+1];
            itemNames[0] = "Inventory:";
            for (int i = 0; i < items.length; i++) {
                itemNames[i+1] = items[i].getName();
            }
            dataDrawArea.setContent("Inventory", itemNames);

        });
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
