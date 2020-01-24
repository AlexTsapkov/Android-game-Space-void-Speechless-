package com.mygdx.game.attributes;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.screens.GameScreen;

public class GamePointCounter extends Actor {
    private int value;
    private float posX;
    private float posY;

    private BitmapFont font;

    public GamePointCounter() {
        posX = 10;
        posY = 617;
        value = 0;

        font = new TextManager().setFont();

        setX(posX);
        setY(posY);
        setWidth(400);
        setHeight(40);

        //debug();
    }

    public void draw(Batch batch, float parentAlpha) {
        if (GameScreen.gameState) {
            addPoints();
        }
        font.draw(batch, "POINTS: " + value, this.getX(), this.getY() + 30);
    }

    private void addPoints(){
        ++value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
