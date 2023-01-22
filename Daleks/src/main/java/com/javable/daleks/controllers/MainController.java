package com.javable.daleks.controllers;

import com.javable.daleks.DaleksApp;
import com.javable.daleks.Settings;
import com.javable.daleks.interfaces.IControllerFxmlBased;
import com.javable.daleks.logic.ViewManager;
import com.javable.daleks.models.Level;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.Optional;

public class MainController implements IControllerFxmlBased {
    @FXML
    private TextField daleksCountInput, mapSizeInput, attractorsCountInput, teleportersCountInput;

    @FXML
    private Text errorText;

    private Optional<Level> parseInput() {
        int map_size = Integer.parseInt(mapSizeInput.getText());
        int daleks_count = Integer.parseInt(daleksCountInput.getText());
        int attractorsCount = Integer.parseInt(attractorsCountInput.getText());
        int teleportersCount = Integer.parseInt(teleportersCountInput.getText());
        StringBuilder sb = new StringBuilder();

        if (daleks_count < 1)
            sb.append("Daleks count must be greater than 0\n");
        if (attractorsCount < 1)
            sb.append("Daleks count must be greater than 0\n");
        if (teleportersCount < 1)
            sb.append("Daleks count must be greater than 0\n");
        if (map_size < 3)
            sb.append("Map size must be greater than 2\n");
        if ((daleks_count + attractorsCount + teleportersCount) > map_size * map_size - 1)
            sb.append("Props count too large\n");

        if (sb.isEmpty())
            return Optional.of(new Level(map_size, daleks_count, attractorsCount, teleportersCount, ""));

        errorText.setText(sb.toString());
        return Optional.empty();
    }

    @FXML
    protected void newGameBtn() {
        Optional<Level> settings = parseInput();
        settings.ifPresent(GameController::startGame);
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

    public void exitBtn() {
        DaleksApp.QuitGame();
    }
}