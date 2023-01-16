package com.javable.daleks.controllers;

import com.google.inject.Inject;
import com.javable.daleks.Settings;
import com.javable.daleks.interfaces.IController;
import com.javable.daleks.logic.*;
import com.javable.daleks.models.GameMap;
import com.javable.daleks.models.Level;
import com.javable.daleks.models.Position;
import com.javable.daleks.models.objects.Player;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.FileNotFoundException;

public class GameController implements IController {
    private final GridPane gameGrid;
    private final BorderPane borderPane;

    public GameController(int mapSize, int daleksCount, int attractorsCount, int teleportersCount) throws FileNotFoundException {
        GameMap gameMap = new GameMap(mapSize, new Player(new Position(mapSize)));

        gameGrid = new GridPane();
        borderPane = new BorderPane();
        borderPane.setCenter(gameGrid);
        GridManager gridManager = new GridManager(gameGrid, gameMap);

        MoveHandler moveHandler = new MoveHandler(gameMap, gridManager);
        RandomGameMapFactory factory = new RandomGameMapFactory(daleksCount, attractorsCount, teleportersCount, mapSize, gameMap);
        factory.setMoveHandler(moveHandler);
        factory.generate();
        gridManager.repaint();

        InputHandler inputHandler = new InputHandler(moveHandler);
        gameGrid.setOnMouseClicked(
                event -> inputHandler.clickGrid(this.gameGrid, event.getTarget()));
    }

    public GameController(Level level) throws FileNotFoundException {
        int mapSize = level.getGridSize();
        int daleksCount = level.getDaleksCount();
        int attractorsCount = level.getAttractorsCount();
        int teleportersCount = level.getTeleportersCount();
        GameMap gameMap = new GameMap(mapSize, new Player(new Position(mapSize)));

        gameGrid = new GridPane();
        borderPane = new BorderPane();
        borderPane.setCenter(gameGrid);
        GridManager gridManager = new GridManager(gameGrid, gameMap);

        MoveHandler moveHandler = new MoveHandler(gameMap, gridManager);
        RandomGameMapFactory factory = new RandomGameMapFactory(daleksCount, attractorsCount, teleportersCount, mapSize, gameMap);
        factory.setMoveHandler(moveHandler);
        factory.generate();
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
