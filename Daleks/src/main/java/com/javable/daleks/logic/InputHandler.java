package com.javable.daleks.logic;

import com.javable.daleks.models.Position;
import javafx.event.EventTarget;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class InputHandler {

    public final MoveHandler MoveHandler;

    public InputHandler(MoveHandler moveHandler) {
        this.MoveHandler = moveHandler;
    }

    public void ClickGrid(GridPane GameGrid, EventTarget target) {
        Node clickedNode = (Node) target;
        if (clickedNode != GameGrid) {
            // click on descendant node
            Integer colIndex = GridPane.getColumnIndex(clickedNode);
            Integer rowIndex = GridPane.getRowIndex(clickedNode);
            MoveHandler.MovePlayer(new Position(colIndex, rowIndex));
        }
    }


}
