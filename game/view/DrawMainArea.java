package game.view;

import game.model.GameEventManager;
import game.model.IBattle;
import game.model.IEnemy;

class DrawMainArea extends DrawScrollingArea{     
    
    DrawMainArea(){this(2,1);}
    DrawMainArea(int x, int y){this(x,y, "");}
    DrawMainArea(int x, int y, String... content) {
        super(x, y, 10, content);

        GameEventManager.registerListener(this::battleStarted);
        //GameEventManager.registerListener(this::roomEntered);
        //GameEventManager.registerListener(this::shopEntered);
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
