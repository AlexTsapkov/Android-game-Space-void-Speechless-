package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.attributes.Background;
import com.mygdx.game.attributes.Button;
import com.mygdx.game.attributes.MusicInit;
import com.mygdx.game.attributes.TextManager;

public class GameOverScreen implements Screen {
    private SpriteBatch batch;
    private Background background;
    private Texture bannerResult;
    private final MyGdxGame game;
    private Stage stage;
    private Button buttonMenu;
    private Button buttonRestart;

    public Music music;
    public MusicInit musicInit;

    private BitmapFont font;
    private int result;
    private String time;

    public GameOverScreen(MyGdxGame game,Music music,MusicInit musicInit, int result, long time){
        this.game = game;

        batch = new SpriteBatch();
        background = new Background(new Texture("background.png"));
        bannerResult = new Texture("bannerResult.png");
        this.music = music;
        this.musicInit = musicInit;
        music.setLooping(false);
        this.result = result;
        this.time = timeSetting(time);

        buttonRestart = new Button(306, 180, 159, 43, new Texture("buttonRestart.png"),
                234, 43, new Texture("buttonClick.png"));
        buttonMenu = new Button(860, 180, 100, 43, new Texture("buttonMenu.png"),
                860 + 100 - 234, 180,
                234, 43, new Texture("buttonClickReverse.png"));

        font = new TextManager().setFont();

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.addActor(background);
        stage.addActor(buttonRestart);
        stage.addActor(buttonMenu);
        stage.draw();

        batch.begin();
        batch.draw(bannerResult, 300, 340, 680, 29);
        font.draw(batch, "RESULT                          " + result + "                          " + time, 330, 390);
        batch.end();

        setMusic();

        buttonMenuClick();
        buttonClick();
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
    }

    private void setMusic(){
        if (!music.isPlaying() && MyGdxGame.musicIncluded) {
            music = Gdx.audio.newMusic(Gdx.files.internal(musicInit.MusicInit(1)));
            music.play();
        }
    }

    private void buttonMenuClick(){
        if (buttonMenu.isButtonClicked()) {
            game.setScreen(new MainMenuScreen(game, music, musicInit));
            dispose();
        }
    }

    private void buttonClick(){
        if (buttonRestart.isButtonClicked()) {
            game.setScreen(new GameScreen(game,music,musicInit));
            music.dispose();
            dispose();
        }
    }

    private String timeSetting(long time){
        time = time / 1000;
        if (time > 60){
            int minutes = (int) time / 60;
            int seconds = (int) time % 60;
            if(seconds >= 10) {
                return minutes + ":" + seconds;
            } else {
                return minutes + ":0" + seconds;
            }
        } else {
            return time + "s";
        }
    }
}
