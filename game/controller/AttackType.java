package game.controller;

/**
 * Enum erillaisille hyökkäystavoille. Näistä vaihtoehdoista pelaaja valitsee taistellessaan
 */
public enum AttackType {
    MELEE {
        @Override
        public int getStaminaCost() {
            return 1;
        }
    }, RANGED {
        @Override
        public int getStaminaCost() {
            return 1;
        }
    },NONE {
        @Override
        public int getStaminaCost() {
            return 0;
        }
    };

    public abstract int getStaminaCost();
}
