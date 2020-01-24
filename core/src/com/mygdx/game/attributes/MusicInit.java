package com.mygdx.game.attributes;

public class MusicInit {
    public MusicInit music;
    private int random;
    private String chosenOne;

    public MusicInit() {

    }

    public String MusicInit(int x) {
        if (x == 1) {
            random = (int) (Math.random() * 2);
            if (random == 0) chosenOne = "music/MainMenu/Track1.ogg";
            else chosenOne = "music/MainMenu/Track2.ogg";
        } else {
            random = (int) (Math.random() * 6);
            switch (random) {
                case (0): {
                    chosenOne = "music/inGame/Track1.ogg";
                    break;
                }
                case (1): {
                    chosenOne = "music/inGame/Track2.ogg";
                    break;
                }
                case (2): {
                    chosenOne = "music/inGame/Track3.ogg";
                    break;
                }
                case (3): {
                    chosenOne = "music/inGame/Track4.ogg";
                    break;
                }
                case (4): {
                    chosenOne = "music/inGame/Track5.ogg";
                    break;
                }
                default: {
                    chosenOne = "music/inGame/Track6.ogg";
                    break;
                }
            }
        }
        return chosenOne;
    }
}
