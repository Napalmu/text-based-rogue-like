package game.model.rooms;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import game.controller.GameController;
import game.controller.RoomType;
import game.model.EntityManager;
import game.model.Item;
import game.model.Player;

class TreasureRoom extends Room{
    private final Item[] items;
    public TreasureRoom(Item[] items) {
        this.items = items;
    }

    @Override
    public void enterRoom() {
        if (hasBeenEntered()) {
            GameController.view.enterMessageRoom(this, Arrays.asList(
                "Saavut huoneeseen, jossa on arkku.",
                "Epäonneksesi huomaat kuitenkin, että olet jo käynyt", 
                "huoneessa."));
                        
            return;
        }
        Player player = EntityManager.getPlayer();
        player.receiveItems(this.items);


        List<String> itemNames = Stream.concat(
            Stream.of(
                "Löydät itsesi huoneesta, josta löytyy", "salaperäinen arkku","","Löysit arkusta:"),
                Arrays.stream(items)
                    .map(i->i.getName())
            ).collect(Collectors.toList());
        
        GameController.view.enterMessageRoom(this, itemNames);
    }

    @Override
    public RoomType getType() {
        return RoomType.TREASURE;
    }
}
