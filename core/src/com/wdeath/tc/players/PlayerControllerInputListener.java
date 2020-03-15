package com.wdeath.tc.players;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.wdeath.tc.players.components.PlayerDataComponent;
import com.wdeath.tc.players.components.PlayerMoveComponent;

public class PlayerControllerInputListener implements InputProcessor {

    private Engine engine;
    private String namePlayer;
    private Entity playerEntity;

    public PlayerControllerInputListener(Engine engine, String namePlayer) {
        this.engine = engine;
        this.namePlayer = namePlayer;

        engine.addEntityListener(Family.all(PlayerMoveComponent.class, PlayerDataComponent.class).get(), new EntityListener() {
            @Override
            public void entityAdded(Entity entity) {
                PlayerDataComponent data = entity.getComponent(PlayerDataComponent.class);
                System.out.println(data.name);
                if(namePlayer.equals(data.name)){
                    playerEntity = entity;
                }
            }

            @Override
            public void entityRemoved(Entity entity) {

            }
        });
    }

    @Override
    public boolean keyDown(int keycode) {
        if(playerEntity == null)
            return false;
        PlayerMoveComponent pmc = playerEntity.getComponent(PlayerMoveComponent.class);
        if(keycode == Input.Keys.A)
            pmc.left = true;
        if(keycode == Input.Keys.D)
            pmc.right = true;
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(playerEntity == null)
            return false;
        PlayerMoveComponent pmc = playerEntity.getComponent(PlayerMoveComponent.class);
        if(keycode == Input.Keys.A)
            pmc.left = false;
        if(keycode == Input.Keys.D)
            pmc.right = false;
        if(keycode == Input.Keys.SPACE)
            pmc.jump = true;
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
