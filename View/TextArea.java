package View;

import Model.GameEventManager;
import Model.Item;

public class TextArea extends DrawArea{

    public TextArea(int x, int y, int width, int height) {
        super(x, y, width, height);
        GameEventManager.registerListener(this::itemReceived);
    }

    public void itemReceived(Item i){
        System.out.println("Saatiin tavara "+i.getName());
    }
    
}
