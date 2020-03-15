package com.wdeath.tc.game;

public class DrawBeginSystem extends AbstractDrawSystem {

    @Override
    public void update(float deltaTime) {
        getDrawSystem().getComponent(CanvasComponent.class).canvas.getBatch().begin();
    }
}
