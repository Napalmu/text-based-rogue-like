package Model;


import java.util.ArrayList;

import Controller.GameController;
import Model.rooms.Dungeon;
import Model.rooms.RoomFactory;
import View.DrawText;
import View.MapRoom;
import View.ViewController.Area;

public class ModelController {
    private DrawText invText;
    private Dungeon dungeon;

    public ModelController(){
        
    }

    //Kuuntelee muutoksia inventoryyn ja ilmoittaa UI:lle niistä
    private void listenForInventoryChanges() {
        invText = (DrawText)GameController.view.createAreaContent(new DrawText(), Area.dataArea);
        invText.setContent("Inventory:");
        Player player = EntityManager.getPlayer();
        player.addInventoryListener(items -> {
            String[] itemNames = new String[items.length+1];
            itemNames[0] = "Inventory:";
            for (int i = 0; i < items.length; i++) {
                itemNames[i+1] = items[i].getName();
            }
            invText.setContent(itemNames);
            System.out.println(itemNames[1]+ " M");
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
