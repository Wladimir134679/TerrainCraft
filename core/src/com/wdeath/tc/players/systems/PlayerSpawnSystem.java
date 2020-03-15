package com.wdeath.tc.players.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.*;
import com.wdeath.tc.players.PlayerBuilder;
import com.wdeath.tc.players.components.PlayerSpawnComponent;
import com.wdeath.tc.world.components.WorldPhysicsComponent;

public class PlayerSpawnSystem extends IteratingSystem {

    private Entity world;

    public PlayerSpawnSystem(){
        super(Family.all(PlayerSpawnComponent.class).get());
    }

    @Override
    public void addedToEngine(Engine engine) {
        world = engine.getEntitiesFor(Family.one(WorldPhysicsComponent.class).get()).first();
        super.addedToEngine(engine);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PlayerSpawnComponent psc = entity.getComponent(PlayerSpawnComponent.class);
        WorldPhysicsComponent wpc = world.getComponent(WorldPhysicsComponent.class);


        Entity playerEntity = new PlayerBuilder()
                .createPhysics(psc.point, wpc)
                .createMove()
                .createData(psc.name)
                .build();

        getEngine().removeEntity(entity);
        getEngine().addEntity(playerEntity);
    }
}
