package game.view;

import game.model.GameEventManager;
import game.model.Item;

public class TextArea extends DrawArea{

    public TextArea(int x, int y) {
        super(x, y);
        GameEventManager.registerListener(this::itemReceived);
    }

    public void itemReceived(Item i){
        System.out.println("Saatiin tavara "+i.getName());
    }
    
}
