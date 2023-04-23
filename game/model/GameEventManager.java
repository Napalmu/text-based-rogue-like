package game.model;

import java.util.ArrayList;
import java.util.List;

import game.model.rooms.IRoom;

/**
 * GameEventManager hoitaa viestien välityksen pelin eri komponenttien välillä ilman tiukkaa paritusta (engl. coupling)
 * 
 * Kuuntelevan komponentin vastuulla on hoitaa kuuntelijan rekisteröinti ja poisto.
*/
 public class GameEventManager {
    
    private GameEventManager(){}

    @FunctionalInterface public interface RoomEnteredListener{ void action(IRoom room,boolean success); }
    private static ArrayList<RoomEnteredListener> roomListeners = new ArrayList<>();
    public static void registerListener(RoomEnteredListener listener){roomListeners.add(listener);}
    public static void unregisterListener(RoomEnteredListener listener){roomListeners.remove(listener);}
    public static void emitRoomEnteredEvent(IRoom room, boolean success){roomListeners.forEach(listener -> listener.action(room, success));}
    public static void emitRoomEnteredEvent(IRoom room) {emitRoomEnteredEvent(room, true);}

    @FunctionalInterface public interface DamageReceivedListener{ void action(int amount); }
    private static ArrayList<DamageReceivedListener> damageListeners = new ArrayList<>();
    public static void registerListener(DamageReceivedListener listener){damageListeners.add(listener);}
    public static void unregisterListener(DamageReceivedListener listener){damageListeners.remove(listener);}
    public static void emitDamageReceivedEvent(int amount){damageListeners.forEach(listener -> listener.action(amount));}

    @FunctionalInterface public interface ItemReceivedListener{ void action(Item item); }
    private static ArrayList<ItemReceivedListener> itemListeners = new ArrayList<>();
    public static void registerListener(ItemReceivedListener listener){itemListeners.add(listener);}
    public static void unregisterListener(ItemReceivedListener listener){itemListeners.remove(listener);}
    public static void emitItemReceivedEvent(Item item){itemListeners.forEach(listener -> listener.action(item));}

    @FunctionalInterface public interface BattleStartedListener{ void action(IBattle battle);}
    private static ArrayList<BattleStartedListener> battleListeners = new ArrayList<>();
    public static void registerListener(BattleStartedListener listener){battleListeners.add(listener);}
    public static void unregisterListener(BattleStartedListener listener){battleListeners.remove(listener);}
    public static void emitBattleStarted(IBattle item){battleListeners.forEach(listener -> listener.action(item));}

    @FunctionalInterface public interface ShopEnteredListener {void action(List<Item> items, Runnable onExit);}
    private static ArrayList<ShopEnteredListener> shoplisteners = new ArrayList<>();
    public static void registerListener(ShopEnteredListener listener) {shoplisteners.add(listener);}
    public static void unregisterListener(ShopEnteredListener listener){shoplisteners.remove(listener);}
    public static void emitShopEntered(List<Item> items, Runnable onExit) {shoplisteners.forEach(listener -> listener.action(items, onExit));}

    @FunctionalInterface public interface BuyItemListener {void action(Item item);}
    private static final ArrayList<BuyItemListener> buyListeners = new ArrayList<>();
    public static void registerListener(BuyItemListener listener) {buyListeners.add(listener);}
    public static void unregisterListener(BuyItemListener listener){buyListeners.remove(listener);}
    public static void emitBuyItem(Item item) {buyListeners.forEach(listener -> listener.action(item));}
        
     //Säätöä. Tällä tavalla ei olisi niin paljoa jaettua koodia, mutta kaikki olisi monimutkaisempaa
    private abstract static class BaseEventManager<T> {
        protected final ArrayList<T> listeners = new ArrayList<>();
         public void listen(T listener) {listeners.add(listener);}
         void unregister(T listener) {listeners.remove(listener);}
    }

    @FunctionalInterface public interface InventoryListener{ void action(Item[] items);}
    public static final InventoryEventManager inventory = new InventoryEventManager();
    public static class InventoryEventManager extends BaseEventManager<InventoryListener> {
         void emit(Item[] items) {this.listeners.forEach(l -> l.action(items));}
    }

}
