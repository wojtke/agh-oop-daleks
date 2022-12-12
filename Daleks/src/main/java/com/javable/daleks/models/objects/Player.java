package com.javable.daleks.models.objects;

import com.javable.daleks.enums.EObjectType;
import com.javable.daleks.logic.CollisionHandlerVisitor;
import com.javable.daleks.models.Position;

public class Player extends ObjectBase {

    private boolean isAlive = true;

    public Player(Position position) {
        super(position, EObjectType.Player);
    }

    public void kill() {
        // TODO move to game over screen
        isAlive = false;
        System.out.println("Player is dead");
    }

    @Override
    public void accept(CollisionHandlerVisitor visitor, ObjectBase other, boolean inWalk) {
        CollisionHandlerVisitor.handlePlayerCollision(this, other, inWalk);
    }
}
