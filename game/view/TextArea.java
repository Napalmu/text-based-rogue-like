package game.view;

import game.model.GameEventManager;
import game.model.Item;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

public class TextArea extends DrawArea{

    public TextArea(int x, int y) {
        super(x, y, "Inventory:");
        GameEventManager.inventory.listen(this::inventoryChanged);
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
        this.setContent(itemNames);
    }
}
