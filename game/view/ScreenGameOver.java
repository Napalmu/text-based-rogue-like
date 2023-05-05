package game.view;

import game.controller.GameController;
import game.controller.InputManager.KeyPressedEvent;
import game.view.ascii_art.AsciiDrawing;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;

class ScreenGameOver extends Screen {
    private final DrawCommand title;
    private final DrawCommand art;

    ScreenGameOver() {
        art = new DrawTextCommand(0, 0, AsciiDrawing.GAMEOVER.getArt());
        title = new DrawTextCommand(25, 13, "                  ",
                " Hävisit          ",
                " Pelin :3         ",
                "                  ",
                "                  ",
                " 2: Poistu        ",
                "                  ");

    }

    void onPlay() {
        GameController.model.startGame();
    }

    //väliaikainen
    void onPlayProcedural() {
        GameController.model.startGame2();
    }

    void onExit() {
        GameController.exitGame();
    }

    @Override
    public DrawCommand getDrawCommand() {
        return new DrawCommand(0, 0, art, title);
    }

    @Override
    protected List<KeyPressedEvent> getListenersForScreen() {
        return Arrays.asList(
                new KeyPressedEvent(KeyEvent.VK_1, this::onPlay),
                new KeyPressedEvent(KeyEvent.VK_2, this::onExit),
                new KeyPressedEvent(KeyEvent.VK_3, this::onPlayProcedural));

    }

    @Override
    protected void enterScreen() {
    }

    @Override
    protected void exitScreen() {
    }

}
