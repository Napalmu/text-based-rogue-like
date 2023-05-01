package game.model;

import java.util.ArrayList;
import java.util.Arrays;

import game.model.DrawCommandInterfaces.DataIconList;

 class Inventory implements DataIconList {
    private final ArrayList<Item> items = new ArrayList<>();

     Inventory() {

     }
     void addItems(Item... items) {
         this.items.addAll(Arrays.asList(items));
    }

     void removeItem(Item item) {
        items.remove(item);
    }
     boolean containsItem(Item item) {
        return items.contains(item);
    }

     public boolean containsItem(Item item, int amount) {
         int n = 0;
         for (Item i : items) {
             if (i.equals(item)) n++;
         }
         return n >= amount;
     }
    @Override
    public String getHeader() {
        return "Inventory";
    }
    @Override
    public ArrayList<Item> getDataList() {
        return items;
    }


 }
