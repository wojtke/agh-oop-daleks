package com.javable.daleks.controllers;

import com.javable.daleks.Settings;
import com.javable.daleks.interfaces.IController;
import com.javable.daleks.logic.InputHandler;
import com.javable.daleks.logic.MoveHandler;
import com.javable.daleks.logic.RandomGameMapFactory;
import com.javable.daleks.logic.ViewManager;
import com.javable.daleks.models.GameMap;
import com.javable.daleks.models.Level;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.FileNotFoundException;

public class GameController implements IController {
    private final GridPane gameGrid;
    private final BorderPane borderPane;

    /*public GameController(Level level) throws FileNotFoundException {
        GameMap gameMap = new GameMap(level);

        gameGrid = new GridPane();
        borderPane = new BorderPane();
        borderPane.setCenter(gameGrid);
        GridManager gridManager = new GridManager(gameGrid, gameMap);

        MoveHandler moveHandler = new MoveHandler(gameMap, gridManager);
        RandomGameMapFactory factory = new RandomGameMapFactory(gameMap);
        //todo factory.setMoveHandler(moveHandler);
        factory.generate();
        gridManager.repaint();

        InputHandler inputHandler = new InputHandler(moveHandler);
        gameGrid.setOnMouseClicked(
                event -> inputHandler.clickGrid(this.gameGrid, event.getTarget()));
    }*/

    public GameController(Level level) throws FileNotFoundException {
        GameMap gameMap = new GameMap(level);

        gameGrid = new GridPane();
        borderPane = new BorderPane();
        borderPane.setCenter(gameGrid);
        GridManager gridManager = new GridManager(gameGrid, gameMap);

        MoveHandler moveHandler = new MoveHandler(gameMap, gridManager);
        RandomGameMapFactory factory = new RandomGameMapFactory(gameMap);
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
