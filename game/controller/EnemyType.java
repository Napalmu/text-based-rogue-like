package game.controller;

public enum EnemyType implements GameConcept{
    GOBLIN {

        @Override
        public String getName() {
            return "Hiisi";
        }

        @Override
        public int GetHp() {
            return 30;
        }

        }, SKELETON{
            
        @Override
        public String getName() {
            return "Luuranko";
        }

        @Override
        public int GetHp() {
            return 40;
        }
        }, GHOST{
        
        @Override
        public String getName() {
            return "Aave";
        }

        @Override
        public int GetHp() {
            return 60;
        }
        },;

    public abstract String getName();
    public abstract int GetHp();
    /*public abstract int getSpeed();*/
}
