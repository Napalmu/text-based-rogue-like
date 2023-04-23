package game.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Yleinen piirtokomento DrawAreoiden ja yleisesti viewin käyttöön
 * Kannattaa tarkistaa periikö tämän joku käyttöön spesifimpi ja parempi komento
 */
class DrawCommand implements Drawable{

    private int x;
    private int y;

    private ArrayList<DrawCommand> subCommands;

    DrawCommand(int x, int y) {
        this.x = x;
        this.y = y;
        this.subCommands = new ArrayList<>();
    }

    DrawCommand(int x, int y, DrawCommand... commands) {
        this(x,y);
        for(DrawCommand dc : commands){
            subCommands.add(dc);
        }
    }

    int getX(){
        return x;
    }
    
    int getY(){
        return y;
    }

    void setX(int x){
        this.x = x;
    }

    void setY(int y){
        this.y = y;
    }

    //kun asetetaan piirrettäväksi
    void activate(){
        //
    }
    
    @Override
    public DrawCommand getDrawCommand() {
        return this;
    }

    void addCommands(DrawCommand... dc){
        subCommands.addAll(Arrays.asList(dc));
    }

    Stream<CharacterPosition> getStream(){
        Stream.Builder<CharacterPosition> c = Stream.builder();

        for (DrawCommand dc : subCommands){
            dc.getStream().forEach(c::accept);
        }

        return c.build();
    }

    class CharacterPosition{
         final char c;
         final int x;
         final int y;

        CharacterPosition(int x, int y, char c){
            this.x = x;
            this.y = y;
            this.c = c;
        }
    }


}
