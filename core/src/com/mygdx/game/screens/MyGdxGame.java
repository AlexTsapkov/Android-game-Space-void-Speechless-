package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.attributes.MusicInit;
import com.mygdx.game.screens.MainMenuScreen;

public class MyGdxGame extends Game {
    SpriteBatch batch;

    public static boolean musicIncluded;
    public Music music;
    public MusicInit musicInit;

    public static boolean soundsIncluded;

    @Override
    public void create() {
        batch = new SpriteBatch();

        musicIncluded = true;
        musicInit = new MusicInit();
        music = Gdx.audio.newMusic(Gdx.files.internal(musicInit.MusicInit(1)));
        music.setLooping(false);

        soundsIncluded = true;

        this.setScreen(new MainMenuScreen(this, music, musicInit));
    }

    @Override
    public void render(){
        super.render();
    }

    @Override
    public void dispose(){
        super.dispose();
        batch.dispose();
    }
}
