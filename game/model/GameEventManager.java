package game.model;

import java.util.ArrayList;

import game.model.rooms.IRoom;

public class GameEventManager {
    
    private GameEventManager(){

    }

    @FunctionalInterface public interface RoomEnteredListener{ void action(IRoom room,boolean success); }

    private static ArrayList<RoomEnteredListener> roomListeners = new ArrayList<>();
    public static void registerListener(RoomEnteredListener listener){
        roomListeners.add(listener);
    }
    public static void unregisterListener(RoomEnteredListener listener){
        roomListeners.remove(listener);
    }
    public static void emitRoomEnteredEvent(IRoom room, boolean success){
        roomListeners.forEach(listener -> listener.action(room, success));
    }
    public static void emitRoomEnteredEvent(IRoom room) {
        emitRoomEnteredEvent(room, true);
    }

    @FunctionalInterface public interface DamageReceivedListener{ void action(int amount); }
    private static ArrayList<DamageReceivedListener> damageListeners = new ArrayList<>();
    public static void registerListener(DamageReceivedListener listener){
        damageListeners.add(listener);
    }
    public static void unregisterListener(DamageReceivedListener listener){
        damageListeners.remove(listener);
    }
    public static void emitDamageReceivedEvent(int amount){
        damageListeners.forEach(listener -> listener.action(amount));
    }

    @FunctionalInterface public interface ItemReceivedListener{ void action(Item item); }
    private static ArrayList<ItemReceivedListener> itemListeners = new ArrayList<>();
    public static void registerListener(ItemReceivedListener listener){
        itemListeners.add(listener);
    }
    public static void unregisterListener(ItemReceivedListener listener){
        itemListeners.remove(listener);
    }
    public static void emitItemReceivedEvent(Item item){
        itemListeners.forEach(listener -> listener.action(item));
    }

    @FunctionalInterface public interface BattleStartedListener{ void action(IBattle battle);}
    private static ArrayList<BattleStartedListener> battleListeners = new ArrayList<>();
    public static void registerListener(BattleStartedListener listener){
        battleListeners.add(listener);
    }
    public static void unregisterListener(BattleStartedListener listener){
        battleListeners.remove(listener);
    }
    public static void emitBattleStarted(IBattle item){
        battleListeners.forEach(listener -> listener.action(item));
    }

    @FunctionalInterface public interface ShopEnteredListener {void action(ArrayList<Item> items);}
    private static ArrayList<ShopEnteredListener> shoplisteners = new ArrayList<>();
    public static void registerListener(ShopEnteredListener listener) {shoplisteners.add(listener);}
    public static void emitShopEntered(ArrayList<Item> items) {
        for (ShopEnteredListener shoplistener : shoplisteners) {
            shoplistener.action(items);
        }
    }

    private static final ArrayList<BuyItemListener> buyListeners = new ArrayList<>();
    @FunctionalInterface public interface BuyItemListener {void action(Item item);}
    public static void registerListener(BuyItemListener listener) {buyListeners.add(listener);}
    public static void emitBuyItem(Item item) {
        for (BuyItemListener buyListener : buyListeners) {
            buyListener.action(item);
        }
    }

    //Säätöä. Tällä tavalla ei olisi niin paljoa jaettua koodia, mutta kaikki olisi monimutkaisempaa
    private abstract static class BaseEventManager<T> {
        protected final ArrayList<T> listeners = new ArrayList<>();
        public void listen(T listener) {
            listeners.add(listener);
        }
        public void unregister(T listener) {
            listeners.remove(listener);
        }
    }
    @FunctionalInterface public interface InventoryListener{ void action(Item[] items);}
    public static class InventoryEventManager extends BaseEventManager<InventoryListener> {
        public void emit(Item[] items) {
            this.listeners.forEach(l -> l.action(items));
        }
    }
    public static InventoryEventManager inventory = new InventoryEventManager();
}
