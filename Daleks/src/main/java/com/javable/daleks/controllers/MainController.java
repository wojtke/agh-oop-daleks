package com.javable.daleks.controllers;

import com.javable.daleks.Settings;
import com.javable.daleks.interfaces.IControllerFxmlBased;
import com.javable.daleks.logic.ViewManager;
import com.javable.daleks.models.Level;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;

public class MainController implements IControllerFxmlBased {
    @FXML
    private TextField daleksCountInput, mapSizeInput;

    @FXML
    private Text errorText;

    private Level parseInput() { // TODO zastąpić exception pustym optionallem i setText
        int map_size = Integer.parseInt(mapSizeInput.getText());
        int daleks_count = Integer.parseInt(daleksCountInput.getText());

        if (daleks_count < 1)
            throw new IllegalArgumentException("Daleks count must be greater than 0");

        if (map_size < 3)
            throw new IllegalArgumentException("Map size must be greater than 2");

        if (daleks_count > map_size * map_size - 1)
            throw new IllegalArgumentException("Daleks count too large");

        return new Level(map_size, daleks_count, 3, 3, "");
    }

    @FXML
    protected void newGameBtn() {
        try {
            Level settings = parseInput();
            startGame(settings);
        } catch (IllegalArgumentException | FileNotFoundException e) {
            errorText.setText(e.getMessage());
        }
    }

    public void startGame(Level level) throws FileNotFoundException {
        GameController gameController = new GameController(level);
        gameController.initView();
    }

    @Override
    public String getViewPath() {
        return Settings.MainView;
    }

    public void levelSelectBtn() {
        ViewManager.setScene(Settings.LevelSelectView);
    }

    public void campaignBtn() {
        ViewManager.setScene(Settings.CampaignView);
    }
}