package com.javable.daleks.logic;

import com.javable.daleks.models.Position;
import javafx.event.EventTarget;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class InputHandler {

    public final MoveHandler moveHandler;

    public InputHandler(MoveHandler moveHandler) {
        this.moveHandler = moveHandler;
    }

    public void clickGrid(GridPane gameGrid, EventTarget target) {
        Node clickedNode = (Node) target;
        if (clickedNode != gameGrid) {
            // click on descendant node
            Integer colIndex = GridPane.getColumnIndex(clickedNode);
            Integer rowIndex = GridPane.getRowIndex(clickedNode);
            moveHandler.movePlayer(new Position(colIndex, rowIndex));
        }
    }


}
