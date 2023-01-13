package com.javable.daleks.logic;

import com.google.inject.Inject;
import com.javable.daleks.models.GameMap;
import com.javable.daleks.models.GameMapSettings;
import com.javable.daleks.models.Position;
import com.javable.daleks.models.objects.Dalek;
import com.javable.daleks.models.objects.Player;

public class RandomGameMapFactory {

    private final int daleksCount;
    private final Position playerStartPosition;
    private final int gridCount;

    @Inject
    public RandomGameMapFactory(GameMapSettings settings) {
        this.gridCount = settings.getGridCount();
        this.daleksCount = settings.getDaleksCount();
        this.playerStartPosition = settings.getPlayerStartPosition();

    }

    public GameMap Create() {
        Player player = new Player(playerStartPosition);
        GameMap gameMap = new GameMap(player, gridCount);
        AddDaleks(gameMap, daleksCount);

        return gameMap;
    }

    public void AddDaleks(GameMap gameMap, int count) {
        Position position;
        for (int i = 0; i < count; i++) {
            do
                position = new Position(gridCount);
            while (!gameMap.IsCellEmptyAndValid(position));

            gameMap.AddDalek(new Dalek(position));
        }
    }

}
