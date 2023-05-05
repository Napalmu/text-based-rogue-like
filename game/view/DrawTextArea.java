package game.view;

import game.controller.GameController;
import game.model.GameEventManager;
import game.model.Item;
import game.model.Item_Weapon;
import game.model.PlayerState;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

/**
 * Alue ruudun oikeassa reunassa. Käytetään pelaajan ominaisuuksien näyttämiseen.
 */
class DrawTextArea extends DrawTextCommand {

    DrawTextArea() {
        this(59, 1);
    }

    DrawTextArea(int x, int y) {
        super(x, y);

        GameEventManager.registerListener(this::updateState);
        this.updateState(GameController.model.getPlayerState());
    }

    private void updateState(PlayerState state) {
        HashSet<Item> itemSet = new HashSet<>(Arrays.asList(state.getItems()));
        //inventoryn lisäksi on elämäpisteet, kestävyys ja reppu-otsikko
        String[] stats = new String[itemSet.size() + 4];
        stats[0] = "Elämäpisteitä: " + state.getHp();
        stats[1] = "Kestävyys: " + state.getStamina() + " / " + state.getMaxStamina();
        stats[2] = "Ase: " + state.getcurrentWeapon().getName() + " " +state.getcurrentWeapon().getWeaponDmg() + " " +state.getcurrentWeapon().getDurability()+ " / "+ state.getcurrentWeapon().getMaxDurability();
        stats[3] = "Reppu:";
        int index = 4;
        for (Item item : itemSet) {
            //jos samaa tavaraa on monta, tavara näytetään muodossa tavara x kpl
            if (item instanceof game.model.Item_Weapon){
                stats[index++] = item.getName() + " " + ((Item_Weapon) item).getDurability() + " / " + ((Item_Weapon) item).getMaxDurability();
            }else{
            int freq = Collections.frequency(Arrays.asList(state.getItems()), item);
            stats[index++] = item.getName() + " " + freq + " kpl";
        }
        }
        
        setContent(stats);
    }

}
