package com.javable.daleks.logic;

import com.javable.daleks.Settings;
import com.javable.daleks.controllers.GridManager;
import com.javable.daleks.enums.EObjectType;
import com.javable.daleks.models.GameMap;
import com.javable.daleks.models.Position;
import com.javable.daleks.models.objects.Dalek;
import com.javable.daleks.models.objects.ObjectBase;

import java.io.IOException;
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

    public void moveDaleks(Position playerPosition) {
        ArrayList<Dalek> toMove = new ArrayList<>();
        // Move all daleks which can move
        for (Dalek dalek : map.daleks) {

            Position newPosition = dalek.position.add(dalek.position.directionTo(playerPosition).toVector());
            Optional<ObjectBase> objectAtCell = map.getObjectAtCell(newPosition);

            if (objectAtCell.isPresent()) {
                if (objectAtCell.get().objectType == EObjectType.Player)
                    handleCollision(dalek, objectAtCell.get());
                toMove.add(dalek);
            } else
                map.moveObject(dalek, newPosition);
        }

        for (Dalek dalek : toMove) {

            Position newPosition = dalek.position.add(dalek.position.directionTo(playerPosition).toVector());
            Optional<ObjectBase> objectAtCell = map.getObjectAtCell(newPosition);

            if (objectAtCell.isPresent())
                handleCollision(dalek, objectAtCell.get());
            else
                map.moveObject(dalek, newPosition);
        }
    }

    public void movePlayer(Position newPosition) {
        if (newPosition.equals(map.player.position)) // teleport
            do
                newPosition = new Position((int) (Math.random() * map.gridCount), (int) (Math.random() * map.gridCount));
            while (!map.playerCanTeleportTo(newPosition));
        else if (!map.playerCanMoveTo(newPosition))
            return;

        Optional<ObjectBase> objectAtCell = map.getObjectAtCell(newPosition);

        if (objectAtCell.isPresent())
            handleCollision(map.player, objectAtCell.get());
        else
            map.moveObject(map.player, newPosition);

        moveDaleks(map.player.position);
        checkIfWon();
        gridManager.repaint();
    }

    public void checkIfWon() {
        if (map.daleks.isEmpty()) {
            try {
                ViewManager.SetScene(Settings.GameWonView);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
