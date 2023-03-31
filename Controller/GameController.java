package Controller;

import View.ScreenMainmenu;
/**
 * -model: Muuttuja, jossa
 */
public class GameController {
    private static GameController main;
    public static final Model.ModelController model = new Model.ModelController();

    public static final View.ViewController view = new View.ViewController();

    private GameController(){
        new ScreenMainmenu();
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
