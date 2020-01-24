package com.mygdx.game.bots;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BotHitPoint extends Actor {
    private Texture texture1;
    private Texture texture2;
    private float posX;
    private float posY;
    private float width;
    private float height;

    private Bot bot;

    private float hitPoint;
    private float k;
    private float value;

    public BotHitPoint(Bot bot) {
        texture1 = new Texture("progressbarValue.png");
        texture2 = new Texture("progressbarFrame.png");

        this.bot = bot;

        width = bot.getWidth() * 0.8f;
        height = bot.getHeight() * 0.1f;
        posX = bot.getX() + bot.getWidth() / 2 - width / 2;
        posY = bot.getY() - height;

        setX(posX);
        setY(posY);
        setWidth(width);
        setHeight(height);

        hitPoint = bot.getHitPoints();
        if (bot instanceof MediumBot){
            k = 2;
        } else if (bot instanceof HardBot){
            k = 4;
        } else {
            k = 1;
        }
        value = width;
        //debug();
    }

    public void draw(Batch batch, float parentAlpha) {
        traffic();
        batch.draw(texture1, posX, posY, value*0.8f, this.height);
        batch.draw(texture2, posX, posY, this.width, this.height);
    }

    private void traffic() {
        posX = bot.getX() + bot.getWidth() / 2 - width / 2;
        posY = bot.getY() - height / 2;
        hitPoint = bot.getHitPoints();
        value = hitPoint / k;
        setPosition(posX, posY);
        setOrigin(this.getWidth() / 2, this.getHeight() / 2);
        setRotation(90);
    }
}
