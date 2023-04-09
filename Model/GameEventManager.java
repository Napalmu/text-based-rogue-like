package Model;

import java.util.ArrayList;

import Model.rooms.Enterable;

public class GameEventManager {
    
    private GameEventManager(){}

    @FunctionalInterface public interface RoomEnteredListener{ void action(Enterable room); }
    private static ArrayList<RoomEnteredListener> roomListeners = new ArrayList<>();
    public static void registerListener(RoomEnteredListener listener){
        roomListeners.add(listener);
    }
    public static void unregisterListener(RoomEnteredListener listener){
        roomListeners.remove(listener);
    }
    public static void emitRoomEnteredEvent(Enterable room){
        roomListeners.forEach(listener -> listener.action(room));
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
}
