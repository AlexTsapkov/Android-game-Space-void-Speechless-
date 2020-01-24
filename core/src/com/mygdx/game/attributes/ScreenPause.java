package com.mygdx.game.attributes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.MainMenuScreen;

public class ScreenPause extends Group {
    private GameScreen gameScreen;
    private Background backgroundPause;
    private Button buttonPauseOn;
    private Button buttonPauseOff;
    private Button buttonResume;
    private Button buttonMenu;
    private SettingsPanel settingsPanel;

    public ScreenPause(GameScreen gameScreen){
        this.gameScreen = gameScreen;

        backgroundPause = new Background(new Texture("backgroundPause.png"));
        backgroundPause.setVisible(false);
        buttonPauseOn = new Button(1220, 659, 40, 40, new Texture("buttonPause.png"),
                40, 40, new Texture("buttonPlayClick.png"));
        buttonPauseOff = new Button(1220, 659, 40, 40, new Texture("buttonResume.png"),
                40, 40, new Texture("buttonPlayClick.png"));
        buttonPauseOff.setVisible(false);
        buttonResume = new Button(280, 381, 145, 43, new Texture("buttonResumeWord.png"),
                234, 43, new Texture("buttonPlayClick.png"));
        buttonResume.setVisible(false);
        buttonMenu = new Button(280, 296, 100, 43, new Texture("buttonMenu.png"),
                234, 43, new Texture("buttonClick.png"));
        buttonMenu.setVisible(false);
        settingsPanel = new SettingsPanel(570, 210, gameScreen.music);
        settingsPanel.setVisible(false);

        addActor(backgroundPause);
        addActor(buttonPauseOn);
        addActor(buttonPauseOff);
        addActor(buttonResume);
        addActor(buttonMenu);
        addActor(settingsPanel);
    }

    private void buttonPauseOnClick() {
        if (buttonPauseOn.isButtonDown()) {
            buttonPauseOn.setVisible(false);
            buttonPauseOff.setVisible(true);
            buttonResume.setVisible(true);
            buttonMenu.setVisible(true);
            settingsPanel.setVisible(true);
            gameScreen.gameState = !gameScreen.gameState;
        }
    }

    private void buttonPauseOffClick() {
        if (buttonPauseOff.isButtonDown() || buttonResume.isButtonDown()) {
            buttonPauseOn.setVisible(true);
            buttonPauseOff.setVisible(false);
            buttonResume.setVisible(false);
            buttonMenu.setVisible(false);
            settingsPanel.setVisible(false);
            gameScreen.gameState = !gameScreen.gameState;
        }
    }

    private void buttonMenuClick(){
        if (buttonMenu.isButtonDown()){
            gameScreen.getGame().setScreen(new MainMenuScreen(gameScreen.getGame(), gameScreen.music, gameScreen.musicInit));
            gameScreen.dispose();
        }
    }

    public void included() {
        if (gameScreen.gameState) {
            buttonPauseOnClick();
            backgroundPause.setVisible(false);
        } else {
            buttonPauseOffClick();
            backgroundPause.setVisible(true);
        }
        buttonMenuClick();
    }
}
