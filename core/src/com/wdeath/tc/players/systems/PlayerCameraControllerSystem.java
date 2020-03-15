package com.wdeath.tc.players.systems;

import com.badlogic.ashley.core.*;
import com.wdeath.tc.game.components.CanvasComponent;
import com.wdeath.tc.players.components.PlayerDataComponent;
import com.wdeath.tc.players.components.PlayerPhysicsComponent;

public class PlayerCameraControllerSystem extends EntitySystem {

    private Entity canvasEntity;
    private Entity playerEntity;
    private String namePlayer;

    public PlayerCameraControllerSystem(String namePlayer) {
        this.namePlayer = namePlayer;
    }

    @Override
    public void addedToEngine(Engine engine) {
        canvasEntity = engine.getEntitiesFor(Family.one(CanvasComponent.class).get()).first();
        engine.addEntityListener(Family.all(PlayerDataComponent.class, PlayerPhysicsComponent.class).get(), new EntityListener() {
            @Override
            public void entityAdded(Entity entity) {
                PlayerDataComponent data = entity.getComponent(PlayerDataComponent.class);
                if(data.name.equals(namePlayer)){
                    playerEntity = entity;
                }
            }

            @Override
            public void entityRemoved(Entity entity) {

            }
        });
    }

    @Override
    public void update(float deltaTime) {
        if(playerEntity == null)
            return;

        PlayerPhysicsComponent ppc = playerEntity.getComponent(PlayerPhysicsComponent.class);
        CanvasComponent cc = canvasEntity.getComponent(CanvasComponent.class);

        cc.canvas.getCamera().position.x = ppc.body.getPosition().x;
        cc.canvas.getCamera().position.y = ppc.body.getPosition().y;
    }
}
