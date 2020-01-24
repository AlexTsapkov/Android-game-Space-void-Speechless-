package com.mygdx.game.shooting;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.mygdx.game.gameEvents.Collision;
import com.mygdx.game.joystick.JoystickShooting;
import com.mygdx.game.shattle.Shattle;

import java.util.ArrayList;

public class BulletGroup extends Group {
    private Texture bullet;
    private float rotation;
    private ArrayList<Bullet> arr;
    private Shattle shattle;
    private int f;
    private JoystickShooting joystickShooting;
    private Collision collision;

    public BulletGroup(Shattle shattle, JoystickShooting joystickShooting, Collision collision) {
        bullet = new Texture("bullet.png");
        this.shattle = shattle;
        f = 0;
        arr = new ArrayList();
        this.joystickShooting = joystickShooting;
        rotation = joystickShooting.getRotation();
        this.collision = collision;

        debug();
    }

    public void shooting() {
        if (joystickShooting.isJoystickDown()) {
            if (f == 10) {
                arr.add(new Bullet(bullet, shattle.getPosX() + shattle.getWidth() / 2,
                        shattle.getPosY() + shattle.getHeight() / 2, joystickShooting));
                for (int i = 0; i < arr.size(); i++) {
                    addActor(arr.get(i));
                }
                f = 0;
                hit();
            }
            f++;
        } else {
            arr.clear();
        }
    }

    private void hit(){
        for (int i = 0; i < arr.size(); i++){
            float bulletX = arr.get(i).getX();
            float bulletY = arr.get(i).getY();
            float bulletWidth = arr.get(i).getWidth();
            float bulletHeight = arr.get(i).getHeight();
            for (int j = 0; j < collision.getArrayListBots().size(); j++){
                float botX = collision.getArrayListBots().get(j).getX();
                float botY = collision.getArrayListBots().get(j).getY();
                float botWidth = collision.getArrayListBots().get(j).getY();
                float botHeight = collision.getArrayListBots().get(j).getY();
//                if (bulletX + bulletWidth >=botX && bulletX+bulletWidth<=botX+botWidth &&
//                bulletY+bulletHeight>=botY && bulletY+bulletHeight<=botY+botHeight ||
//                bulletX>=botX && bulletX<=botX+botWidth && bulletY>=botY && bulletY<= botY + botHeight) {
                if (bulletX >= botX - bulletWidth && bulletY >= botY - bulletHeight &&
                bulletX <= botX + botWidth && bulletY <= botY + botHeight) {
                    Bullet.bullestDispose(arr.get(i));
                    arr.remove(i);
                    --i;
                    //collision.getArrayListBots().get(j).setHitPoints(0);
                    collision.getArrayListBots().get(j).setHitPoints(collision.getArrayListBots().get(j).getHitPoints() - 20);
                    break;
                }
            }
        }
    }
}
