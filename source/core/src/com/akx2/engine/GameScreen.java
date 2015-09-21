package com.akx2.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by Aaron on 8/21/2015.
 */
public abstract class GameScreen {
    public GameScreen ()
    {

    }

    public abstract void init();

    public abstract void render(SpriteBatch batch);

    public abstract void update(float deltaTime);

    public abstract void dispose();
}
