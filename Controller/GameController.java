package Controller;

import View.ScreenMainmenu;

public class GameController {
    
    public static final Model.ModelController model = new Model.ModelController();
    public static final View.ViewController view = new View.ViewController();
    
    public GameController(){
        new ScreenMainmenu();
    }

    public static void exitGame(){
        view.shutDown();
    }
}
