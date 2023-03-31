package Managers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;

public class InputManager implements KeyListener{
    
    public final static InputManager instance = new InputManager();    
    private HashMap<Integer, ArrayList<KeyConsumer>> listeners = new HashMap<>();

    private InputManager(){}

    public static void registerListener(int event, KeyConsumer consumer){
        if (!instance.listeners.containsKey(event)) instance.listeners.put(event, new ArrayList<>());
        instance.listeners.get(event).add(consumer);
    }
    public static void registerListener(KeyPressedEvent keyEvent){
        registerListener(keyEvent.getEvent(), keyEvent.getKeyConsumer());
    }
    // public static void registerListener(int event, KeyConsumer consumer, boolean unregister_after){
    //     if (!instance.listeners.containsKey(event)) instance.listeners.put(event, new ArrayList<>());
    //     instance.listeners.get(event).add(consumer);

    //     if(unregister_after == true) instance.listeners.get(event).add(() -> {unregisterListener(event, consumer);});
    // }

    public static void registerListenerList(ArrayList<KeyPressedEvent> keyList, boolean unregister_after){
        for (KeyPressedEvent kPressedEvent : keyList) {
            registerListener(kPressedEvent);

            KeyConsumer k = 
            () ->{    
                for (KeyPressedEvent _kPressedEvent : keyList) {
                    unregisterListener(_kPressedEvent);
                }
            };
            registerListener(kPressedEvent.getEvent(), k);
        }

    }

    public static void unregisterListener(int event, KeyConsumer consumer){
        if (instance.listeners.containsKey(event))
            instance.listeners.get(event).remove(consumer);
    }
    public static void unregisterListener(KeyPressedEvent keyEvent){
        unregisterListener(keyEvent.getEvent(), keyEvent.getKeyConsumer());
    }

    
    @Override
    public void keyPressed(KeyEvent e) {
        ArrayList<KeyConsumer> copyList = new ArrayList<>(listeners.getOrDefault(e.getKeyCode(), new ArrayList<>()));
        for (KeyConsumer c : copyList){
            c.onKeyPressed();
        }
    }
    
    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}
    
    @FunctionalInterface
    public interface KeyConsumer{
        public void onKeyPressed();
    }
    public static class KeyPressedEvent{
        private int event;
        private KeyConsumer keyConsumer;

        public KeyPressedEvent(int event, KeyConsumer keyConsumer) {
            this.event = event;
            this.keyConsumer = keyConsumer;
        }

        public int getEvent() {
            return event;
        }

        public KeyConsumer getKeyConsumer() {
            return keyConsumer;
        }
    }
}
