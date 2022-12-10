package com.javable.daleks.models.objects;

import com.javable.daleks.enums.EDirection;
import com.javable.daleks.enums.EObjectType;
import com.javable.daleks.interfaces.IMovable;
import com.javable.daleks.logic.CollisionHandlerVisitor;
import com.javable.daleks.models.Position;
import kotlin.NotImplementedError;

import java.util.Vector;

public abstract class ObjectBase implements IMovable {
    public Position position;
    public final EObjectType objectType;

    public ObjectBase(Position position, EObjectType type) {
        this.position = position;
        objectType = type;
    }

    public void move(EDirection direction) {
        position.add(Position.ToVector(direction));
    }

    public abstract void accept(CollisionHandlerVisitor visitor, ObjectBase other, boolean inWalk);
}
