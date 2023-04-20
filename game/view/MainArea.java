package game.view;

import game.controller.GameController;
import game.model.GameEventManager;
import game.model.IBattle;
import game.model.IEnemy;
import game.model.rooms.IRoom;

import java.util.stream.Stream;

class MainArea extends DrawArea {
    private final DrawCommand message;
    private final DrawCommand options;
    public MainArea(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.message = new DrawCommand(x+1,y+1, "");
        this.options = new DrawCommand(x+5,y+6, "");

        GameEventManager.registerListener(this::battleStarted);
        GameEventManager.registerListener(this::roomEntered);
    }
    private void roomEntered(IRoom room) {
        this.message.setContent("");
        this.options.setContent("");
        switch (room.getType()) {
            case MSG:
                this.message.setContent("Tulit viestihuoneeseen!");
                break;
            case TREASURE:
                //TODO pitkät lauseet hankalia
                if (room.hasBeenEntered()) {
                    this.message.setContent("Saavut huoneeseen, jossa on arkku.",
                            "Epäonneksesi huomaat kuitenkin, että olet jo käynyt", "huoneessa.");
                } else {
                    this.message.setContent("Löydät itsesi huoneesta, josta löytyy", "salaperäinen arkku");
                }
                break;
        }
    }
    private void battleStarted(IBattle battle) {
        StringBuilder enemies = new StringBuilder();
        for (IEnemy enemy : battle.getEnemies()) {
            enemies.append("Vihollinen: ").append(enemy.getName()).append("\n");
        }
        System.out.println(enemies);
        this.message.setContent(enemies.toString());
    }

    @Override
    public Stream<CharacterPosition> getStream() {
        //message ja options ovat tämän lapsia, joten ne lisätään virtaan myös
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
