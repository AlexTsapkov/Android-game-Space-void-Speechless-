package com.mygdx.game.gameEvents;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.mygdx.game.attributes.GamePointCounter;
import com.mygdx.game.bots.Bot;
import com.mygdx.game.bots.HardBot;
import com.mygdx.game.bots.MediumBot;
import com.mygdx.game.bots.WeakBot;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.shattle.Shattle;

import java.util.ArrayList;

public class Collision extends Group {
    private Shattle shattle;
    private GamePointCounter gamePointCounter;
    private float shattleCenterX;
    private float shattleCenterY;

    private ArrayList<Bot> arrayListBots;
    private int quantityBots;

    public Collision(Shattle shattle, GamePointCounter gamePointCounter) {
        this.shattle = shattle;
        shattleCenterX = shattle.getX() + shattle.getWidth() / 2;
        shattleCenterY = shattle.getY() + shattle.getHeight() / 2;
        addActor(shattle);
        this.gamePointCounter = gamePointCounter;
        arrayListBots = new ArrayList<Bot>();
    }

    public void botsGenration(int quantity) {
        shattleCenterX = shattle.getX() + shattle.getWidth() / 2;
        shattleCenterY = shattle.getY() + shattle.getHeight() / 2;

        if (quantity < 0) quantity = 0;
        int side;
        float tempX;
        float tempY;

        there:
        for (int i = 0; i < quantity; i++) {
            side = (int) ((Math.random() * 4) + 1);
            switch (side) {
                case 1: {
                    tempY = shattleCenterY + 400;
                    tempX = shattleCenterX + (int) ((Math.random() * 1281) - 640);
                    break;
                }
                case 2: {
                    tempX = shattleCenterX + 700;
                    tempY = shattleCenterY + (int) ((Math.random() * 721) - 360);
                    break;
                }
                case 3: {
                    tempY = shattleCenterY - 470;
                    tempX = shattleCenterX + (int) ((Math.random() * 1281) - 640);
                    break;
                }
                default: {
                    tempX = shattleCenterX - 770;
                    tempY = shattleCenterY + (int) ((Math.random() * 721) - 360);
                    break;
                }
            }
            for (int j = 0; j < arrayListBots.size(); j++) {
                float x = arrayListBots.get(j).getX();
                float y = arrayListBots.get(j).getY();
                float width = arrayListBots.get(j).getWidth();
                if ((tempX >= x - 70 && tempX <= x + width) &&
                        (tempY >= y - 70 && tempY <= y + width)) {
                    i--;
                    continue there;
                }
            }
            ///////////////////////////////////////////
            side = (int) ((Math.random() * 10) + 1);
            Bot bot;
            if (side < 7) {
                bot = new WeakBot(shattle, tempX, tempY);
            } else if (side < 10) {
                bot = new MediumBot(shattle, tempX, tempY);
            } else {
                bot = new HardBot(shattle, tempX, tempY);
            }
            bot.setName("bot" + (++quantityBots));
            addActor(bot);
            arrayListBots.add(bot);
        }
    }

    public void check() {
        borders();
        lol:
        for (int i = 0; i < arrayListBots.size(); i++) {
            float firstX = arrayListBots.get(i).getX();
            float firstY = arrayListBots.get(i).getY();
            float width = arrayListBots.get(i).getWidth();
            for (int j = 0; j < arrayListBots.size(); j++) {
                if (i != j) {
//                    float secondX = arrayListBots.get(j).getX();
//                    float secondY = arrayListBots.get(j).getY();
                    //Касание с кораблем
                    if ((firstX >= shattle.getX() - 70 && firstX <= shattle.getX() + shattle.getWidth()) &&
                            (firstY >= shattle.getY() - 70 && firstY <= shattle.getY() + shattle.getWidth())) {
                        arrayListBots.get(i).setSpeed(0);
                        if (GameScreen.gameState) {
                            shattle.getProgressBarLife().setValue(shattle.getProgressBarLife().getValue() - 1);
                        }
                    } else {
                        if (arrayListBots.get(i).getSpeed() == 0) {
                            arrayListBots.get(i).setSpeed((float) ((Math.random() * 0.0151) + 0.005));
                        }
                    }
//                    Касание с другими ботами
//                    if ((firstX >= secondX - 70 && firstX <= secondX + width) &&
//                            (firstY >= secondY - 70 && firstY <= secondY + width)) {
//                        if (arrayListBots.get(j).getSpeed() != 0) {
//                            arrayListBots.get(i).setSpeed(0);
//                            continue lol;
//                        } else {
//                            arrayListBots.get(i).setSpeed((float) ((Math.random() * 0.0151) + 0.005));
//                        }
//                    } else {
//                        if (arrayListBots.get(i).getSpeed() == 0) {
//                            arrayListBots.get(i).setSpeed((float) ((Math.random() * 0.0151) + 0.005));
//                        }
//                    }
                }
            }
        }
    }

    private void borders() {
        for (int i = 0; i < arrayListBots.size(); i++) {
            arrayListBots.get(i).borders();
            botDeat();
        }
    }

    public void botDeat() {
        for (int i = 0; i < arrayListBots.size(); i++) {
            if (arrayListBots.get(i).getHitPoints() <= 0) {
                Bot.dead(arrayListBots.get(i));
                arrayListBots.remove(i);
                --i;
                gamePointCounter.setValue(gamePointCounter.getValue() + 500);
            }
        }
    }

    public boolean isEmpty() {
        if (arrayListBots.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Bot> getArrayListBots() {
        return arrayListBots;
    }
}

