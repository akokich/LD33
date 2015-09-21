package com.akx2.LD33;

import com.akx2.engine.GameTimer;
import com.akx2.engine.Util;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class RaidLeader {
    public Raider[] raiders;

    int maxRaiders = 1;

    int i = 0;

    GameTimer startMelee = new GameTimer(0.3175f);
    boolean startedMelee = false;
    GameTimer startCasters = new GameTimer(0.252f);
    public boolean startedCasters = false;

    RaiderType tType = RaiderType.MELEE;

    boolean isHeroic = false;

    Random rnd = new Random();

    public RaidLeader (int maxRaiders, boolean isHeroic)
    {
        this.maxRaiders = maxRaiders;
        this.isHeroic = isHeroic;

        raiders = new Raider[maxRaiders];

        int y = 730;

        for (i=0; i<maxRaiders; i++)
        {
            switch (i%11)
            {
                case 0:
                    tType = RaiderType.MELEE;
                    break;
                case 1:
                case 2:
                case 3:
                    tType = RaiderType.CASTER;
                    tType = RaiderType.CASTER;
                    break;
                case 4:
                    tType = RaiderType.HEALER;
                    break;
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                    switch (rnd.nextInt(8)) {
                        case 0:
                            tType = RaiderType.HEALER;
                            break;
                        case 1:
                        case 2:
                            tType = RaiderType.MELEE;
                            break;
                        case 3:
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                            tType = RaiderType.CASTER;
                            break;
                    }
                    break;
            }

            switch (i%3) {
                case 0:
                    raiders[i] = new Raider(tType, RaiderAssignment.LEFT, 200 + rnd.nextInt(100), y + rnd.nextInt(20), isHeroic);
                    break;
                case 1:
                    raiders[i] = new Raider(tType, RaiderAssignment.MIDDLE, 580 + rnd.nextInt(100), y + rnd.nextInt(20), isHeroic);
                    break;
                case 2:
                    raiders[i] = new Raider(tType, RaiderAssignment.RIGHT, 900 + rnd.nextInt(100), y + rnd.nextInt(20), isHeroic);

                    y += 50;
                    break;
            }
        }
    }

    public void render (SpriteBatch batch)
    {
        for (i=0; i<maxRaiders; i++)
        {
            if (!raiders[i].isAlive) {
                raiders[i].render(batch);
            }
        }

        for (i=0; i<maxRaiders; i++)
        {
            if (raiders[i].isAlive) {
                raiders[i].render(batch);
            }
        }
    }

    public void update (float delta, SparkManager sparks, BossMob boss, SpellManager spells, Raider[] raiders)
    {
        startMelee.update(delta);
        if (startMelee.hasTriggered() && !startedMelee)
        {
            Gdx.app.log("Leader", "Start Melee");
            for (i=0; i<maxRaiders; i++)
            {
                if (raiders[i].type == RaiderType.MELEE) {
                    raiders[i].setup();
                }
            }

            startedMelee = true;
        }

        startCasters.update(delta);
        if (startCasters.hasTriggered() && !startedCasters)
        {
            Gdx.app.log("Leader", "Start Casters");
            for (i=0; i<maxRaiders; i++)
            {
                /*
                if (raiders[i].type == RaiderType.CASTER) {
                    raiders[i].setup();
                }
                */

                raiders[i].canAttack = true;
            }

            startedCasters = true;
        }

        for (i=0; i<maxRaiders; i++)
        {
            raiders[i].update(delta, sparks, boss, spells, raiders);
        }
    }

    public void handleCircleThreat (float x, float y, int diameter)
    {
        for (i=0; i<maxRaiders; i++)
        {
            //Gdx.app.log("Leader", "distance: " + Util.getDistance(raiders[i].position.x, raiders[i].position.y, x, y));
            if (Util.getDistance(raiders[i].position.x, raiders[i].position.y, x, y) < diameter)
            {
                raiders[i].evade(diameter);
            }
        }
    }


}
