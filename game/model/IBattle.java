package game.model;

import game.controller.AttackType;

import java.util.List;

public interface IBattle {
    EnemyFighter[] getEnemies();
    void move(AttackType type);
    boolean isOnGoing();

    List<AttackType> getPossibleAttackTypesForPlayer();
}
