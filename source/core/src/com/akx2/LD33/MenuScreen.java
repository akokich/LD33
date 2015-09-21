package com.akx2.LD33;

import com.akx2.engine.GameScreen;
import com.akx2.engine.Util;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class MenuScreen extends GameScreen {

    public Texture background;
    public Texture menuStart;
    public Texture menuInst;
    public Texture menuQuit;

    Vector2 backgroundPosition;

    OrthographicCamera cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    Vector3 mPos;

    boolean mouseDown = false;

    float waitTimer = 0;
    float waitDelay = 1;

    @Override
    public void init() {
        background = new Texture ("menu/menu_main.png");
        menuStart = new Texture ("menu/menu_start_mo.png");
        menuInst = new Texture ("menu/menu_inst_mo.png");
        menuQuit = new Texture ("menu/menu_quit_mo.png");

        backgroundPosition = new Vector2(103, 104);

        cam.position.x = Gdx.graphics.getWidth()/2;
        cam.position.y = Gdx.graphics.getHeight()/2;

        mPos = new Vector3(0, 0, 0);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(cam.combined);
        cam.update();

        batch.draw(background, backgroundPosition.x, backgroundPosition.y);

        mPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        cam.unproject(mPos);

        if (Util.isPointInBox(mPos.x, mPos.y, 617, 420, 479, 62))
        {
            batch.draw(menuStart, 617, 358);
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && (!mouseDown))
            {
                mouseDown = true;
                LD33.self.screens.loadScreen(new PlayScreen("5-Person Normal", 5, false));
                //LD33.self.screens.loadScreen(new PlayScreen("40-Person Heroic", 40, false));
            }
        }

        /*
        if (Util.isPointInBox(mPos.x, mPos.y, 617, 340, 479, 62))
        {
            batch.draw(menuInst, 617, 278);
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && (!mouseDown))
            {
                mouseDown = true;
                //LD33.self.screens.loadScreen(new PlayScreen());
            }
        }
        */

        if (Util.isPointInBox(mPos.x, mPos.y, 617, 260, 479, 62))
        {
            batch.draw(menuQuit, 617, 198);
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && (!mouseDown) && ((waitTimer > waitDelay)))
            {
                mouseDown = true;
                Gdx.app.exit();
                waitTimer = 0;
            }
        }

    }

    @Override
    public void update(float deltaTime) {

        waitTimer += deltaTime;
    }

    @Override
    public void dispose() {
        background.dispose();
        menuStart.dispose();
    }
}

