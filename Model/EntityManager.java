package Model;
import java.util.ArrayList;
import java.util.HashMap;

import Controller.EntityTypes;
import Controller.GameConcept;
import Controller.ItemType;

public class EntityManager {
    private static HashMap<GameConcept, ArrayList<Entity>> entities = new HashMap<>();
    public static Player getPlayer() {
        if (!entities.containsKey(EntityTypes.PLAYER)) {
            return (Player) createPlayer(100, "Pekka");
        }
        return (Player) entities.get(EntityTypes.PLAYER).get(0);
    }
    static Entity createPlayer(int hp, String name){
        Entity e = new Player(hp, name);
        ArrayList<Entity> l = new ArrayList<>();
        entities.put(EntityTypes.PLAYER, l);
        l.add(e);
        return e;
    }
    public static Entity createEnemy(int hp, String name){
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
    public static Item createItem(ItemType type) {
        Item i = new Item(type);
        ArrayList<Entity> list = entities.getOrDefault(type, new ArrayList<>());
        entities.putIfAbsent(type, list);
        list.add(i);
        return i;
    }
}
