package View;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Controller.GameController;
import Controller.InputManager;

public class ScreenMainmenu extends Screen{
    private DrawCommand title;
    private DrawCommand menu;

    InputManager.KeyConsumer a = this::onPlay;
    InputManager.KeyConsumer b = this::onExit;

    InputManager.KeyConsumer c =this::onShop;

    public ScreenMainmenu(){
        title = new DrawCommand(10, 10, "Rosmonkaltainen","Peli");
        menu = new DrawCommand(10, 14, "1: Pelaa","2: Poistu");

        GameController.view.setContent(title);
        GameController.view.setContent(menu);

        InputManager.registerListener(KeyEvent.VK_1, a);
        InputManager.registerListener(KeyEvent.VK_2, b);
        InputManager.registerListener(KeyEvent.VK_3, c);
    }

    /**
     * 1)Poistaa mainmenun titlen ja tapahtumakuuntelijat.
     * 2)Luo uuden screengame olion, pelin perusnäkymän.
     * 3)gamecontroller luokalla on staattinen muuttuja 'model', jossa on kasa settereitä piirrettäville asioille.
     * kutsumalla setDungeon metodia, piirretään perusnäkymään dungeon olio.
     * Eli model paketissa on joukko asioita, joita perusnäkymään voidaan piirtää.
     */
    public void onPlay(){
        System.out.println("Fhbasfvhnfsdghds");
        GameController.view.clearContent(title);
        GameController.view.clearContent(menu);

        InputManager.unregisterListener(KeyEvent.VK_1, a);
        InputManager.unregisterListener(KeyEvent.VK_2, b);
        InputManager.unregisterListener(KeyEvent.VK_3, c);

        new ScreenGame();
        GameController.model.setDungeon();
    }
    public void onShop() {
        System.out.println("Fhbasfvhnfsdghds");
        GameController.view.clearContent(title);
        GameController.view.clearContent(menu);

        InputManager.unregisterListener(KeyEvent.VK_1, a);
        InputManager.unregisterListener(KeyEvent.VK_2, b);
        InputManager.unregisterListener(KeyEvent.VK_3, c);

        new ScreenShop();
    }
    public void onExit(){
        GameController.exitGame();
    }
    
}
