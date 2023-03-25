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

    public static void unregisterListener(int event, KeyConsumer consumer){
        if (instance.listeners.containsKey(event))
            instance.listeners.get(event).remove(consumer);
    }

    
    @Override
    public void keyPressed(KeyEvent e) {
        for (KeyConsumer c : listeners.getOrDefault(e.getKeyCode(), new ArrayList<>())){
            c.onKeyPressed();
        }
    }
    
    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}
    
    @FunctionalInterface
    public interface KeyConsumer{
        public void onKeyPressed();
    }
}
