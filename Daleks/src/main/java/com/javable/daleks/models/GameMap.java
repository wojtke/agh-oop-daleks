package com.javable.daleks.models;

import com.javable.daleks.models.objects.Dalek;
import com.javable.daleks.models.objects.Destination;
import com.javable.daleks.models.objects.ObjectBase;
import com.javable.daleks.models.objects.Player;

import java.util.*;

public class GameMap {

    private final int gridSize;
    private final Player player;
    private final List<Dalek> daleks = new ArrayList<>();

    private final List<Destination> destinations = new ArrayList<>();
    private final Map<Position, ObjectBase> occupiedCells = new HashMap<>(); // referencje do postaci na planszy
    public Optional<Level> levelData = Optional.empty();

    public GameMap(Level level) {
        gridSize = level.getGridSize();
        this.player = new Player(level.getPlayerPosition());
        occupiedCells.put(player.getPosition(), player);
        destinations.add(player);
        levelData = Optional.of(level);
    }
    public GameMap(int gridSize, Player player) {
        this.gridSize = gridSize;
        this.player = player;
        occupiedCells.put(player.getPosition(), player);
        destinations.add(player);
    }
    public boolean isInBounds(Position position) {
        return position.x >= 0 && position.x < gridSize && position.y >= 0 && position.y < gridSize;
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
            newPosition = new Position(gridSize);
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
        return gridSize;
    }
}
