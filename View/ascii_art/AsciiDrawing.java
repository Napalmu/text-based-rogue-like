package View.ascii_art;

public enum AsciiDrawing {
    
    MAINMENU(){

        @Override
        public String[] getArt() {
            return new String[]{
                "_______________________________________________________________________________",
                " |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   | ",
                "───────────────────────────────────────────────────────────────────────────────",
                "|   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |  ",
                "───────────────────────────────────────────────────────────────────────────────",
                " |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   | ",
                "────────────────────────────█████████──────────────────────────────────────────",
                "|   |   |   |   |   |   | ██bbbb|bbbb██ |   |   |   |   |   |   |   |   |   |  ",
                "───────────────────────██bbbbbbb|bbbbbbb██─────────────────────────────────────",
                " |   |   |   |   |   |█bbbbbbbbb|bbbbbbbbb█  |   |   |   |   |   |   |   |   | ",
                "─────────────────────█bbbbbbbbbb|bbbbbbbbbb█───────────────────────────────────",
                "|   |   |   |   |   █bbbbbbbbbbb|bbbbbbbbbbb█ |   |   |   |   |   |   |   |   |",
                "───────────────────█bbbbbbbbbbbb|bbbbbbbbbbbb█─────────────────────────────────",
                " |   |   |   |   | █bbbbbbbbbbbb|bbbbbbbbbbbb█ |   |   |   |   |   |   |   |   ",
                "───────────────────█bbbbbbbbbbbb|bbbbbbbbbbbb█─────────────────────────────────",
                "|   |   |   |   |  █bbbbbbbbbbbb|bbbbbbbbbbbb█|   |   |   |   |   |   |   |   |",
                "───────────────────█bbbbbbbbbbbb|bbbbbbbbbbbb█─────────────────────────────────",
                " |   |   |   |   | █bbbbbbbbbbbb|bbbbbbbbbbbb█ |   |   |   |   |   |   |   |   ",
                "───────────────────█bbbbbbbbbbbb|bbbbbbbbbbbb█─────────────────────────────────",
                "|   |   |   |   |  █bbbbbbbbbbbb|bbbbbbbbbbbb█|   |   |   |   |   |   |   |   |",
                "───────────────────█bbbbbbbbbbbb|bbbbbbbbbbbb█─────────────────────────────────",
                " |   |   |   |   | █bbbbbbbbbbbb|bbbbbbbbbbbb█ |   |   |   |   |   |   |   |   ",
                "░░░░░░░░░░░░░░░░░░░█bbbbbbbbbbbb|bbbbbbbbbbbb█░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░"
            };
        }

    },
    OUTSIDE(){

        @Override
        public String[] getArt() {
            return new String[]{
                "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO                                  ",
                "OOOOOOOOOOOOOOOOOOOOOOOO      OOOOOOO  OOOO                                    ",
                "OOOOOOOOOOOOOOO      OOO        OOOOO                                          ",
                "OOOOOOOOOOOO                                       oo                          ",
                "OOOOOOOOO                                         oooo                         ",
                "OOOOOOOO                                          oooo                         ",
                "OOOOOO                                             ii                          ",
                "OOO█                                               ii                          ",
                "OOO█                                                                           ",
                "OOOOO                                                                          ",
                "OOOOO                                         oo                               ",
                "OOOO                                         oooo                              ",
                "OOOO                                         oooo                              ",
                "OOO                                           ii                               ",
                "OO                                            ii         oo                    ",
                "O                                                       oooo                   ",
                "OOO                                                     oooo                   ",
                "OOO                                                      ii                    ",
                "OO                                                       ii                    ",
                "OO                                                                             ",
                "OO                                                                             ",
                "O                                                                              "
            };
        }

    },
    SCREEN(){

        @Override
        public String[] getArt() {
            return new String[]{
                "╔═════════════════════════════════════════════════════════╤════════════════════╗",
                "║                                                         │                    ║",
                "║                                                         │                    ║",
                "║                                                         │                    ║",
                "║                                                         │                    ║",
                "║                                                         │                    ║",
                "║                                                         │                    ║",
                "║                                                         │                    ║",
                "║                                                         │                    ║",
                "║                                                         │                    ║",
                "║                                                         │                    ║",
                "║                                                         │                    ║",
                "║                                                         │                    ║",
                "║                                                         │                    ║",
                "║                                                         │                    ║",
                "╟─────────────────────────────────────────────────────────┴────────────────────╢",
                "║                                                                              ║",
                "║                                                                              ║",
                "║                                                                              ║",
                "║                                                                              ║",
                "║                                                                              ║",
                "║                                                                              ║",
                "║                                                                              ║",
                "╚══════════════════════════════════════════════════════════════════════════════╝",
            };
        }

    },
    ;

    public abstract String[] getArt();
}
