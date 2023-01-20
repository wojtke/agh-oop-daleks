package com.javable.daleks.logic.gameMapFactory;

import com.javable.daleks.logic.MoveHandler;
import com.javable.daleks.models.GameMap;

public abstract class GameMapFactoryBase {
    protected final GameMap gameMap;
    protected final MoveHandler moveHandler;

    public GameMapFactoryBase(GameMap gameMap, MoveHandler moveHandler) {
        this.gameMap = gameMap;
        this.moveHandler = moveHandler;

        addAttractors();
        addTeleporters();
        addDaleks();
    }

    protected abstract void addAttractors();
    protected abstract void addTeleporters();
    protected abstract void addDaleks();
}
