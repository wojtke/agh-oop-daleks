package com.javable.daleks.controllers;

import com.javable.daleks.DaleksApp;
import com.javable.daleks.Settings;
import com.javable.daleks.interfaces.iController;
import com.javable.daleks.logic.ViewHelper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.io.IOException;

public class MainController implements iController {
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