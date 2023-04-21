package game.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class ParentDrawCommand extends DrawCommand{
    private final ArrayList<DrawCommand> children = new ArrayList<>();

    protected void addChildren(DrawCommand... children) {
        this.children.addAll(Arrays.asList(children));
    }
    public ParentDrawCommand(int x, int y, String... content) {
        super(x, y, content);
    }

    @Override
    public Stream<CharacterPosition> getStream() {
        Stream.Builder<CharacterPosition> builder = Stream.builder();
        for (DrawCommand child : children) {
            child.getStream().forEach(builder::accept);
        }
        return builder.build();
    }
}
