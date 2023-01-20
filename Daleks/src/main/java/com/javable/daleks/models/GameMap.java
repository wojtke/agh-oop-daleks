package com.javable.daleks.models;

import com.javable.daleks.models.objects.characters.Dalek;
import com.javable.daleks.interfaces.Destination;
import com.javable.daleks.models.objects.ObjectBase;
import com.javable.daleks.models.objects.characters.Player;

import java.util.*;

public class GameMap {
    private final Player player;
    private final List<Dalek> daleks = new ArrayList<>();

    private final List<Destination> destinations = new ArrayList<>();
    private final Map<Position, ObjectBase> occupiedCells = new HashMap<>(); // referencje do postaci na planszy
    public final Level levelData;

    public GameMap(Level level) {
        this.player = new Player(level.getPlayerPosition());
        occupiedCells.put(player.getPosition(), player);
        destinations.add(player);
        levelData = level;
    }

    public boolean isInBounds(Position position) {
        return position.x >= 0 && position.x < levelData.getGridSize() && position.y >= 0 && position.y < levelData.getGridSize();
    }

    public Player getPlayer() {
        return player;
    }

    public List<Dalek> getDaleks() {
        return daleks;
    }

    public boolean playerCanMoveTo(Position position) {
        return isInBounds(position) && player.canMove(position);
    }
    public void teleportPlayer(){
        Position newPosition;
        do
            newPosition = new Position(levelData.getGridSize());
        while (!isCellEmptyAndValid(newPosition));
        moveObject(player, newPosition);
    }

    public boolean isCellEmptyAndValid(Position position) {
        return isInBounds(position) && !occupiedCells.containsKey(position);
    }

    public void addDalek(Dalek newDalek) {
        if (occupiedCells.containsKey(newDalek.position))
            throw new IllegalStateException("Cell is not empty");
        daleks.add(newDalek);
        occupiedCells.put(newDalek.position, newDalek);
    }

    public List<Destination> getDestinations(){
        return destinations;
    }
    public void addDestination(Destination newDestination) {
        destinations.add(newDestination);
    }
    public void removeDestination(Destination destination) {
        destinations.remove(destination);
    }
    public void addObject(ObjectBase newObject) {
        if (occupiedCells.containsKey(newObject.position))
            throw new IllegalStateException("Cell is not empty");
        occupiedCells.put(newObject.position, newObject);
    }
    public void removeObject(ObjectBase object) {
        occupiedCells.remove(object.position);
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

    public Collection<ObjectBase> getObjects() {
        return occupiedCells.values();
    }

    public int getGridSize() {
        return levelData.getGridSize();
    }
}
