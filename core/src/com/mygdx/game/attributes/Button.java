package com.mygdx.game.attributes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.screens.MyGdxGame;

public class Button extends Actor {
    private Texture button;
    private Texture buttonClicked;
    //Sprite sprite;
    //public static float alpha = 1;
    private boolean buttonDown;
    private boolean buttonClick;
    private float inaccuracy;

    public float posX;
    public float posY;
    private float textureClickX;
    private float textureClickY;
    private float textureClickWidth;
    private float textureClicHeight;

    private Sound sound;

    public Button(float x, float y, float width, float height, Texture button,
                  float textureClickWidth, float textureClicHeight, Texture buttonClicked) {

        sound = Gdx.audio.newSound(Gdx.files.internal("sounds/soundButton.ogg"));

        this.button = button;
        this.buttonClicked = buttonClicked;
        //sprite = new Sprite(button);

        buttonDown = false;
        buttonClick = false;

        posX = x;
        posY = y;
        this.textureClickX = x;
        this.textureClickY = y;
        this.textureClickWidth = textureClickWidth;
        this.textureClicHeight = textureClicHeight;

        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);

        addListener(new ButtonListener());

        //debug();
    }

    public Button(float x, float y, float width, float height, Texture button,
                  float textureClickWidth, float textureClicHeight, Texture buttonClicked,
                  float inaccuracy) {
        this(x, y, width, height, button, textureClickWidth, textureClicHeight, buttonClicked);
        this.inaccuracy = inaccuracy;
        this.textureClicHeight = textureClicHeight - inaccuracy;
    }

    public Button(float x, float y, float width, float height, Texture button,
                  float textureClickX, float textureClickY,
                  float textureClickWidth, float textureClickHeight, Texture buttonClicked) {
        this(x, y, width, height, button, textureClickWidth, textureClickHeight, buttonClicked);
        this.textureClickX = textureClickX;
        this.textureClickY = textureClickY;
        this.textureClicHeight = textureClicHeight - inaccuracy;
    }

    private class ButtonListener extends InputListener {
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            setTouched();
            return true;
        }

        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            if (MyGdxGame.soundsIncluded) sound.play();
            setUntouched();
            buttonClick = !buttonClick;
        }

        public void touchDragged(InputEvent event, float x, float y, int pointer) {
            setTouched();
        }
    }


    public void draw(Batch batch, float parentAlpha) {
        if (buttonDown) {
//            batch.draw(buttonClicked, posX, posY + inaccuracy, widthClickTexture, heightClickTexture);
//            batch.draw(button, posX, posY, this.getWidth(), this.getHeight());
            batch.draw(buttonClicked, textureClickX, textureClickY + inaccuracy, textureClickWidth, textureClicHeight);
            batch.draw(button, this.getX(), this.getY(), this.getWidth(), this.getHeight());
//            batch.draw(buttonClicked, this.getX(), this.getY() + inaccuracy, this.getWidth(), this.getHeight() - inaccuracy);
//            sprite.setX(posX);
//            sprite.setY(posY);
//            sprite.setAlpha(alpha);
//            sprite.draw(batch);
        } else {
            batch.draw(button, this.getX(), this.getY(), this.getWidth(), this.getHeight());
//            sprite.setX(posX);
//            sprite.setY(posY);
//            sprite.setAlpha(alpha);
//            sprite.draw(batch);
        }
    }

    public void setTouched() {
        buttonDown = true;
    }

    public void setUntouched() {
        buttonDown = false;
    }

    public boolean isButtonDown() {
        return buttonDown;
    }

    public boolean isButtonClicked() {
        return buttonClick;
    }
}
