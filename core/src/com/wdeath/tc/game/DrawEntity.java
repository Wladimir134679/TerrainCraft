package com.wdeath.tc.game;

import com.badlogic.ashley.core.Entity;
import com.wdeath.tc.utils.Canvas;

public class DrawEntity {

    public static final float SIZE_BLOCK = 32f;

    public static Entity build(){
        Entity entity = new Entity();
        entity.add(buildCanvas());
        return entity;
    }

    private static CanvasComponent buildCanvas(){
        CanvasComponent component = new CanvasComponent();
        component.canvas = new Canvas();
        return component;
    }
}
