package game.controller;

public enum ItemType implements GameConcept{
    NYRKIT {
        @Override
        public String getName() {return "Nyrkit";}
        
        @Override
        public int price() {
            return 0;
        }
        public int dmg(){
            return 5;
        }
        @Override
        public String getDescription() {
            return "Perushyökkäys";
        }
    },
    STICK {
        @Override
        public String getName() {return "Tikku";}
        
        @Override
        public int price() {
            return 0;
        }

        @Override
        public String getDescription() {
            return "Tikku";
        }
    },
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
            return "kevyt ja hyvän näköinen miekka";
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
            return "jotenkin tehokas teräaseita vastaan, mukava levypanssarin alla";
        }
    },
    CHAINMAIL_ARMOR {
        @Override
        public String getName() {
            return "Ketjuhaarniska";
        }

        @Override
        public int price() {
            return 20;
        }

        @Override
        public String getDescription() {
            return "tehokas teräaseita vastaan, tärkeä osa levyketjupanssaria";
        }
    },
    FIREBALL_SPELL{
        @Override
        public String getName() {
            return "Tulipallo";
        }

        @Override
        public int price() {
            return 40;
        }

        @Override
        public String getDescription() {
            return "Maaginen käärö, joka opetta sinulle tulipallo-loitsun";
        }
    },
    BOW{
        @Override
        public String getName() {
            return "Jousipyssy";
        }

        @Override
        public int price() {
            return 10;
        }

        @Override
        public String getDescription() {
            return "yksinkertainen pitkänmatkanase, käyttää nuolia ammuksinaan";
        }
    },
    COIN {
        @Override
        public String getName() {
            return "Kolikko";
        }

        @Override
        public int price() {
            return 1;
        }

        @Override
        public String getDescription() {
            return "Kovaa valuuttaa";
        }
    };

    public abstract String getName();
    public abstract int price();
    public abstract String getDescription();
}
