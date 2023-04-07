package UI;

import Controller.GameController;
import View.DrawCommand;
import View.ascii_art.AsciiDrawing;

class ScreenGame extends Screen{
    

    public ScreenGame(){
        GameController.view.setContent(new DrawCommand(0, 0, AsciiDrawing.SCREEN.getArt()));
    }
}
