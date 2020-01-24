package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.attributes.Button;
import com.mygdx.game.attributes.MusicInit;
import com.mygdx.game.attributes.SettingsPanel;
import com.mygdx.game.attributes.TextManager;

public class SettingsScreen implements Screen {
    private SpriteBatch batch;
    private final MyGdxGame game;
    private Texture background;
    private Stage stage;
    private Button buttonBack;
    private BitmapFont font;

    public Music music;
    public MusicInit musicInit;
    private SettingsPanel settingsPanel;

    public SettingsScreen(MyGdxGame game, Music music, MusicInit musicInit) {
        this.game = game;

        this.music = music;
        this.musicInit = musicInit;

        settingsPanel = new SettingsPanel(130, 250, music);

        batch = new SpriteBatch();
        background = new Texture("background.png");

        buttonBack = new Button(140, 180, 95, 43, new Texture("buttonBack.png"),
                234, 43, new Texture("buttonClick.png"));

        stage = new Stage();

        font = new TextManager().setFont();

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

        batch.begin();
        batch.draw(background, 0, 0);
        batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.addActor(settingsPanel);
        stage.addActor(buttonBack);
        stage.draw();

        setMusic();
        buttonBackClick();
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
        batch.dispose();
        font.dispose();
        stage.dispose();

    }

    private void setMusic() {
        if (!music.isPlaying() && MyGdxGame.musicIncluded) {
            music = Gdx.audio.newMusic(Gdx.files.internal(musicInit.MusicInit(1)));
            music.play();
        }
    }

    private void buttonBackClick() {
        if (buttonBack.isButtonClicked()) {
            game.setScreen(new MainMenuScreen(game, music, musicInit));
            dispose();
        }
    }
}
