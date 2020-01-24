package com.mygdx.game.shooting;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.joystick.JoystickShooting;
import com.mygdx.game.screens.GameScreen;

public class Bullet extends Actor {
    private float speed;
    private float angle;
    private float x;
    private float y;
    private float width;
    private float height;
    private Texture texture;
    private float being;
    private JoystickShooting joystickShooting;

    public Bullet(Texture texture, float originX, float originY, JoystickShooting joystickShooting){
        this.texture = texture;
        speed = 20;
        this.joystickShooting = joystickShooting;
        angle = joystickShooting.getRotation();
        width = 10;
        height = 18;

        setX(originX-width/2);
        setY(originY-height/2);
        setWidth(width);
        setHeight(height);
        setOriginX(width/2);
        setOriginY(height/2);

        being = 0;

        float length = (float) Math.sqrt(joystickShooting.getValueX() * joystickShooting.getValueX() +
                joystickShooting.getValueY() * joystickShooting.getValueY());
        float k = 1 / length;
        x = joystickShooting.getValueX() * k * speed;
        y = joystickShooting.getValueY() * k * speed;

        //debug();
    }

    public void draw(Batch batch, float parentAlpha) {
        flyBullet();
        batch.draw(texture, this.getX(), this.getY(), this.width/2, this.height/2, this.width, this.height,
                1, 1, angle, 0, 0, 10, 18, true, true);

    }

    public void flyBullet(){
        if (GameScreen.gameState) {
            if (++being == 100) bullestDispose(this);
            this.setPosition(getX() + x, getY() + y);
            this.setRotation(angle);
        }
    }

    public static void bullestDispose(Bullet bullet){
        bullet.setVisible(false);
        bullet.clear();
        bullet.remove();
    }
}
