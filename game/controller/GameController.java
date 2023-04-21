package game.controller;

import game.view.UIController;

/**
 * -model: Muuttuja, jossa
 */
public class GameController {
    private static GameController main;

    public static final game.view.ViewController view = new game.view.ViewController();

    public static final game.model.ModelController model = new game.model.ModelController();

    public static final UIController ui = new UIController();

    private GameController(){
        view.startGame();
    }
    public static GameController startGame() {
        if (main== null) {
            main = new GameController();
        }
        return main;
    }
    public static void exitGame(){
        view.shutDown();
    }
}
