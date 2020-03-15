package com.wdeath.tc.items;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@NoArgsConstructor
public class Item {

    private static Item[] items = new Item[1024];
    private static HashMap<String, Item> itemsMap = new HashMap<>();

    public static Item get(String name){
        return itemsMap.get(name);
    }

    public static Item get(int i){
        if(i < 0 || i >= items.length)
            return null;
        return items[i];
    }

    public static void add(Item item){
        if(get(item.getId()) != null)
            return;
        items[item.getId()] = item;
        itemsMap.put(item.getName(), item);
    }

    private int id;
    private String name;
    private String textureName;

    private int stackMax;
}
