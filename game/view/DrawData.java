package game.view;
import game.controller.GameController;
import game.model.DrawCommandInterfaces.DataIcon;

/**
 * Luokka helpontamaan PÄIVITTYVIEN OLIOIDEN käyttämistä piirtämisessä
 * Esimerkiksi inventory, hp palkki taistelussa, yms... 
 * Vielä kesken
 */
class DrawData extends DrawCommand{

    DrawData drawCommand;

    public DrawData(int x, int y, DataIcon content) {
        super(x, y, content.getName());
    }
    public DrawData(DataIcon content) {
        super(0, 0, content.getName());
    }

    public void setContent(DataIcon content){
        super.setContent(content.getName());
    }
    
}
