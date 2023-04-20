package game.view;

import java.util.ArrayList;
import java.util.Arrays;

public class ScrollingDrawArea extends DrawCommand{
    private final int rows;
    private final String[] view;
    private final ArrayList<String> messages = new ArrayList<>();

    public ScrollingDrawArea(int x, int y, String[] content) {
        super(x, y, "");
        this.view = content;
        this.clear();
        this.rows = content.length;
    }
    public void addMessage(String... msgs) {
        for (String msg : msgs) {
            addMessage(msg);
        }
    }
    public void clear() {
        Arrays.fill(this.view,"");
    }
    public void addMessage(String msg) {
        for (int i = 0; i < this.rows; i++) {
            if (this.view[i].equals("")) {
                this.view[i] = msg;
                this.setContent(this.view);
                return;
            }
        }
        this.messages.add(msg);
        for (int i = 1; i < this.rows; i++) {
            this.view[i-1] = this.view[i];
        }
        this.view[this.rows-1] = msg;
        this.setContent(this.view);
    }

}
