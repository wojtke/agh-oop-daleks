package com.javable.daleks.logic;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.javable.daleks.models.GameMap;
import com.javable.daleks.models.GameMapSettings;
import com.javable.daleks.models.Position;
import com.javable.daleks.models.objects.Dalek;
import com.javable.daleks.models.objects.Player;
import com.javable.daleks.models.objects.powers.Attractor;
import com.javable.daleks.models.objects.powers.Teleporter;

public class RandomGameMapFactory {

    private final int daleksCount;
    private final Position playerStartPosition;
    private final int gridCount;
    private MoveHandler moveHandler;
    private GameMap gameMap;

    @Inject
    public RandomGameMapFactory(GameMapSettings settings) {
        this.gridCount = settings.getGridCount();
        this.daleksCount = settings.getDaleksCount();
        this.playerStartPosition = settings.getPlayerStartPosition();
    }
    public void setMoveHandler(MoveHandler moveHandler) {
        this.moveHandler = moveHandler;
    }
    public GameMap create() {
        Player player = new Player(playerStartPosition);
        gameMap = new GameMap(player, gridCount);
        return gameMap;
    }
    public void generate(){
        addAttractors(gameMap,3);
        addTeleporters(gameMap,3);
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
        Position position;
        for (int i = 0; i < teleportersCount; i++) {
            do
                position = new Position(gridCount);
            while (!gameMap.isCellEmptyAndValid(position));

            gameMap.addObject(new Teleporter(position, moveHandler));
        }
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
