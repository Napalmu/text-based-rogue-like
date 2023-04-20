package game.model.rooms;

public class Direction {
    private final String name;
    private final Enterable destination;

    public Direction(String name, Enterable destination) {
        this.name = name;
        this.destination = destination;
    }

    public String getLabel() {
        return name;
    }


    public Enterable getDestination() {
        return destination;
    }
}
