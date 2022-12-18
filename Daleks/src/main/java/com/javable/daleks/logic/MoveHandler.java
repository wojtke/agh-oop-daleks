package com.javable.daleks.logic;

import com.javable.daleks.Settings;
import com.javable.daleks.controllers.GridManager;
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

    public MoveHandler(GameMap map, GridManager gridManager) {
        this.map = map; this.gridManager = gridManager;
    }

    private void HandleCollision(ObjectBase o1, ObjectBase o2) {
        o1.Collide(map, o2, false); // object o1 walked into object o2
        o2.Collide(map, o1, true);
    }

    public void MoveDaleks(Position playerPosition) { // TODO rozdzielić na mniejsze metody
        ArrayList<Dalek> toMove = new ArrayList<>();
        // Move all daleks which can move
        for (Dalek dalek : map.daleks) {

            Position newPosition = dalek.Position.add(dalek.Position.directionTo(playerPosition).toVector());
            Optional<ObjectBase> objectAtCell = map.GetObjectAtCell(newPosition);

            if (objectAtCell.isPresent()) {
                if (objectAtCell.get().ObjectType == EObjectType.PLAYER)
                    HandleCollision(dalek, objectAtCell.get());
                toMove.add(dalek);
            } else
                map.MoveObject(dalek, newPosition);
        }

        for (Dalek dalek : toMove) {
            Position newPosition = dalek.Position.add(dalek.Position.directionTo(playerPosition).toVector());
            Optional<ObjectBase> objectAtCell = map.GetObjectAtCell(newPosition);

            if (objectAtCell.isPresent())
                HandleCollision(dalek, objectAtCell.get());
            else
                map.MoveObject(dalek, newPosition);
        }
    }

    public void MovePlayer(Position newPosition) {
        if (newPosition.equals(map.player.Position)) // teleport
            do
                newPosition = new Position(map.gridCount);
            while (!map.IsCellEmptyAndValid(newPosition));
        else if (!map.playerCanMoveTo(newPosition))
            return;

        Optional<ObjectBase> objectAtCell = map.GetObjectAtCell(newPosition);

        if (objectAtCell.isPresent())
            HandleCollision(map.player, objectAtCell.get());
        else
            map.MoveObject(map.player, newPosition);

        MoveDaleks(map.player.Position);

        if (!CheckIfWon())
            gridManager.repaint();
    }

    public boolean CheckIfWon() {
        if (map.daleks.isEmpty()) {
            ViewManager.SetScene(Settings.GameWonView);
            return true;
        }
        return false;
    }
}
