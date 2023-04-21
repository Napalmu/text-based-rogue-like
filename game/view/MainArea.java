package game.view;

import game.controller.GameController;
import game.model.GameEventManager;
import game.model.IBattle;
import game.model.IEnemy;
import game.model.rooms.IRoom;

import java.util.stream.Stream;

class MainArea extends DrawArea {
    private final ScrollingDrawArea message;
    private final DrawCommand options;
    public MainArea(int x, int y) {
        super(x, y);
        this.message = new ScrollingDrawArea(x+1,y+1, new String[10]);
        //this.message = new DrawCommand(x+1,y+1, "");
        this.options = new DrawCommand(x+5,y+6, "");

        GameEventManager.registerListener(this::battleStarted);
        GameEventManager.registerListener(this::roomEntered);
    }
    private void roomEntered(IRoom room, boolean success) {
        this.message.clear();
        this.options.setContent("");
        //TODO erilliset metodit
        switch (room.getType()) {
            case MSG:
                this.message.addMessage("Tulit viestihuoneeseen!");
                break;
            case TREASURE:
                //TODO pitkät lauseet hankalia
                if (room.hasBeenEntered()) {
                    this.message.addMessage("Saavut huoneeseen, jossa on arkku.",
                            "Epäonneksesi huomaat kuitenkin, että olet jo käynyt", "huoneessa.");
                } else {
                    this.message.addMessage("Löydät itsesi huoneesta, josta löytyy", "salaperäinen arkku");
                }
                break;
            case BOSS:
                if (!success) {
                    this.message.addMessage("Höh, näyttää siltä, että tarvitset", "avaimen päästäksesi sisään");
                    break;
                }
                if (room.hasBeenEntered()) {
                    this.message.addMessage("Tässä huoneessa asui aikoinaan hirveä mörkö",
                            "onneksi tapoit hänet jo");
                } else {
                    this.message.addMessage("Tässä huoneessa asuu hirveä mörkö");
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
        this.message.addMessage(enemies.toString());
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
