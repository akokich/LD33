package com.akx2.LD33;

import com.akx2.engine.GameTimerIndex;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Spell {
    private final int ROTATION_SPEED_MAX = 10;
    private final int ROTATION_SPEED_MIN = -10;

    public Vector2 position;
    public Vector2 previousPosition;
    public float rotation;
    public Vector2 velocity;

    public boolean active;

    private GameTimerIndex animationIndex;

    private float rotationSpeed;

    private float tailTimer = 0;
    private float tailDelay = 0.025f;

    public Spell()
    {
        position = new Vector2(0,0);
        previousPosition = new Vector2(0,0);
        velocity = new Vector2(0,0);
        rotation = 0;
        rotationSpeed = new Random().nextInt(ROTATION_SPEED_MAX - ROTATION_SPEED_MIN + 1) + (ROTATION_SPEED_MIN);

        active = false;
        animationIndex = new GameTimerIndex(2, 0, 7);
    }

    public void activate (float x, float y)
    {
        position.set(x, y);
        //velocity.set(velX, velY);

        velocity.set(Gdx.graphics.getWidth() / 2 - position.x, 10 - position.y).nor().scl(Math.min(position.dst(Gdx.graphics.getWidth() / 2, 10), 500));

        active = true;
        animationIndex.reset();
    }

    public void deactivate ()
    {
        active = false;
    }

    public int getAnimationIndex ()
    {
        return animationIndex.getIndex();
    }

    public void update(float deltaTime, SparkManager sparks, BossMob boss)
    {
        if (active)
        {
            if (tailTimer >= tailDelay)
            {
                sparks.add(position.x + 8, position.y + 8, 2, SparkType.CASTER, 30);
                tailTimer = 0;
            } else {
                tailTimer += deltaTime;
            }

            position.add(velocity.x * deltaTime, velocity.y * deltaTime);

            previousPosition.set(position.x, position.y);

            if (position.y < 100)
            {
                sparks.add(position.x, position.y, 10, SparkType.CASTER, 3);
                this.active = false;
                boss.damage(Settings.CASTER_DAMAGE);
            }

            if (animationIndex.getIndex() == 7) {
                deactivate ();
            } else {
                animationIndex.update(deltaTime);
                rotation += rotationSpeed * deltaTime;
            }
        }
    }
}
