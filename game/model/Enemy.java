package game.model;

import java.util.ArrayList;
import java.util.function.Consumer;

import game.controller.AttackType;
import game.controller.ItemType;

public class Enemy extends LivingEntity implements EnemyFighter, InventoryHolder{
    private final ArrayList<Consumer<Integer>> hpListeners = new ArrayList<>();
    private final ArrayList<Consumer<Integer>> staminaListeners = new ArrayList<>();

    public Enemy(int hp, String name, int speed){
        super(hp, name, speed);
    }

    @Override
    public AttackType getAttackType(int stamina) {
        if (stamina >= 3) {
            return AttackType.MELEE;
        }
        return AttackType.NONE;
    }

    @Override
    protected void setStamina(int newValue) {
        super.setStamina(newValue);
        for (Consumer<Integer> staminaListener : this.staminaListeners) {
            staminaListener.accept(this.getStamina());
        }
    }

    @Override
    protected void setHp(int hp) {
        super.setHp(hp);
        for (Consumer<Integer> hpListener : hpListeners) {
            hpListener.accept(this.getHp());
        }
    }

    public void registerHpListener(Consumer<Integer> hpConsumer) {
        hpListeners.add(hpConsumer);
    }
    public void unregisterHpListener(Consumer<Integer> hpConsumer) {
        hpListeners.remove(hpConsumer);
    }

    public void registerStaminaListener(Consumer<Integer> staminaConsumer) {staminaListeners.add(staminaConsumer);}
    public void unregisterStaminaListener(Consumer<Integer> staminaConsumer) {staminaListeners.remove(staminaConsumer);}
}
