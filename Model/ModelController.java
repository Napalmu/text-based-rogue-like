package Model;


import Controller.GameController;
import Model.rooms.Dungeon;
import View.DrawCommand;
import View.DrawText;
import View.MapRoom;
import View.DrawArea;

public class ModelController {
    private DrawArea infoDrawArea;
    private DrawArea dataDrawArea;
    public ModelController(){
        dataDrawArea = GameController.view.getDataDrawArea();
        infoDrawArea = GameController.view.getInfoDrawArea();
    }

    private void listenForInventoryChanges() {
        dataDrawArea.createContent("Inventory", new DrawText("Inventory:"));
        Player player = (Player) EntityManager.createPlayer(100, "Pekka");
        player.getInventory().addListener(items -> {
            String[] itemNames = new String[items.length+1];
            itemNames[0] = "Inventory:";
            for (int i = 0; i < items.length; i++) {
                itemNames[i+1] = items[i].getName();
            }
            dataDrawArea.setContent("Inventory", itemNames);

        });
    }
    public void startGame(){
        listenForInventoryChanges();
        Dungeon dungeon = new Dungeon();
        dungeon.enter();
    }
    public MapRoom[][] getMap() {
        return Dungeon.currentDungeon.getMap();
    }

}
