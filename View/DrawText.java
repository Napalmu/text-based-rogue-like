package View;
import Controller.GameController;
public class DrawText extends DrawCommand {


    public DrawText(String... content) {
        super(0, 0, content);
    }
    public void setContent(String... content) {
        super.setContent(content);
        GameController.view.refresh();
    }
    public char getAt(int x, int y){
        return GetContent()[y].charAt(x);
    }
}
