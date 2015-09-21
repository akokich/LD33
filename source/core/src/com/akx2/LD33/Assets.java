package com.akx2.LD33;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public final class Assets  {

    public static Texture tMeleeAlive;
    public static TextureRegion rMeleeAlive;
    public static Texture tMeleeDead;
    public static TextureRegion rMeleeDead;

    public static Texture tCasterAlive;
    public static TextureRegion rCasterAlive;
    public static Texture tCasterDead;
    public static TextureRegion rCasterDead;

    public static Texture tHealerAlive;
    public static TextureRegion rHealerAlive;
    public static Texture tHealerDead;
    public static TextureRegion rHealerDead;

    public static Texture tAECircle;
    public static TextureRegion rAECircle;

    public static Texture tAECircleCursor;
    public static TextureRegion rAECircleCursor;

    public static Texture tHKQ;
    public static TextureRegion rHKQ;

    public static Texture tHKW;
    public static TextureRegion rHKW;

    public static Texture tHKE;
    public static TextureRegion rHKE;

    public static Texture tHKMO;
    public static TextureRegion rHKMO;

    public static Texture tHealthBar;
    public static TextureRegion rHealthBar;

    public static Texture tManaBar;
    public static TextureRegion rManaBar;

    public static Texture tBackground;
    public static TextureRegion rBackground;

    public static void Init ()
    {
        tMeleeAlive = new Texture ("raiders/melee_alive.png");
        rMeleeAlive = new TextureRegion (tMeleeAlive);

        tMeleeDead = new Texture ("raiders/melee_dead.png");
        rMeleeDead = new TextureRegion (tMeleeDead);

        tCasterAlive = new Texture ("raiders/caster_alive.png");
        rCasterAlive = new TextureRegion (tCasterAlive);

        tCasterDead = new Texture ("raiders/caster_dead.png");
        rCasterDead = new TextureRegion (tCasterDead);

        tHealerAlive = new Texture ("raiders/healer_alive.png");
        rHealerAlive = new TextureRegion (tHealerAlive);

        tHealerDead = new Texture ("raiders/healer_dead.png");
        rHealerDead = new TextureRegion (tHealerDead);

        tAECircle = new Texture ("mob/aecircle.png");
        rAECircle = new TextureRegion (tAECircle);

        tAECircleCursor = new Texture ("mob/aecircle_cursor.png");
        rAECircleCursor = new TextureRegion (tAECircleCursor);

        tHKQ = new Texture ("mob/hotkey_q.png");
        rHKQ = new TextureRegion (tHKQ);

        tHKW = new Texture ("mob/hotkey_w.png");
        rHKW = new TextureRegion (tHKW);

        tHKE = new Texture ("mob/hotkey_e.png");
        rHKE = new TextureRegion (tHKE);

        tHKMO = new Texture ("mob/hotkey_mo.png");
        rHKMO = new TextureRegion (tHKMO);

        tHealthBar = new Texture ("healthbar.png");
        rHealthBar = new TextureRegion (tHealthBar);

        tManaBar = new Texture ("manabar.png");
        rManaBar = new TextureRegion (tManaBar);

        tBackground = new Texture ("background.png");
        rBackground = new TextureRegion (tBackground);
    }
}
