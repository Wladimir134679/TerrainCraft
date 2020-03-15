package com.wdeath.tc.players.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.wdeath.tc.players.components.PlayerDataComponent;
import com.wdeath.tc.players.components.PlayerPhysicsComponent;

public class PlayerUpdateSystem extends IteratingSystem {

    public PlayerUpdateSystem(){
        super(Family.all(PlayerPhysicsComponent.class, PlayerDataComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

    }
}
