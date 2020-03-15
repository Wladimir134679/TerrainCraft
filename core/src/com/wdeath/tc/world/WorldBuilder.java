package com.wdeath.tc.world;

import com.badlogic.ashley.core.Entity;

public class WorldBuilder {

    public static Entity build(){
        Entity entity = new Entity();
        entity.add(getLayers());
        return entity;
    }

    private static WorldLayerComponent getLayers(){
        WorldLayerComponent com = new WorldLayerComponent();
        com.ground = new WorldLayer(30, 10);
        return com;
    }
}
