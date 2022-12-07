package com.javable.daleks.controllers;

import com.javable.daleks.Settings;
import com.javable.daleks.interfaces.iController;
import javafx.scene.control.Label;
import kotlin.NotImplementedError;

import java.io.IOException;

public class GameController implements iController {
    public Label welcomeText;

    @Override
    public String GetViewPath() {
        return Settings.GameView;
    }

    public void GameOverBtn() throws IOException { //ActionEvent actionEvent) {
        // TODO return to menu (mainController)
        throw new NotImplementedError();
    }
}
