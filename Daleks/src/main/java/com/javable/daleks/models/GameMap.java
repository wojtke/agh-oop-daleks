package com.javable.daleks.models;

import com.javable.daleks.models.objects.Dalek;
import com.javable.daleks.models.objects.ObjectBase;
import com.javable.daleks.models.objects.Player;
import com.javable.daleks.models.objects.Scrap;

import java.util.*;

public class GameMap {
    public final Level levelData;
    public final Player player;
    public final List<Dalek> daleks = new ArrayList<>();
    public final List<Scrap> scraps = new ArrayList<>();
    private final Map<Position, ObjectBase> occupiedCells = new HashMap<>(); // referencje do postaci na planszy

    public GameMap(Level settings) {
        this.levelData = settings;
        this.player = new Player(settings.PlayerPosition);
        occupiedCells.put(player.Position, player);
    }

    public boolean isInBounds(Position position) {
        return position.x >= 0 && position.x < this.levelData.GridCount && position.y >= 0 && position.y < this.levelData.GridCount;
    }

    public boolean playerCanMoveTo(Position position) {
        return IsCellEmptyAndValid(position) && player.CanMove(position);
    }

    public boolean IsCellEmptyAndValid(Position position) {
        return isInBounds(position) && !occupiedCells.containsKey(position);
    }

    public void AddDalek(Dalek newDalek) {
        daleks.add(newDalek);
        occupiedCells.put(newDalek.Position, newDalek);
    }

    public void AddScrap(Scrap newScrap) {
        if (occupiedCells.containsKey(newScrap.Position))
            return;
        scraps.add(newScrap);
        occupiedCells.put(newScrap.Position, newScrap);
    }

    public void MoveObject(ObjectBase object, Position newPosition) {
        Position oldPosition = object.Position;
        object.Position = newPosition;
        occupiedCells.remove(oldPosition);
        occupiedCells.put(object.Position, object);
    }

    public Optional<ObjectBase> GetObjectAtCell(Position position) {
        return occupiedCells.containsKey(position)
                ? Optional.of(occupiedCells.get(position))
                : Optional.empty();
    }

    public void RemoveDalek(Dalek dalek) {
        this.daleks.remove(dalek);
        this.occupiedCells.remove(dalek.Position);
    }
}
