package com.javable.daleks.models.objects;

import com.javable.daleks.enums.EObjectType;
import com.javable.daleks.models.GameMap;
import com.javable.daleks.models.Position;

public abstract class ObjectBase {
    public final EObjectType ObjectType;
    public Position Position;

    public ObjectBase(Position position, EObjectType type) {
        this.Position = position;
        ObjectType = type;
    }

    public abstract void Collide(GameMap map, ObjectBase other, boolean inWalk);
}
