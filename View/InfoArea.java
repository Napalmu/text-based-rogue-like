package View;

import Model.GameEvent;

public class InfoArea extends DrawArea{

    public InfoArea(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void handleGameEvent(GameEvent e) {
        switch(e.getEventType()){
            case ITEMXRECEIVED:
                System.out.println("Infoarea: "+e.getEventText());
            default:
                return;
        }
    }
    
}
