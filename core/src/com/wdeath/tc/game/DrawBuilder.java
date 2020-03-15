package com.wdeath.tc.game;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.wdeath.tc.game.components.CanvasComponent;
import com.wdeath.tc.utils.Canvas;

public class DrawBuilder {

    public static final float SIZE_BLOCK_PIXEL = 32f, PPM = 32f;

    public static Entity build(){
        Entity entity = new Entity();
        entity.add(buildCanvas());
        return entity;
    }

    private static CanvasComponent buildCanvas(){
        CanvasComponent component = new CanvasComponent();
        component.canvas = new Canvas(Gdx.graphics.getWidth() / PPM, Gdx.graphics.getHeight() / PPM);
        return component;
    }
}
