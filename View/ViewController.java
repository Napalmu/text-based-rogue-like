package View;

import Controller.GameController;
import Controller.RoomType;

import java.util.Arrays;

public class ViewController {
    private Terminal t = new Terminal(80,24);

    private DrawArea infoDrawArea;
    public DrawArea getInfoDrawArea() {
        return infoDrawArea;
    }
    private DrawArea mainDrawArea;
    public DrawArea getMainDrawArea() {
        return mainDrawArea;
    }
    private MainAreaI mainArea; //Andrein säätö
    public MainAreaI getMainArea() {
        return this.mainArea; //Andrein säätö
    }
    private DrawArea dataDrawArea;

    public DrawArea getDataDrawArea() {
        return dataDrawArea;
    }

    public ViewController(){
        infoDrawArea = new DrawArea(2, 16, 0, 0);
        mainDrawArea = new DrawArea(2, 1, 0, 0);
        dataDrawArea = new DrawArea(59, 1, 0, 0);
        mainArea = new MainArea(2, 1,0,0); //Andrein säätö
    }

    public void startGame(){
        GameController.ui.mainMenu();
    }

    public void refresh(){
        t.redraw();
    }

    /**
     * Draws the given command directly on the terminal once.
     * 
     * @param content the content to draw
     */
    public void drawContentOneShot(DrawCommand content){
        t.drawContent(content);
    }

    /**
     * Adds the given command to the draw queue to be drawn every frame.
     * 
     * @param content the content to draw
     */
    public void setContent(DrawCommand content){
        content.Activate();
        t.addContent(content);
    }

    public void clearContent(DrawCommand content){
        t.removeContent(content);
    }

    public void shutDown(){
        t.dispose();
    }

    private char roomTypeToChar(RoomType type) {
        switch (type) {
            case MSG:
                return 'M';
            case BOSS:
                return 'B';
            case ENEMY:
                return 'E';
            case TREASURE:
                return 'T';
            case ADVENTURE:
                return 'A';
            case SHOP:
                return 'S';
            default:
                throw new IllegalArgumentException();
        }
    }

    public void drawMap() {
        MapRoom[][] map = GameController.model.getMap();
        String[] stringMap = new String[map.length];
        for (int y = 0; y < map.length; y++) {
            char[] chars = new char[map[y].length];
            for (int x = 0; x < map[y].length; x++) {
                if (map[y][x] == null) {
                    chars[x] = ' ';
                    continue;
                }
                chars[x] = roomTypeToChar(map[y][x].getRoomType());
                if (map[y][x].hasPlayerInside()) {
                    chars[x] = '$';
                }
            }
            stringMap[y] = new String(chars);
        }
        setContent(new DrawCommand(50, 16, stringMap));
    }
}
