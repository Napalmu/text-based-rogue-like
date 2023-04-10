package game.view;

import java.util.stream.Stream;

import game.controller.GameController;

public class DrawCommand {

    private int x;
    private int y;

    private String[] content;

    public DrawCommand(int x, int y, String... content) {
        this.x = x;
        this.y = y;
        this.content = content;
    }

    int GetX(){
        return x;
    }
    
    int GetY(){
        return y;
    }

    void SetX(int x){
        this.x = x;
    }

    void SetY(int y){
        this.y = y;
    }


    String[] GetContent(){
        return content;
    }

    void setContent(String... content) {
        this.content = content;
        GameController.view.refresh();
    }

    //kun asetetaan piirrettäväksi
    void Activate(){
        //
    }

    // public char getAt(int x, int y){
    //     return content[y].charAt(x);
    // }

    // void setXY(int x, int y){
    //     this.x=x;
    //     this.y=y;
    //     GameController.view.refresh();
    // }




    public Stream<CharacterPosition> getStream(){
        Stream.Builder<CharacterPosition> c = Stream.builder();
        for (int y=0;y<content.length; y++){
            for (int x=0;x<content[y].length(); x++){
                c.accept(new CharacterPosition(this.x+x, this.y+y, content[y].charAt(x)));
            }
        } 
        return c.build();
    }

    public class CharacterPosition{
        public final char c;
        public final int x;
        public final int y;

        CharacterPosition(int x, int y, char c){
            this.x = x;
            this.y = y;
            this.c = c;
        }
    }

}
