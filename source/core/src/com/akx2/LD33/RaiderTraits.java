package com.akx2.LD33;

import com.akx2.engine.Util;

import java.util.Random;

public class RaiderTraits {
    // raider character traits
    public float damage = 1;
    public float healthMax = 1;
    public float health = 1;
    public float speed = 1;

    // raider player traits
    public float awareness = 0.0f;

    // the time it takes for the setup (or heal) spell cools down
    public float cooldown = 1;
    public float cooldownTimer = 0;

    public boolean isHeroic;

    public RaiderTraits (RaiderType type, boolean isHeroic)
    {
        this.isHeroic = isHeroic;

        awareness = 0.5f + (Util.rnd.nextFloat() * 0.6f);
        switch (type)
        {
            case MELEE:

                if (isHeroic) {
                    healthMax = 2500;
                    damage = 1 + Util.rnd.nextFloat();
                    cooldown = 0.75f;
                } else {
                    healthMax = 2000;
                    damage = 1 + Util.rnd.nextFloat();
                    cooldown = 1;
                }
                health = healthMax;
                break;
            case CASTER:
                if (isHeroic) {
                    healthMax = 1500;
                    damage = 3 + Util.rnd.nextFloat();
                    cooldown = 1.75f;
                } else {
                    healthMax = 1000;
                    damage = 2 + Util.rnd.nextFloat();
                    cooldown = 2;
                }
                health = healthMax;
                break;
            case HEALER:
                if (isHeroic) {
                    healthMax = 1500;
                    damage = 0;
                    cooldown = 4;
                } else {
                    healthMax = 1250;
                    damage = 0;
                    cooldown = 5;
                }
                health = healthMax;
                break;
        }
    }
}
