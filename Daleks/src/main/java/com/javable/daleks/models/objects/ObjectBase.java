package com.javable.daleks.models.objects;

import com.javable.daleks.enums.eDirection;
import kotlin.NotImplementedError;

public abstract class ObjectBase {

    private final int _id;
    private int positionX, positionY;

    public ObjectBase(int id, int x, int y)
    { _id = id; positionX = x; positionY = y;}

    public boolean canMove(eDirection direction) {
        // TODO canMove(eDirection direction)
        throw new NotImplementedError();
    }

    public void move(eDirection direction) {
        // TODO move(eDirection direction)
        throw new NotImplementedError();
    }

    @Override
    public String toString() {
        return String.format("[%s ID: %o - %o, %o]", this.getClass().getSimpleName(), _id, positionX, positionY);
    }
}
