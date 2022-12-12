package com.javable.daleks.models.objects;

import com.javable.daleks.enums.EObjectType;
import com.javable.daleks.logic.CollisionHandlerVisitor;
import com.javable.daleks.models.Position;

import static java.lang.Math.abs;

public class Player extends ObjectBase {

    private boolean isAlive = true;

    public Player(Position position) {
        super(position, EObjectType.Player);
    }

    public boolean canMove(Position newPosition) {
        if(abs(newPosition.x - position.x) > 1 || abs(newPosition.y - position.y) > 1) {
            return false;
        }else if (newPosition==position) {
            return false;
        }
        return true;
    }

    public void kill() {
        isAlive = false;
    }

    @Override
    public void accept(CollisionHandlerVisitor visitor, ObjectBase other, boolean inWalk) {
        CollisionHandlerVisitor.handlePlayerCollision(this, other, inWalk);
    }
}
