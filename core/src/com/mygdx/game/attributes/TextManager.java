package com.mygdx.game.attributes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class TextManager extends BitmapFont{
    private BitmapFont font;

    public TextManager() {
        font = new BitmapFont(Gdx.files.internal("operational-amplifier.fnt"));
        font.setColor(Color.WHITE);
    }

    public BitmapFont setFont(){
        return font;
    }
}
