package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.attributes.GamePointCounter;
import com.mygdx.game.attributes.MusicInit;
import com.mygdx.game.attributes.ScreenPause;
import com.mygdx.game.attributes.ProgressBarLife;
import com.mygdx.game.attributes.WaveCounter;
import com.mygdx.game.gameEvents.WaveManagment;
import com.mygdx.game.joystick.JoystickMove;
import com.mygdx.game.joystick.JoystickArea;
import com.mygdx.game.joystick.JoystickShooting;
import com.mygdx.game.gameEvents.Collision;
import com.mygdx.game.shattle.Shattle;
import com.mygdx.game.shooting.BulletGroup;

public class GameScreen implements Screen {
    private final MyGdxGame game;
    public static boolean gameState;
    private Stage stage;
    private OrthographicCamera camera;
    private ProgressBarLife progressBarLife;
    private GamePointCounter gamePointCounter;
    private WaveCounter waveCounter;

    private JoystickMove joystickMove;
    private JoystickShooting joystickShooting;
    private JoystickArea joystickAreaMove;
    private JoystickArea joystickAreaShooting;

    private ScreenPause screenPause;

    private Shattle shattle;
    private final float SHATTLE_CENTER_X;
    private final float SHATTLE_CENTER_Y;

    private Collision collision;
    private WaveManagment waveManagment;
    private BulletGroup bulletGroup;

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private long startTime;

    public Music music;
    public MusicInit musicInit;

    public GameScreen(final MyGdxGame game,Music music,MusicInit musicInit) {
        this.game = game;
        gameState = true;

        this.music = music;
        this.musicInit = musicInit;
        setMusic();

        camera = new OrthographicCamera(1280, 720);
        stage = new Stage(new ScreenViewport(camera));

        progressBarLife = new ProgressBarLife();
        gamePointCounter = new GamePointCounter();
        waveCounter = new WaveCounter();

        joystickMove = new JoystickMove();
        joystickShooting = new JoystickShooting();
        joystickAreaMove = new JoystickArea(joystickMove, 5);
        joystickAreaShooting = new JoystickArea(joystickShooting, 775);

        screenPause = new ScreenPause(this);

        shattle = new Shattle(joystickMove, joystickShooting, progressBarLife);
        SHATTLE_CENTER_X = shattle.getWidth() / 2;
        SHATTLE_CENTER_Y = shattle.getHeight() / 2;

//        weakBot1 = new WeakBot(shattle, 200, 500);
//        weakBot2 = new WeakBot(shattle, 1000, 200);
        collision = new Collision(shattle, gamePointCounter);
        waveManagment = new WaveManagment(waveCounter, collision);
        bulletGroup = new BulletGroup(shattle,joystickShooting, collision);

        //map = new TmxMapLoader().load("map/build1.tmx");
        map = new TmxMapLoader().load("map/build2.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);

        startTime = TimeUtils.millis();

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.05f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        renderer.setView(camera);
        renderer.render();

        setMusic();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.addActor(progressBarLife);
        stage.addActor(gamePointCounter);
        stage.addActor(waveCounter);
        stage.addActor(joystickMove);
        stage.addActor(joystickShooting);
        stage.addActor(joystickAreaMove);
        stage.addActor(joystickAreaShooting);

        stage.addActor(collision);
        stage.addActor(bulletGroup);

        stage.addActor(screenPause);
        screenPause.included();

        collision.check();
        waveManagment.check();
        bulletGroup.shooting();

        if (gameState) {
            joystickAreaMoveUse();
            joystickAreaShootingUse();
            gameOver();
        }
        setPositionAllActor();

        stage.draw();
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        music.dispose();
    }

    private void setMusic(){
        if (!music.isPlaying() && MyGdxGame.musicIncluded) {
            music = Gdx.audio.newMusic(Gdx.files.internal(musicInit.MusicInit(2)));
            music.play();
        }
    }

    private void joystickAreaMoveUse() {
        if (joystickAreaMove.isJoystickAreaDown()) {
            joystickMove.setCenterPosition(joystickAreaMove.getDownX(), joystickAreaMove.getDownY() + 5);
            joystickMove.setTouched();
            joystickMove.changeKnob(joystickAreaMove.getDraggedX() - joystickAreaMove.getDownX() + 100,
                    joystickAreaMove.getDraggedY() - joystickAreaMove.getDownY() + 100);
        }
    }

    private void joystickAreaShootingUse() {
        if (joystickAreaShooting.isJoystickAreaDown()) {
            joystickShooting.setCenterPosition(joystickAreaShooting.getDownX(), joystickAreaShooting.getDownY() + 5);
            joystickShooting.setTouched();
            joystickShooting.changeKnob(joystickAreaShooting.getDraggedX() - joystickAreaShooting.getDownX() + 100,
                    joystickAreaShooting.getDraggedY() - joystickAreaShooting.getDownY() + 100);
        }
    }

    private void setPositionAllActor() {
        progressBarLife.setPosition(shattle.getPosX() - 635 + SHATTLE_CENTER_X, shattle.getPosY() + 302 + SHATTLE_CENTER_Y);
        gamePointCounter.setPosition(shattle.getPosX() - 635 + SHATTLE_CENTER_X, shattle.getPosY() + 262 + SHATTLE_CENTER_Y);
        waveCounter.setPosition(shattle.getPosX() - 55 + SHATTLE_CENTER_X, shattle.getPosY() + 320 + SHATTLE_CENTER_Y);
        joystickAreaMove.setPosition(shattle.getPosX() - 635 + SHATTLE_CENTER_X, shattle.getPosY() - 355 + SHATTLE_CENTER_Y);
        if (joystickMove.isJoystickDown()) {
            joystickMove.setPosition(joystickMove.getX() + joystickAreaMove.getX(),
                    joystickMove.getY() + joystickAreaMove.getY());
        } else {
            joystickMove.setPosition(joystickAreaMove.getX() + 100, joystickAreaMove.getY() + 100);
        }
        joystickAreaShooting.setPosition(shattle.getPosX() + 135 + SHATTLE_CENTER_X, shattle.getPosY() - 355 + SHATTLE_CENTER_Y);
        if (joystickShooting.isJoystickDown()) {
            joystickShooting.setPosition(joystickShooting.getX() + joystickAreaShooting.getX(),
                    joystickShooting.getY() + joystickAreaShooting.getY());
        } else {
            joystickShooting.setPosition(joystickAreaShooting.getX() + 200, joystickAreaShooting.getY() + 100);
        }
        screenPause.setPosition(shattle.getPosX() + 50 - 640, shattle.getPosY() + 50 - 360);
        camera.position.set(shattle.getPosX() + SHATTLE_CENTER_X, shattle.getPosY() + SHATTLE_CENTER_Y, 0);
    }

    private void gameOver(){
        if (progressBarLife.getValue() < 20) {
            game.setScreen(new GameOverScreen(game,music,musicInit, gamePointCounter.getValue(), TimeUtils.timeSinceMillis(startTime)));
            dispose();
        }
    }

    public MyGdxGame getGame() {
        return game;
    }
}
