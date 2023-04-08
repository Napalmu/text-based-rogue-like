package View;
import Controller.GameController;
import java.util.HashMap;
import java.util.Map.Entry;

public class DrawArea extends DrawCommand {

    private HashMap<String, DrawCommand> contentList = new HashMap<>();

    public DrawArea(int x, int y, int width, int height) {
        super(x, y);
        
    }
    public DrawCommand createContent(String nameTag, DrawCommand drawCommand) {
        DrawCommand content = new DrawCommand(drawCommand.GetX() + this.GetX(), drawCommand.GetY() +  this.GetY(), drawCommand.GetContent());
        this.contentList.put(nameTag, content);        
        GameController.view.setContent(content);
        return content;
    }

    //öäh en tiiä mitä vittua teen
    // void Activate(){
    //     for (DrawCommand drawCommand : contentList.values()) {
    //         GameController.view.setContent(drawCommand);
    //     }
    // }

    public void removeContent(String nameTag){
        GameController.view.clearContent(contentList.get(nameTag));
        contentList.remove(nameTag);
    }
    public void removeContent(DrawCommand content){
        GameController.view.clearContent(content);
        String key = "";
        for(Entry<String, DrawCommand> entry : contentList.entrySet()){
            if(entry.getValue().equals(content)){
                key = entry.getKey();
            }
        };
        contentList.remove(key);
    }

    public void setContent(String nameTag, String... content){
        contentList.get(nameTag).setContent(content);
    }

    public void clearArea(){
        for (DrawCommand drawCommand : contentList.values()) {
            GameController.view.clearContent(drawCommand);
        }
        contentList.clear();
    }

}
