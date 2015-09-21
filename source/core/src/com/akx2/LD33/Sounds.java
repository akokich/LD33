package com.akx2.LD33;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Sounds {
    public static Sound caster = Gdx.audio.newSound(Gdx.files.internal("sounds/caster.mp3"));

    public static Sound heal = Gdx.audio.newSound(Gdx.files.internal("sounds/heal.mp3"));
    public static Sound res = Gdx.audio.newSound(Gdx.files.internal("sounds/res.mp3"));

    public static Sound melee = Gdx.audio.newSound(Gdx.files.internal("sounds/melee.mp3"));

    public static Sound explo1 = Gdx.audio.newSound(Gdx.files.internal("sounds/Explosion2.mp3"));
    public static Sound explo2 = Gdx.audio.newSound(Gdx.files.internal("sounds/Explosion4.mp3"));

    public static Sound die = Gdx.audio.newSound(Gdx.files.internal("sounds/die.mp3"));
}
