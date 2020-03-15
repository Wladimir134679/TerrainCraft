package com.wdeath.tc.game;

public class DrawEndSystem extends AbstractDrawSystem {

    @Override
    public void update(float deltaTime) {
        getDrawSystem().getComponent(CanvasComponent.class).canvas.getBatch().end();
    }
}
