package com.javable.daleks.models;

import com.javable.daleks.models.objects.Dalek;
import com.javable.daleks.models.objects.ObjectBase;
import com.javable.daleks.models.objects.Player;
import com.javable.daleks.models.objects.Scrap;

import java.util.*;

public class GameMap {
    public final Player player;
    public final List<Dalek> daleks = new ArrayList<>();
    public final List<Scrap> scraps = new ArrayList<>();
    private final Map<Position, ObjectBase> occupiedCells = new HashMap<>(); // referencje do postaci na planszy
    public final int gridCount;

    public GameMap(Player player, int gridCount) {
        this.gridCount = gridCount;
        this.player = player;
        occupiedCells.put(player.position, player);
    }

    public boolean isInBounds(Position position) {
        return position.x >= 0 && position.x < this.gridCount && position.y >= 0 && position.y < this.gridCount;
    }

    public boolean playerCanMoveTo(Position position) {
        return isInBounds(position) && !occupiedCells.containsKey(position);
    }

    public void addDalek(Dalek newDalek) {
        if (occupiedCells.containsKey(newDalek.position))
            return;
        daleks.add(newDalek);
        occupiedCells.put(newDalek.position, newDalek);
    }

    public void addScrap(Scrap newScrap) {
        if (occupiedCells.containsKey(newScrap.position))
            return;
        scraps.add(newScrap);
        occupiedCells.put(newScrap.position, newScrap);
    }

    public void moveObject(ObjectBase object, Position newPosition) {
        Position oldPosition = object.position;
        object.position = newPosition;
        occupiedCells.remove(oldPosition);
        occupiedCells.put(object.position, object);
    }

    public Optional<ObjectBase> getObjectAtCell(Position position) {
        return occupiedCells.containsKey(position)
                ? Optional.of(occupiedCells.get(position))
                : Optional.empty();
    }

    public void removeDalek(Dalek dalek) {
        this.daleks.remove(dalek);
        this.occupiedCells.remove(dalek.position);
    }
}
