package Model.rooms;

class CompassPoints {
    public static String NORTH = "Pohjoiseen";
    public static String SOUTH = "Etelään";
    public static String WEST = "Länteen";
    public static String EAST = "Itään";

    public static int getKeyMatchingDirection(String dir) {
        if (dir.equals(NORTH)) return 1;
        if (dir.equals(EAST)) return 2;
        if (dir.equals(SOUTH)) return 3;
        if (dir.equals(WEST)) return 4;
        return -1;
    }
}
