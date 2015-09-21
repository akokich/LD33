package com.akx2.LD33;

import com.akx2.engine.GameTimer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import javafx.scene.input.KeyCode;

public class Hotkeys {

    final float mofadeSpeed = 2f;

    final float qCooldown = 4f;
    final float wCooldown = 2f;
    final float eCooldown = 2f;

    public HotkeyMode mode = HotkeyMode.NONE;

    Vector2 positionQ;
    Vector2 positionW;
    Vector2 positionE;

    float qFadeAlpha = 0;
    float wFadeAlpha = 0;
    float eFadeAlpha = 0;

    Vector3 mPos = new Vector3(0,0,0);

    public Hotkeys ()
    {
        int middle = Gdx.graphics.getWidth() / 2;

        positionQ = new Vector2(128,0);
        positionW = new Vector2(196,0);
        //positionE = new Vector2(middle + 32,0);
    }

    public void activate (int key)
    {
        switch (key) {
            case Input.Keys.Q:
                qFadeAlpha = 1;
                mode = HotkeyMode.FIREBALL;
                break;
            case Input.Keys.W:
                wFadeAlpha = 1;
                mode = HotkeyMode.AE;
                break;
            case Input.Keys.E:
                eFadeAlpha = 1;
                mode = HotkeyMode.WAVE;
                break;
        }
    }

    public void  deactivate ()
    {
        mode = HotkeyMode.NONE;
    }

    public void update (float delta)
    {
        if (qFadeAlpha > 0)
        {
            qFadeAlpha -= mofadeSpeed * delta;
        }

        if (wFadeAlpha > 0)
        {
            wFadeAlpha -= mofadeSpeed * delta;
        }

        /*
        if (eFadeAlpha > 0)
        {
            eFadeAlpha -= mofadeSpeed * delta;
        }*/

    }

    public void render (SpriteBatch batch, Camera cam)
    {
        batch.draw (Assets.rHKQ, positionQ.x, positionQ.y);
        batch.draw(Assets.rHKW, positionW.x, positionW.y);
        //batch.draw(Assets.rHKE, positionE.x, positionE.y);

        batch.setColor(1f, 1f, 1f, qFadeAlpha);
        batch.draw(Assets.rHKMO, positionQ.x, positionQ.y);

        batch.setColor(1f, 1f, 1f, wFadeAlpha);
        batch.draw(Assets.rHKMO, positionW.x, positionW.y);

        /*
        batch.setColor(1f, 1f, 1f, eFadeAlpha);
        batch.draw(Assets.rHKMO, positionE.x, positionE.y);
        */

        batch.setColor(1f, 1f, 1f, 1f);

        /*
        if (mode == HotkeyMode.AE) {
            mPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(mPos);

            batch.draw(Assets.rAECircleCursor, mPos.x - 128, mPos.y - 128);
        }
        */
    }
}
