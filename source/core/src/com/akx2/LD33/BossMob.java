package com.akx2.LD33;

import com.akx2.engine.Util;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Set;

public class BossMob {
    public int health;
    public int mana;

    public int healthMax = 5000;
    public int manaMax = 5000;

    public int score;

    float regenTimer;

    float tW = 0;

    public BossMob ()
    {
        health = healthMax;
        mana = manaMax;

        regenTimer = 0;

        score = 0;
    }

    public void setDifficulty (int players, boolean isHeroic)
    {
        if (isHeroic) {
            healthMax = 6000 * players;
        } else {
            healthMax = 4000 * players;
        }

        health = healthMax;
    }

    public boolean hasMana (int amount)
    {
        if (amount <= mana)
        {
            return true;
        } else {
            return false;
        }
    }

    public void useMana (int amount)
    {
        mana -= amount;
        if (mana < 0)
        {
            mana = 0;
        }
    }

    public void damage (int amount)
    {
        health -= amount;

        if (health <= 0)
        {
            die();
            health = 0;
        }
    }

    public void die()
    {

    }

    public void update (float delta)
    {
        if (regenTimer >= Settings.BOSS_MANAREGENCD) {

            mana += Settings.BOSS_MANAREGEN;
            if (mana > manaMax) {
                mana = manaMax;
            }
            regenTimer = 0;
        } else {
            regenTimer += delta;
        }
    }

    public void render (SpriteBatch batch)
    {
        tW = Util.ratioN(healthMax, 420, health);
        batch.draw(Assets.rHealthBar, (Gdx.graphics.getWidth() / 2) - (tW / 2), 680, tW, 20);

        /*
        tW = Util.ratioN(manaMax, 420, mana);
        batch.draw(Assets.rManaBar, (Gdx.graphics.getWidth() / 2) - (tW / 2), 70, tW, 10);
        */

    }
}
