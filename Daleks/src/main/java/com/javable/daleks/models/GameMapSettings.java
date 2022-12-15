package com.javable.daleks.models;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class GameMapSettings extends AbstractModule {

    public GameMapSettings(int GridCount, int DaleksCount) {
        this.GridCount = GridCount;
        this.DaleksCount = DaleksCount;
        this.PlayerStartPosition = new Position(GridCount / 2, GridCount / 2);
    }

    public final int GridCount;
    public final int DaleksCount;
    public final Position PlayerStartPosition;

    @Provides
    public GameMapSettings provideGameMapSettings() {
        return this;
    }
}
