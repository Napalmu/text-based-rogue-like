package game.view;

import game.model.Fighter;
import game.model.Item;
import game.model.rooms.IRoom;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import javax.swing.text.html.parser.Entity;

 public class ViewController {
    private final Terminal t = new Terminal(80,24);

    private Stack<Screen> uiStack;
    
    public ViewController(){        
        uiStack = new Stack<>();        
        //GameEventManager.registerListener(this.shop::shopEntered);
    }

     void refresh(){
        t.redraw();
    }

    /**
     * Adds the given command to the draw queue to be drawn every frame.
     * 
     * @param content the content to draw
     */
     void setContent(Drawable content){
        t.setContent(content);
    }

     public void shutDown(){
        t.dispose();
    }

    void changeRoom(Screen room){
        try{
            Screen s = uiStack.pop();
            s.exit();
        }catch(Exception e){}

        uiStack.add(room);
        room.enter();
        setContent(uiStack.peek());
    }

     void onPlay() {
        setContent(uiStack.peek());
    }

    public void enterMessageRoom(IRoom room, List<String> messages){
        changeRoom(new ScreenMessage(room, messages));
    }
    public void enterBossRoom(IRoom room, Fighter boss){
        changeRoom(new ScreenBossroom(room, boss));
    }
    public void enterEnemyRoom(IRoom room, Fighter... enemies){
        changeRoom(new ScreenEnemy(room, enemies));
    }
    public void enterAdventureRoom(IRoom room){
        changeRoom(new ScreenMessage(room, Arrays.asList(new String[]{"Seikkailuhuone"})));
    }
    public void enterShopRoom(IRoom room, List<Item> items){
        changeRoom(new ScreenShop(room, items));
    }
    public void enterMainMenu() {
        changeRoom(new ScreenMainMenu());
    }

     public enum Area{
        infoArea,
        mainArea,
        dataArea
    }

}
