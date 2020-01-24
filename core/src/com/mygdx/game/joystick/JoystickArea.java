package com.mygdx.game.joystick;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class JoystickArea extends Actor {

    private class AreaListener extends InputListener {
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            setTouched();
            downX = x;
            downY = y;
            draggedX = x;
            draggedY = y;
            return true;
        }

        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            joystick.setUntouched();
            joystick.setDefaultPosition();
            setUntouched();
        }

        public void touchDragged(InputEvent event, float x, float y, int pointer) {
            draggedX = x;
            draggedY = y;
            if (joystick instanceof JoystickMove && joystick.getLength() > joystick.getRadiusCircle()) {
                downX += joystick.getValueX() * 3 * joystick.getLength() / joystick.getRadiusCircle();
                downY += joystick.getValueY() * 3 * joystick.getLength() / joystick.getRadiusCircle();
            }
        }
    }

    private Joystick joystick;

    boolean joystickAreaDown;
    float downX;
    float downY;
    float draggedX;
    float draggedY;

    public JoystickArea(Joystick joystick, float x) {
        this.joystick = joystick;

        setX(x);
        setY(5);
        setWidth(500);
        setHeight(600);

        joystickAreaDown = false;

        addListener(new AreaListener());

        //debug();
    }

    public void setTouched() {
        joystickAreaDown = true;
    }

    public void setUntouched() {
        joystickAreaDown = false;
    }

    public boolean isJoystickAreaDown() {
        return joystickAreaDown;
    }

    public float getDownX() {
        return downX;
    }

    public float getDownY() {
        return downY;
    }

    public float getDraggedX() {
        return draggedX;
    }

    public float getDraggedY() {
        return draggedY;
    }
}
