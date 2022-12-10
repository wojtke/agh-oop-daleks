package com.javable.daleks.controllers;

import com.javable.daleks.logic.ImageLoader;
import com.javable.daleks.Settings;
import com.javable.daleks.enums.EDirection;
import com.javable.daleks.interfaces.IController;
import com.javable.daleks.logic.GridManager;
import com.javable.daleks.logic.ViewManager;
import com.javable.daleks.models.GameMap;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import java.io.FileNotFoundException;
import java.io.IOException;

public class GameController implements IController {
    //TODO private final ImageLoader ImageLoader = new ImageLoader();
    private final com.javable.daleks.models.GameMap GameMap = new GameMap();
    public final Button UpBtn = new Button(), DownBtn = new Button(), LeftBtn = new Button(), RightBtn = new Button();
    public final GridPane GameGrid;
    public final BorderPane borderPane;
    public final Label[][] Cells = new Label[Settings.GridCount][];

    public GameController() throws FileNotFoundException {
        GameGrid = new GridPane();
        GameGrid.setAlignment(Pos.CENTER);
        GameGrid.setPadding(new Insets(10, 10, 10, 10));
        GameGrid.setGridLinesVisible(true);

        for (int i = 0; i < Settings.GridCount; i++) {
            GameGrid.getColumnConstraints().add(new ColumnConstraints(Settings.GridSize));
            Cells[i] = new Label[Settings.GridCount];

            for (int j = 0; j < Settings.GridCount; j++) {
                if (i == 0)
                    GameGrid.getRowConstraints().add(new RowConstraints(Settings.GridSize));
                Cells[i][j] = new Label("___");
                GameGrid.add(Cells[i][j], i, j);
                GridPane.setHalignment(Cells[i][j], HPos.CENTER);
            }
        }

        Cells[GameMap.Player.position.x][GameMap.Player.position.x].setText("😘");

        for (int i = 0; i < Settings.DaleksCount; i++) {
            // TODO init daleks (👻)
        }

        UpBtn.setText("⬆");
        UpBtn.setOnAction(this::MoveUpBtn);
        DownBtn.setText("⬇");
        DownBtn.setOnAction(this::MoveDownBtn);
        LeftBtn.setText("⬅");
        LeftBtn.setOnAction(this::MoveLeftBtn);
        RightBtn.setText("➡");
        RightBtn.setOnAction(this::MoveRightBtn);

        HBox bottomPanel = new HBox();
        bottomPanel.getChildren().addAll(UpBtn, DownBtn, LeftBtn, RightBtn);
        bottomPanel.setPadding(new Insets(10, 10, 10, 10));

        borderPane = new BorderPane();
        borderPane.setCenter(GameGrid);
        borderPane.setBottom(bottomPanel);
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

    private void MoveUpBtn(ActionEvent x) {
        MovePlayer(EDirection.Top);
    }

    private void MoveRightBtn(ActionEvent x) {
        MovePlayer(EDirection.Right);
    }

    private void MoveDownBtn(ActionEvent x) {
        MovePlayer(EDirection.Bottom);
    }

    private void MoveLeftBtn(ActionEvent x) {
        MovePlayer(EDirection.Left);
    }

    private void MovePlayer(EDirection direction) {
        if (GameMap.Player.canMove(direction))
            GridManager.FrameUpdate(Cells, GameMap, direction);
    }
}
