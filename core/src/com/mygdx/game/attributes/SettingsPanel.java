package com.mygdx.game.attributes;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.mygdx.game.screens.MyGdxGame;

public class SettingsPanel extends Group {
    private float posX;
    private float posY;
    private SpriteBatch batch;
    private Music music;

    private Texture bannerSettings;
    private Texture bannerMusic;
    private Texture bannerSounds;

    private Button buttonMusicOn;
    private Button buttonMusicOff;
    private Button buttonSoundsOn;
    private Button buttonSoundsOff;

    public SettingsPanel(float posX, float posY, Music music){
        this.posX = posX;
        this.posY = posY;
        batch = new SpriteBatch();
        this.music = music;

        bannerSettings = new Texture("bannerSettings.png");
        bannerMusic = new Texture("bannerMusic.png");
        bannerSounds = new Texture("bannerSounds.png");

        buttonMusicOn = new Button(posX + 370, posY + 180, 51, 43, new Texture("buttonOn.png"),
                90, 43, new Texture("buttonClick.png"));
        buttonMusicOff = new Button(posX + 370, posY + 180, 72, 43, new Texture("buttonOff.png"),
                90, 43, new Texture("buttonClick.png"));
        if (MyGdxGame.musicIncluded) {
            buttonMusicOff.setVisible(false);
        } else {
            buttonMusicOn.setVisible(false);
        }

        buttonSoundsOn = new Button(posX + 370, posY + 80, 51, 43, new Texture("buttonOn.png"),
                90, 43, new Texture("buttonClick.png"));
        buttonSoundsOff = new Button(posX + 370, posY + 80, 72, 43, new Texture("buttonOff.png"),
                90, 43, new Texture("buttonClick.png"));
        if (MyGdxGame.soundsIncluded) {
            buttonSoundsOff.setVisible(false);
        } else {
            buttonSoundsOn.setVisible(false);
        }

        addActor(buttonMusicOn);
        addActor(buttonMusicOff);
        addActor(buttonSoundsOn);
        addActor(buttonSoundsOff);
    }

    public void draw(Batch batch, float parentAlpha) {
        checkClick();
        batch.draw(bannerSettings, posX, posY, 555, 300);
        batch.draw(bannerMusic, posX + 80, posY + 180);
        batch.draw(bannerSounds, posX + 80, posY + 80);
        if (MyGdxGame.musicIncluded) {
            buttonMusicOn.draw(batch ,parentAlpha);
        } else {
            buttonMusicOff.draw(batch ,parentAlpha);
        }
        if (MyGdxGame.soundsIncluded) {
            buttonSoundsOn.draw(batch ,parentAlpha);
        } else {
            buttonSoundsOff.draw(batch ,parentAlpha);
        }
    }

    private void checkClick(){
        musicIncluded();
        soundsIncluded();
    }

    private void buttonMusicOnClick() {
        if (buttonMusicOn.isButtonDown()) {
            buttonMusicOn.setVisible(false);
            buttonMusicOff.setVisible(true);
            MyGdxGame.musicIncluded = !MyGdxGame.musicIncluded;
            music.stop();
        }
    }

    private void buttonMusicOffClick() {
        if (buttonMusicOff.isButtonDown()) {
            buttonMusicOff.setVisible(false);
            buttonMusicOn.setVisible(true);
            MyGdxGame.musicIncluded = !MyGdxGame.musicIncluded;
            music.play();
        }
    }

    private void buttonSoundsOnClick() {
        if (buttonSoundsOn.isButtonDown()) {
            buttonSoundsOn.setVisible(false);
            buttonSoundsOff.setVisible(true);
            MyGdxGame.soundsIncluded = !MyGdxGame.soundsIncluded;
        }
    }

    private void buttonSoundsOffClick() {
        if (buttonSoundsOff.isButtonDown()) {
            buttonSoundsOff.setVisible(false);
            buttonSoundsOn.setVisible(true);
            MyGdxGame.soundsIncluded = !MyGdxGame.soundsIncluded;
        }
    }

    private void musicIncluded() {
        if (MyGdxGame.musicIncluded) {
            buttonMusicOnClick();
        } else {
            buttonMusicOffClick();
        }
    }

    private void soundsIncluded() {
        if (MyGdxGame.soundsIncluded) {
            buttonSoundsOnClick();
        } else {
            buttonSoundsOffClick();
        }
    }
}
