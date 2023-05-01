package game.model;
import java.util.ArrayList;
import java.util.Arrays;
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
            return (Player) createPlayer(100, 0, 2);
        }
        return (Player) entities.get(EntityTypes.PLAYER).get(0);
    }

    static Entity createPlayer(int hp, int money, int speed){
         String name = "Pelaaja";
        Player e = new Player(hp, name, speed);
        Item[] moneys = new Item[money];
        Arrays.fill(moneys, createItem(ItemType.COIN));
        e.receiveItems(moneys);

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
     public static Entity createEnemy(int hp, String name, int speed){
        Enemy e = new Enemy(hp, name, speed);
        e.receiveItems(createItem(ItemType.BLUEBERRY));
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
