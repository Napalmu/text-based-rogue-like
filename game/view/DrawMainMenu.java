package game.view;

import game.controller.GameController;
import game.controller.InputManager;
import game.view.ascii_art.AsciiDrawing;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.stream.Stream;

public class DrawMainMenu extends DrawArea{
    private DrawCommand title;
    private DrawCommand menu;
    private DrawCommand art;

    public DrawMainMenu(int x, int y) {
        super(x, y);
        art = new DrawCommand(0, 0, AsciiDrawing.MAINMENU.getArt());
        title = new DrawCommand(25, 13, "                  ",
                " Rosmonkaltainen  ",
                " Peli             ");
        menu = new DrawCommand(25, 16, "                  ",
                " 1: Pelaa         ",
                " 2: Poistu        ",
                "                  ");
        ArrayList<InputManager.KeyPressedEvent> options = new ArrayList<>();
        options.add(new InputManager.KeyPressedEvent(KeyEvent.VK_1, this::onPlay));
        options.add(new InputManager.KeyPressedEvent(KeyEvent.VK_2, this::onExit));
        InputManager.registerListenerList(options, true);
    }
    public void onPlay(){
        GameController.view.onPlay();
        GameController.model.startGame();
    }
    public void onExit(){
        GameController.exitGame();
    }

    @Override
    public Stream<CharacterPosition> getStream() {
        Stream.Builder<CharacterPosition> c = Stream.builder();

        this.art.getStream().forEach(c::accept);
        this.title.getStream().forEach(c::accept);
        this.menu.getStream().forEach(c::accept);
        return c.build();
    }
}
