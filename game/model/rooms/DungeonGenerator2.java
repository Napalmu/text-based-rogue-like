package game.model.rooms;

import game.controller.ItemType;
import game.model.Enemy;
import game.model.EntityManager;
import game.model.Item;
import game.view.MapRoom;

import java.util.ArrayList;
import java.util.Random;

import static game.model.rooms.CompassPoints.*;

public class DungeonGenerator2 {
    private final Room[][] rooms;
    private final int width, height;
    private final Random random = new Random();
    private final RoomFactory roomFactory;
    private final Item key;
    private class Node {
        private final int x;
        private final int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public boolean inBounds() {
            return x >= 0 && y >= 0 && x < width && y < height;
        }
        public boolean hasRoom() {
            if (!inBounds()) return false;
            return rooms[y][x] != null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
    public DungeonGenerator2(int width, int height) {
        this.rooms = new Room[height][width];
        this.width = width;
        this.height = height;
        this.roomFactory = new RoomFactory();
        this.key = EntityManager.createItem(ItemType.KEY);
    }
    //rakentaa reittejä läpi ruudukon yhdestä ruudusta toiseen
    private class PathBuilder {
        private Node start, end, current;
        private ArrayList<Node> editedNodes;
        public boolean buildPath(Node from, Node to, Room start, Room end) {
            this.start = from;
            this.end = to;
            //yritetään ensin tehdä reitti, joka mutkittelee ja tekee muuta jännää
            boolean firstTimeSuccess = build(false,start,end);
            if (firstTimeSuccess) return true;
            //jos monimutkainen reitti ei toimi, yritetään tehdä nopein ja helpoin reitti
            return build(true,start,end);
        }
        //rakentaa reitin alkuruudusta loppuruutuun
        //jos safe argumentti on true, reitti rakennetaan suorinta reittiä
        //jos safe argumentti on false, reitti kiemurtelee, jotta reitistä
        //tulisi mielenkiintoisempi
        private boolean build(boolean safe, Room startR, Room endR) {
            this.current = this.start;
            //tallennetaan kaikki ruudut, joita muokattiin, jotta ne voidaan
            //poistaa jos reitin rakennus ei onnistu
            this.editedNodes = new ArrayList<>();
            boolean b = setRoomIfEmpty(start.x, start.y, startR);
            if (b) editedNodes.add(start);
            b = setRoomIfEmpty(end.x, end.y, endR);
            if (b) editedNodes.add(end);

            int limit = 0;
            while (!isComplete() && limit < 1000) {
                step(safe);
                limit++;
            }
            if (limit == 1000) { //reitin rakentaminen ei onnistunut
                for (Node n : this.editedNodes) {
                    rooms[n.y][n.x] = null; //poistetaan muutokset
                }
            }
            return limit != 1000;
        }
        //palauttaa onko reitti valmis, eli ollaanko loppu saavutettu
        private boolean isComplete() {
            return this.current.x == this.end.x && this.current.y == this.end.y;
        }
        //yrittää siirtyä askeleen kohti loppuruutua
        //jos safe argumentti on true, reitti rakennetaan suorinta reittiä
        //jos safe argumentti on false, reitti kiemurtelee, jotta reitistä
        //tulisi mielenkiintoisempi
        private void step(boolean safe) {
            //yritetään sata kertaa löytää sopiva seuraava ruutu naapureista
            int limit = 0;
            while (limit++ < 100) {
                Node node = nextNode(safe);
                if (node.x == end.x && node.y == end.y) {
                    //loppu saavutettu
                    this.current = node;
                    return;
                }
                //ruudun pitää olla kartan alueella ja se ei saa luoda 2*2 huoneruudukkoa
                if (node.inBounds() && !formsSquare(node)) {
                    this.current = node;
                    break;
                }
            }
            //TODO valitse huone älykkäämmin
            Room room = (Room) roomFactory.createMessageRoom("Lol");
            boolean b = setRoomIfEmpty(current.x, current.y, room);
            if (b) editedNodes.add(current);
        }
        //yrittää siirtyä kohti loppuruutua nykyisestä ruudusta
        //jos safe argumentti on true, valitaan lähin ruutu loppua
        //jos safe argumentti on false, saatetaan valita "huono" ruutu, jolla ei päästä lähemmäs ruutua
        private Node nextNode(boolean safe) {
            //valitaan siirrytäänkö vaaka vai pystysuuntaan
            //siirrytään todennäköisemmin siihen suuntaan, mihin on pidempi matka
            //esim:
            //....    A = Alku    L = Loppu
            //...L    <-- Vaakasuunta on todennäköisempi, koska siinä ero on 3 ruutua, mutta
            //....         pystysuunnassa vain ero on vain 2 ruutua
            //A...
            int xDif = this.end.x - this.current.x;
            int yDif = this.end.y - this.current.y;
            double sum = Math.abs(xDif) + Math.abs(yDif);
            //vaaka vai pysty suunnassa:
            double d = random.nextDouble();
            //väärään vai oikeaan suuntaan
            boolean wrong = !safe && random.nextDouble() < 0.2;
            int x = 0; int y = 0;
            double horizontalChance = Math.abs(xDif) / sum;
            //min 0.1 ja max 0.9, koska muuten saatetaan jäädä jumiin
            horizontalChance = Math.max(horizontalChance, 0.1);
            horizontalChance = Math.min(horizontalChance, 0.9);
            if (d < horizontalChance) { //vaaka
                int i = xDif < 0 ? -1 : 1;
                x = wrong ? -i : i;
            } else { //pysty
                int i = yDif < 0 ? -1 : 1;
                y = wrong ? -i : i;
            }
            return new Node(current.x+x,current.y+y);
        }
    }
    //asettaa huoneen koordinaatteihin ja palauttaa false, jos ruudussa oli jo huone
    private boolean setRoomIfEmpty(int x, int y, Room room) {
        if (this.rooms[y][x] != null) return false;
        this.rooms[y][x] = room;

        this.addNeighbour(x,y - 1, NORTH, SOUTH, room);
        this.addNeighbour(x,y + 1, SOUTH, NORTH, room);
        this.addNeighbour(x - 1,y, WEST, EAST, room);
        this.addNeighbour(x + 1,y, EAST, WEST, room);
        return true;
    }

    private void addNeighbour(int x, int y, String dir, String dir2, Room room) {
        if (y < 0 ||
                x < 0 ||
                y >= this.height ||
                x >= this.width ||
                this.rooms[y][x] == null)
            return;

        this.rooms[y][x].addDirection(new Direction(dir2, room));
        room.addDirection(new Direction(dir, this.rooms[y][x]));
    }
    //kaikki parametreina annetut ruudut sisältävät huoneen
    private boolean allHaveRooms(Node... nodes) {
        for (Node node : nodes) {
            if (!node.hasRoom()) return false;
        }
        return true;
    }

    //tarkistaa muodostaako huoneen lisääminen karttaa 2*2 huonemuodostelman, jota me ei haluta
    //koska ilman 2*2 muodostelmia kartasta tulee ilmavampi
    private boolean formsSquare(Node node) {
        int x = node.x; int y = node.y;
        //vasenalakulma
        if (allHaveRooms(new Node(x - 1, y), new Node(x - 1, y + 1), new Node(x, y + 1)))
            return true;
        //oikea-alakulma
        if (allHaveRooms(new Node(x + 1, y), new Node(x + 1, y + 1), new Node(x, y + 1)))
            return true;
        //vasenyläkulma
        if (allHaveRooms(new Node(x - 1, y), new Node(x - 1, y - 1), new Node(x, y - 1)))
            return true;
        //oikeayläkulma
        if (allHaveRooms(new Node(x + 1, y), new Node(x + 1, y - 1), new Node(x, y - 1)))
            return true;
        return false;
    }

    //Valitsee satunnaisen ruudun, joka on tyhjä
    //ei myöskään valitse ruutua, joka voisi muodostaa 2*2 huone-ruudukon
    private Node randomNode() {
        ArrayList<Node> choices = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Node node = new Node(x,y);
                if (rooms[y][x] == null && !formsSquare(node))
                    choices.add(node);
            }
        }
        //todella epätodennäköinen tapahtuma
        if (choices.size() == 0) {
            debugMap(new Dungeon(this.rooms, null));
            throw new IllegalStateException("Ei yhtään vapaata ruutua!");
        } else {
            return choices.get(random.nextInt(choices.size()));
        }
    }
    public Dungeon generate() {
        //luodaan tärkeät huoneet
        Room start = (Room) roomFactory.createMessageRoom("Aloitushuone!");
        Room boss = (Room) roomFactory.createBossRoom((Enemy)EntityManager.createEnemy(100, "Mörkö"), this.key);
        Room key = (Room) roomFactory.createTreasureRoom(this.key);

        PathBuilder pathBuilder = new PathBuilder();
        Node startNode = randomNode(); //arvotaan aloitusruudun paikka
        //asetetaan huone ruudukkoon
        //se pitää asettaa tässä ettei avain huoneeksi valittais sattumalta samaa kuin aloitushuone
        this.rooms[startNode.y][startNode.x] = start;

        //rakennetaan reitti aloitushuoneesta avainhuoneeseen
        pathBuilder.buildPath(startNode, randomNode(), start, key);

        //rakennetaan reitti aloitushuoneesta pomo-huoneeseen.
        //On mahdollista, mutta hyvin epätodennäköistä, että reittiä ei voida rakentaa
        int limit = 0;
        while (++limit < 1000) {
            boolean b = pathBuilder.buildPath(startNode, randomNode(), start, boss);
            if (b) break; //reitin rakentaminen onnistui
        }
        //yritettiin tuhat kertaa, mutta ei onnistuttu.
        //tämä on todella epätodennäköistä
        if (limit >= 1000) {
            System.out.println("Map:");
            debugMap(new Dungeon(rooms,null));
            throw new IllegalStateException("Ei voida rakentaa reittiä pomo-huoneeseen!");
        }

        return new Dungeon(rooms, start);
    }
    //testi metodi
    private static void stressTest() {
        for (int i = 0; i < 10000; i++) {
            int w = new Random().nextInt(5, 30);
            int h = new Random().nextInt(5, 20);
            DungeonGenerator2 g = new DungeonGenerator2(w,h);
            g.generate();
        }
    }
    public static void main(String[] args) {
        stressTest();
    }


    private static void debugMap(Dungeon dungeon) {
        MapRoom[][] map = dungeon.getMap();
        //pöllitty muualta testaamista varten
        String[] stringMap = new String[map.length];
        for (int y = 0; y < map.length; y++) {
            char[] chars = new char[map[y].length];
            for (int x = 0; x < map[y].length; x++) {
                if (map[y][x] == null) {
                    chars[x] = '.';
                    continue;
                }
                chars[x] = map[y][x].getRoomType().symbol;
                if (map[y][x].hasPlayerInside()) {
                    chars[x] = '$';
                }
            }
            stringMap[y] = new String(chars);
        }
        for (String row : stringMap) {
            System.out.println(row);
        }
    }
}
