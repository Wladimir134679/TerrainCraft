package com.wdeath.tc.players;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.wdeath.tc.players.components.PlayerDataComponent;
import com.wdeath.tc.players.components.PlayerMoveComponent;
import com.wdeath.tc.players.components.PlayerPhysicsComponent;
import com.wdeath.tc.world.components.WorldPhysicsComponent;

public class PlayerBuilder {

    public static final float WIDTH = 0.9f, HEIGHT = 1.7f;

    private Entity entity;

    public PlayerBuilder(){
        entity = new Entity();
    }

    public PlayerBuilder createMove(){
        entity.add(new PlayerMoveComponent());
        return this;
    }

    public PlayerBuilder createPhysics(Vector2 spawn, WorldPhysicsComponent worldPhysics){
        PlayerPhysicsComponent ppc = new PlayerPhysicsComponent();

        BodyDef def = new BodyDef();
        def.fixedRotation = true;
        def.position.set(spawn);
        def.type = BodyDef.BodyType.DynamicBody;

        ppc.body = worldPhysics.world.createBody(def);

        PolygonShape shape = new PolygonShape();
        Vector2[] vector2s = new Vector2[4];
        vector2s[0] = new Vector2(0, 0);
        vector2s[1] = new Vector2(WIDTH, 0);
        vector2s[2] = new Vector2(WIDTH, HEIGHT);
        vector2s[3] = new Vector2(0, HEIGHT);
        shape.set(vector2s);

        FixtureDef fDef = new FixtureDef();
        fDef.shape = shape;
        fDef.density = 0.5f;
        fDef.friction = 0.1f;

        ppc.fixture = ppc.body.createFixture(fDef);

        entity.add(ppc);

        return this;
    }

    public PlayerBuilder createData(String name){
        PlayerDataComponent pdc = new PlayerDataComponent();
        pdc.name = name;
        entity.add(pdc);
        return this;
    }

    public Entity build(){
        return entity;
    }
}
