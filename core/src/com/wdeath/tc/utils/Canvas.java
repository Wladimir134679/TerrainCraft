package com.wdeath.tc.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Canvas {

    private OrthographicCamera camera;
    private SpriteBatch spriteBatch;

    public Canvas() {
        this(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public Canvas(float w, float h){
        camera = new OrthographicCamera();
        camera.setToOrtho(false, w, h);
        spriteBatch = new SpriteBatch();
    }

    public SpriteBatch getBatch(){
        return spriteBatch;
    }

    public OrthographicCamera getCamera(){
        return camera;
    }
}
