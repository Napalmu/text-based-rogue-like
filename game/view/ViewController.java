package game.view;

import game.model.Fighter;
import game.model.IBattle;
import game.model.InventoryHolder;
import game.model.Item;
import game.model.rooms.IRoom;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ViewController {
    private final Terminal t = new Terminal(80, 24);

    private final Stack<Screen> uiStack;

    public ViewController() {
        uiStack = new Stack<>();
    }

    void refresh() {
        t.redraw();
    }

    /**
     * Vaihtaa annetun kohdan boldiksi, voi lisätä enumin ja muita fontteja myöhemmin
     * @param x x koordinaatti
     * @param y y koordinaatti
     */
    void ChangeFont(int x, int y){
        t.setBold(x, y);
    }

    /**
     * Adds the given command to the draw queue to be drawn every frame.
     *
     * @param content the content to draw
     */
    private void setContent(Drawable content) {
        t.setContent(content);
    }

    public void shutDown() {
        t.dispose();
    }

    void changeRoom(Screen room) {
        if (!uiStack.isEmpty()) {
            Screen s = uiStack.pop();
            s.exit();
        }
        uiStack.add(room);
        room.enter();
        setContent(uiStack.peek());
    }

    public void enterMessageRoom(IRoom room, List<String> messages) {
        changeRoom(new ScreenMessage(room, messages));
    }

    public void enterBossRoom(IRoom room, IBattle battle, Fighter boss) {
        changeRoom(new ScreenBossroom(room, battle, boss));
    }

    public void enterEnemyRoom(IRoom room, IBattle battle, Fighter... enemies) {
        changeRoom(new ScreenEnemy(room, battle, enemies));
    }

    public void enterAdventureRoom(IRoom room) {
        changeRoom(new ScreenMessage(room, Arrays.asList(new String[]{"Seikkailuhuone"})));
    }

    public void enterShopRoom(IRoom room, InventoryHolder items) {
        changeRoom(new ScreenShop(room, items));
    }

    public void enterMainMenu() {
        changeRoom(new ScreenMainMenu());
    }

    public void enterGameOver() {
        changeRoom(new ScreenGameOver());
    }
}
