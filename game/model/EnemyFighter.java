package game.model;

import game.controller.AttackType;

import java.util.function.Consumer;

public interface EnemyFighter extends Fighter{
    AttackType getAttackType(int stamina);
    void registerHpListener(Consumer<Integer> hpConsumer);
    void unregisterHpListener(Consumer<Integer> hpConsumer);
    void registerStaminaListener(Consumer<Integer> staminaConsumer);
    void unregisterStaminaListener(Consumer<Integer> hpConsumer);
}
