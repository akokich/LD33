package com.akx2.LD33;

import com.akx2.engine.GameDebug;
import com.akx2.engine.GameMain;
import com.akx2.engine.GameScreens;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class LD33 extends GameMain {
	public static LD33 self;

	@Override
	public void create () {
		super.create();

		self = this;

		Assets.Init();

		Pixmap pm = new Pixmap(Gdx.files.internal("cursor.png"));
		Gdx.input.setCursorImage(pm, 16, 16);
		pm.dispose();

		screens.loadScreen(new MenuScreen());
	}
}
