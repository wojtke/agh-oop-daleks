package com.javable.daleks.models;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class GameMapSettings extends AbstractModule {
    private final int gridCount;
    private final int daleksCount;
    private final Position playerStartPosition;
    private final String levelName;

    public GameMapSettings(int gridCount, int daleksCount, String levelName) {
        this.gridCount = gridCount;
        this.daleksCount = daleksCount;
        this.levelName = levelName;
        playerStartPosition = new Position(gridCount / 2, gridCount / 2);
    }

    @Provides
    public GameMapSettings ProvideGameMapSettings() {
        return this;
    }

    public int GetGridCount() {
        return gridCount;
    }

    public int GetDaleksCount() {
        return daleksCount;
    }

    public Position GetPlayerStartPosition() {
        return playerStartPosition;
    }

    public String GetLevelName() {
        return levelName;
    }
}
