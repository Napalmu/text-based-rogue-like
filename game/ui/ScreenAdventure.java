package game.ui;

import game.model.rooms.RoomFactory;
import game.view.ascii_art.AsciiDrawing;

public class ScreenAdventure {
    
    public ScreenAdventure(){        
        new RoomFactory().createAdventureRoom( AsciiDrawing.OUTSIDE);
    }
}
