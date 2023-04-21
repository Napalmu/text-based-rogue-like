package game.controller;

public enum ItemType implements GameConcept{
    BLUEBERRY {
        @Override
        public String getName() {return "Mustikka";}

        @Override
        public int price() {
            return 1;
        }

        @Override
        public String getDescription() {
            return "Makoisa mustikka";
        }
    },
    STRAWBERRY {
        @Override
        public String getName() {return "Mansikka";}

        @Override
        public int price() {
            return 2;
        }

        @Override
        public String getDescription() {
            return "Makoisa mansikka";
        }
    },
    KEY {
        @Override
        public String getName() {return "Mahtava avain";}

        @Override
        public int price() {
            return 0;
        }

        @Override
        public String getDescription() {
            return "mahtava, avaa oven toiminnajohtajan huoneesen";
        }
    },
    SWORD {
        @Override
        public String getName() {return "Komea miekka";}

        @Override
        public int price() {
            return 10;
        }

        @Override
        public String getDescription() {
            return null;
        }
    },
    HALBERG {
        @Override
        public String getName() {return "kätevä halperi";}

        @Override
        public int price() {
            return 20;
        }

        @Override
        public String getDescription() {
            return "pitkä varsi, terävä kärki, tehokas panssaroitujakin vastustajia vastaan";
        }
    },
    PADDED_ARMOR {
        @Override
        public String getName() {return "Nätti pehmustepanssari";}

        @Override
        public int price() {
            return 10;
        }

        @Override
        public String getDescription() {
            return null;
        }
    },
    CHAINMAIL_ARMOR{
        @Override
        public String getName() {return "Ketjuhaarniska";}

        @Override
        public int price() {
            return 20;
        }

        @Override
        public String getDescription() {
            return null;
        }
    };

    public abstract String getName();
    public abstract int price();
    public abstract String getDescription();
}
