package View;
import Controller.GameController;
import Model.GameEvent;
import Model.ModelController.GameEventListener;

import java.util.ArrayList;


public abstract class DrawArea extends DrawCommand implements GameEventListener{

    private ArrayList<DrawCommand> contentList = new ArrayList<>();

    public DrawArea(int x, int y, int width, int height) {
        super(x, y);
        
    }
    public DrawCommand createContent(DrawCommand drawCommand) {
        DrawCommand content;
        if(drawCommand instanceof DrawText) content = new DrawText(drawCommand.GetX() + this.GetX(), drawCommand.GetY() +  this.GetY(), drawCommand.GetContent());
        else content = new DrawCommand(drawCommand.GetX() + this.GetX(), drawCommand.GetY() +  this.GetY(), drawCommand.GetContent());
        this.contentList.add(content);
        GameController.view.setContent(content);
        return content;
    }
    //öäh en tiiä mitä vittua teen
    // void Activate(){
    //     for (DrawCommand drawCommand : contentList.values()) {
    //         GameController.view.setContent(drawCommand);
    //     }
    // }

    public void removeContent(DrawCommand content){
        GameController.view.clearContent(content);
        contentList.remove(content);
    }

    public void clearArea(){
        for (DrawCommand drawCommand : contentList) {
            GameController.view.clearContent(drawCommand);
        }
        contentList.clear();
    }

    public abstract void handleGameEvent(GameEvent e);

}
