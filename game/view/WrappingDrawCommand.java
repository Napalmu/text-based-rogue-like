package game.view;

/**
 * Aattelin, että vois tehdä sellasen drawcommandin, että teksti siirtyy seuraavalle riville
 * , jos edelliselle ei mahdu
 */
public class WrappingDrawCommand extends DrawCommand{
    public WrappingDrawCommand(int x, int y, int width, String... content) {
        super(x, y, content);

    }
}
