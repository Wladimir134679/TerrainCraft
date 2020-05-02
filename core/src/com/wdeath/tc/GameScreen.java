package com.wdeath.tc;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.wdeath.tc.game.components.CanvasComponent;
import com.wdeath.tc.game.systems.DrawBeginSystem;
import com.wdeath.tc.game.systems.DrawEndSystem;
import com.wdeath.tc.game.DrawBuilder;
import com.wdeath.tc.players.PlayerControllerInputListener;
import com.wdeath.tc.players.components.PlayerSpawnComponent;
import com.wdeath.tc.players.systems.PlayerCameraControllerSystem;
import com.wdeath.tc.players.systems.PlayerControllerMoveSystem;
import com.wdeath.tc.players.systems.PlayerSpawnSystem;
import com.wdeath.tc.utils.Canvas;
import com.wdeath.tc.world.*;
import com.wdeath.tc.world.systems.WorldDrawSystem;
import com.wdeath.tc.world.systems.WorldPhysicsDebugSystem;
import com.wdeath.tc.world.systems.WorldPhysicsUpdateSystem;
import lombok.extern.log4j.Log4j2;

import java.util.Timer;
import java.util.TimerTask;

@Log4j2
public class GameScreen implements Screen {

    private String value;

    private Engine engine;
    Canvas canvas;
    BitmapFont font;

    @Override
    public void show() {
        engine = new Engine();

        String namePlayer = "MainPlayer";

        //singleton entity
        Entity world = WorldBuilder.build();
        Entity draw = DrawBuilder.build();
        //add entity
        engine.addEntity(world);
        engine.addEntity(draw);

        canvas = draw.getComponent(CanvasComponent.class).displayCanvas;
        font = new BitmapFont();


        //all systems
        engine.addSystem(new PlayerControllerMoveSystem());
        engine.addSystem(new PlayerSpawnSystem());
        engine.addSystem(new WorldPhysicsUpdateSystem());
        engine.addSystem(new PlayerCameraControllerSystem(namePlayer));
        engine.addSystem(new DrawBeginSystem());
        {
            //DRAW SYSTEMS
            engine.addSystem(new WorldDrawSystem());
        }
        engine.addSystem(new DrawEndSystem());
        engine.addSystem(new WorldPhysicsDebugSystem());

        //World generator
        long start = System.currentTimeMillis();
        WorldGenerator generator = new WorldGenerator(world);
        generator.generation();
        long end = System.currentTimeMillis();
        log.info("World generator: " + (end - start) + "mls");

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(new PlayerControllerInputListener(engine, namePlayer));
        Gdx.input.setInputProcessor(inputMultiplexer);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Gdx.app.postRunnable(() -> {
                    PlayerSpawnComponent psc = new PlayerSpawnComponent();
                    psc.point.set(5, 10);
                    psc.name = namePlayer;
                    Entity entitySpawn = new Entity();
                    entitySpawn.add(psc);
                    engine.addEntity(entitySpawn);
                });
            }
        }, 1000);
    }

    @Override
    public void render(float delta) {
        engine.update(delta);
        canvas.getBatch().begin();
        font.draw(canvas.getBatch(), "FPS: " + Gdx.graphics.getFramesPerSecond(), 5, 5 + font.getLineHeight());
        canvas.getBatch().end();
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
