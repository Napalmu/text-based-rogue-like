package game.view;

public class DrawText extends DrawCommand {

    public DrawText(int x, int y, String... content) {
        super(x, y, content);
    }
    public DrawText(String... content) {
        super(0, 0, content);
    }

    public void setContent(String... content) {
        super.setContent(content);
    }
    public char getAt(int x, int y){
        return GetContent()[y].charAt(x);
    }
}
