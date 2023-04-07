package View;

import Model.rooms.RoomFactory;
import View.ascii_art.AsciiDrawing;

public class ScreenAdventure {

    private DrawCommand art;
    
    public ScreenAdventure(){        
        new RoomFactory().createAdventureRoom( AsciiDrawing.OUTSIDE);
    }
}
