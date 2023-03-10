package com.javable.daleks.logic;

import com.javable.daleks.Settings;
import com.javable.daleks.controllers.GridManager;
import com.javable.daleks.models.GameMap;
import com.javable.daleks.models.Position;
import com.javable.daleks.models.objects.characters.Dalek;
import com.javable.daleks.interfaces.Destination;
import com.javable.daleks.models.objects.ObjectBase;

import java.util.HashMap;
import java.util.Optional;

public class MoveHandler{

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

    public void moveDaleks() {
        HashMap<Dalek, Position> daleksToMove = new HashMap<>();

        for (Dalek dalek : map.getDaleks()) {
            Destination destination = findDestination(dalek);
            Position newPosition = dalek.position.add(dalek.position.directionTo(destination.getPosition()).toVector());
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
    public Destination findDestination(Dalek dalek){
        Destination closest = null;
        int closestDistance = Integer.MAX_VALUE;
        for (Destination destination : map.getDestinations()){
            int distance = destination.getPosition().distanceSqr(dalek.position);
            if(distance < closestDistance){
                closest = destination;
                closestDistance = destination.getPosition().distanceSqr(dalek.position);
            }
        }
        if(closest == null)
            throw new IllegalStateException("No closest destination");

        return closest;
    }

    public void movePlayer(Position newPosition) {

        /* stara teloportacja poprzez klikanie gracza, teraz tylko przez teleportery
        if (newPosition.equals(map.getPlayer().position)) {
            this.teleportPlayer();
            return;
        }*/

        if(!map.playerCanMoveTo(newPosition))
            return;

        Optional<ObjectBase> objectAtCell = map.getObjectAtCell(newPosition);

        if (objectAtCell.isPresent())
            handleCollision(map.getPlayer(), objectAtCell.get());
        else
            map.moveObject(map.getPlayer(), newPosition);
        moveDaleks();

        if (checkIfLost())
            gridManager.repaint();
    }
    public void teleportPlayer(){
        map.teleportPlayer();
        if (checkIfLost())
            gridManager.repaint();
    }

    public boolean checkIfLost() {
        if (map.getDaleks().isEmpty()) {
            if (map.levelData.isCampaign())
                CampaignManager.IncrementMaxCampaignLvAfterBeatingLv(map.levelData.getCampaignOrder());

            ViewManager.setScene(Settings.GameWonView);
            return false;
        }
        return true;
    }
}
