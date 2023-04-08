package View;

import Controller.GameController;
import Controller.RoomType;

public class ViewController {
    private Terminal t = new Terminal(80,24);

    private DrawArea infoDrawArea;

    private DrawArea mainDrawArea;

    private DrawArea dataDrawArea;

    public DrawCommand createAreaContent(DrawCommand drawCommand, Area area){
        switch (area) {
            case infoArea:
                return infoDrawArea.createContent(drawCommand);
            case mainArea:
                return mainDrawArea.createContent(drawCommand);
            case dataArea:
                return dataDrawArea.createContent(drawCommand);
            default:
                return null;
        }
    }

    public void clearArea(Area area){
        switch (area) {
            case infoArea:
                infoDrawArea.clearArea();
                break;
            case mainArea:
                mainDrawArea.clearArea();
                break;
            case dataArea:
                dataDrawArea.clearArea();
                break;
            default:
        }
    }

    public void removeContent(DrawCommand content, Area area){
        switch (area) {
            case infoArea:
                infoDrawArea.removeContent(content);
                break;
            case mainArea:
                mainDrawArea.removeContent(content);
                break;
            case dataArea:
                dataDrawArea.removeContent(content);
                break;
            default:
        }
    }

    public ViewController(){
        infoDrawArea = new InfoArea(2, 16, 0, 0);
        mainDrawArea = new MainArea(2, 1, 0, 0);
        dataDrawArea = new TextArea(59, 1, 0, 0);
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
    public enum Area{
        infoArea,
        mainArea,
        dataArea
    }
}
