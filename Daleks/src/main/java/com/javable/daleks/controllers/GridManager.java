package com.javable.daleks.controllers;

import com.javable.daleks.Settings;
import com.javable.daleks.enums.EDirection;
import com.javable.daleks.logic.ImageLoader;
import com.javable.daleks.models.GameMap;
import com.javable.daleks.models.Position;
import com.javable.daleks.models.objects.ObjectBase;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.io.FileNotFoundException;

public class GridManager {
    private final ImageLoader imageLoader = new ImageLoader();
    private final ImageView[][] cells;
    private final GameMap map;
    private final GridPane gameGrid;
    private final int gridSize;


    public GridManager(GridPane gameGrid, GameMap gameMap) {
        this.gameGrid = gameGrid;
        gameGrid.setAlignment(Pos.CENTER);
        gameGrid.setPadding(new Insets(10, 10, 10, 10));
        gameGrid.setGridLinesVisible(true);

        cells = new ImageView[gameMap.getGridSize()][];
        gridSize = (Settings.WindowHeight-50)/ gameMap.getGridSize();
        map = gameMap;

        this.initialize();
    }

    public void repaint() {
        clear();
        cells[map.getPlayer().position.x][map.getPlayer().position.y]
                .setImage(imageLoader.getPlayerImage());

        for (EDirection direction : EDirection.values()) {
            Position playerPosition = map.getPlayer().position;
            Position newPosition = playerPosition.add(direction.toVector());

            if (map.isInBounds(newPosition) && map.getObjectAtCell(newPosition).isEmpty()) {
                ImageView current = cells[newPosition.x][newPosition.y];
                Effect effect = new ColorAdjust(1, 1, 0.5, 0.5);
                current.setEffect(effect);
            }

        }
        for (ObjectBase object : map.getObjects()){
            cells[object.position.x][object.position.y]
                    .setImage(object.getImage(imageLoader));

            object.getEffect(imageLoader).ifPresent(
                    value -> cells[object.position.x][object.position.y].setEffect(value));

        }
    }

    public void clear() {
        for (int i = 0; i < map.getGridSize(); i++) {
            for (int j = 0; j < map.getGridSize(); j++) {
                cells[i][j].setImage(imageLoader.getEmptyImage());
                cells[i][j].setEffect(null);
            }
        }
    }

    public void initialize() {

        for (int i = 0; i < map.getGridSize(); i++) {
            gameGrid.getColumnConstraints().add(new ColumnConstraints(gridSize));
            cells[i] = new ImageView[map.getGridSize()];


            for (int j = 0; j < map.getGridSize(); j++) {
                if (i == 0)
                    gameGrid.getRowConstraints().add(new RowConstraints(gridSize));

                cells[i][j] = new ImageView(imageLoader.getEmptyImage());
                cells[i][j].setFitHeight(gridSize-1);
                cells[i][j].setPreserveRatio(true);
                gameGrid.add(cells[i][j], i, j);
                GridPane.setHalignment(cells[i][j], HPos.CENTER);
            }
        }
        this.repaint();
    }
}
