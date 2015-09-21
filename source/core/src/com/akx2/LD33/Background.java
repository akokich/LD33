package com.akx2.LD33;

import com.akx2.engine.Util;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Background {
    final int TOTAL_ROCKS = 25;
    public Texture rock01;
    public Texture rock02;
    public Texture rock03;
    public Texture rock04;

    public Texture smallRocks;
    public TextureRegion[] smallRocksRegion;

    public Vector3[] randomRocks;

    public Background ()
    {
        rock01 = new Texture ("env/rock01.png");
        rock02 = new Texture ("env/rock02.png");
        rock03 = new Texture ("env/rock03.png");
        rock04 = new Texture ("env/rock04.png");

        smallRocks = new Texture("env/smlrocks.png");
        smallRocksRegion = new TextureRegion[5];
        for (int i=0;i<5; i++)
        {
            smallRocksRegion[i] = new TextureRegion(smallRocks, i*32, 0, 32, 32);
        }

        randomRocks = new Vector3[TOTAL_ROCKS];
        for (int i=0; i<TOTAL_ROCKS; i++)
        {
            randomRocks[i] = new Vector3(0,0,0);
            randomRocks[i].x = 10 + Util.rnd.nextInt(1200);
            randomRocks[i].y = 10 + Util.rnd.nextInt(720);
            randomRocks[i].z = Util.rnd.nextInt(5);
        }
    }

    public void update (float delta)
    {

    }

    public void render (SpriteBatch batch)
    {
        for (int i=0; i<TOTAL_ROCKS; i++)
        {
            batch.draw (smallRocksRegion[(int)randomRocks[i].z], randomRocks[i].x, randomRocks[i].y);
        }

        batch.draw (rock03, -10, 625);
        batch.draw (rock01, 380, 625);
        batch.draw (rock02, 740, 625);
        batch.draw (rock04, 1140, 625);
    }
}



