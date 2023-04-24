package game.model;
import java.util.ArrayList;
import java.util.HashMap;

import game.controller.EntityTypes;
import game.controller.GameConcept;
import game.controller.ItemType;

public class EntityManager {
    private static HashMap<GameConcept, ArrayList<Entity>> entities = new HashMap<>();

    /**
     * Metodin avulla pääsee käsiksi pelaaja-olioon. 
     * @return palauttaa pelaaja-olion luomalla sellaisen tarvittaesssa. Pelaaja-oliolle annetaan oletusarvot, jos sitä ei ole vielä olemassa.
     */
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

    /**
     * Metodin avulla luodaan vihollinen. 
     * @param hp Vihollisen hp.
     * @param name Vihollisen nimi.
     * @return Vihollis-olio.
     */
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

    /**
     * 
     * @param type
     * @return
     */
     public static Item createItem(ItemType type) {
        Item i = new Item(type);
        ArrayList<Entity> list = entities.getOrDefault(type, new ArrayList<>());
        entities.putIfAbsent(type, list);
        list.add(i);
        return i;
    }
}
