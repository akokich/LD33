package com.akx2.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameScreens {
    private Stack<GameScreen> screens;

    public GameScreens ()
    {
        screens = new Stack<GameScreen>();
    }

    public void loadScreen (GameScreen screen)
    {
        if (!screens.empty()) {
            screens.peek().dispose();
        }

        screen.init();
        screens.push(screen);

        Gdx.app.log("GameMain", "loadScreen " + screen);
    }

    public void update(float deltaTime)
    {
        screens.peek().update(deltaTime);
    }

    public void render(SpriteBatch batch)
    {
        screens.peek().render(batch);
    }

    public void dispose ()
    {
        for (int i=0; i<screens.size(); i++) {
            screens.peek().dispose();
            screens.pop();
        }

        screens = null;
    }
}
