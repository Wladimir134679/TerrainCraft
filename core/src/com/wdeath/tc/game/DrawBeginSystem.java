package com.wdeath.tc.game;

import com.wdeath.tc.utils.Canvas;

public class DrawBeginSystem extends AbstractDrawSystem {

    @Override
    public void update(float deltaTime) {
        CanvasComponent cc = getDrawSystem().getComponent(CanvasComponent.class);
        cc.canvas.getCamera().update();
        cc.canvas.getBatch().setProjectionMatrix(cc.canvas.getCamera().combined);
        cc.canvas.getBatch().begin();
    }
}
