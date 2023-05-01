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

    @FunctionalInterface public interface ItemReceivedListener{ void action(Item item); }
    private static ArrayList<ItemReceivedListener> itemListeners = new ArrayList<>();
    public static void registerListener(ItemReceivedListener listener){itemListeners.add(listener);}
    public static void unregisterListener(ItemReceivedListener listener){itemListeners.remove(listener);}
    public static void emitItemReceivedEvent(Item item){itemListeners.forEach(listener -> listener.action(item));}

    @FunctionalInterface public interface BuyItemListener {void action(Item item);}
    private static final ArrayList<BuyItemListener> buyListeners = new ArrayList<>();
    public static void registerListener(BuyItemListener listener) {buyListeners.add(listener);}
    public static void unregisterListener(BuyItemListener listener){buyListeners.remove(listener);}
    public static void emitBuyItem(Item item) {buyListeners.forEach(listener -> listener.action(item));}

    @FunctionalInterface public interface PlayerStateListener {void action(PlayerState state);}
    private static final ArrayList<PlayerStateListener> playerStateListeners = new ArrayList<>();
    public static void registerListener(PlayerStateListener listener) {playerStateListeners.add(listener);}
    public static void emitPlayerStateChanged(PlayerState state) {playerStateListeners.forEach(l->l.action(state));}

   //TODO tämä eventti ottaa nyt stringin ku en jaks muuta
   @FunctionalInterface public interface BattleActionListener {void action(String str);}
   private static final ArrayList<BattleActionListener> actionListeners = new ArrayList<>();
    public static void registerListener(BattleActionListener listener) {actionListeners.add(listener);}
   public static void emitBattleAction(String str) {actionListeners.forEach(l -> l.action(str));}
}
