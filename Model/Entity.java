package Model;

public class Entity {
    private int hp;
    private String name;

    public Entity(int hp, String name){
        this.hp = hp;
        this.name = name;
    }
    public int getHp(){
      return hp;  
    }
    public String getName(){
        return name;
    }
    public void setHp(int hp){
        this.hp = hp;
    }
    public void setName(String name){
        this.name = name;
    }    

}

