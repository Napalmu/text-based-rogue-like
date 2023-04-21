package game.controller;

public enum ItemType implements GameConcept{
    BLUEBERRY {
        @Override
        public String getName() {return "Mustikka";}
    }, 
    KEY {
        @Override
        public String getName() {return "Mahtava avain";}
    },
    SWORD {
        @Override
        public String getName() {return "Komea miekka";}
    },
    AXE {
        @Override
        public String getName() {return "Terävä kirves";}
    },
    LEATHER_CHESTPLATE {
        @Override
        public String getName() {return "Nätti nahkahaarniska";}
    },
    CHAINMAIL_CHESTPLATE{
        @Override
        public String getName() {return "Ketjuhaarniska";}
    };

    public abstract String getName();
}
