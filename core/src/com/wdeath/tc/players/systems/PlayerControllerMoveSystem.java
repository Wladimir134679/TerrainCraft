package com.wdeath.tc.players.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.wdeath.tc.players.components.PlayerMoveComponent;
import com.wdeath.tc.players.components.PlayerPhysicsComponent;

public class PlayerControllerMoveSystem extends IteratingSystem {

    public PlayerControllerMoveSystem(){
        super(Family.all(PlayerMoveComponent.class, PlayerPhysicsComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PlayerMoveComponent pmc = entity.getComponent(PlayerMoveComponent.class);
        PlayerPhysicsComponent ppc = entity.getComponent(PlayerPhysicsComponent.class);

        Vector2 vec = ppc.body.getLinearVelocity();
        if(pmc.left){
            vec.x = Math.min(10, (vec.x - 0.1f));
        }else if(pmc.right){
            vec.x = Math.max(-10, (vec.x + 0.1f));
        }else{
            vec.x /= 1.05f;
            if(Math.abs(vec.x) < 0.1f)
                vec.x = 0;
        }

        ppc.body.setLinearVelocity(vec);
        if(pmc.jump){
            ppc.body.applyForceToCenter(0,200, true);
            pmc.jump = false;
        }
    }
}
