package com.javable.daleks.controllers;

import com.javable.daleks.Settings;
import com.javable.daleks.interfaces.iController;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public class GameOverController implements iController {
    public Label welcomeText;

    @Override
    public String GetViewPath() {
        return Settings.GameOverView;
    }

    public void ReturnToMenuBtn() { // ActionEvent actionEvent) {

    }
}
