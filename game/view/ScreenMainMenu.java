package game.view;

import game.controller.GameController;
import game.controller.InputManager;
import game.controller.InputManager.KeyPressedEvent;
import game.view.ascii_art.AsciiDrawing;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;

 class ScreenMainMenu extends Screen{
    private DrawCommand title;
    private DrawCommand art;

    ScreenMainMenu() {
        art = new DrawTextCommand(0, 0, AsciiDrawing.MAINMENU.getArt());
        title = new DrawTextCommand(25, 13, "                  ",
                " Rosmonkaltainen  ",
                " Peli             ",
                "                  ",
                " 1: Pelaa         ",
                " 2: Poistu        ",
                "                  ");
        
    }
     void onPlay(){
        GameController.model.startGame();
    }
     void onExit(){
        GameController.exitGame();
    }

    @Override
    public DrawCommand getDrawCommand(){
        return new DrawCommand(0,0, art, title);
    }
    @Override
    List<KeyPressedEvent> getListenersForScreen() {
        return Arrays.asList(new KeyPressedEvent[]{
            new InputManager.KeyPressedEvent(KeyEvent.VK_1, this::onPlay),
            new InputManager.KeyPressedEvent(KeyEvent.VK_2, this::onExit)
        });
        
    }
    @Override
    void enterScreen() {}
}