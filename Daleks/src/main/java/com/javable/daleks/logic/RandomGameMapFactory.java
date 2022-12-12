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
        this.gridCount = settings.GridCount;
        this.daleksCount = settings.DaleksCount;
        this.playerStartPosition = settings.PlayerStartPosition;

    }

    public GameMap create() {
        Player player = new Player(playerStartPosition);
        GameMap gameMap = new GameMap(player, gridCount);
        addDaleks(gameMap, daleksCount);

        return gameMap;
    }

    public void addDaleks(GameMap gameMap, int count) {
        for (int i = 0; i < count; i++) {
            Position position = new Position(
                    (int) (Math.random() * this.gridCount),
                    (int) (Math.random() * this.gridCount)
            );
            gameMap.addDalek(new Dalek(position));
        }
    }

}
