package Model;
import java.util.ArrayList;
import java.util.HashMap;

import Controller.EntityTypes;

class EntityManager {
    private static HashMap<EntityTypes, ArrayList<Entity>> entities = new HashMap<>();

    static Entity createPlayer(int hp, String name){
        Entity e = new Player(hp, name);
        ArrayList<Entity> l = new ArrayList<>();
        entities.put(EntityTypes.PLAYER, l);
        l.add(e);
        return e;
    }
    static Entity createEnemy(int hp, String name){
        Entity e = new Enemy(hp, name);
        if (!entities.containsKey(EntityTypes.ENEMY)){
            ArrayList<Entity> l = new ArrayList<>();
            entities.put(EntityTypes.ENEMY, l);
            l.add(e);
        }else {
            entities.get(EntityTypes.ENEMY).add(e);
        }
        return e;
    }

}
