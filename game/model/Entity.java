package game.model;

abstract class Entity {
    private final String name;
    Entity(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}

