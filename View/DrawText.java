package View;

public class DrawText extends DrawCommand {


    public DrawText(String[] content) {
        super(0, 0, content);
    }

    public char getAt(int x, int y){
        return GetContent()[y].charAt(x);
    }
}
