package com.javable.daleks.models.objects;

import com.javable.daleks.enums.EObjectType;
import com.javable.daleks.logic.CollisionHandlerVisitor;
import com.javable.daleks.models.Position;

public abstract class ObjectBase {
    public final EObjectType objectType;
    public Position position;

    public ObjectBase(Position position, EObjectType type) {
        this.position = position;
        objectType = type;
    }

    public abstract void accept(CollisionHandlerVisitor visitor, ObjectBase other, boolean inWalk);
}
