package com.javable.daleks.controllers;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.javable.daleks.Settings;
import com.javable.daleks.interfaces.IControllerFxmlBased;
import com.javable.daleks.logic.MoveHandler;
import com.javable.daleks.logic.RandomGameMapFactory;
import com.javable.daleks.logic.ViewManager;
import com.javable.daleks.models.GameMap;
import com.javable.daleks.models.Level;
import com.javable.daleks.models.Position;
import com.javable.daleks.models.objects.Player;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;

public class MainController implements IControllerFxmlBased {
    @FXML
    private TextField daleksCountInput, mapSizeInput;

    @FXML
    private Text errorText;

    private void parseInput() { // TODO zastąpić exception pustym optionallem i setText
        int map_size = Integer.parseInt(mapSizeInput.getText());
        int daleks_count = Integer.parseInt(daleksCountInput.getText());

        if (daleks_count < 1)
            throw new IllegalArgumentException("Daleks count must be greater than 0");

        if (map_size < 3)
            throw new IllegalArgumentException("Map size must be greater than 2");

        if (daleks_count > map_size * map_size - 1)
            throw new IllegalArgumentException("Daleks count too large");

        GameMap gameMap = new GameMap(map_size, new Player(new Position(map_size)));
        RandomGameMapFactory factory = new RandomGameMapFactory(daleks_count, 3, 0, map_size, gameMap);
        factory.generate();
        try {
            GameController gameController = new GameController(daleks_count, map_size, 3, 3);
            gameController.initView();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void newGameBtn() {
        try {
            parseInput();
//            Level settings = parseInput();
//            startGame(settings);
        } catch (IllegalArgumentException e) {
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