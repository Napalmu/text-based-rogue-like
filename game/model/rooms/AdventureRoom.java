package game.model.rooms;

import java.util.ArrayList;
import java.util.List;
import java.awt.event.KeyEvent;

import game.controller.GameController;
import game.controller.InputManager;
import game.controller.ItemType;
import game.controller.RoomType;
import game.controller.InputManager.KeyConsumer;
import game.model.EntityManager;
import game.view.DrawCommand;
import game.view.DrawMovingStructure;
import game.view.ascii_art.AsciiDrawing;

class AdventureRoom extends Room{

    private int x=10;
    private int y=10;

    private final ArrayList<DrawCommand> commands = new ArrayList<>();

    List<Room> areaStack = new ArrayList<>();
    List<PointOfInterest> pointsOfInterest;
    AsciiDrawing map;
    
    DrawMovingStructure player = new DrawMovingStructure(x, y, new DrawCommand(0, 0, "X"));
    private KeyConsumer up    = ()->{y = y-1; move();};
    private KeyConsumer down  = ()->{y = y+1; move();};
    private KeyConsumer left  = ()->{x = x-1; move();};
    private KeyConsumer right = ()->{x = x+1; move();};

    public AdventureRoom(AsciiDrawing map) {
        pointsOfInterest = getPointsOfInterest();
        this.map = map;
    }

    protected List<PointOfInterest> getPointsOfInterest() {
        ArrayList<PointOfInterest> pois = new ArrayList<>();
        pois.add(new PointOfInterest(20,10,'b', this::gainBlueberry));
        pois.add(new PointOfInterest(30, 15, 'E', this::leave));
        return pois;
    }
    private void leave(PointOfInterest poi) {
        InputManager.unregisterListener(KeyEvent.VK_W, up);
        InputManager.unregisterListener(KeyEvent.VK_A, left);
        InputManager.unregisterListener(KeyEvent.VK_S, down);
        InputManager.unregisterListener(KeyEvent.VK_D, right);
        this.player.setContent(new String[0]);
        this.clear();
        this.moveToNextRoom();
    }

    private void gainBlueberry(PointOfInterest poi){
        GameController.view.clearContent(poi.drawCommand);
        GameController.view.drawContentOneShot(new DrawCommand(5, 5, "You picked up a blueberry"));
        pointsOfInterest.remove(poi);
        
        EntityManager.getPlayer().addItems(EntityManager.createItem(ItemType.BLUEBERRY));
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
        this.moveToNextRoom();
    }
    
    private void startAdveture(){        
        this.player.setContent("X");
        render(new DrawCommand(0, 0, map.getArt()));
        pointsOfInterest.forEach((poi) -> render(poi.drawCommand));
        InputManager.registerListener(KeyEvent.VK_W, up);
        InputManager.registerListener(KeyEvent.VK_A, left);
        InputManager.registerListener(KeyEvent.VK_S, down);
        InputManager.registerListener(KeyEvent.VK_D, right);

        render(player);
    }
    private void render(DrawCommand command) {
        this.commands.add(command);
        GameController.view.setContent(command);
    }

    private void clear() {
        for (DrawCommand command : this.commands) {
            GameController.view.clearContent(command);
        }
    }

    @Override
    protected ArrayList<InputManager.KeyPressedEvent> getKeyEvents(){
        ArrayList<InputManager.KeyPressedEvent> keys = new ArrayList<>();
        keys.add(new InputManager.KeyPressedEvent(KeyEvent.VK_A, this::startAdveture));
        return keys;
    }
    @Override
    protected ArrayList<String> getEvents(){
        ArrayList<String> result = new ArrayList<>();
        result.add("A: Aloita seikkailu.");
        return result;
    }

    @Override
    public RoomType getType() {
        return RoomType.ADVENTURE;
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