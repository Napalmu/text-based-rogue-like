
package game.view;

import game.controller.InputManager;
import game.model.*;

import java.awt.event.KeyEvent;
import java.util.HashSet;

/**
 * Ruudun alin ruutu, jossa näkyy erinäistä infoa liittyen pelin kulkuun
 */
class DrawInfoArea extends DrawScrollingArea {


    DrawInfoArea() {
        this(2, 16, new String[5]);
    }

    DrawInfoArea(int x, int y, String... content) {
        super(x, y, 7, content);

        GameEventManager.registerListener((GameEventManager.ItemReceivedListener) this::itemReceived);
        GameEventManager.registerListener((GameEventManager.BattleActionListener) this::battleAction);
        InputManager.registerListener((new InputManager.KeyPressedEvent(
                KeyEvent.VK_V,
                this::equipmentChoice)));

    }

    private void itemReceived(Item item) {
        this.addMessage("Saatiin tavara: " + item.getName());
    }

    private void battleAction(String str) {
        this.addMessage(str);
    }

    private void equipmentChoice(){
        String marks ="qwertyuio";
        int[] index=new int[]{0};
        int[] keys = new int[]{
                KeyEvent.VK_Q,
                KeyEvent.VK_W,
                KeyEvent.VK_E,
                KeyEvent.VK_R,
                KeyEvent.VK_T,
                KeyEvent.VK_Y,
                KeyEvent.VK_U,
                KeyEvent.VK_I,
                KeyEvent.VK_O
        };
        HashSet <Item> items = new HashSet<>();
        EntityManager.getPlayer().items().forEach(item -> {
            items.add(item);
                });
        items.forEach(item -> {
            int i=index[0];
            InputManager.registerListener((new InputManager.KeyPressedEvent(
                    keys[i],
                    ()->{
                        if (item instanceof Item_Weapon){
                            EntityManager.getPlayer().setEquipment((Item_Weapon) item);
                        }
                        if (item instanceof Item_Armor){
                            EntityManager.getPlayer().setEquipment((Item_Armor) item);
                        }

                    }
                    )));
            index[0]++;

        });
        addMessage("Valitse esine(QWERTYUIO)");

    }


}
