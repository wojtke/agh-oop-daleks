package com.javable.daleks.controllers;

import com.javable.daleks.Settings;
import com.javable.daleks.enums.EDirection;
import com.javable.daleks.enums.EObjectType;
import com.javable.daleks.logic.ImageLoader;
import com.javable.daleks.models.GameMap;
import com.javable.daleks.models.Position;
import com.javable.daleks.models.objects.Dalek;
import com.javable.daleks.models.objects.Scrap;
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


    public GridManager(GridPane gameGrid, GameMap gameMap) throws FileNotFoundException {
        this.gameGrid = gameGrid;
        gameGrid.setAlignment(Pos.CENTER);
        gameGrid.setPadding(new Insets(10, 10, 10, 10));
        gameGrid.setGridLinesVisible(true);

        cells = new ImageView[gameMap.gridCount][];
        gridSize = (Settings.WindowHeight-50)/ gameMap.gridCount;
        map = gameMap;

        this.initialize();
    }

    public void repaint() {
        clear();
        cells[map.player.Position.x][map.player.Position.y]
                .setImage(imageLoader.GetImage(EObjectType.PLAYER));

        for (EDirection direction : EDirection.values()) {
            Position playerPosition = map.player.Position;
            Position newPosition = playerPosition.add(direction.toVector());

            if (map.isInBounds(newPosition) && map.GetObjectAtCell(newPosition).isEmpty()) {
                ImageView current = cells[newPosition.x][newPosition.y];
                Effect effect = new ColorAdjust(1, 1, 0.5, 0.5);
                current.setEffect(effect);
            }

        }
        for (Dalek dalek : map.daleks)
            cells[dalek.Position.x][dalek.Position.y]
                    .setImage(imageLoader.GetImage(EObjectType.DALEK));

        for (Scrap scrap : map.scraps)
            cells[scrap.Position.x][scrap.Position.y]
                    .setImage(imageLoader.GetImage(EObjectType.SCRAP));
    }

    public void clear() {
        for (int i = 0; i < map.gridCount; i++) {
            for (int j = 0; j < map.gridCount; j++) {
                cells[i][j].setImage(imageLoader.GetImage(EObjectType.EMPTY));
                cells[i][j].setEffect(null);
            }
        }
    }

    public void initialize() {

        for (int i = 0; i < map.gridCount; i++) {
            gameGrid.getColumnConstraints().add(new ColumnConstraints(gridSize));
            cells[i] = new ImageView[map.gridCount];


            for (int j = 0; j < map.gridCount; j++) {
                if (i == 0)
                    gameGrid.getRowConstraints().add(new RowConstraints(gridSize));

                cells[i][j] = new ImageView(imageLoader.GetImage(EObjectType.EMPTY));
                cells[i][j].setFitHeight(gridSize-1);
                cells[i][j].setPreserveRatio(true);
                gameGrid.add(cells[i][j], i, j);
                GridPane.setHalignment(cells[i][j], HPos.CENTER);
            }
        }
        this.repaint();
    }
}
