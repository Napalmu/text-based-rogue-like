package View;

import Model.GameEvent;

public class TextArea extends DrawArea{

    public TextArea(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void handleGameEvent(GameEvent e) {
        switch(e.getEventType()){
            case XDAMAGERECEIVED:
                System.out.println("Data area: "+e.getEventText());
            default:
                return;
        }
    }
    
}
