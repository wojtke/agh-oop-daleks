package com.javable.daleks.logic;

import com.google.inject.Inject;
import com.javable.daleks.models.GameMap;
import com.javable.daleks.models.Level;
import com.javable.daleks.models.Position;
import com.javable.daleks.models.objects.Dalek;
import com.javable.daleks.models.objects.Player;
import com.javable.daleks.models.objects.powers.Attractor;
import com.javable.daleks.models.objects.powers.Teleporter;

public class RandomGameMapFactory {

    private final int daleksCount, attractorsCount, teleportersCount;
    private final int gridCount;
    private MoveHandler moveHandler = null;
    private final GameMap gameMap;

    public RandomGameMapFactory(int daleksCount, int attractorsCount, int teleportersCount, int gridCount, GameMap gameMap) {
        this.daleksCount = daleksCount;
        this.attractorsCount = attractorsCount;
        this.teleportersCount = teleportersCount;
        this.gridCount = gridCount;
        this.gameMap = gameMap;
    }

    public void setMoveHandler(MoveHandler moveHandler) {
        this.moveHandler = moveHandler;
    }

    public void generate() {
        addAttractors(gameMap,attractorsCount);
        addTeleporters(gameMap,teleportersCount);
        addDaleks(gameMap, daleksCount);
    }
    public void addAttractors(GameMap gameMap, int attractorsCount){
        Position position;
        for (int i = 0; i < attractorsCount; i++) {
            do
                position = new Position(gridCount);
            while (!gameMap.isCellEmptyAndValid(position));

            gameMap.addObject(new Attractor(position));
        }
    }
    public void addTeleporters(GameMap gameMap, int teleportersCount){
        return;
//        if(moveHandler == null){
//            throw new IllegalStateException("Did not recive move handler - cannot create new Teleporter");
//        }
//        Position position;
//        for (int i = 0; i < teleportersCount; i++) {
//            do
//                position = new Position(gridCount);
//            while (!gameMap.isCellEmptyAndValid(position));
//
//            gameMap.addObject(new Teleporter(position, moveHandler));
//        }
    }
    public void addDaleks(GameMap gameMap, int count) {
        Position position;
        for (int i = 0; i < count; i++) {
            do
                position = new Position(gridCount);
            while (!gameMap.isCellEmptyAndValid(position));

            gameMap.addDalek(new Dalek(position));
        }
    }

}
