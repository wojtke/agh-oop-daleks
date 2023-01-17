package com.javable.daleks.controllers;

import com.javable.daleks.DaleksApp;
import com.javable.daleks.Settings;
import com.javable.daleks.interfaces.IControllerFxmlBased;
import javafx.fxml.FXML;
import javafx.scene.image.Image;

import java.io.IOException;

public class GameOverController implements IControllerFxmlBased {
    @FXML
    public Image gameOverImage;

    @Override
    public String getViewPath() {
        return Settings.GameOverView;
    }

    public void returnToMenuBtn() throws IOException {
        DaleksApp.getMainController().initView();
    }
}

