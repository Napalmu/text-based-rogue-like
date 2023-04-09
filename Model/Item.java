package Model;

import Controller.ItemType;
import Model.DrawCommandInterfaces.DataIcon;

public class Item extends Entity implements DataIcon{
    
    private ItemType type;
    
    public Item(ItemType type) {
        super(1,type.getName());
        this.type = type;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Item other = (Item) obj;
        if (type != other.type)
            return false;
        return true;
    }
}
