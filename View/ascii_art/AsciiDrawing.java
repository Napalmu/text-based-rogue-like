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

    };

    public abstract String[] getArt();
}
