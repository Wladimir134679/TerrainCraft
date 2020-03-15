package com.wdeath.tc.world;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.wdeath.tc.world.components.WorldLayerComponent;
import com.wdeath.tc.world.components.WorldPhysicsComponent;
import com.wdeath.tc.world.components.WorldPhysicsDebugComponent;

public class WorldBuilder {

    public static final int width = 30, height = 10;

    public static Entity build(){
        Entity entity = new Entity();
        entity.add(getLayers());
        entity.add(getPhysics());
        entity.add(getDebug());
        return entity;
    }

    private static WorldLayerComponent getLayers(){
        WorldLayerComponent com = new WorldLayerComponent();
        com.ground = new WorldLayer(width, height);
        return com;
    }

    private static WorldPhysicsComponent getPhysics(){
        WorldPhysicsComponent wpc = new WorldPhysicsComponent();
        wpc.world = new World(new Vector2(0, -9f), false);
        wpc.fixtureGround = new Fixture[width][height];
        BodyDef def = new BodyDef();
        def.fixedRotation = true;
        def.position.set(0, 0);
        def.type = BodyDef.BodyType.StaticBody;
        wpc.groundBody = wpc.world.createBody(def);
        return wpc;
    }

    private static WorldPhysicsDebugComponent getDebug(){
        WorldPhysicsDebugComponent wpdc = new WorldPhysicsDebugComponent();
        wpdc.debugRenderer = new Box2DDebugRenderer();
        return wpdc;
    }
}
