package game.view;

import game.controller.GameController;
import game.model.GameEventManager;

import static game.model.rooms.CompassPoints.*;

import java.util.Arrays;
import game.model.rooms.Direction;
/**
 * Alue, joka piirtää kartan tarvittaessa
 */
class DrawMapArea extends DrawTextCommand {
    public DrawMapArea() {
        super(60, 16);
        GameEventManager.registerListener((GameEventManager.RoomEnteredListener) room -> {
            this.drawMap();

        });
        this.drawMap();
    }

    private void drawMap() {
        MapRoom[][] map = GameController.model.getMap();
        MapWindow mapWindow = new MapWindow(map);

        this.setContent(mapWindow.getStringMap());
    }
}

class MapWindow{
    MapRoom[][] map;
    char[][] mapDrawning;

    final int HEIGHT = 7;
    final int WIDTH = 11;
    MapWindow(MapRoom[][] map){
        mapDrawning = new char[HEIGHT][WIDTH];
        for (int y = 0; y < mapDrawning.length; y++) {
            Arrays.fill(mapDrawning[y], ' ');
        }

        this.map = map;

        GenerateCenteredDraw();

        DrawBorders();
    }
    void DrawBorders(){
        for (int i = 0; i < WIDTH; i++) {
            mapDrawning[0][i] = '-';
            mapDrawning[HEIGHT - 1][i] = '-';
        }
        for (int i = 0; i < HEIGHT; i++) {
            mapDrawning[i][0] = '|';
            mapDrawning[i][WIDTH - 1] = '|';
        }
    }
    void GenerateCenteredDraw(){
        class Cord{
            int x;
            int y;
        }

        Cord center = new Cord();
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                if(map[y][x] == null) continue;

                if (map[y][x].hasPlayerInside()) {
                    center.x = x;
                    center.y = y;
                }
            }
        }

        for (int y = center.y - 1; y <= center.y + 1; y++) {
            for (int x = center.x - 2; x <= center.x + 2; x++) {
                if(y < 0 || y >= map.length){
                    continue;
                }
                if(x < 0 || x >= map[y].length){
                    continue;
                }
                System.out.println(y + " "+ x + "cenX" + center.x + "cenY" + center.y);

                SetRoom(map[y][x], (y-center.y), (x-center.x));
            }
        }

    }
    void SetRoom(MapRoom room, int y, int x){
        if(room == null) return;

        x = (x+2)*2 + 1;
        y = (y+1)*2 + 1;

        mapDrawning[y][x] = room.getRoomType().symbol;

        

        for (Direction dir : room.neighbours()) {
            if(dir.getLabel() == NORTH){
                mapDrawning[y-1][x] = '|'; 
            }
            if(dir.getLabel() == WEST){
                mapDrawning[y][x-1] = '-'; 
            }
        }
    }

    String[] getStringMap(){
        String[] stringMap = new String[HEIGHT];
        
        for (int y = 0; y < HEIGHT; y++) {
            stringMap[y] = new String(mapDrawning[y]);

        }
        System.out.println(Arrays.toString(stringMap));
        return stringMap;
    }


}
