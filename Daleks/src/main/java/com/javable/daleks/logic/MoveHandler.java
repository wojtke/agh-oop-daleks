package com.javable.daleks.logic;

import com.javable.daleks.Settings;
import com.javable.daleks.controllers.GridManager;
import com.javable.daleks.models.GameMap;
import com.javable.daleks.models.Position;
import com.javable.daleks.models.objects.Dalek;
import com.javable.daleks.models.objects.ObjectBase;

import java.util.HashMap;
import java.util.Optional;

public class MoveHandler {

    private final GameMap map;
    private final GridManager gridManager;

    public MoveHandler(GameMap map, GridManager gridManager) {
        this.map = map;
        this.gridManager = gridManager;
    }

    private void handleCollision(ObjectBase o1, ObjectBase o2) {
        o1.createCollision(map, o2, true);
        o2.createCollision(map, o1, false);
    }

    public void moveDaleks(Position playerPosition) {
        HashMap<Dalek, Position> daleksToMove = new HashMap<>();

        for (Dalek dalek : map.daleks) {
            Position newPosition = dalek.position.add(dalek.position.directionTo(playerPosition).toVector());
            map.getObjectAtCell(newPosition).ifPresentOrElse(
                    object -> daleksToMove.put(dalek, newPosition),
                    () -> map.moveObject(dalek, newPosition)
            );
        }
        for (Dalek dalek : daleksToMove.keySet()) {
            Position newPosition = daleksToMove.get(dalek);
            map.getObjectAtCell(newPosition).ifPresentOrElse(
                    object -> handleCollision(dalek, object),
                    () -> map.moveObject(dalek, newPosition)
            );
        }
    }

    public void movePlayer(Position newPosition) {
        if (newPosition.equals(map.player.position)) // teleport
            do
                newPosition = new Position(map.gridCount);
            while (!map.isCellEmptyAndValid(newPosition));
        else if (!map.playerCanMoveTo(newPosition))
            return;

        Optional<ObjectBase> objectAtCell = map.getObjectAtCell(newPosition);

        if (objectAtCell.isPresent())
            handleCollision(map.player, objectAtCell.get());
        else
            map.moveObject(map.player, newPosition);

        moveDaleks(map.player.position);

        if (!checkIfWon())
            gridManager.repaint();
    }

    public boolean checkIfWon() {
        if (map.daleks.isEmpty()) {
            ViewManager.setScene(Settings.GameWonView);
            return true;
        }
        return false;
    }
}
