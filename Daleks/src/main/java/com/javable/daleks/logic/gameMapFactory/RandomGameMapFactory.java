package com.javable.daleks.logic.gameMapFactory;

import com.javable.daleks.logic.MoveHandler;
import com.javable.daleks.models.GameMap;
import com.javable.daleks.models.Position;
import com.javable.daleks.models.objects.characters.Dalek;
import com.javable.daleks.models.objects.powersUps.Attractor;
import com.javable.daleks.models.objects.powersUps.Teleporter;

public class RandomGameMapFactory extends GameMapFactoryBase {
    public RandomGameMapFactory(GameMap gameMap, MoveHandler moveHandler) {
        super(gameMap, moveHandler);
    }

    private Position getNextRandValidPosition() {
        Position position;

        do
            position = new Position(gameMap.levelData.getGridSize());
        while (!gameMap.isCellEmptyAndValid(position));
        return position;
    }

    @Override
    protected void addAttractors() {
        for (int i = 0; i < gameMap.levelData.getAttractorsCount(); i++)
            gameMap.addObject(new Attractor(getNextRandValidPosition()));
    }

    @Override
    protected void addTeleporters(){
        for (int i = 0; i < gameMap.levelData.getTeleportersCount(); i++)
            gameMap.addObject(new Teleporter(getNextRandValidPosition(), moveHandler));
    }

    @Override
    protected void addDaleks() {
        for (int i = 0; i < gameMap.levelData.getDaleksCount(); i++)
            gameMap.addDalek(new Dalek(getNextRandValidPosition()));
    }

}
