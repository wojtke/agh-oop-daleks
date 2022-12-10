package com.javable.daleks.logic;

import com.almasb.fxgl.dsl.components.OffscreenPauseComponent;
import com.javable.daleks.controllers.GridManager;
import com.javable.daleks.enums.EDirection;
import com.javable.daleks.enums.EObjectType;
import com.javable.daleks.models.GameMap;
import com.javable.daleks.models.Position;
import com.javable.daleks.models.objects.Dalek;
import com.javable.daleks.models.objects.ObjectBase;

import java.util.ArrayList;
import java.util.Optional;

public class MoveHandler {

    private final GameMap map;
    private final GridManager gridManager;
    private final CollisionHandlerVisitor collisionHandlerVisitor;

    public MoveHandler(GameMap map, GridManager gridManager) {
        this.map = map;
        this.gridManager = gridManager;
        collisionHandlerVisitor = new CollisionHandlerVisitor(map);
    }

    private void handleCollision(ObjectBase o1, ObjectBase o2) {
        // object o1 walked into object o2
        o1.accept(collisionHandlerVisitor, o2, false);
        o2.accept(collisionHandlerVisitor, o1, true);
    }
    private Position calculatePosition(Dalek dalek, Position playerPosition){
        Position newPosition = new Position(dalek.position);

        if (dalek.position.x < playerPosition.x) {
            newPosition.add(Position.ToVector(EDirection.Right));
        }
        if (dalek.position.x > playerPosition.x) {
            newPosition.add(Position.ToVector(EDirection.Left));
        }
        if (dalek.position.y < playerPosition.y) {
            newPosition.add(Position.ToVector(EDirection.Bottom));
        }
        if (dalek.position.y > playerPosition.y) {
            newPosition.add(Position.ToVector(EDirection.Top));
        }
        return newPosition;
    }
    public void MoveDaleks(Position playerPosition) {
        ArrayList<Dalek> toMove = new ArrayList<>();
        // Move all daleks which can move
        for (Dalek dalek : map.Daleks){

            Position newPosition = calculatePosition(dalek, playerPosition);
            Optional<ObjectBase> objectAtCell = map.GetObjectAtCell(newPosition);

            if(objectAtCell.isPresent()){
                if (objectAtCell.get().objectType == EObjectType.Player){
                    handleCollision(dalek, objectAtCell.get());
                }
                toMove.add(dalek);
            }
            else{
                map.MoveObject(dalek, newPosition);
            }
        }

        for (Dalek dalek : toMove){

            Position newPosition = calculatePosition(dalek, playerPosition);
            Optional<ObjectBase> objectAtCell = map.GetObjectAtCell(newPosition);

            if(objectAtCell.isPresent()){
                handleCollision(dalek, objectAtCell.get());
            }
            else{
                map.MoveObject(dalek, newPosition);
            }
        }
    }

    public void MovePlayer(Position newPosition) {

        if(!map.Player.canMove(newPosition))
            return;

        Optional<ObjectBase> objectAtCell = map.GetObjectAtCell(newPosition);
        if(objectAtCell.isPresent()){
            handleCollision(map.Player, objectAtCell.get());
        }
        else{
            map.MoveObject(map.Player, newPosition);
        }
        MoveDaleks(map.Player.position);
        gridManager.repaint();

    }

}
