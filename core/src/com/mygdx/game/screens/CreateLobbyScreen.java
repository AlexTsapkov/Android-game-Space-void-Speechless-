package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.game.attributes.MusicInit;

public class CreateLobbyScreen implements Screen {
    private final MyGdxGame game;
    private OrthographicCamera camera;

    public Music music;
    public MusicInit musicInit;

    public CreateLobbyScreen(MyGdxGame game, Music music, MusicInit musicInit){
        this.game = game;

        this.music = music;
        this.musicInit = musicInit;

        camera = new OrthographicCamera(1280, 720);
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

        setMusic();
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

    }

    private void setMusic(){
        if (!music.isPlaying() && MyGdxGame.musicIncluded) {
            music = Gdx.audio.newMusic(Gdx.files.internal(musicInit.MusicInit(1)));
            music.play();
        }
    }
}
