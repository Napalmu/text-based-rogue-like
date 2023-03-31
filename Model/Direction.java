package Model;

public class Direction {
    private final String name;
    private final Room destination;

    public Direction(String name, Room destination) {
        this.name = name;
        this.destination = destination;
    }

    public String getName() {
        return name;
    }

    public Room getDestination() {
        return destination;
    }
}
