package com.mygdx.game.attributes;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class WaveCounter extends Actor {
    private int value;
    private float posX;
    private float posY;

    private BitmapFont font;

    public WaveCounter() {
        posX = 585;
        posY = 680;
        value = 1;

        font = new TextManager().setFont();

        setX(posX);
        setY(posY);
        setWidth(110);
        setHeight(40);

        //debug();
    }

    public void draw(Batch batch, float parentAlpha) {
        font.draw(batch, "WAVE - " + value, this.getX(), this.getY() + 30);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
