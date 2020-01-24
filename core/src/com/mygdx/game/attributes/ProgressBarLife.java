package com.mygdx.game.attributes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ProgressBarLife extends Actor {
    private Texture textureFrameBackground;
    private Texture textureFrame;
    private Texture textureValue;
    private float value;
    private float posX;
    private float posY;

    public ProgressBarLife() {
        textureFrameBackground = new Texture("progressbarFrameBg.png");
        textureFrame = new Texture("progressbarFrame.png");
        textureValue = new Texture("progressbarValue.png");
        posX = 10;
        posY = 647;
        value = 400 - 20;

        setX(posX);
        setY(posY);
        setWidth(400);
        setHeight(53);

        //debug();
    }

    public void draw(Batch batch, float parentAlpha) {
        batch.draw(textureFrameBackground, this.getX(), this.getY(), this.getWidth(), this.getHeight());
        batch.draw(textureValue, this.getX() + 10, this.getY(), value, this.getHeight());
        batch.draw(textureFrame, this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
