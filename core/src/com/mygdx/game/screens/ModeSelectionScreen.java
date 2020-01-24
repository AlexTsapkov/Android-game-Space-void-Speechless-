package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.attributes.Background;
import com.mygdx.game.attributes.MusicInit;

public class ModeSelectionScreen implements Screen {
    private final MyGdxGame game;
    private Stage stage;
    private Background background;
    public Music music;
    public MusicInit musicInit;

    public ModeSelectionScreen(MyGdxGame game, Music music, MusicInit musicInit){
        this.game = game;
        this.music = music;
        this.musicInit = musicInit;
        background = new Background(new Texture("background.png"));
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

        setMusic();

        if (Gdx.input.isTouched()) {/////////////////////////////////////////////////////////////////////////////////////////
            game.setScreen(new GameScreen(game,music,musicInit));///////////////////////////////////////////////////////////////////////////////
            dispose();/////////////////////////////////////////////////////////////////////////////////////////////////////
        }/////////////////////////////////////////////////////////////////////////////////////////////////////////////
        stage.act(Gdx.graphics.getDeltaTime());
        stage.addActor(background);
        stage.draw();
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
        music.dispose();
    }

    private void setMusic(){
        if (!music.isPlaying() && MyGdxGame.musicIncluded) {
            music = Gdx.audio.newMusic(Gdx.files.internal(musicInit.MusicInit(1)));
            music.play();
        }
    }
}
