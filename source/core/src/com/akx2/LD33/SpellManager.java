package com.akx2.LD33;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpellManager {
    public final int DAMAGE = 25;

    private final int MAX = 20;
    private final int WIDTH = 16;
    private final int HEIGHT = 16;
    private final int ORIGINX = 8;
    private final int ORIGINY = 8;

    private final float LIFE = 10f;

    private Texture texture;
    private TextureRegion textureRegion;

    public Spell[] spells;

    private Spell tSpell;

    public SpellManager ()
    {
        texture = new Texture("raiders/spell.png");
        textureRegion = new TextureRegion(texture);

        spells = new Spell[MAX];
        for (int i=0; i<MAX; i++)
        {
            spells[i] = new Spell();
        }
    }

    public void add (float x, float y)
    {
        tSpell = findAvailable();
        if (tSpell != null) {
            tSpell.activate(x, y);
        }
    }

    private Spell findAvailable ()
    {
        for (int tI = 0; tI < MAX; tI++)
        {
            if (!spells[tI].active)
            {
                return spells[tI];
            }
        }

        return null;
    }

    public void update (float deltaTime, SparkManager sparks, BossMob boss)
    {
        for (int tI = 0; tI < MAX; tI++)
        {
            spells[tI].update(deltaTime, sparks, boss);
        }
    }

    public void render (SpriteBatch batch)
    {
        for (int tI = 0; tI < MAX; tI++)
        {
            if (spells[tI].active) {
                batch.draw(textureRegion, spells[tI].position.x, spells[tI].position.y, ORIGINX, ORIGINY, WIDTH, HEIGHT, 1, 1, spells[tI].rotation);
            }
        }
    }

    public void dispose()
    {
        texture.dispose();
    }
}
