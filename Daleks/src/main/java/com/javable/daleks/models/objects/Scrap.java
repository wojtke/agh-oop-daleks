package com.javable.daleks.models.objects;

import com.javable.daleks.Settings;
import com.javable.daleks.enums.EDirection;
import com.javable.daleks.enums.EObjectType;
import com.javable.daleks.logic.CollisionHandlerVisitor;
import com.javable.daleks.models.Position;

public class Scrap extends ObjectBase{

    public Scrap(Position position) {
        super(position, EObjectType.Scrap);
    }

    @Override
    public boolean canMove(EDirection direction) {
        return false;
    }

    @Override
    public void accept(CollisionHandlerVisitor visitor, ObjectBase other, boolean inWalk) {
        visitor.handleScrapCollision(this, other, inWalk);
    }
}
