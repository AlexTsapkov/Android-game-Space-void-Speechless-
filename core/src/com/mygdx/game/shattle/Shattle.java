package com.mygdx.game.shattle;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.attributes.ProgressBarLife;
import com.mygdx.game.joystick.JoystickMove;
import com.mygdx.game.joystick.JoystickShooting;

public class Shattle extends Actor {
    private Texture ship;
    private Texture gun;
    private final float SPEED;
    private float rotation;
    private float rotationGun;
    private float posX;
    private float posY;

    private JoystickMove joystickMove;
    private JoystickShooting joystickShooting;
    private ProgressBarLife progressBarLife;

    public Shattle(JoystickMove joystickMove, JoystickShooting joystickShooting, ProgressBarLife progressBarLife) {
        ship = new Texture("testShattle.png");
        gun = new Texture("testGun.png");
        posX = 3100;
        posY = 3100;
        SPEED = 15;
        rotation = 180;
        rotationGun = 180;

        setX(posX);
        setY(posY);
        setWidth(100);
        setHeight(100);

        this.joystickMove = joystickMove;
        this.joystickShooting = joystickShooting;
        this.progressBarLife = progressBarLife;

        //debug();
    }

    public void draw(Batch batch, float parentAlpha) {
        if (joystickMove.isJoystickDown()) {
            posX += joystickMove.getValueX() * SPEED;
            posY += joystickMove.getValueY() * SPEED;
            rotation = joystickMove.getRotation();
            rotationGun = rotation;
        }
            batch.draw(ship, posX, posY, this.getWidth() / 2, this.getHeight() / 2, this.getWidth(), this.getHeight(),
                    1, 1, rotation, 0, 0, 115, 115, true, true);


        if (joystickShooting.isJoystickDown()) {
            rotationGun = joystickShooting.getRotation();
        }
        batch.draw(gun, posX, posY, this.getWidth() / 2, this.getHeight() / 2, this.getWidth(), this.getHeight(),
                1, 1, rotationGun, 0, 0, 115, 115, true, true);

        setPosition(posX, posY);
        setOrigin(this.getWidth() / 2, this.getHeight() / 2);
        setRotation(rotation);

        borders();
    }

    private void borders(){
        if (posX < 0) progressBarLife.setValue(progressBarLife.getValue() - 1);
        if (posY < 0) progressBarLife.setValue(progressBarLife.getValue() - 1);
        if (posX + getWidth() > 6300) progressBarLife.setValue(progressBarLife.getValue() - 1);
        if (posY + getHeight() > 6300) progressBarLife.setValue(progressBarLife.getValue() - 1);
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    public ProgressBarLife getProgressBarLife() {
        return progressBarLife;
    }
}
