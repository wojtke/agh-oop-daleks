package com.javable.daleks.models.objects;

import com.javable.daleks.Settings;
import com.javable.daleks.enums.EDirection;
import com.javable.daleks.enums.EObjectType;
import com.javable.daleks.logic.CollisionHandlerVisitor;
import com.javable.daleks.models.GameMap;
import com.javable.daleks.models.Position;

public class Player extends ObjectBase {

    private boolean isAlive = true;

    public Player() {
        super(new Position(Settings.StartX, Settings.StartY), EObjectType.Player);

    }

    @Override
    public boolean canMove(EDirection direction) {
        if (!isAlive)
            return false;

        Position newPosition = new Position(position);
        newPosition.add(Position.ToVector(direction));

        return newPosition.x >= 0
                && newPosition.y >= 0
                && newPosition.x < Settings.GridCount
                && newPosition.y < Settings.GridCount;
    }

    public boolean canMove(Position newPosition) {
        if (!GameMap.IsInBounds(newPosition)) {
            return false;
        }
        return Math.abs(newPosition.x - position.x) <= 1 && Math.abs(newPosition.y - position.y) <= 1;
    }

    public void kill() {
        // TODO move to game over screen
        isAlive = false;
        System.out.println("Player is dead");
    }

    @Override
    public void accept(CollisionHandlerVisitor visitor, ObjectBase other, boolean inWalk) {
        visitor.handlePlayerCollision(this, other, inWalk);
    }
}
