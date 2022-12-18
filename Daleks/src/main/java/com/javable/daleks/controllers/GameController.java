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
    public final GridPane GameGrid;
    public final BorderPane BorderPane;

    @Inject
    public GameController(RandomGameMapFactory factory) throws FileNotFoundException {
        com.javable.daleks.models.GameMap gameMap = factory.Create();
        GameGrid = new GridPane();
        BorderPane = new BorderPane();
        BorderPane.setCenter(GameGrid);
        GridManager gridManager = new GridManager(GameGrid, gameMap);

        MoveHandler moveHandler = new MoveHandler(gameMap, gridManager);

        InputHandler inputHandler = new InputHandler(moveHandler);
        GameGrid.setOnMouseClicked(
                event -> inputHandler.ClickGrid(this.GameGrid, event.getTarget()));
    }


    @Override
    public void InitView() {
        Scene scene = new Scene(BorderPane,
                Settings.WindowWidth,
                Settings.WindowHeight);
        scene.getRoot().setStyle("-fx-base:black");
        ViewManager.SetScene(scene);
    }
}
