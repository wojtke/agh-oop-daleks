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
    private final Map OccupiedCells = new HashMap<Position, ObjectBase>(); // referencje do postaci na planszy

    public GameMap() {
        OccupiedCells.put(Player.position, Player);

        for (int i = 0; i < Settings.DaleksCount; i++) {
            // TODO dodać dalekich
        }
    }

    public List<Position> NextFrame(EDirection moveDirection) {
        // rusz gracza i daleks
        // zakładam, że ruch jest sprawdzony
        // info która komóka jest do aktualizacji

        HashSet<Position> buffer = new HashSet<>(); // nie chcemy duplikatów

        MoveObject(Player, moveDirection, buffer);

        //TODO ruszanie dalekich

        return buffer.stream().toList();
    }

    private void MoveObject(ObjectBase object, EDirection move, HashSet<Position> buffer) {
        Position oldPosition = new Position(object.position);

        buffer.add(oldPosition);
        object.move(move);

        OccupiedCells.remove(oldPosition);
        OccupiedCells.put(object.position, object);

        buffer.add(object.position);
    }

    public EObjectType GetObjectAtCell(Position position) {
        return !OccupiedCells.containsKey(position)
                ? EObjectType.Empty
                : ((ObjectBase)OccupiedCells.get(position)).objectType;
    }
}
