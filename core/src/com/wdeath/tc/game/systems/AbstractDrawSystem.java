package com.wdeath.tc.game.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.wdeath.tc.game.components.CanvasComponent;

public abstract class AbstractDrawSystem extends EntitySystem {

    private Entity drawSystem;

    @Override
    public void addedToEngine(Engine engine) {
        drawSystem = engine.getEntitiesFor(Family.one(CanvasComponent.class).get()).first();
    }

    public abstract void update(float deltaTime);

    public Entity getDrawSystem() {
        return drawSystem;
    }
}
