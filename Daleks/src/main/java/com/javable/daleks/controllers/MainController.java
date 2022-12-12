package com.javable.daleks.controllers;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.javable.daleks.Settings;
import com.javable.daleks.interfaces.IControllerFxmlBased;
import com.javable.daleks.models.GameMapSettings;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class MainController implements IControllerFxmlBased {
    @FXML
    private TextField daleks_count_input, map_size_input;

    private GameMapSettings parseInput() {
        int daleks_count = Integer.parseInt(daleks_count_input.getText());
        int map_size = Integer.parseInt(map_size_input.getText());
        return new GameMapSettings(
                map_size,
                daleks_count
        );
    }

    @FXML
    protected void NewGameBtn() throws IOException {
        GameMapSettings settings = parseInput();
        Injector injector = Guice.createInjector(settings);
        GameController gameController = injector.getInstance(GameController.class);
        gameController.InitView();
    }

    @Override
    public String GetViewPath() {
        return Settings.MainView;
    }
}