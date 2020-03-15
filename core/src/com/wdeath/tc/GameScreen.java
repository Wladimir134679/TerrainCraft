package com.wdeath.tc;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Screen;
import com.wdeath.tc.game.DrawBeginSystem;
import com.wdeath.tc.game.DrawEndSystem;
import com.wdeath.tc.game.DrawEntity;
import com.wdeath.tc.world.WorldBuilder;
import com.wdeath.tc.world.WorldDrawSystem;

public class GameScreen implements Screen {

    private Engine engine;

    @Override
    public void show() {
        engine = new Engine();

        //singleton entity
        engine.addEntity(WorldBuilder.build());
        engine.addEntity(DrawEntity.build());

        //all systems
        engine.addSystem(new DrawBeginSystem());
        {
            //DRAW SYSTEMS
            engine.addSystem(new WorldDrawSystem());
        }
        engine.addSystem(new DrawEndSystem());
    }

    @Override
    public void render(float delta) {
        engine.update(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
