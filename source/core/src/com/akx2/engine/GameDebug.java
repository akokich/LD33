package com.akx2.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameDebug {
    private BitmapFont font;

    public FPSLogger fpsLogger;

    public GameDebug ()
    {
        fpsLogger = new FPSLogger();
        font = new BitmapFont();
    }

    public void render (SpriteBatch batch)
    {
        font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 15, 15);
    }
}


