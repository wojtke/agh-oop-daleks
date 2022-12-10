package com.javable.daleks.logic;

import com.javable.daleks.enums.EDirection;
import com.javable.daleks.enums.EObjectType;
import com.javable.daleks.models.GameMap;
import com.javable.daleks.models.Position;
import javafx.scene.control.Label;

import java.util.List;

public class GridManager {

    public static void FrameUpdate(Label[][] cells, GameMap map, EDirection direction) {
        List<Position> updatedCells = map.NextFrame(direction);

        for (Position cell : updatedCells) {
            EObjectType objectAtOldPosition = map.GetObjectAtCell(cell);

            // TODO temp zamienić na obrazki (ImageLOader)
            String str;
            switch (objectAtOldPosition) {
                case Player -> str = "😘";
                default -> str = "___";
            }
            cells[cell.x][cell.y].setText(str);
        }
    }
}
