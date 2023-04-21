package game.view;

import game.controller.GameController;
import game.view.DrawCommand;
import game.view.ascii_art.AsciiDrawing;

class ScreenGame extends Screen{
    

    public ScreenGame(){
        GameController.view.setContent(new DrawCommand(0, 0, AsciiDrawing.SCREEN.getArt()));
    }
}
