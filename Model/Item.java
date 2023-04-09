package Model;

import Model.DrawCommandInterfaces.DataIcon;

public class Item implements DataIcon{
    private String name;

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
