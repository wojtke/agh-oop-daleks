package com.javable.daleks.models.objects;

import com.javable.daleks.enums.EObjectType;
import com.javable.daleks.logic.CollisionHandlerVisitor;
import com.javable.daleks.models.Position;

public class Dalek extends ObjectBase {

    public Dalek(Position position) {
        super(position, EObjectType.Dalek);
    }

    @Override
    public void accept(CollisionHandlerVisitor visitor, ObjectBase other, boolean inWalk) {
        visitor.handleDalekCollision(this, other, inWalk);
    }
}
