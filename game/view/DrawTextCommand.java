package game.view;

import java.util.stream.Stream;

import game.controller.GameController;

/**
 * Luokka, jonka avulla voi piirtää tekstiä
 */
class DrawTextCommand extends DrawCommand {
    private String[] content;

     DrawTextCommand(int x, int y, String... content) {
        super(x, y);
        setContent(content);
    }

     void setContent(String... content) {
        this.content = content;
        GameController.view.refresh();
    }

    String[] getContent(){
        return content;
    }

     char getAt(int x, int y){
        return getContent()[y].charAt(x);
    }

    Stream<CharacterPosition> getStream(){
        Stream.Builder<CharacterPosition> c = Stream.builder();
        if (getContent()==null) return c.build();

        for (int y=0;y<getContent().length; y++){
            for (int x=0;x<getContent()[y].length(); x++){
                c.accept(new CharacterPosition(getX() + x, getY() + y, getContent()[y].charAt(x)));
            }
        } 
        return c.build();
    }
}
