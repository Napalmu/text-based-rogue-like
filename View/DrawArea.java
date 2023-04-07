package View;
import Controller.GameController;
import java.util.HashMap;

public class DrawArea extends DrawCommand {

    private HashMap<String, DrawCommand> contentList = new HashMap<>();

    public DrawArea(int x, int y, int width, int height) {
        super(x, y);
        
    }
    public void createContent(DrawCommand drawCommand, String nameTag) {
        DrawCommand content = new DrawCommand(drawCommand.GetX() + this.GetX(), drawCommand.GetY() +  this.GetY(), drawCommand.GetContent());
        this.contentList.put(nameTag, content);        
        GameController.view.setContent(drawCommand);
    }

    //öäh en tiiä mitä vittua teen
    void Activate(){
        for (DrawCommand drawCommand : contentList.values()) {
            GameController.view.setContent(drawCommand);
        }
    }

    public void removeContent(String nameTag){
        GameController.view.clearContent(contentList.get(nameTag));
        contentList.remove(nameTag);
    }

    public DrawCommand getContent(String nameTag){
        return contentList.get(nameTag);
    }

    public void clearArea(){
        for (DrawCommand drawCommand : contentList.values()) {
            GameController.view.clearContent(drawCommand);
        }
    }

}
