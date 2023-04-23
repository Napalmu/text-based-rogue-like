package game.view;

import game.model.GameEventManager;
import game.model.IBattle;
import game.model.IEnemy;
import game.model.rooms.IRoom;

class MainArea extends ScrollingDrawArea{     
    
    MainArea(){this(2,1);}
    MainArea(int x, int y){this(x,y, "");}
    MainArea(int x, int y, String... content) {
        super(x, y, 10, content);

        GameEventManager.registerListener(this::battleStarted);
        //GameEventManager.registerListener(this::roomEntered);
        //GameEventManager.registerListener(this::shopEntered);
    }

    private void roomEntered(IRoom room, boolean success) {
        clear();
        setContent("");
        //TODO erilliset metodit
        switch (room.getType()) {
            case MSG:
                addMessage("Tulit viestihuoneeseen!");
                break;
            case TREASURE:
                //TODO pitkät lauseet hankalia
                
            case BOSS:
                
        }
    }
    private void battleStarted(IBattle battle) {
        StringBuilder enemies = new StringBuilder();
        for (IEnemy enemy : battle.getEnemies()) {
            enemies.append("Vihollinen: ").append(enemy.getName()).append("\n");
        }
        System.out.println(enemies);
        addMessage(enemies.toString());
    }

     void drawMessages(String... msgs) {
        addMessage(msgs);
    }
     void drawOptions(String... options) {
        addMessage(options);
    }
}
