package com.mygdx.game.joystick;

import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class Joystick extends Actor {
    public abstract void changeKnob(float x, float y);
    public abstract void setTouched();
    public abstract void setUntouched();
    public abstract void setCenterPosition(float x, float y);
    public abstract void setDefaultPosition();

    public abstract boolean isJoystickDown();
    public abstract float getValueX();
    public abstract float getValueY();
    public abstract float getRotation();
    public abstract float getLength();
    public abstract float getRadiusCircle();

    public float findAngle(float dx, float dy) {
        float angle = (float) (Math.atan2(dx, dy) * 180 / Math.PI);
        if (angle < 90) {
            angle = 90 - angle;
            angle -= 270;
        } else if (angle < 180) {
            angle = 270 - angle;
            angle += 270;
        }
        return angle;
    }
}
