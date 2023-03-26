package View;

import java.util.stream.Stream;

public class DrawCommand {

    private int x;
    private int y;
    private String[] content;

    public DrawCommand(int x, int y, String... content) {
        this.x = x;
        this.y = y;
        this.content = content;
    }

    public char getAt(int x, int y){
        return content[y].charAt(x);
    }

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
