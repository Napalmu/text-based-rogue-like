package game.controller;


/**
 * -model: Muuttuja, jossa
 */
public class GameController {
    private static GameController main;

    public static final game.model.ModelController model = new game.model.ModelController();

    public static final game.view.ViewController view = new game.view.ViewController();

    private GameController(){
        view.enterMainMenu();
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

    public static void deathGame(){
        view.enterGameOver();
    }
}
