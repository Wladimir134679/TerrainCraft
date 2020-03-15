package com.wdeath.tc.players.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;

public class PlayerPhysicsComponent implements Component {

    public Body body;
    public Fixture fixture;
}
