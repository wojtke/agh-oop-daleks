package com.javable.daleks.models.objects;

import com.javable.daleks.Settings;
import com.javable.daleks.enums.EDirection;
import com.javable.daleks.enums.EObjectType;
import com.javable.daleks.models.Position;

public class Player extends ObjectBase{

    public Player() {
        super(new Position(Settings.StartX, Settings.StartY), EObjectType.Player);
    }

    @Override
    public boolean canMove(EDirection direction) {
        Position newPosition = new Position(position);
        newPosition.add(Position.ToVector(direction));

        return newPosition.x >= 0
                && newPosition.y >= 0
                && newPosition.x < Settings.GridCount
                && newPosition.y < Settings.GridCount;
    }
}
