package com.wdeath.tc.world;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.wdeath.tc.game.CanvasComponent;

public class WorldPhysicsDebugSystem  extends EntitySystem {

    private Entity worldEntity;
    private Entity canvasEntity;

    @Override
    public void addedToEngine(Engine engine) {
        worldEntity = engine.getEntitiesFor(Family.one(WorldPhysicsComponent.class, WorldPhysicsDebugComponent.class).get()).first();
        canvasEntity = engine.getEntitiesFor(Family.one(CanvasComponent.class).get()).first();
    }

    @Override
    public void update(float deltaTime) {
        WorldPhysicsComponent worldPhysicsComponent = worldEntity.getComponent(WorldPhysicsComponent.class);
        WorldPhysicsDebugComponent wpdc = worldEntity.getComponent(WorldPhysicsDebugComponent.class);
        CanvasComponent canvasComponent = canvasEntity.getComponent(CanvasComponent.class);

        wpdc.debugRenderer.render(worldPhysicsComponent.world, canvasComponent.canvas.getCamera().combined);
    }
}
