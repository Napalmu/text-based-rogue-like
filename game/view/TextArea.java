package game.view;

import game.model.GameEventManager;
import game.model.Item;

public class TextArea extends DrawArea{

    public TextArea(int x, int y) {
        super(x, y);
        this.setContent("Inventory:");
        GameEventManager.inventory.listen(this::inventoryChanged);
    }
    private void inventoryChanged(Item[] items) {
        String[] itemNames = new String[items.length+1];
        itemNames[0] = "Inventory:";
        for (int i = 0; i < items.length; i++) {
            itemNames[i+1] = items[i].getName();
        }
        this.setContent(itemNames);
    }
    public void itemReceived(Item i){
        System.out.println("Saatiin tavara "+i.getName());
    }
    
}
