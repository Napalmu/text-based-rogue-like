package game.controller;

public enum ItemType implements GameConcept{
    BLUEBERRY {
        @Override
        public String getName() {return "Mustikka";}
    }, 
    KEY {
        @Override
        public String getName() {return "Mahtava avain";}
    };

    public abstract String getName();
}
