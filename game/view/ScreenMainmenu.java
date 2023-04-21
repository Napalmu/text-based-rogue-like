package game.view;

import java.awt.event.KeyEvent;

import game.controller.GameController;
import game.controller.InputManager;
import game.view.DrawCommand;
import game.view.ascii_art.AsciiDrawing;

class ScreenMainmenu extends Screen{
    private DrawCommand title;
    private DrawCommand menu;
    private DrawCommand art;

    InputManager.KeyConsumer a = this::onPlay;
    InputManager.KeyConsumer b = this::onExit;

    InputManager.KeyConsumer c =this::onShop;
    InputManager.KeyConsumer d =this::onAdventure;

    public ScreenMainmenu(){
        title = new DrawCommand(25, 13, "                  ",
                                                       " Rosmonkaltainen  ",
                                                       " Peli             ");
        menu = new DrawCommand(25, 16, "                  ",
                                                      " 1: Pelaa         ",
                                                      " 2: Poistu        ",
                                                      "                  ");
        art = new DrawCommand(0, 0, AsciiDrawing.MAINMENU.getArt());

        GameController.view.setContent(art);
        GameController.view.setContent(title);
        GameController.view.setContent(menu);

        InputManager.registerListener(KeyEvent.VK_1, a);
        InputManager.registerListener(KeyEvent.VK_2, b);
        InputManager.registerListener(KeyEvent.VK_3, c);
        InputManager.registerListener(KeyEvent.VK_4, d);
    }

    /**
     * 1)Poistaa mainmenun titlen ja tapahtumakuuntelijat.
     * 2)Luo uuden screengame olion, pelin perusnäkymän.
     * 3)gamecontroller luokalla on staattinen muuttuja 'model', jossa on kasa settereitä piirrettäville asioille.
     * kutsumalla startGame metodia, piirretään perusnäkymään dungeon olio.
     * Eli model paketissa on joukko asioita, joita perusnäkymään voidaan piirtää.
     */
    public void onPlay(){
        GameController.view.clearContent(title);
        GameController.view.clearContent(menu);
        GameController.view.clearContent(art);
        unregisterListeners();

        GameController.view.onPlay();
        GameController.model.startGame();
    }
    public void onShop() {
        GameController.view.clearContent(title);
        GameController.view.clearContent(menu);
        unregisterListeners();


        new ScreenShop();        
    }

    public void onAdventure(){
        GameController.view.clearContent(art);
        GameController.view.clearContent(title);
        GameController.view.clearContent(menu);
        unregisterListeners();


        new ScreenAdventure(); 
    }


    public void unregisterListeners(){
        InputManager.unregisterListener(KeyEvent.VK_1, a);
        InputManager.unregisterListener(KeyEvent.VK_2, b);
        InputManager.unregisterListener(KeyEvent.VK_3, c);
        InputManager.unregisterListener(KeyEvent.VK_4, d);
    }
    public void onExit(){
        GameController.exitGame();
    }
    
}
