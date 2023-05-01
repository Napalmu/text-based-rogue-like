package game.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Yleinen piirtokomento DrawAreoiden ja yleisesti viewin käyttöön
 * Kannattaa tarkistaa periikö tämän joku käyttöön spesifimpi ja parempi komento
 */
class DrawCommand implements Drawable {

    private final int x;
    private final int y;

    private final ArrayList<DrawCommand> subCommands;

    DrawCommand(int x, int y) {
        this.x = x;
        this.y = y;
        this.subCommands = new ArrayList<>();
    }

    DrawCommand(int x, int y, DrawCommand... commands) {
        this(x, y);
        subCommands.addAll(Arrays.asList(commands));
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    @Override
    public DrawCommand getDrawCommand() {
        return this;
    }


    Stream<CharacterPosition> getStream() {
        Stream.Builder<CharacterPosition> c = Stream.builder();

        for (DrawCommand dc : subCommands) {
            dc.getStream().forEach(c::accept);
        }

        return c.build();
    }

    protected static class CharacterPosition {
        final char c;
        final int x;
        final int y;

        CharacterPosition(int x, int y, char c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }
    }


}
