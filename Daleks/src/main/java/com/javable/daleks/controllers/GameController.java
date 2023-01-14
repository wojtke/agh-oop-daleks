package com.javable.daleks.controllers;

import com.google.inject.Inject;
import com.javable.daleks.Settings;
import com.javable.daleks.interfaces.IController;
import com.javable.daleks.logic.*;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.FileNotFoundException;

public class GameController implements IController {
    private final GridPane gameGrid;
    private final BorderPane borderPane;

    @Inject
    public GameController(RandomGameMapFactory factory) throws FileNotFoundException {
        com.javable.daleks.models.GameMap gameMap = factory.create();
        gameGrid = new GridPane();
        borderPane = new BorderPane();
        borderPane.setCenter(gameGrid);
        GridManager gridManager = new GridManager(gameGrid, gameMap);

        MoveHandler moveHandler = new MoveHandler(gameMap, gridManager);

        InputHandler inputHandler = new InputHandler(moveHandler);
        gameGrid.setOnMouseClicked(
                event -> inputHandler.clickGrid(this.gameGrid, event.getTarget()));
    }


    public void initView() {
        Scene scene = new Scene(borderPane,
                Settings.WindowWidth,
                Settings.WindowHeight);
        scene.getRoot().setStyle("-fx-base:black");
        ViewManager.setScene(scene);
    }
}
