package Model;


import Model.rooms.Dungeon;

public class ModelController {
    
    public ModelController(){}

    public void setDungeon(){
        Dungeon dungeon = new Dungeon();
        dungeon.enter();
    }

}
