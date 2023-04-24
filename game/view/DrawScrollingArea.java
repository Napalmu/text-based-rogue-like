package game.view;

import java.util.ArrayList;

import game.controller.GameController;

 class DrawScrollingArea extends DrawTextCommand{
    private final int rows;
    private final ArrayList<String> messages = new ArrayList<>();

     DrawScrollingArea(int x, int y, int rows, String... content) {
        super(x, y, "");
        this.clear();
        this.rows = rows;
    }

     void addMessage(String... msgs) {
        for (String msg : msgs) {
            addMessage(msg);
        }
    }

     void clear() {
        this.messages.clear();
    }

     void addMessage(String msg) {
        System.out.println("Jep:"+msg);
        this.messages.add(msg);
        GameController.view.refresh();
    }

     void setMessage(String msg) {
        this.clear();
        this.addMessage(msg);
    }

    @Override
    String[] getContent() {
        int from = Math.max(0,messages.size()-rows);
        int to = messages.size();
        return messages.subList(from, to).toArray(new String[0]);
    }
    
}
