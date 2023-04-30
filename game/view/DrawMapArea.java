package game.view;

import game.controller.GameController;
import game.model.GameEventManager;

import java.util.Arrays;

public class DrawMapArea extends DrawTextCommand{
    public DrawMapArea() {
        super(50, 16);
        GameEventManager.registerListener((GameEventManager.RoomEnteredListener) (room, success) -> {
            this.drawMap();
        });
        this.drawMap();
    }
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
        System.out.println(stringMap.length);
        for (String s : stringMap) {
            System.out.println(s);
        }
        this.setContent(stringMap);
    }
}
