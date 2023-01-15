package com.javable.daleks.logic;

import com.javable.daleks.Settings;
import com.javable.daleks.controllers.GridManager;
import com.javable.daleks.models.Position;
import com.javable.daleks.models.objects.Dalek;
import com.javable.daleks.models.objects.ObjectBase;

import java.util.HashMap;
import java.util.Optional;
import java.util.Random;

public class MoveHandler {
    private final GridManager gridManager;

    public MoveHandler(GridManager gridManager) {
        this.gridManager = gridManager;
    }

    private void HandleCollision(ObjectBase o1, ObjectBase o2) {
        o1.Collide(gridManager.map, o2, false); // object o1 walked into object o2
        o2.Collide(gridManager.map, o1, true);
    }

    public void MoveDaleks(Position playerPosition) {
        HashMap<Dalek, Position> daleksToMove = new HashMap<>();

        for (Dalek dalek : gridManager.map.daleks) {
            Position newPosition = dalek.Position.add(dalek.Position.directionTo(playerPosition).toVector());
            gridManager.map.GetObjectAtCell(newPosition).ifPresentOrElse(
                    object -> daleksToMove.put(dalek, newPosition),
                    () -> gridManager.map.MoveObject(dalek, newPosition)
            );
        }
        for (Dalek dalek : daleksToMove.keySet()) {
            Position newPosition = daleksToMove.get(dalek);
            gridManager.map.GetObjectAtCell(newPosition).ifPresentOrElse(
                    object -> HandleCollision(dalek, object),
                    () -> gridManager.map.MoveObject(dalek, newPosition)
            );
        }
    }

    public void MovePlayer(Position newPosition) {
        Random rand = new Random();
        if (newPosition.equals(gridManager.map.player.Position)) // teleport
            do
                newPosition = new Position(gridManager.map.levelData.GridCount, rand);
            while (!gridManager.map.IsCellEmptyAndValid(newPosition));
        else if (!gridManager.map.playerCanMoveTo(newPosition))
            return;

        Optional<ObjectBase> objectAtCell = gridManager.map.GetObjectAtCell(newPosition);

        if (objectAtCell.isPresent())
            HandleCollision(gridManager.map.player, objectAtCell.get());
        else
            gridManager.map.MoveObject(gridManager.map.player, newPosition);

        MoveDaleks(gridManager.map.player.Position);

        if (!CheckIfWon())
            gridManager.repaint();
    }

    public boolean CheckIfWon() {
        if (gridManager.map.daleks.isEmpty()) {
            if (gridManager.map.levelData.IsCampaignLv)
                CampaignManager.IncrementMaxCampaignLvAfterBeatingLv(gridManager.map.levelData.CampaignOrder);

            ViewManager.SetScene(Settings.GameWonView);
            return true;
        }
        return false;
    }
}
