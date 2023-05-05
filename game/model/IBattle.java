package game.model;

import game.controller.AttackType;

import java.util.List;

public interface IBattle {
    EnemyFighter[] getEnemies();
    boolean isOnGoing();
    List<AttackType> getPossibleAttackTypesForPlayer();

    void meleeAttack(EnemyFighter enemy);

    void instaKill(EnemyFighter enemy);
}
