package com.mygdx.game.gameEvents;

import com.mygdx.game.attributes.WaveCounter;
import com.mygdx.game.screens.GameScreen;

public class WaveManagment{
    private WaveCounter waveCounter;
    private Collision collision;
    private Wave wave;

    public WaveManagment(WaveCounter waveCounter, Collision collision){
        this.waveCounter = waveCounter;
        this.collision = collision;

        wave = new Wave(waveCounter.getValue(), collision);
    }

    public void check(){
        if (GameScreen.gameState) {
            if (wave.end()) {
                waveCounter.setValue(waveCounter.getValue() + 1);
                wave = new Wave(waveCounter.getValue(), collision);
            } else {
                wave.allObliterated();
                wave.botGeneration();
            }
        }
    }
}
