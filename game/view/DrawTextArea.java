package game.view;

import game.controller.GameController;
import game.model.GameEventManager;
import game.model.Item;
import game.model.PlayerState;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

 class DrawTextArea extends DrawTextCommand{

    DrawTextArea(){this(59,1);}
    DrawTextArea(int x, int y) {
        super(x, y);

        GameEventManager.registerListener(this::updateState);
        this.updateState(GameController.model.getPlayerState());
    }
    private void updateState(PlayerState state) {
        HashSet<Item> itemSet = new HashSet<>(Arrays.asList(state.getItems()));
        //inventoryn lisäksi on elämäpisteet, kestävyys ja repun otsikko
        String[] stats = new String[itemSet.size()+3];
        stats[0] = "Elämäpisteitä: " + state.getHp();
        stats[1] = "Kestävyys: " + state.getStamina() + " / " + state.getMaxStamina();
        stats[2] = "Reppu:";
        int index = 3;
        for (Item item : itemSet) {
            int freq = Collections.frequency(Arrays.asList(state.getItems()), item);
            stats[index++] = item.getName() + " " + freq + " kpl";
        }
        setContent(stats);
    }
    
}
