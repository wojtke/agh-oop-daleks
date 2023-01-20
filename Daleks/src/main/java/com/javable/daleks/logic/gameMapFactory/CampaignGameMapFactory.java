package com.javable.daleks.logic.gameMapFactory;

import com.javable.daleks.logic.MoveHandler;
import com.javable.daleks.models.GameMap;
import com.javable.daleks.models.Position;
import com.javable.daleks.models.objects.characters.Dalek;
import com.javable.daleks.models.objects.powersUps.Attractor;
import com.javable.daleks.models.objects.powersUps.Teleporter;

public class CampaignGameMapFactory extends GameMapFactoryBase {

    public CampaignGameMapFactory(GameMap gameMap, MoveHandler moveHandler) {
        super(gameMap, moveHandler);
    }

    @Override
    protected void addAttractors() {
        for (Position attractor : gameMap.levelData.getAttractorsPositions())
            gameMap.addObject(new Attractor(attractor));
    }

    @Override
    protected void addTeleporters() {
        for (Position teleporter : gameMap.levelData.getTeleportersPositions())
            gameMap.addObject(new Teleporter(teleporter, moveHandler));
    }

    @Override
    protected void addDaleks() {
        for (Position dalek : gameMap.levelData.getDaleksPositions())
            gameMap.addObject(new Dalek(dalek));
    }
}
