package com.javable.daleks.models;

import com.javable.daleks.Settings;
import com.javable.daleks.enums.EDirection;
import com.javable.daleks.enums.EObjectType;
import com.javable.daleks.models.objects.Dalek;
import com.javable.daleks.models.objects.ObjectBase;
import com.javable.daleks.models.objects.Player;
import com.javable.daleks.models.objects.Scrap;

import java.util.*;

public class GameMap {
    public final Player Player = new Player();
    public final List<Dalek> Daleks = new ArrayList<>();
    public final List<Scrap> Scrap = new ArrayList<>();
    private final Map<Position, ObjectBase> OccupiedCells = new HashMap<Position, ObjectBase>(); // referencje do postaci na planszy

    public GameMap() {
        // TODO Przeniesc incjalizacje do osobnej klasy - np. MapGenerator
        OccupiedCells.put(Player.position, Player);
        for (int i = 0; i < Settings.DaleksCount; i++) {

            Dalek dalek = new Dalek(new Position(i, 3));
            Daleks.add(dalek);
            OccupiedCells.put(dalek.position, dalek);
        }
    }
    public void AddDalek(Dalek newDalek){
        if(OccupiedCells.containsKey(newDalek.position))
            return;
        Daleks.add(newDalek);
        OccupiedCells.put(newDalek.position, newDalek);
    }
    public void AddScrap(Scrap newScrap){
        if(OccupiedCells.containsKey(newScrap.position))
            return;
        Scrap.add(newScrap);
        OccupiedCells.put(newScrap.position, newScrap);
    }

//    public List<Position> NextFrame(EDirection moveDirection) {
//        // rusz gracza i daleks
//        // zakładam, że ruch jest sprawdzony
//        // info która komóka jest do aktualizacji
//
//        HashSet<Position> buffer = new HashSet<>(); // nie chcemy duplikatów
//
//        MoveObject(Player, moveDirection, buffer);
//
//        return buffer.stream().toList();    // Lista komórek do aktualizacji
//    }

//    private void MoveObject(ObjectBase object, EDirection move, HashSet<Position> buffer) {
//        Position oldPosition = new Position(object.position);
//
//        buffer.add(oldPosition);
//        object.move(move);
//
//        OccupiedCells.remove(oldPosition);
//        OccupiedCells.put(object.position, object);
//
//        buffer.add(object.position);
//    }
    public void MoveObject(ObjectBase object, Position newPosition) {
        Position oldPosition = object.position;
        object.position = newPosition;
        OccupiedCells.remove(oldPosition);
        OccupiedCells.put(object.position, object);
    }
    public Optional<ObjectBase> GetObjectAtCell(Position position) {
        return OccupiedCells.containsKey(position)
                ? Optional.of(OccupiedCells.get(position))
                : Optional.empty();
    }

    public static boolean IsInBounds(Position position) {
        return position.x >= 0 && position.x < Settings.GridCount
                && position.y >= 0 && position.y < Settings.GridCount;
    }

    public void removeDalek(Dalek dalek) {
        this.Daleks.remove(dalek);
        this.OccupiedCells.remove(dalek.position);
    }
}
