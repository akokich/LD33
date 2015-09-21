package com.akx2.LD33;

import com.akx2.engine.Util;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Raider {
    public boolean isAlive = true;
    public boolean canAttack = false;

    public RaiderMode mode;
    public RaiderType type;
    public RaiderAssignment assignment;

    public RaiderTraits traits;

    public Vector2 position;
    public Vector2 targetPosition;

    public boolean isHeroic;

    Random rnd = new Random();

    public Raider (RaiderType type, RaiderAssignment assignment, int posX, int posY, boolean isHeroic)
    {
        this.isHeroic = isHeroic;
        mode = RaiderMode.SETUP;
        this.assignment = assignment;
        this.type = type;
        traits = new RaiderTraits(type, isHeroic);

        position = new Vector2(posX,posY); // determined by the AI motor
        targetPosition = new Vector2(posX, 500);
    }

    public void render (SpriteBatch batch)
    {
        if (isAlive)
        {
            switch (type)
            {
                case MELEE:
                    batch.draw(Assets.rMeleeAlive, position.x, position.y);
                    break;
                case CASTER:
                    batch.draw(Assets.rCasterAlive, position.x, position.y);
                    break;
                case HEALER:
                    batch.draw(Assets.rHealerAlive, position.x, position.y);
                    break;
            }

            switch (type)
            {
                case MELEE:
                case CASTER:
                case HEALER:
                    batch.draw(Assets.rHealthBar, position.x, position.y + 34, Util.ratioN(traits.healthMax, 32, traits.health), 2);
            }
        } else {
            switch (type)
            {
                case MELEE:
                    batch.draw(Assets.rHealerDead, position.x, position.y);
                    break;
                case CASTER:
                    batch.draw(Assets.rHealerDead, position.x, position.y);
                    break;
                case HEALER:
                    batch.draw(Assets.rHealerDead, position.x, position.y);
                    break;
            }
        }
    }

    public void update (float delta, SparkManager sparks, BossMob boss, SpellManager spells, Raider[] raiders)
    {
        if (isAlive) {
            switch (mode) {
                case SETUP:
                    position.lerp(targetPosition, delta * traits.speed);
                    if (position.y < 620) {

                        mode = RaiderMode.MOVE_ENTRY;

                        switch (assignment) {
                            case LEFT:
                                targetPosition.x = 40 + rnd.nextInt(400);
                                targetPosition.y = 350 + rnd.nextInt(200);
                                break;
                            case MIDDLE:
                                targetPosition.x = 420 + rnd.nextInt(400);
                                targetPosition.y = 350 + rnd.nextInt(200);
                                break;
                            case RIGHT:
                                targetPosition.x = 800 + rnd.nextInt(400);
                                targetPosition.y = 350 + rnd.nextInt(200);
                                break;
                        }
                    }
                    break;
                case MOVE_ENTRY:
                case MOVE_ATTACK:
                case MOVE_EVADE:
                    position.lerp(targetPosition, delta * traits.speed);

                    if (Util.getDistance(position.x, position.y, targetPosition.x, targetPosition.y) < 32) {
                        targetPosition.x = position.x;
                        targetPosition.y = position.y;

                        switch (type) {
                            case MELEE:
                                mode = RaiderMode.ATTACK_MELEE;
                                //Gdx.app.log("Raider", "ATTACK MELEE");
                                break;
                            case CASTER:
                                mode = RaiderMode.ATTACK_RANGED;
                                //Gdx.app.log("Raider", "ATTACK_RANGED");
                                break;
                            case HEALER:
                                mode = RaiderMode.HEAL;
                                //Gdx.app.log("Raider", "HEALER");
                                break;
                        }
                    }
                    break;
            }

            // make sure target position isn't off screen
            if (targetPosition.x > 1200)
            {
                targetPosition.x = 1200;
            } else if (targetPosition.x < 80)
            {
                targetPosition.x = 80;
            }

            if ((traits.cooldownTimer > traits.cooldown) && (canAttack))
            {
                switch (mode) {
                    case ATTACK_MELEE:
                        if (Util.rnd.nextFloat() + traits.awareness > 1) {
                            sparks.add(position.x + 16, position.y - 16, 15, SparkType.MELEE, 15);
                            traits.cooldownTimer = 0;
                            boss.damage((int) (Settings.MELEE_DAMAGE * traits.damage));
                            Sounds.melee.play();
                        }
                        break;
                    case ATTACK_RANGED:
                        if (Util.rnd.nextFloat() + traits.awareness > 1) {
                            //Gdx.app.log("Raider", "SPELL");
                            Sounds.caster.play();
                            spells.add(position.x, position.y);
                            traits.cooldownTimer = 0;
                        }
                        break;
                    case HEAL:
                        if (Util.rnd.nextFloat() + traits.awareness > 1) {
                            //Gdx.app.log("Raider", "HEAL");
                            boolean didRes = false;
                            for (int i=0; i<raiders.length; i++)
                            {
                                if ((!raiders[i].isAlive) && (!(raiders[i].type == RaiderType.HEALER)))
                                {
                                    sparks.add(position.x + 16, position.y + 16, 20, SparkType.HEALER, 10);
                                    sparks.add(raiders[i].position.x + 16, raiders[i].position.y + 16, 20, SparkType.HEALER, 10);
                                    raiders[i].res();
                                    Sounds.res.play(0.75f);
                                    didRes = true;
                                    break;
                                }
                            }

                            if (!didRes)
                            {
                                for (int i=0; i<raiders.length; i++)
                                {
                                    if (raiders[i].isAlive) {
                                        if (raiders[i].traits.health < raiders[i].traits.healthMax) {
                                            sparks.add(position.x + 16, position.y + 16, 20, SparkType.HEALER, 10);
                                            sparks.add(raiders[i].position.x + 16, raiders[i].position.y + 16, 20, SparkType.HEALER, 10);
                                            raiders[i].heal(Settings.HEAL_AMOUNT);
                                            Sounds.heal.play();
                                            break;
                                        }
                                    }
                                }
                            }

                            traits.cooldownTimer = 0;
                        }
                }
            } else {
                traits.cooldownTimer += delta;
            }
        }


    }

    public void setup()
    {
        switch (type)
        {
            case MELEE:
                mode = RaiderMode.MOVE_ATTACK;

                targetPosition.x = 420 + rnd.nextInt(400);
                targetPosition.y = 100 + rnd.nextInt(25);

            case CASTER:

                break;

            case HEALER:

                break;
        }
    }

    public boolean damage (int amount)
    {
        //Gdx.app.log("Raider", "Ouch");
        traits.health -= amount;
        if (traits.health < 0)
        {
            traits.health = 0;
            die();
            return true;
        }

        return false;
    }

    public void die ()
    {
        isAlive = false;
        Sounds.die.play();
    }

    public void res ()
    {
        isAlive = true;
        traits.health = traits.healthMax;
    }

    public void heal (int amount)
    {
        traits.health += amount;
        if (traits.health > traits.healthMax)
        {
            traits.health = traits.healthMax;
        }
    }

    public void evade(int diameter)
    {
        if (mode == RaiderMode.MOVE_EVADE)
        {
            // already evading
            //Gdx.app.log("Raider", "Already Evading");
            return;
        }

        mode = RaiderMode.MOVE_EVADE;

        switch (assignment)
        {
            case LEFT:
                targetPosition.x += 200 + rnd.nextInt(200);
                //targetPosition.y += 10 + rnd.nextInt(20);
                assignment = RaiderAssignment.MIDDLE;
                break;
            case MIDDLE:
                if (rnd.nextInt(1) > 0) {
                    targetPosition.x -= 200 + rnd.nextInt(200);
                    //targetPosition.y += 10 + rnd.nextInt(20);
                    assignment = RaiderAssignment.LEFT;
                } else {
                    targetPosition.x += 200 + rnd.nextInt(200);
                    //targetPosition.y += 10 + rnd.nextInt(20);
                    assignment = RaiderAssignment.RIGHT;
                }
                break;
            case RIGHT:
                targetPosition.x -= 200 + rnd.nextInt(200);
                //targetPosition.y += 10 + rnd.nextInt(20);
                assignment = RaiderAssignment.MIDDLE;
                break;
        }


    }
}
