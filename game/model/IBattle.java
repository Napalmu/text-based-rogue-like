package game.model;

import game.controller.AttackType;

import java.util.List;

public interface IBattle {
    EnemyFighter[] getEnemies();
    boolean isOnGoing();
    List<AttackType> getPossibleAttackTypesForPlayer();

    void meleeAttack(Fighter enemy);
    void instaKill(Fighter enemy);
    void recoverStamina(Fighter player);
    void rangedAttack(Fighter enemyFighter);
}
