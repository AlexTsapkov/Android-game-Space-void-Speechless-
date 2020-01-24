package com.mygdx.game.attributes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Background extends Actor {
    private Texture background;

    public Background(Texture background) {
        this.background = background;
    }

    public void draw(Batch batch, float parentAlpha) {
        batch.draw(background, this.getX(), this.getY(), 1280, 720);
    }
}

