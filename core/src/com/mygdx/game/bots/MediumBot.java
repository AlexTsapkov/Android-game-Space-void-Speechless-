package com.mygdx.game.bots;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.shattle.Shattle;

public class MediumBot extends Bot {
    private Texture ship;
    private Texture ship2;
    private Texture ship3;
    private int hitPoints;
    private float speed;
    private float rotation;
    private float posX;
    private float posY;

    private Shattle shattle;

    private BotHitPoint botHitPoint;

    public MediumBot(Shattle shattle, float x, float y) {
        ship = new Texture("mediumBot.png");
        ship2 = new Texture("mediumBot2.png");
        ship3 = new Texture("mediumBot3.png");
        hitPoints = 200;
        posX = x;
        posY = y;
        speed = (float) ((Math.random() * 0.0111) + 0.005);
        rotation = 180;

        setX(posX);
        setY(posY);
        setWidth(100);
        setHeight(100);

        this.shattle = shattle;

        botHitPoint = new BotHitPoint(this);
        addActor(botHitPoint);
        //debug();
    }

    int i = 0;
    public void draw(Batch batch, float parentAlpha) {
        rotation = findAngle(shattle.getX() - this.getX(), shattle.getY() - this.getY());
        if (GameScreen.gameState) {
            movementToTheShattle(shattle.getX(), shattle.getY());
        }
////////////////////////////////////////////
        if (i < 20) {
            batch.draw(ship, posX, posY, this.getWidth() / 2, this.getHeight() / 2, this.getWidth(), this.getHeight(),
                    1, 1, rotation, 0, 0, 115, 115, true, true);
        } else if(i < 40) {
            batch.draw(ship2, posX, posY, this.getWidth() / 2, this.getHeight() / 2, this.getWidth(), this.getHeight(),
                    1, 1, rotation, 0, 0, 115, 115, true, true);
        } else if (i < 60) {
            batch.draw(ship, posX, posY, this.getWidth() / 2, this.getHeight() / 2, this.getWidth(), this.getHeight(),
                    1, 1, rotation, 0, 0, 115, 115, true, true);
        } else {
            batch.draw(ship3, posX, posY, this.getWidth() / 2, this.getHeight() / 2, this.getWidth(), this.getHeight(),
                    1, 1, rotation, 0, 0, 115, 115, true, true);
        }
        i++;
        if (i == 80){ i = 0; }
////////////////////////////////////////
        setPosition(posX, posY);
        setOrigin(this.getWidth() / 2, this.getHeight() / 2);
        setRotation(rotation);

        botHitPoint.draw(batch, parentAlpha);
    }

    private void movementToTheShattle(float shattleX, float shattleY) {
        this.posX += (shattleX + ((Math.random() * 100)) - this.getX()) * speed;
        this.posY += (shattleY + ((Math.random() * 100)) - this.getY()) * speed;
    }

    public void borders() {
        if (posX < 0) --hitPoints;
        if (posY < 0) --hitPoints;
        if (posX + getWidth() > 6300) --hitPoints;
        if (posY + getHeight() > 6300) --hitPoints;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getSpeed() {
        return speed;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }
}
