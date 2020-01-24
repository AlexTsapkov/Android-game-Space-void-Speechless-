package com.mygdx.game.joystick;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class JoystickShooting extends Joystick {
    private Texture circle;
    private Texture knob;
    private float radiusCircle;
    private float inverseRadiusCircle;
    private float radiusKnob;
    private boolean joystickDown;

    private float touchScreenX;
    private float touchScreenY;
    private float valueX;
    private float valueY;
    private float length;

    private float rotation;

    private float x;
    private float y;

    public JoystickShooting() {
        circle = new Texture("circleShooting.png");
        knob = new Texture("knobShooting.png");

        x = 975;
        y = 100;

        setX(x);
        setY(y);
        setWidth(200);
        setHeight(200);

        radiusCircle = this.getWidth() / 2;
        inverseRadiusCircle = 1 / radiusCircle;
        radiusKnob = 100;

        joystickDown = false;

        touchScreenX = 0;
        touchScreenY = 0;
        valueX = 0;
        valueY = 0;

        rotation = 180;

        addListener(new JoystickInputListener(this));

        //debug();
    }

    public void draw(Batch batch, float parentAlpha) {
        batch.draw(circle, this.getX(), this.getY(), this.getWidth(), this.getHeight());
        if (joystickDown) {
            batch.draw(knob,
                    this.getX() + radiusCircle - radiusKnob + touchScreenX,
                    this.getY() + radiusCircle - radiusKnob + touchScreenY,
                    radiusKnob * 2,
                    radiusKnob * 2);
        } else {
            batch.draw(knob,
                    this.getX() + radiusCircle - radiusKnob,
                    this.getY() + radiusCircle - radiusKnob,
                    radiusKnob * 2,
                    radiusKnob * 2);
        }
    }

    @Override
    public Actor hit(float x, float y, boolean touchable) {
        Actor actor = super.hit(x, y, touchable);
        if (actor == null) {
            return null;
        } else {
            float dx = x - radiusCircle;
            float dy = y - radiusCircle;
            return (dx * dx + dy * dy <= radiusCircle * radiusCircle) ? this : null;
        }
    }

    public void changeKnob(float x, float y) {
        float dx = x - radiusCircle;
        float dy = y - radiusCircle;
        length = (float) Math.sqrt(dx * dx + dy * dy);
        if (length < radiusCircle) {
            this.touchScreenX = dx;
            this.touchScreenY = dy;
        } else {
            float k = radiusCircle / length;
            this.touchScreenX = dx * k;
            this.touchScreenY = dy * k;
        }
        valueX = this.touchScreenX * inverseRadiusCircle;
        valueY = this.touchScreenY * inverseRadiusCircle;

        rotation = findAngle(dx, dy);
    }

    public void setTouched() {
        joystickDown = true;
    }

    public void setUntouched() {
        joystickDown = false;
    }

    public void setCenterPosition(float x, float y) {
        setPosition(x - radiusCircle, y - radiusCircle);
    }

    public void setDefaultPosition() {
        setPosition(975, 100);
    }

    public boolean isJoystickDown() {
        return joystickDown;
    }
    public float getValueX() {
        return valueX;
    }
    public float getValueY() {
        return valueY;
    }
    public float getRotation() {
        return rotation;
    }
    public float getLength() {
        return length;
    }
    public float getRadiusCircle() {
        return radiusCircle;
    }
}
