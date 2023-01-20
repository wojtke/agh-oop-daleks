package com.javable.daleks.controllers;

import com.javable.daleks.Settings;
import com.javable.daleks.interfaces.IController;
import com.javable.daleks.logic.InputHandler;
import com.javable.daleks.logic.MoveHandler;
import com.javable.daleks.logic.ViewManager;
import com.javable.daleks.logic.gameMapFactory.CampaignGameMapFactory;
import com.javable.daleks.logic.gameMapFactory.RandomGameMapFactory;
import com.javable.daleks.models.GameMap;
import com.javable.daleks.models.Level;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class GameController implements IController {
    private final GridPane gameGrid;
    private final BorderPane borderPane;

    public static void startGame(Level level) {
        GameController gameController = new GameController(level);
        gameController.initView();
    }

    public GameController(Level level) {
        GameMap gameMap = new GameMap(level);

        gameGrid = new GridPane();
        borderPane = new BorderPane();
        borderPane.setCenter(gameGrid);
        GridManager gridManager = new GridManager(gameGrid, gameMap);

        MoveHandler moveHandler = new MoveHandler(gameMap, gridManager);

        if (level.isCampaign())
            new CampaignGameMapFactory(gameMap, moveHandler);
        else
            new RandomGameMapFactory(gameMap, moveHandler);

        gridManager.repaint();

        InputHandler inputHandler = new InputHandler(moveHandler);
        gameGrid.setOnMouseClicked(
                event -> inputHandler.clickGrid(this.gameGrid, event.getTarget()));
    }


    @Override
    public void initView() {
        Scene scene = new Scene(borderPane,
                Settings.WindowWidth,
                Settings.WindowHeight);
        scene.getRoot().setStyle("-fx-base:black");
        ViewManager.setScene(scene);
    }
}
