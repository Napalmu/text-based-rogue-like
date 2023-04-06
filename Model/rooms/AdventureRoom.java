package Model.rooms;

import java.util.List;
import java.awt.event.KeyEvent;

import Controller.GameController;
import Controller.InputManager;
import Controller.InputManager.KeyConsumer;
import View.DrawCommand;
import View.ascii_art.AsciiDrawing;

public class AdventureRoom extends Room{

    private int x=10;
    private int y=10;
    
    DrawCommand player = new DrawCommand(x, y, "X");
    private KeyConsumer up    = ()->{y = y-1; player.setXY(x, y); GameController.view.refresh();};
    private KeyConsumer down  = ()->{y = y+1; player.setXY(x, y); GameController.view.refresh();};
    private KeyConsumer left  = ()->{x = x-1; player.setXY(x, y); GameController.view.refresh();};
    private KeyConsumer right = ()->{x = x+1; player.setXY(x, y); GameController.view.refresh();};
    Boolean running = true;

    public AdventureRoom(List<Direction> nextRooms, AsciiDrawing map) {
        super(nextRooms);
        GameController.view.setContent(new DrawCommand(0, 0, map.getArt()));
        InputManager.registerListener(KeyEvent.VK_W, up);
        InputManager.registerListener(KeyEvent.VK_A, left);
        InputManager.registerListener(KeyEvent.VK_S, down);
        InputManager.registerListener(KeyEvent.VK_D, right);
    
        GameController.view.setContent(player);
    }

    @Override
    public void enter() {
    }

    private void exit(){
        InputManager.unregisterListener(KeyEvent.VK_W, up);
        InputManager.unregisterListener(KeyEvent.VK_A, left);
        InputManager.unregisterListener(KeyEvent.VK_S, down);
        InputManager.unregisterListener(KeyEvent.VK_D, right);
        running = false;
    }
    
}