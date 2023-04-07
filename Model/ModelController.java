package Model;


import Controller.GameController;
import Model.rooms.Dungeon;
import View.DrawText;
import View.MapRoom;

public class ModelController {

    public ModelController(){

    }

    private void listenForInventoryChanges() {
        DrawText inventoryList = new DrawText(60, 1,"Inventory:");
        Player player = (Player) EntityManager.createPlayer(100, "Pekka");
        player.getInventory().addListener(items -> {
            String[] itemNames = new String[items.length+1];
            itemNames[0] = "Inventory:";
            for (int i = 0; i < items.length; i++) {
                itemNames[i+1] = items[i].getName();
            }
            inventoryList.setContent(itemNames);
        });
        GameController.view.setContent(inventoryList);
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
