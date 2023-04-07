package Model.rooms;

import java.util.ArrayList;
import java.util.List;
import java.awt.event.KeyEvent;

import Controller.GameController;
import Controller.InputManager;
import Controller.InputManager.KeyConsumer;
import Controller.RoomType;
import View.DrawCommand;
import View.ascii_art.AsciiDrawing;

public class AdventureRoom extends Room{

    private int x=10;
    private int y=10;

    List<Room> areaStack = new ArrayList<>();
    List<PointOfInterest> pointsOfInterest;

    
    DrawCommand player = new DrawCommand(x, y, "X");
    private KeyConsumer up    = ()->{y = y-1; move();};
    private KeyConsumer down  = ()->{y = y+1; move();};
    private KeyConsumer left  = ()->{x = x-1; move();};
    private KeyConsumer right = ()->{x = x+1; move();};

    public AdventureRoom(AsciiDrawing map) {
        pointsOfInterest = getPointsOfInterest();
        GameController.view.setContent(new DrawCommand(0, 0, map.getArt()));
        pointsOfInterest.forEach((poi) -> GameController.view.setContent(poi.drawCommand));
        InputManager.registerListener(KeyEvent.VK_W, up);
        InputManager.registerListener(KeyEvent.VK_A, left);
        InputManager.registerListener(KeyEvent.VK_S, down);
        InputManager.registerListener(KeyEvent.VK_D, right);
    
        GameController.view.setContent(player);
    }

    protected List<PointOfInterest> getPointsOfInterest() {
        ArrayList<PointOfInterest> pois = new ArrayList<>();
        pois.add(new PointOfInterest(20,10,'b', this::gainBlueberry));
        return pois;
    }

    private void gainBlueberry(PointOfInterest poi){
        GameController.view.clearContent(poi.drawCommand);
        GameController.view.drawContentOneShot(new DrawCommand(5, 5, "You picked up a blueberry"));
        pointsOfInterest.remove(poi);
        //Add item to inventory
    }

    private void move(){
        player.setXY(x, y); 
        GameController.view.refresh();
        for (PointOfInterest poi : pointsOfInterest) {
            if(x==poi.x && y==poi.y){
                poi.callback.onEntry(poi);
                break;
            }
        }
    }

    @Override
    protected void enterRoom() {

    }

    @Override
    public RoomType getType() {
        return RoomType.ADVENTURE;
    }

    private void exit(){
        InputManager.unregisterListener(KeyEvent.VK_W, up);
        InputManager.unregisterListener(KeyEvent.VK_A, left);
        InputManager.unregisterListener(KeyEvent.VK_S, down);
        InputManager.unregisterListener(KeyEvent.VK_D, right);
    }
    
    protected interface ActionOnEntry{
        void onEntry(PointOfInterest poi);
    }

    protected static class PointOfInterest{
        final int x;
        final int y;
        final String symbol;
        final ActionOnEntry callback;
        final DrawCommand drawCommand;

        public PointOfInterest(int x, int y, char symbol, ActionOnEntry callback) {
            this.x = x;
            this.y = y;
            this.symbol = symbol+"";
            this.callback = callback;
            this.drawCommand = new DrawCommand(x,y, this.symbol);
        }
    }
}