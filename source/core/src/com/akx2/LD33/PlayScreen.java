package com.akx2.LD33;

import com.akx2.engine.GameScreen;
import com.akx2.engine.GameSettings;
import com.akx2.engine.Util;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class PlayScreen extends GameScreen {

    public BossMob boss;
    public RaidLeader leader;

    public Hotkeys hotkeys;

    public SparkManager sparks;

    public SpellManager spells;

    public AEManager aeManager;

    public Background background;

    BitmapFont scoreFont;

    String title;
    int players;
    boolean isHeroic;

    boolean isWDown = false;
    boolean isEDown = false;
    boolean isQDown = false;

    boolean isGameOver = false;
    boolean didWin = false;
    Popup popup;

    OrthographicCamera cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    Vector3 mPos;


    Texture bossTexture;


    public PlayScreen (String title, int players, boolean isHeroic)
    {
        this.title = title;
        this.players = players;
        this.isHeroic = isHeroic;
    }

    @Override
    public void init() {
        boss = new BossMob();
        boss.setDifficulty(players, isHeroic);

        leader = new RaidLeader(players, isHeroic);
        hotkeys = new Hotkeys();

        bossTexture = new Texture ("mob/boss.png");

        aeManager = new AEManager();
        sparks = new SparkManager();
        spells = new SpellManager ();

        background = new Background ();

        scoreFont = new BitmapFont(Gdx.files.internal("fonts/GilSans32-Mid.fnt"), false);

        cam.position.x = Gdx.graphics.getWidth()/2;
        cam.position.y = Gdx.graphics.getHeight()/2;

        mPos = new Vector3(0, 0, 0);

        popup = new Popup ("Defeat the raid group", true);
        popup.show();

        GameSettings.backgroundColor.set(0.2f, 0.15f, 0.05f);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(cam.combined);
        cam.update();

        background.render(batch);

        aeManager.render(batch);

        if (!popup.isShowing) {
            batch.draw(bossTexture, (Gdx.graphics.getWidth() / 2) - 243, -175);
        }

        leader.render(batch);

        spells.render(batch);

        sparks.render(batch);

        hotkeys.render(batch, cam);

        boss.render(batch);

        scoreFont.draw(batch, title, 950, 46);

        popup.render(batch);
    }

    @Override
    public void update(float delta) {
        background.update(delta);
        spells.update(delta, sparks, boss);
        aeManager.update(delta, sparks, leader.raiders, boss);
        sparks.update(delta);

        if ((!popup.isShowing) && (!isGameOver)) {
            handleInput();

            leader.update(delta, sparks, boss, spells, leader.raiders);
            hotkeys.update(delta);

            boss.update(delta);

            boolean hasAlive = false;
            for (int i = 0; i < leader.raiders.length; i++) {
                if (leader.raiders[i].isAlive) {
                    hasAlive = true;
                    break;
                }
            }
            if (!hasAlive) {
                isGameOver = true;
                if (title == "40-Person Heroic") {
                    popup.reset("Thank you for playing.", true);
                } else {
                    popup.reset("Those noobs are toast!", true);
                }
                didWin = true;
                popup.show();
            }
            if (boss.health <= 0) {
                isGameOver = true;
                popup.reset("They took your loots", false);
                didWin = false;
                popup.show();
            }
        }

        popup.update(delta);

        if ((isGameOver) && (!popup.isShowing))
        {
            if (didWin) {
                goNextGame();
            } else {
                LD33.self.screens.loadScreen(new MenuScreen());
            }
        }
    }

    private void handleInput ()
    {
        mPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        cam.unproject(mPos);

        if (leader.startedCasters) {
            if ((!isQDown) && Gdx.input.isKeyPressed(Input.Keys.Q)) {
                if (boss.hasMana(Settings.FELBALL_MANA)) {
                    boss.useMana(Settings.FELBALL_MANA);

                    hotkeys.activate(Input.Keys.Q);

                    Sounds.explo1.play(0.25f);

                    sparks.add(mPos.x, mPos.y, 50, SparkType.MOB, 10);
                    leader.handleCircleThreat(mPos.x, mPos.y, Settings.FELBALL_DISTANCE);

                    for (int r = 0; r < leader.raiders.length; r++) {
                        if (leader.raiders[r].isAlive) {
                            if (Util.getDistance(leader.raiders[r].position.x + 16, leader.raiders[r].position.y + 16, mPos.x, mPos.y) < Settings.FELBALL_DISTANCE) {
                                leader.raiders[r].damage(Settings.FELBALL_DAMAGE);

                                sparks.add(leader.raiders[r].position.x + 16, leader.raiders[r].position.y + 16, 25, SparkType.BLOOD, 15);
                            }
                        }
                    }

                    isQDown = true;
                }
            } else if (!Gdx.input.isKeyPressed(Input.Keys.Q)) {
                isQDown = false;
            }

            if ((!isWDown) && Gdx.input.isKeyPressed(Input.Keys.W)) {
                if (boss.hasMana(Settings.FELBLAST_MANA)) {

                    if (aeManager.add(mPos.x, mPos.y)) {
                        hotkeys.activate(Input.Keys.W);
                        boss.useMana(Settings.FELBLAST_MANA);
                        leader.handleCircleThreat(mPos.x, mPos.y, Settings.FELBLAST_DISTANCE);
                    }

                    isWDown = true;
                }

            } else if (!Gdx.input.isKeyPressed(Input.Keys.W)) {
                isWDown = false;
            }
        }
    }


    private void goNextGame ()
    {
        if (title == "5-Person Normal") {
            LD33.self.screens.loadScreen(new PlayScreen("5-Person Heroic", 5, true));
        } else if (title == "5-Person Heroic") {
            LD33.self.screens.loadScreen(new PlayScreen("10-Person Normal", 10, false));
        } else if (title == "10-Person Normal") {
            LD33.self.screens.loadScreen(new PlayScreen("10-Person Heroic", 10, true));
        } else if (title == "10-Person Heroic") {
            LD33.self.screens.loadScreen(new PlayScreen("25-Person Normal", 25, false));
        } else if (title == "25-Person Normal") {
            LD33.self.screens.loadScreen(new PlayScreen("25-Person Heroic", 25, true));
        } else if (title == "25-Person Heroic") {
            LD33.self.screens.loadScreen(new PlayScreen("40-Person Normal", 40, false));
        } else if (title == "40-Person Normal") {
            LD33.self.screens.loadScreen(new PlayScreen("40-Person Heroic", 40, true));
        } else {
            LD33.self.screens.loadScreen(new MenuScreen ());
        }
    }

    @Override
    public void dispose() {

    }
}
