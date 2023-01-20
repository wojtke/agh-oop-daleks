package com.javable.daleks.logic;

import com.javable.daleks.models.GameMap;
import com.javable.daleks.models.Position;
import com.javable.daleks.models.objects.Dalek;
import com.javable.daleks.models.objects.powers.Attractor;
import com.javable.daleks.models.objects.powers.Teleporter;

public class ConcreteGameMapFactory {
    private final GameMap gameMap;
    private MoveHandler moveHandler;
    public ConcreteGameMapFactory(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public void generate() {
        addAttractors();
        addTeleporters();
        addDaleks();
    }
    public void setMoveHandler(MoveHandler moveHandler){
        this.moveHandler = moveHandler;
    }

    private void addAttractors() {
        for (Position position : gameMap.levelData.getAttractorsPositions())
            gameMap.addObject(new Attractor(position));
    }
    private void addTeleporters(){
        if(moveHandler == null){
            throw new IllegalStateException("Did not recive move handler - cannot create new Teleporter");
        }
        for (Position position : gameMap.levelData.getTeleportersPositions())
            gameMap.addObject(new Teleporter(position, moveHandler));
    }
    private void addDaleks() {
        for (Position position : gameMap.levelData.getDaleksPositions())
            gameMap.addObject(new Dalek(position));
    }

}
