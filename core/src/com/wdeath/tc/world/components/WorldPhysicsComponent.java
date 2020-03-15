package com.wdeath.tc.world.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

public class WorldPhysicsComponent implements Component {

    public World world;

    public Body groundBody;
    public Fixture[][] fixtureGround;
}
