package com.akx2.LD33;

import com.akx2.engine.GameTimerIndex;
import com.badlogic.gdx.math.Vector2;
import java.util.Random;

public class Spark {
    private final int ROTATION_SPEED_MAX = 10;
    private final int ROTATION_SPEED_MIN = -10;
    private final float GRAVITY = 0f;

    public Vector2 position;
    public Vector2 previousPosition;
    public float rotation;
    public Vector2 velocity;

    public boolean active;

    public SparkType type;

    private GameTimerIndex animationIndex;

    private float rotationSpeed;

    public Spark ()
    {
        position = new Vector2(0,0);
        previousPosition = new Vector2(0,0);
        velocity = new Vector2(0,0);
        rotation = 0;
        rotationSpeed = new Random().nextInt(ROTATION_SPEED_MAX - ROTATION_SPEED_MIN + 1) + (ROTATION_SPEED_MIN);

        active = false;
        animationIndex = new GameTimerIndex(20, 0, 7);

    }

    public void activate (float x, float y, float velX, float velY, SparkType type, float speed)
    {
        position.set(x, y);
        velocity.set(velX, velY);
        active = true;
        this.type = type;
        animationIndex.reset();
        animationIndex.setSpeed(speed);
    }

    public int getAnimationIndex ()
    {
        return animationIndex.getIndex();
    }

    public void update (float deltaTime)
    {
        if (active) {
            velocity.y += GRAVITY * deltaTime;
            position.add(velocity.x * deltaTime, velocity.y * deltaTime);

            if (animationIndex.getIndex() == 7) {
                active = false;
            } else {
                animationIndex.update(deltaTime);
                rotation += rotationSpeed * deltaTime;
            }
        }
    }
}
