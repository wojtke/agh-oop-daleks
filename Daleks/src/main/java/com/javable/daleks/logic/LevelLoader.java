package com.javable.daleks.logic;

import com.google.inject.Inject;
import com.javable.daleks.models.GameMap;
import com.javable.daleks.models.Level;
import com.javable.daleks.models.Position;
import com.javable.daleks.models.objects.Dalek;

public class LevelLoader {

    private Level level;
    @Inject
    public LevelLoader(Level level) {
        this.level = level;
    }
    public GameMap load(){
        GameMap gameMap = new GameMap(level);
        for (Position position : level.getDaleksPositions())
            gameMap.addDalek(new Dalek(position));
        return gameMap;
    }
}
