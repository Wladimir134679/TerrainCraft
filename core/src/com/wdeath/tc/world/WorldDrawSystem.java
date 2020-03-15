package com.wdeath.tc.world;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.wdeath.tc.Assets;
import com.wdeath.tc.game.CanvasComponent;
import com.wdeath.tc.game.DrawEntity;
import com.wdeath.tc.items.Item;
import com.wdeath.tc.utils.Canvas;

public class WorldDrawSystem extends EntitySystem {

    private Entity worldEntity;
    private Entity canvasEntity;

    @Override
    public void addedToEngine(Engine engine) {
        worldEntity = engine.getEntitiesFor(Family.one(WorldLayerComponent.class).get()).first();
        canvasEntity = engine.getEntitiesFor(Family.one(CanvasComponent.class).get()).first();
    }

    @Override
    public void update(float deltaTime) {
        WorldLayerComponent worldLayerComponent = worldEntity.getComponent(WorldLayerComponent.class);
        Canvas canvas = canvasEntity.getComponent(CanvasComponent.class).canvas;

        WorldLayer worldLayer = worldLayerComponent.ground;
        for(int i = 0; i < worldLayer.getWidth(); i++){
            for(int j = 0; j < worldLayer.getHeight(); j++){
                int id = worldLayer.get(i, j);
                if(id == 0)
                    continue;

                Item item = Item.get(id);
                if(item == null)
                    continue;

                String texture = item.getTextureName();
                TextureRegion textureRegion = Assets.get(texture);
                if(textureRegion == null)
                    continue;

                float x = DrawEntity.SIZE_BLOCK * i;
                float y = DrawEntity.SIZE_BLOCK * j;
                canvas.getBatch().draw(textureRegion, x, y);
            }
        }
    }
}
