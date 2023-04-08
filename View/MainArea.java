package View;

import Controller.GameController;

import java.util.stream.Stream;

class MainArea extends DrawArea implements MainAreaI{
    private final DrawCommand message;
    private final DrawCommand options;
    public MainArea(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.message = new DrawCommand(x+1,y+1, "");
        this.options = new DrawCommand(x+5,y+6, "");
    }

    @Override
    public Stream<CharacterPosition> getStream() {
        //message ja options ovat tämän lapsia, joten ne lisätään streamiin
        Stream.Builder<CharacterPosition> c = Stream.builder();

        this.message.getStream().forEach(c::accept);
        this.options.getStream().forEach(c::accept);
        return c.build();
    }

    public void show() {
        GameController.view.setContent(this);
    }
    public void hide() {
        GameController.view.clearContent(this);
    }

    public void drawMessages(String... msgs) {
        this.message.setContent(msgs);
    }
    public void drawOptions(String... options) {
        this.options.setContent(options);
    }
}
