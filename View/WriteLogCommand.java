package View;

public class WriteLogCommand {
    private final int X = 3;
    private final int Y = 16;

    private String content;
    public WriteLogCommand(String content) {
        this.content = content;
        drawText();
    }
    void drawText(){
        DrawCommand text = new DrawCommand(X, Y, content);
    }

}
