package game.view;

import game.controller.GameController;
import game.model.GameEventManager;
import game.view.ascii_art.AsciiDrawing;

class Screen {
    protected final InfoArea infoDrawArea;
    protected final MainArea mainDrawArea;
    protected final TextArea dataDrawArea;
    private final DrawCommand art;

    Screen(){
        this.infoDrawArea = new InfoArea();
        this.mainDrawArea = new MainArea();
        this.dataDrawArea = new TextArea();
        this.art = new DrawCommand(0, 0, AsciiDrawing.SCREEN.getArt());

        //huone vaihtuu, joten kartta pitää piirtää uudestaan
        GameEventManager.registerListener((GameEventManager.RoomEnteredListener) (room, success) -> {
            this.drawMap();
        });
    }

    InfoArea getInfoArea(){return infoDrawArea; }
    MainArea getMainArea(){return mainDrawArea;}
    TextArea getDataArea(){return dataDrawArea;}    
    DrawCommand getArt() {return art;}

    private void drawMap() {
        MapRoom[][] map = GameController.model.getMap();
        String[] stringMap = new String[map.length];
        for (int y = 0; y < map.length; y++) {
            char[] chars = new char[map[y].length];
            for (int x = 0; x < map[y].length; x++) {
                if (map[y][x] == null) {
                    chars[x] = ' ';
                    continue;
                }
                chars[x] = map[y][x].getRoomType().symbol;
                if (map[y][x].hasPlayerInside()) {
                    chars[x] = '$';
                }
            }
            stringMap[y] = new String(chars);
        }
        //setContent(new DrawCommand(50, 16, stringMap));
    }

}