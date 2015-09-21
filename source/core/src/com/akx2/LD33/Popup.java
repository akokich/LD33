package com.akx2.LD33;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Popup {
    public boolean isShowing;
    String popupText;
    boolean isHappy;

    BitmapFont font;

    Texture textureHappy;
    Texture textureSad;

    float textWidth;

    float waitTimer = 0;
    float waitDelay = 1;

    public Popup (String popupText, boolean isHappy)
    {
        this.isShowing = false;
        this.popupText = popupText;
        this.isHappy =  isHappy;
        font = new BitmapFont(Gdx.files.internal("fonts/GilSans32-Mid.fnt"), false);

        textureHappy = new Texture("menu/popup_happy.png");
        textureSad = new Texture("menu/popup_sad.png");

        reset(popupText, isHappy);
    }

    public void reset (String popupText, boolean isHappy)
    {
        this.isShowing = false;
        this.popupText = popupText;
        this.isHappy =  isHappy;

        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(font,popupText);
        textWidth= glyphLayout.width;
    }

    public void show ()
    {
        isShowing = true;
    }

    public void hide ()
    {
        isShowing = false;
    }

    public void update (float delta)
    {
        waitTimer += delta;

        if ((Gdx.input.isButtonPressed(Input.Buttons.LEFT)) && (waitTimer > waitDelay))
        {
            hide();
        }
    }

    public void render (SpriteBatch batch)
    {
        if (isShowing) {
            if (isHappy) {
                batch.draw(textureHappy, (Gdx.graphics.getWidth() / 2) - 256, (Gdx.graphics.getHeight() / 2) - 220);
            } else {
                batch.draw(textureSad, (Gdx.graphics.getWidth() / 2) - 256, (Gdx.graphics.getHeight() / 2) - 220);
            }
            font.draw(batch, popupText, (Gdx.graphics.getWidth() / 2) - (textWidth / 2), 270);
        }
    }
}
