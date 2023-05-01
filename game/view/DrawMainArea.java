package game.view;

/**
 * Suurin alue ruudun yläosassa, jossa näkyy suurin osa pelin asioista.
 */
class DrawMainArea extends DrawScrollingArea {

    DrawMainArea() {
        this(2, 1);
    }

    DrawMainArea(int x, int y) {
        this(x, y, "");
    }

    DrawMainArea(int x, int y, String... content) {
        super(x, y, 10, content);
    }

    void drawMessages(String... msgs) {
        addMessage(msgs);
    }
}
