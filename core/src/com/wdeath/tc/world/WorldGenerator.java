package com.wdeath.tc.world;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.wdeath.tc.world.components.WorldLayerComponent;
import com.wdeath.tc.world.components.WorldPhysicsComponent;

import java.util.Random;

public class WorldGenerator {

    private Entity worldEntity;
    private Random rnd;

    public WorldGenerator(Entity worldEntity) {
        this.worldEntity = worldEntity;
        rnd = new RandomXS128(1);
    }

    public void generation(){
        WorldLayerComponent wlc = worldEntity.getComponent(WorldLayerComponent.class);
        WorldLayer ground = wlc.ground;

        int step = rnd.nextInt(4) + 2;

        int[] heightPoint = new int[ground.getWidth()];
        heightPoint[0] = ground.getHeight() / 2;
        for(int x = 1; x < ground.getWidth(); x++){
            int point = heightPoint[x - 1] + rnd.nextInt(step) - step / 2;
            if(point < 0)
                point = 0;
            if(point >= ground.getHeight())
                point = ground.getHeight() - 1;
            heightPoint[x] = point;
        }

        for(int i = 0; i < 3; i++){
            for(int x = 1; x < ground.getWidth() - 1; x++){
                heightPoint[x] = (heightPoint[x-1]+heightPoint[x+1])/2;
            }
        }

        for(int x = 0; x < ground.getWidth(); x++){
            for(int y = 0; y < 3; y++){
                int yp = heightPoint[x] - y;
                if(yp < 0)
                    yp = 0;
                if(yp >= ground.getHeight())
                    yp = ground.getHeight() - 1;
                ground.set(1, x, yp);
            }
        }

        generatorPhysics();
    }

    private void generatorPhysics(){
        WorldLayerComponent wlc = worldEntity.getComponent(WorldLayerComponent.class);
        WorldLayer ground = wlc.ground;
        WorldPhysicsComponent wpc = worldEntity.getComponent(WorldPhysicsComponent.class);

        for(int x = 0; x< ground.getWidth(); x++){
            for(int y = 0; y < ground.getHeight(); y++){
                int id = ground.get(x, y);
                if(id == 0)
                    continue;
                FixtureDef def = new FixtureDef();
                def.density = 0;
                def.friction = 0.1f;
                def.restitution = 0.1f;
                PolygonShape shape = new PolygonShape();
                Vector2[] vector2s = new Vector2[4];
                vector2s[0] = new Vector2(x, y);
                vector2s[1] = new Vector2(x, y + 1);
                vector2s[2] = new Vector2(x + 1, y + 1);
                vector2s[3] = new Vector2(x + 1, y);
                shape.set(vector2s);
                def.shape = shape;

                Fixture fix = wpc.groundBody.createFixture(def);
                wpc.fixtureGround[x][y] = fix;
            }
        }
    }
}
