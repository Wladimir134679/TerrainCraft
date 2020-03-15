package com.wdeath.tc;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.wdeath.tc.items.ItemLoad;

public class MainGame extends Game {

	@Override
	public void create() {
		Assets.load();
		ItemLoad.load();
		this.setScreen(new GameScreen());
	}

	@Override
	public void render() {
		Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);
		super.render();
	}
}
