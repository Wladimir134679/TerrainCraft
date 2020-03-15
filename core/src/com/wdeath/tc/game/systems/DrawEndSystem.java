package com.wdeath.tc.game.systems;

import com.wdeath.tc.game.components.CanvasComponent;

public class DrawEndSystem extends AbstractDrawSystem {

    @Override
    public void update(float deltaTime) {
        getDrawSystem().getComponent(CanvasComponent.class).canvas.getBatch().end();
    }
}
