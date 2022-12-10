package com.javable.daleks.controllers;

import com.javable.daleks.Settings;
import com.javable.daleks.enums.EDirection;
import com.javable.daleks.enums.EObjectType;
import com.javable.daleks.models.GameMap;
import com.javable.daleks.models.Position;
import com.javable.daleks.models.objects.Dalek;
import com.javable.daleks.models.objects.Scrap;
import javafx.geometry.HPos;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class GridManager {

    private final com.javable.daleks.logic.ImageLoader ImageLoader;
    private final ImageView[][] cells;
    private final GameMap map;
    private final GridPane GameGrid;


    public GridManager(com.javable.daleks.logic.ImageLoader imageLoader, ImageView[][] cells, GameMap gameMap, GridPane gameGrid) {
        ImageLoader = imageLoader;
        this.cells = cells;
        map = gameMap;
        GameGrid = gameGrid;
    }

//    public void FrameUpdate(EDirection direction) {
//        /*
//        The next time we update the grid we only care about what has changed
//         */
//        List<Position> updatedCells = map.NextFrame(direction);
//
//        for (Position cell : updatedCells) {
//            EObjectType objectAtOldPosition = map.GetObjectAtCell(cell);
//
//            Image image = ImageLoader.ToImage(objectAtOldPosition);
//
//            cells[cell.x][cell.y].setImage(image);
//        }
//    }
    public void repaint(){
        clear();
        cells[map.Player.position.x][map.Player.position.y].setImage(ImageLoader.ToImage(EObjectType.Player));

        for (EDirection direction: EDirection.values()) {
            Position playerPosition = map.Player.position;
            Position newPosition = Position.Move(playerPosition, direction);
            if(GameMap.IsInBounds(newPosition) && map.GetObjectAtCell(newPosition).isEmpty()){
                ImageView current = cells[newPosition.x][newPosition.y];
                Effect effect = new ColorAdjust(1,1,0.5,1);
                current.setEffect(effect);
            }

        }
        for(Dalek dalek:map.Daleks)
        {
            cells[dalek.position.x][dalek.position.y].setImage(ImageLoader.ToImage(EObjectType.Dalek));
        }
        for(Scrap scrap : map.Scrap){
            cells[scrap.position.x][scrap.position.y].setImage(ImageLoader.ToImage(EObjectType.Scrap));
        }

    }
    public void clear(){
        for (int i = 0; i < Settings.GridCount; i++) {
            for (int j = 0; j < Settings.GridCount; j++) {
                cells[i][j].setImage(ImageLoader.ToImage(EObjectType.Empty));
                cells[i][j].setEffect(null);
            }
        }
    }
    public void Initialize() {

        for (int i = 0; i < Settings.GridCount; i++) {
            GameGrid.getColumnConstraints().add(new ColumnConstraints(Settings.GridSize));
            cells[i] = new ImageView[Settings.GridCount];


            for (int j = 0; j < Settings.GridCount; j++) {
                if (i == 0)
                    GameGrid.getRowConstraints().add(new RowConstraints(Settings.GridSize));
                cells[i][j] = new ImageView(ImageLoader.ToImage(EObjectType.Empty));
                cells[i][j].setFitHeight(40);
                cells[i][j].setPreserveRatio(true);
                GameGrid.add(cells[i][j], i, j);
                GridPane.setHalignment(cells[i][j], HPos.CENTER);
            }
        }
        this.repaint();
    }
}
