package com.akx2.engine;

public class GameTimer {
    float timer = 0;
    float speed = 0;
    boolean triggered = false;

    public GameTimer (float speed)
    {
        this.timer = 0;
        this.speed = speed;
    }

    public boolean update (float delta)
    {
        timer += delta * speed;
        if (timer > 1)
        {
            triggered = true;
        }

        return triggered;
    }

    public boolean hasTriggered ()
    {
        return triggered;
    }

    public void reset()
    {
        timer = 0;
        triggered = false;
    }
}
