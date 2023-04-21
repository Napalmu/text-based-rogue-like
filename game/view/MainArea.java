package game.view;

import game.model.GameEventManager;
import game.model.IBattle;
import game.model.IEnemy;
import game.model.Item;
import game.model.rooms.IRoom;

import java.util.ArrayList;

class MainArea extends ParentDrawCommand {
    private final ScrollingDrawArea message;
    private final DrawCommand options;
    public MainArea(int x, int y) {
        super(x, y);
        this.message = new ScrollingDrawArea(x+1,y+1, new String[10]);
        this.options = new DrawCommand(x+5,y+6, "");
        this.addChildren(this.message, this.options);

        GameEventManager.registerListener(this::battleStarted);
        GameEventManager.registerListener(this::roomEntered);
        //GameEventManager.registerListener(this::shopEntered);
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
    public void drawShopItems(ArrayList<Item> items) {
        String[] strings = new String[items.size()+2];
        strings[0] = "Kaupan valikoima:";
        strings[1] = "0: Pois kaupasta";
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            strings[i+2] = (i+1) +": "+ item.getType().getName() + " " + item.getType().price() + "€";
        }
        this.message.setContent(strings);
    }


    public void drawMessages(String... msgs) {
        this.message.setContent(msgs);
    }
    public void drawOptions(String... options) {
        this.options.setContent(options);
    }
}
