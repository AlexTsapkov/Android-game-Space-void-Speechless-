package com.mygdx.game.bots;

import com.badlogic.gdx.scenes.scene2d.Group;

public abstract class Bot extends Group {
    public abstract void borders();
    public abstract void setSpeed(float speed) ;
    public abstract float getSpeed() ;
    public abstract int getHitPoints();
    public abstract void setHitPoints(int hitPoints);

    public static void dead(Bot bot) {
        bot.setVisible(false);
        bot.clear();
        bot.remove();
    }

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
