package Model;


import Controller.GameController;
import Model.rooms.Dungeon;
import Model.rooms.RoomFactory;
import View.DrawText;
import View.MapRoom;
import View.DrawArea;

public class ModelController {
    private DrawText invText;
    private Dungeon dungeon;
    public ModelController(){
        invText = (DrawText)GameController.view.CreateInfoAreaContent(new DrawText());
    }
    //Kuuntelee muutoksia inventoryyn ja ilmoittaa UI:lle niistä
    private void listenForInventoryChanges() {
        invText.setContent("Inventory:");
        Player player = EntityManager.getPlayer();
        player.addInventoryListener(items -> {
            String[] itemNames = new String[items.length+1];
            itemNames[0] = "Inventory:";
            for (int i = 0; i < items.length; i++) {
                itemNames[i+1] = items[i].getName();
            }
            invText.setContent(itemNames);

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
