package com.javable.daleks.controllers;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.javable.daleks.Settings;
import com.javable.daleks.interfaces.IControllerFxmlBased;
import com.javable.daleks.models.GameMapSettings;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class MainController implements IControllerFxmlBased {

    @FXML
    private TextField daleks_count_input, map_size_input;

    @FXML
    private Text error_text;

    private GameMapSettings parseInput() {
        int map_size = Integer.parseInt(map_size_input.getText());
        int daleks_count = Integer.parseInt(daleks_count_input.getText());

        if (daleks_count < 1) {
            throw new IllegalArgumentException("Daleks count must be greater than 0");
        }

        if (map_size < 3) {
            throw new IllegalArgumentException("Map size must be greater than 2");
        }

        if (daleks_count > map_size * map_size - 1) {
            throw new IllegalArgumentException("Daleks count too large");
        }

        return new GameMapSettings(
                map_size,
                daleks_count
        );
    }

    @FXML
    protected void NewGameBtn() {
        try {
            GameMapSettings settings = parseInput();
            Injector injector = Guice.createInjector(settings);
            GameController gameController = injector.getInstance(GameController.class);
            gameController.InitView();
        } catch (IllegalArgumentException e) {
            error_text.setText(e.getMessage());
        }

    }

    @Override
    public String GetViewPath() {
        return Settings.MainView;
    }
}