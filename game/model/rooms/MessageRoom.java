package game.model.rooms;

import java.util.List;

import game.controller.RoomType;

class MessageRoom extends Room{
    private final List<String> messages;

    public MessageRoom(List<String> messages) {
        this.messages = messages;
    }

    @Override
    protected void enterRoom() {

        //roomText.setContent(messages.toArray(new String[0]));

        this.moveToNextRoom();
    }

    @Override
    public RoomType getType() {
        return RoomType.MSG;
    }
}
