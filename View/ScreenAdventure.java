package View;

import Controller.GameController;
import Model.rooms.Room;
import Model.rooms.RoomFactory;
import View.ascii_art.AsciiDrawing;

public class ScreenAdventure {

    private DrawCommand art;
    
    public ScreenAdventure(){        
        new RoomFactory().createAdventureRoom(new Room[0], AsciiDrawing.OUTSIDE);
    }
}
