package com.wdeath.tc;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Screen;
import com.wdeath.tc.game.systems.DrawBeginSystem;
import com.wdeath.tc.game.systems.DrawEndSystem;
import com.wdeath.tc.game.DrawBuilder;
import com.wdeath.tc.world.*;
import com.wdeath.tc.world.systems.WorldDrawSystem;
import com.wdeath.tc.world.systems.WorldPhysicsDebugSystem;
import com.wdeath.tc.world.systems.WorldPhysicsUpdateSystem;

public class GameScreen implements Screen {

    private Engine engine;

    @Override
    public void show() {
        engine = new Engine();

        //singleton entity
        Entity world = WorldBuilder.build();
        //add entity
        engine.addEntity(world);
        engine.addEntity(DrawBuilder.build());

        //all systems
        engine.addSystem(new WorldPhysicsUpdateSystem());
        engine.addSystem(new DrawBeginSystem());
        {
            //DRAW SYSTEMS
            engine.addSystem(new WorldDrawSystem());
        }
        engine.addSystem(new DrawEndSystem());
        engine.addSystem(new WorldPhysicsDebugSystem());

        //World generator
        WorldGenerator generator = new WorldGenerator(world);
        generator.generation();
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
