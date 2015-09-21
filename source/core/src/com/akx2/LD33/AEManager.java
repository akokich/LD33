package com.akx2.LD33;

import com.akx2.engine.Util;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class AEManager {

    public final int MAXAE = 1;

    private Texture texture;
    private TextureRegion region;

    public AE[] aes;

    public AEManager ()
    {
        texture = new Texture ("mob/aecircle.png");
        region = new TextureRegion (texture);

        aes = new AE[MAXAE];
        for (int i=0; i<MAXAE; i++)
        {
            aes[i] = new AE();
        }
    }

    public boolean add (float x, float y)
    {
        for (int i=0; i<aes.length; i++)
        {
            if (!aes[i].active)
            {
                aes[i].activate(x, y);
                return true;
            }
        }

        return false;
    }

    public void update (float delta, SparkManager sparks, Raider[] raiders, BossMob boss)
    {
        for (int i=0; i<aes.length; i++)
        {
            if (aes[i].active)
            {
                aes[i].update (delta);

                if (aes[i].timer.hasTriggered() && !aes[i].hasExploded)
                {
                    aes[i].deactivate();
                    sparks.add(aes[i].position.x + Util.rnd.nextInt(64), aes[i].position.y + Util.rnd.nextInt(64), 50, SparkType.MOB, 8);
                    sparks.add(aes[i].position.x + Util.rnd.nextInt(64), aes[i].position.y - Util.rnd.nextInt(64), 50, SparkType.MOB, 8);
                    sparks.add(aes[i].position.x - Util.rnd.nextInt(64), aes[i].position.y + Util.rnd.nextInt(64), 50, SparkType.MOB, 8);
                    sparks.add(aes[i].position.x - Util.rnd.nextInt(64), aes[i].position.y - Util.rnd.nextInt(64), 50, SparkType.MOB, 8);

                    sparks.add(aes[i].position.x + Util.rnd.nextInt(96), aes[i].position.y + Util.rnd.nextInt(96), 50, SparkType.MOB, 6);
                    sparks.add(aes[i].position.x + Util.rnd.nextInt(96), aes[i].position.y - Util.rnd.nextInt(96), 50, SparkType.MOB, 6);
                    sparks.add(aes[i].position.x - Util.rnd.nextInt(96), aes[i].position.y + Util.rnd.nextInt(96), 50, SparkType.MOB, 6);
                    sparks.add(aes[i].position.x - Util.rnd.nextInt(96), aes[i].position.y - Util.rnd.nextInt(96), 50, SparkType.MOB, 6);

                    for (int r=0; r<raiders.length; r++)
                    {
                        if (raiders[r].isAlive) {
                            if (Util.getDistance(raiders[r].position.x + 16, raiders[r].position.y + 16, aes[i].position.x, aes[i].position.y) < Settings.FELBLAST_DISTANCE) {
                                if (raiders[r].damage(Settings.FELBLAST_DAMAGE))
                                {
                                    // killed
                                    boss.score ++;
                                }

                                Sounds.explo2.play(0.75f);

                                sparks.add(raiders[r].position.x + 16, raiders[r].position.y + 16, 25, SparkType.BLOOD, 15);
                            }
                        }
                    }
                }
            }
        }
    }

    public void render (SpriteBatch batch)
    {
        for (int i=0; i<aes.length; i++)
        {
            if (aes[i].active)
            {
                batch.draw(region, aes[i].position.x-128, aes[i].position.y-128);
            }
        }
    }
}
