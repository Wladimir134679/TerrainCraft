package com.wdeath.tc.game.systems;

import com.wdeath.tc.game.components.CanvasComponent;

public class DrawBeginSystem extends AbstractDrawSystem {

    @Override
    public void update(float deltaTime) {
        CanvasComponent cc = getDrawSystem().getComponent(CanvasComponent.class);
        cc.canvas.getCamera().update();
        cc.canvas.getBatch().setProjectionMatrix(cc.canvas.getCamera().combined);
        cc.canvas.getBatch().begin();
    }
}
