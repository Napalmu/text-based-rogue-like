package game.model;

import java.util.ArrayList;
import java.util.HashMap;

import game.model.rooms.Enterable;
import game.model.rooms.IRoom;

public class GameEventManager {
    
    private GameEventManager(){

    }
    /*
    //Säätöä. Tällä tavalla ei olisi niin paljoa jaettua koodia, mutta kaikki olisi monimutkaisempaa
    private abstract static class SmallEventManager<T> {
        protected final ArrayList<T> listeners = new ArrayList<>();
        public void register(T listener) {
            listeners.add(listener);
        }
        public void unregister(T listener) {
            listeners.remove(listener);
        }
    }

    private static class RoomManager extends SmallEventManager<RoomEnteredListener> {
        public void emit(IRoom room) {
            this.listeners.forEach(l -> l.action(room));
        }
    }
    public static RoomManager roomManager = new RoomManager()
     */
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
}
