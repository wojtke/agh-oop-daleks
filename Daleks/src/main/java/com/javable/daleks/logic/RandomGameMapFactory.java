package com.javable.daleks.logic;

import com.google.inject.Inject;
import com.javable.daleks.models.GameMap;
import com.javable.daleks.models.Level;
import com.javable.daleks.models.Position;
import com.javable.daleks.models.objects.Dalek;

public class RandomGameMapFactory {
    private final Level settings;

    @Inject
    public RandomGameMapFactory(Level settings) {
        this.settings = settings;
    }

    public GameMap Create() {
        GameMap gameMap = new GameMap(settings);
        for (Position position : settings.DaleksPositions)
            gameMap.AddDalek(new Dalek(position));
        return gameMap;
    }
}
