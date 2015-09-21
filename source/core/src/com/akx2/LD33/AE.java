package com.akx2.LD33;

import com.akx2.engine.GameTimer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class AE {
    public Vector2 position;
    public boolean active;

    public GameTimer timer;

    public boolean hasExploded;


    public AE ()
    {
        position = new Vector2 (0,0);
        active = false;
        timer = new GameTimer(0.5f);
        hasExploded = false;
    }

    public void activate (float x, float y)
    {
        active = true;
        position.x = x;
        position.y = y;
        timer.reset();
        hasExploded = false;
    }

    public void deactivate ()
    {
        active = false;
    }


    public void update (float delta)
    {
        timer.update(delta);
    }
}
