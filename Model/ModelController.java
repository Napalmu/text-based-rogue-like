package Model;


import Controller.GameController;
import Model.rooms.Dungeon;
import View.DrawCommand;

public class ModelController {
    
    public ModelController(){

    }


    public void setDungeon(){
        DrawCommand inventoryList = new DrawCommand(60, 1, "Inventory:");
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

        Dungeon dungeon = new Dungeon();
        dungeon.enter();
    }

}
