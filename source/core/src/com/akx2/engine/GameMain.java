package com.akx2.engine;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameMain implements ApplicationListener {
    SpriteBatch batch;

    public GameDebug debug;

    public GameScreens screens;

    @Override
    public void create() {

        batch = new SpriteBatch();

        screens = new GameScreens();
        debug = new GameDebug();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        screens.update(Gdx.graphics.getDeltaTime());

        Gdx.graphics.getGL20().glClearColor( GameSettings.backgroundColor.x, GameSettings.backgroundColor.y, GameSettings.backgroundColor.z, 1 );
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        screens.render(batch);

        //debug.render(this.batch);

        batch.end();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        screens.dispose();
    }
}
