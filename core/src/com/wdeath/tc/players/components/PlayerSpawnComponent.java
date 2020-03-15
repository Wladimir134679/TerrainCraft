package com.wdeath.tc.players.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class PlayerSpawnComponent implements Component {

    public Vector2 point = new Vector2();
    public String name;
}
