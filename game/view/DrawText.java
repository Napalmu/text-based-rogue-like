package game.view;

/**
 * Luokka helpontamaan KIRJOITTAMISTA eli esim lauseiden lähettämistä drawcommandin avulla.
 * Ei vielä tarjoa hirveästi etuja drawcommandin yli.
 * Vain viewin (ja siten) draw areoiden pääasiallisessa käytössä
 */
class DrawText extends DrawCommand {

    public DrawText(int x, int y, String... content) {
        super(x, y, content);
    }
    public DrawText(String... content) {
        super(0, 0, content);
    }

    public void setContent(String... content) {
        super.setContent(content);
    }
    public char getAt(int x, int y){
        return GetContent()[y].charAt(x);
    }
}
