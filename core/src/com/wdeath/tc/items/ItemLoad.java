package com.wdeath.tc.items;

import com.badlogic.gdx.Gdx;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONObject;

@Log4j2
public class ItemLoad {

    public static void load(){
        String dataItem = new String(Gdx.files.internal("items.json").readBytes());
        JSONObject objectData = new JSONObject(dataItem);
        JSONArray arrayData = objectData.getJSONArray("items");
        loadArray(arrayData);
    }

    private static void loadArray(JSONArray arrayData){
        log.info("Load item: " + arrayData.length() + " size");
        for(int i = 0; i < arrayData.length(); i++){
            JSONObject itemData = arrayData.getJSONObject(i);
            loadItem(itemData);
        }
    }

    private static void loadItem(JSONObject obj){
        log.debug("item.json: " + obj.toString());
        Item item = new Item();
        item.setId(obj.getInt("id"));
        item.setName(obj.getString("name"));
        item.setStackMax(obj.getInt("stackMax"));
        item.setTextureName(obj.getString("textureName"));

//        log.info("load: " + item.getId() + "/" + item.getName());

        Item.add(item);
    }
}
