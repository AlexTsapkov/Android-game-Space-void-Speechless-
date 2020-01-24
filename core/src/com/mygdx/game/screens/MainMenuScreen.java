package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.attributes.Background;
import com.mygdx.game.attributes.Button;
import com.mygdx.game.attributes.MusicInit;

public class MainMenuScreen implements Screen {
    private final MyGdxGame game;
    private OrthographicCamera camera;
    private Stage stage;
    private Button buttonPlay;
    private Button buttonMultiplayer;
    private Button buttonSettings;
    private Button buttonQuitGame;

    private Background background;

    public Music music;
    public MusicInit musicInit;

    public MainMenuScreen(MyGdxGame game, Music music, MusicInit musicInit){
        this.game = game;

        this.music = music;
        this.musicInit = musicInit;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);

        buttonPlay = new Button(140, 400, 234, 105, new Texture("buttonPlay.png"),
                234, 105, new Texture("buttonPlayClick.png"));
        //buttonMultiplayer = new Button(140, 320, 241, 43, new Texture("buttonMultiplayer.png"),
        //        234, 43, new Texture("buttonClick.png"));
        buttonSettings = new Button(140, 300, 174, 43, new Texture("buttonSettings.png"),
                234, 43, new Texture("buttonClick.png"));
        buttonQuitGame = new Button(140, 230 - 5, 190, 43 + 5, new Texture("buttonQuitGame.png"),
                234, 48, new Texture("buttonClick.png"), 5);

        background = new Background(new Texture("background.png"));

        stage = new Stage();///////////////////////////////////////////////////////////////////////////////////////////////////////////
        Gdx.input.setInputProcessor(stage);///////////////////////////////////////////////////////////////////////////////////////////
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.batch.begin();
        game.batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.addActor(background);
        stage.addActor(buttonPlay);
        //stage.addActor(buttonMultiplayer);
        stage.addActor(buttonSettings);
        stage.addActor(buttonQuitGame);
        stage.draw();

        setMusic();
        buttonPlayClick();
        //buttonMultiplayerClick();
        buttonSettingsClick();
        buttonQuitGameClick();
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

    private void buttonPlayClick(){
        if (buttonPlay.isButtonClicked()) {
            music.dispose();
            game.setScreen(new GameScreen(game,music,musicInit));
            dispose();
        }
    }

    private void buttonMultiplayerClick(){

    }

    private void buttonSettingsClick(){
        if (buttonSettings.isButtonClicked()){
            game.setScreen(new SettingsScreen(game, music, musicInit));
            dispose();
        }
    }

    private void buttonQuitGameClick(){
        if (buttonQuitGame.isButtonClicked()){
            game.dispose();
            System.exit(0);
        }
    }
}
