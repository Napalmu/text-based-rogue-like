package game.view;

import game.controller.GameController;
import game.model.EntityManager;
import game.model.GameEventManager;
import game.model.Item;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

 class TextArea extends DrawTextCommand{

    TextArea(){this(59,1);}
    TextArea(int x, int y) {
        super(x, y);
        setContent("Inventory:");
        GameEventManager.inventory.listen(this::inventoryChanged);
        inventoryChanged(EntityManager.getPlayer().getItems());
        GameController.view.refresh();
    }

    private void inventoryChanged(Item[] items) {
        HashSet<Item> set = new HashSet<>(Arrays.asList(items));
        String[] itemNames = new String[set.size()+1];
        itemNames[0] = "Inventory:";
        int index = 1;
        for (Item item : set) {
            int freq = Collections.frequency(Arrays.asList(items), item);
            itemNames[index++] = item.getName() + " " + freq + " kpl";
        }
        System.out.println(Arrays.toString(itemNames));
        setContent(itemNames);
    }
    
}
