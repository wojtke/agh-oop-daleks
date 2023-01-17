package com.javable.daleks.logic;

import com.javable.daleks.models.GameMap;
import com.javable.daleks.models.Position;
import com.javable.daleks.models.objects.Dalek;
import com.javable.daleks.models.objects.powers.Attractor;
import com.javable.daleks.models.objects.powers.Teleporter;

public class RandomGameMapFactory {
    private final GameMap gameMap;
    private MoveHandler moveHandler;
    public RandomGameMapFactory(GameMap gameMap) {
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
    private Position getNextRandValidPosition() {
        Position position;
        do
            position = new Position(gameMap.levelData.getGridSize());
        while (!gameMap.isCellEmptyAndValid(position));
        return position;
    }

    public void addAttractors() {
        for (int i = 0; i < gameMap.levelData.getAttractorsCount(); i++)
            gameMap.addObject(new Attractor(getNextRandValidPosition()));
    }
    public void addTeleporters(){
        if(moveHandler == null){
            throw new IllegalStateException("Did not recive move handler - cannot create new Teleporter");
        }
        Position position;
        for (int i = 0; i < gameMap.levelData.getTeleportersCount(); i++) {
            do
                position = new Position(gameMap.levelData.getGridSize());
            while (!gameMap.isCellEmptyAndValid(position));

            gameMap.addObject(new Teleporter(position, moveHandler));
        }
    }
    public void addDaleks() {
        for (int i = 0; i < gameMap.levelData.getDaleksCount(); i++)
            gameMap.addDalek(new Dalek(getNextRandValidPosition()));
    }

}
