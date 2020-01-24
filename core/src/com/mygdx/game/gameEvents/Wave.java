package com.mygdx.game.gameEvents;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.TimeUtils;

public class Wave extends Group {
    private int number;
    private Collision collision;
    private long startTime;
    private int time;
    private int quantity;
    private int limit;

    public Wave(int number, Collision collision) {
        this.number = number;
        this.collision = collision;
        startTime = TimeUtils.millis();
        time = 0;
        quantity = 0;
        limit = number * 5;

        start();
    }

    public void start() {
        if (number < 5) {
            collision.botsGenration(number);
            quantity += number;
        } else {
            collision.botsGenration(5);
            quantity += 5;
        }
    }

    public void allObliterated() {
        if (collision.isEmpty()) {
            collision.botsGenration(2);
            quantity += 2;
        }
    }

    public void botGeneration() {
        if (++time % 200 == 0) {
            if (number < 5) {
                collision.botsGenration(number);
                quantity += number;
            } else {
                collision.botsGenration(5);
                quantity += 5;
            }
        }
    }

    public boolean end() {
        if (quantity >= limit) {
            clear();
            remove();
            return true;
        }
        return false;
    }
}
