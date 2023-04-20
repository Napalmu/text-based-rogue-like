package game.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class ScrollingDrawArea extends DrawCommand{
    private final int rows;
    private ArrayList<String> messages = new ArrayList<>();

    public ScrollingDrawArea(int x, int y, String[] content) {
        super(x, y, content);
        this.rows = content.length;
    }
    public void addMessage(String msg) {
        for (int i = 0; i < this.rows; i++) {
            if (this.content[i].equals("")) {
                this.content[i] = msg;
                return;
            }
        }
        this.messages.add(msg);
        for (int i = 1; i < this.rows; i++) {
            this.content[i-1] = this.content[i];
        }
        this.content[this.rows-1] = msg;
    }

}
