package com.wdeath.tc.world.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.wdeath.tc.world.components.WorldPhysicsComponent;

public class WorldPhysicsUpdateSystem extends EntitySystem {

    private Entity world;

    @Override
    public void addedToEngine(Engine engine) {
        world = engine.getEntitiesFor(Family.one(WorldPhysicsComponent.class).get()).first();
    }

    @Override
    public void update(float deltaTime) {
        WorldPhysicsComponent wpc = world.getComponent(WorldPhysicsComponent.class);
        wpc.world.step(1/60f, 6, 4);
    }
}
