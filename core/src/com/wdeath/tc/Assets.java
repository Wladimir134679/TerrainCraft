package com.wdeath.tc;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

public class Assets {

    private static HashMap<String, TextureRegion> textures;

    public static void load(){
        textures = new HashMap<>();
        load("dirt");
    }

    private static void load(String name){
        add(name, new TextureRegion(new Texture(name + ".png")));
    }

    private static void add(String name, TextureRegion reg){
        textures.put(name, reg);
    }

    public static TextureRegion get(String name){
        return textures.get(name);
    }
}
