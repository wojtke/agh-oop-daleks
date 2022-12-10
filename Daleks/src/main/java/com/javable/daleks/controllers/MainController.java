package com.javable.daleks.controllers;

import com.javable.daleks.Settings;
import com.javable.daleks.interfaces.IController;
import com.javable.daleks.interfaces.IControllerFxmlBased;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class MainController implements IControllerFxmlBased {
    @FXML
    private Label welcomeText;

    @FXML
    protected void NewGameBtn() throws IOException {
        //welcomeText.setText("Welcome to JavaFX Application!");
        GameController gameController = new GameController();
        gameController.InitView();
    }

    @Override
    public String GetViewPath() {
        return Settings.MainView;
    }
}