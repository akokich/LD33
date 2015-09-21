package com.akx2.LD33;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Random;

public class SparkManager {
    private final int MAX = 5000;
    private final int WIDTH = 2;
    private final int HEIGHT = 2;
    private final int ORIGINX = 2;
    private final int ORIGINY = 2;

    private final int MAX_THROWHORZ = 100;
    private final int MAX_THROWVERT = 100;

    private Texture texture;
    private TextureRegion[] meleeRegions;
    private TextureRegion[] casterRegions;
    private TextureRegion[] healerRegions;
    private TextureRegion[] mobRegions;
    private TextureRegion[] bloodRegions;

    public Spark[] melee;
    public Spark[] caster;
    public Spark[] healer;
    public Spark[] mob;
    public Spark[] blood;

    private Spark tSpark = new Spark();

    private Random rng;

    public SparkManager ()
    {
        texture = new Texture("sparks.png");
        meleeRegions = new TextureRegion[8];
        for (int i=0; i<meleeRegions.length; i++)
        {
            meleeRegions[i] = new TextureRegion(texture, WIDTH * i, 0, WIDTH, HEIGHT);
        }
        melee = new Spark[MAX];
        for (int i=0; i<MAX; i++)
        {
            melee[i] = new Spark();
        }

        casterRegions = new TextureRegion[8];
        for (int i=0; i<casterRegions.length; i++)
        {
            casterRegions[i] = new TextureRegion(texture, WIDTH * i, 2, WIDTH, HEIGHT);
        }
        caster = new Spark[MAX];
        for (int i=0; i<MAX; i++)
        {
            caster[i] = new Spark();
        }

        healerRegions = new TextureRegion[8];
        for (int i=0; i<healerRegions.length; i++)
        {
            healerRegions[i] = new TextureRegion(texture, WIDTH * i, 4, WIDTH, HEIGHT);
        }
        healer = new Spark[MAX];
        for (int i=0; i<MAX; i++)
        {
            healer[i] = new Spark();
        }

        mobRegions = new TextureRegion[8];
        for (int i=0; i<mobRegions.length; i++)
        {
            mobRegions[i] = new TextureRegion(texture, WIDTH * i, 6, WIDTH, HEIGHT);
        }
        mob = new Spark[MAX];
        for (int i=0; i<MAX; i++)
        {
            mob[i] = new Spark();
        }

        bloodRegions = new TextureRegion[8];
        for (int i=0; i<bloodRegions.length; i++)
        {
            bloodRegions[i] = new TextureRegion(texture, WIDTH * i, 8, WIDTH, HEIGHT);
        }
        blood = new Spark[MAX];
        for (int i=0; i<MAX; i++)
        {
            blood[i] = new Spark();
        }

        rng = new Random();
    }

    public void add (float x, float y, int count, SparkType type, float speed)
    {
        for (int tI = 0; tI < count; tI++)
        {
            switch (type)
            {
                case MELEE:
                    tSpark = findAvailableMelee();
                    break;
                case CASTER:
                    tSpark = findAvailableCaster();
                    break;
                case HEALER:
                    tSpark = findAvailableHealer();
                    break;
                case MOB:
                    tSpark = findAvailableMob();
                    break;
                case BLOOD:
                    tSpark = findAvailableBlood();
                    break;
            }

            if (tSpark != null)
            {
                tSpark.activate(x, y, rng.nextInt(MAX_THROWHORZ - (-MAX_THROWHORZ) + 1) + (-MAX_THROWHORZ), rng.nextInt(MAX_THROWVERT - (-MAX_THROWVERT) + 1) + (-MAX_THROWVERT), type, speed);
            }
        }
    }

    private Spark findAvailableMelee ()
    {
        for (int tI = 0; tI < MAX; tI++)
        {
            if (!melee[tI].active)
            {
                return melee[tI];
            }
        }

        return null;
    }

    private Spark findAvailableCaster ()
    {
        for (int tI = 0; tI < MAX; tI++)
        {
            if (!caster[tI].active)
            {
                return caster[tI];
            }
        }

        return null;
    }

    private Spark findAvailableHealer ()
    {
        for (int tI = 0; tI < MAX; tI++)
        {
            if (!healer[tI].active)
            {
                return healer[tI];
            }
        }

        return null;
    }

    private Spark findAvailableMob ()
    {
        for (int tI = 0; tI < MAX; tI++)
        {
            if (!mob[tI].active)
            {
                return mob[tI];
            }
        }

        return null;
    }

    private Spark findAvailableBlood ()
    {
        for (int tI = 0; tI < MAX; tI++)
        {
            if (!blood[tI].active)
            {
                return blood[tI];
            }
        }

        return null;
    }

    public void update (float deltaTime)
    {
        for (int tI = 0; tI < MAX; tI++)
        {
            melee[tI].update(deltaTime);
            caster[tI].update(deltaTime);
            healer[tI].update(deltaTime);
            mob[tI].update(deltaTime);
            blood[tI].update(deltaTime);
        }
    }

    public void render (SpriteBatch batch)
    {
        for (int tI = 0; tI < MAX; tI++)
        {
            if (melee[tI].active) {
                batch.draw(meleeRegions[melee[tI].getAnimationIndex()], melee[tI].position.x - (WIDTH / 2), melee[tI].position.y - (HEIGHT/2), ORIGINX, ORIGINY, WIDTH, HEIGHT, 1, 1, melee[tI].rotation);
            }

            if (caster[tI].active) {
                batch.draw(casterRegions[caster[tI].getAnimationIndex()], caster[tI].position.x - (WIDTH / 2), caster[tI].position.y - (HEIGHT/2), ORIGINX, ORIGINY, WIDTH, HEIGHT, 1, 1, caster[tI].rotation);
            }

            if (healer[tI].active) {
                batch.draw(healerRegions[healer[tI].getAnimationIndex()], healer[tI].position.x - (WIDTH / 2), healer[tI].position.y - (HEIGHT/2), ORIGINX, ORIGINY, WIDTH, HEIGHT, 1, 1, healer[tI].rotation);
            }

            if (mob[tI].active) {
                batch.draw(mobRegions[mob[tI].getAnimationIndex()], mob[tI].position.x - (WIDTH / 2), mob[tI].position.y - (HEIGHT/2), ORIGINX, ORIGINY, WIDTH, HEIGHT, 1, 1, mob[tI].rotation);
            }

            if (blood[tI].active) {
                batch.draw(bloodRegions[blood[tI].getAnimationIndex()], blood[tI].position.x - (WIDTH / 2), blood[tI].position.y - (HEIGHT/2), ORIGINX, ORIGINY, WIDTH, HEIGHT, 1, 1, blood[tI].rotation);
            }
        }
    }

    public void dispose()
    {
        texture.dispose();
    }
}

