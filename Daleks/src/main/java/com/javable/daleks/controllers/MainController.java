package com.javable.daleks.controllers;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.javable.daleks.Settings;
import com.javable.daleks.interfaces.IControllerFxmlBased;
import com.javable.daleks.logic.ViewManager;
import com.javable.daleks.models.GameMapSettings;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class MainController implements IControllerFxmlBased {
    @FXML
    private TextField daleksCountInput, mapSizeInput;

    @FXML
    private Text errorText;

    private GameMapSettings parseInput() { // TODO zastąpić exception pustym optionallem i setText
        int map_size = Integer.parseInt(mapSizeInput.getText());
        int daleks_count = Integer.parseInt(daleksCountInput.getText());

        if (daleks_count < 1)
            throw new IllegalArgumentException("Daleks count must be greater than 0");

        if (map_size < 3)
            throw new IllegalArgumentException("Map size must be greater than 2");

        if (daleks_count > map_size * map_size - 1)
            throw new IllegalArgumentException("Daleks count too large");

        return new GameMapSettings(map_size, daleks_count, "name_placeholder"); // TODO wymagać imienia
    }

    @FXML
    protected void NewGameBtn() {
        try {
            GameMapSettings settings = parseInput();
            startGame(settings);
        } catch (IllegalArgumentException e) {
            errorText.setText(e.getMessage());
        }
    }

    public void startGame(GameMapSettings settings) {
        Injector injector = Guice.createInjector(settings);
        GameController gameController = injector.getInstance(GameController.class);
        gameController.InitView();
    }

    @Override
    public String GetViewPath() {
        return Settings.MainView;
    }

    public void LevelSelectBtn() {
        ViewManager.SetScene(Settings.LevelSelectView);
    }
}