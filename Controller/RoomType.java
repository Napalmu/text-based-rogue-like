package Controller;

public enum RoomType {
    ADVENTURE('A'), 
    BOSS('B'), 
    ENEMY('E'), 
    SHOP('S'), 
    MSG('M'), 
    TREASURE('T');

    public final char symbol;
    
    private RoomType(char symbol){
        this.symbol = symbol;
    }
}