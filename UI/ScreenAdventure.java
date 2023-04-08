package UI;

import Model.rooms.RoomFactory;
import View.ascii_art.AsciiDrawing;

public class ScreenAdventure {
    
    public ScreenAdventure(){        
        new RoomFactory().createAdventureRoom( AsciiDrawing.OUTSIDE);
    }
}
