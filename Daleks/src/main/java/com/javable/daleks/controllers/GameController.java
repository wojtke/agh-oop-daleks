package com.javable.daleks.controllers;

import com.google.inject.Inject;
import com.javable.daleks.Settings;
import com.javable.daleks.interfaces.IController;
import com.javable.daleks.logic.*;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.FileNotFoundException;
import java.io.IOException;

public class GameController implements IController {
    public final GridPane gameGrid;
    public final BorderPane borderPane;
    private final com.javable.daleks.models.GameMap gameMap;
    private final GridManager gridManager;

    @Inject
    public GameController(RandomGameMapFactory factory) throws FileNotFoundException {
        gameMap = factory.create();
        gameGrid = new GridPane();
        borderPane = new BorderPane();
        borderPane.setCenter(gameGrid);
        gridManager = new GridManager(gameGrid, gameMap);

        MoveHandler moveHandler = new MoveHandler(gameMap, gridManager);

        InputHandler inputHandler = new InputHandler(moveHandler);
        gameGrid.setOnMouseClicked(event -> {
            inputHandler.clickGrid(this.gridManager, this.gameGrid, event.getTarget());
        });
    }


    @Override
    public void InitView() {
        Scene scene = new Scene(borderPane,
                Settings.WindowWidth,
                Settings.WindowHeight);
        scene.getRoot().setStyle("-fx-base:black");
        ViewManager.SetScene(scene);
    }

    private void GameOver() throws IOException { //ActionEvent actionEvent) {
        GameOverController gameOverController = new GameOverController();
        gameOverController.InitView();
    }
}
